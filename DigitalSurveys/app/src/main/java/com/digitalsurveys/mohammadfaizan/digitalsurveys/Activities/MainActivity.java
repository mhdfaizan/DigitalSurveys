package com.digitalsurveys.mohammadfaizan.digitalsurveys.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.digitalsurveys.mohammadfaizan.digitalsurveys.Database.DatabaseHandler;
import com.digitalsurveys.mohammadfaizan.digitalsurveys.HelperClasses.Logging;
import com.digitalsurveys.mohammadfaizan.digitalsurveys.HelperClasses.PreferencesManager;
import com.digitalsurveys.mohammadfaizan.digitalsurveys.Models.User;
import com.digitalsurveys.mohammadfaizan.digitalsurveys.R;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    DatabaseHandler databaseHandler;
    Logging logging;
    SharedPreferences sharedPreference;
    public static final String SHAREDPREF = "SHAREDPREF";
    PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        try {
            attachControls();
            attachListeners();
            databaseHandler = new DatabaseHandler(MainActivity.this);
//            Cursor cursor = databaseHandler.getAllOutletsForReferenceNo("0586");
//            System.out.println("0586: "+cursor.getCount());
            logging = new Logging(true);
            preferencesManager = new PreferencesManager(getApplicationContext());
//            usersssname.setText("admin");
//            password.setText("123");
            if(isLocationServiceEnabled() == false){
                showLocationDialog();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void attachControls(){
        try {
            username = (EditText) findViewById(R.id.username);
            password = (EditText) findViewById(R.id.password);
            login = (Button) findViewById(R.id.login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void attachListeners(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String username_et, password_et;
                    if (username.getText().toString().length() > 0) {
                        if (password.getText().toString().length() > 0) {
                            username_et = username.getText().toString();
                            password_et = password.getText().toString();
                            User user = databaseHandler.getUser(username_et, password_et);
                            logging.log(user.getUsername() + " " + user.getPassword());
                            if (user.getUsername() == null) {
                                Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                            } else {
                                preferencesManager.addPreference("username", username_et);
                                if (preferencesManager.getStringPreference("ref_no") != null) {
                                    showNewExistingDialog();
                                } else {
                                    showReferenceNumberDialog();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showNewExistingDialog() {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Survey Type");
            alertDialogBuilder.setMessage("Do you want to create and new survey or continue with the existing survey?");
            alertDialogBuilder.setCancelable(false);

            alertDialogBuilder.setNeutralButton(R.string.survey_new,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            showReferenceNumberDialog();
                        }
                    });
            alertDialogBuilder.setNegativeButton(R.string.survey_existing,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, SurveyActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showReferenceNumberDialog() {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("New Survey");
            alertDialogBuilder.setMessage("Please enter reference number");
            alertDialogBuilder.setCancelable(false);

            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 0, 30, 0);

            final EditText editText = new EditText(MainActivity.this);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            layout.addView(editText, params);
            alertDialogBuilder.setView(layout);

            alertDialogBuilder.setPositiveButton(R.string.ref_dialog_ok,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int arg1) {

                        }
                    });
            alertDialogBuilder.setNegativeButton(R.string.ref_dialog_cancel,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ref_no = editText.getText().toString();
                    if (editText.getText().toString().isEmpty()) {
                        editText.setError("Required");
                    } else {
                        preferencesManager.addPreference("ref_no", editText.getText().toString());
                        Intent intent = new Intent(MainActivity.this, SurveyActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    public void showExitDialog() {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Are you sure you want to exit the application?");
            alertDialogBuilder.setCancelable(false);

            alertDialogBuilder.setNeutralButton(R.string.ref_dialog_ok,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            finish();
                        }
                    });
            alertDialogBuilder.setNegativeButton(R.string.ref_dialog_cancel,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isLocationServiceEnabled(){
        LocationManager locationManager = null;
        boolean gps_enabled= false,network_enabled = false;

        if (locationManager ==null)
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try{
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }catch(Exception ex){
            //do nothing...
        }

        try{
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }catch(Exception ex){
            //do nothing...
        }

        return gps_enabled || network_enabled;

    }

    public void showLocationDialog() {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("You have location services turned off, you will not be able to get current location coordinates. Would you like to enable location service now?");
            alertDialogBuilder.setCancelable(false);

            alertDialogBuilder.setNeutralButton(R.string.ref_dialog_ok,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(myIntent);
                        }
                    });
            alertDialogBuilder.setNegativeButton(R.string.ref_dialog_cancel,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

