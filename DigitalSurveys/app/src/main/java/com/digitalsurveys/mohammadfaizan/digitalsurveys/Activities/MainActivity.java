package com.digitalsurveys.mohammadfaizan.digitalsurveys.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.digitalsurveys.mohammadfaizan.digitalsurveys.Models.User;
import com.digitalsurveys.mohammadfaizan.digitalsurveys.HelperClasses.PreferencesManager;
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
            logging = new Logging(true);
            preferencesManager = new PreferencesManager(getApplicationContext());
            username.setText("admin");
            password.setText("123");
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
                                Toast.makeText(MainActivity.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
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
                        public void onClick(DialogInterface arg0, int arg1) {
                            preferencesManager.addPreference("ref_no", editText.getText().toString());

                            Intent intent = new Intent(MainActivity.this, SurveyActivity.class);
                            startActivity(intent);
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
}

