package com.digitalsurveys.mohammadfaizan.digitalsurveys.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalsurveys.mohammadfaizan.digitalsurveys.Database.DatabaseHandler;
import com.digitalsurveys.mohammadfaizan.digitalsurveys.HelperClasses.DatabaseExporter;
import com.digitalsurveys.mohammadfaizan.digitalsurveys.HelperClasses.Logging;
import com.digitalsurveys.mohammadfaizan.digitalsurveys.Models.Outlet;
import com.digitalsurveys.mohammadfaizan.digitalsurveys.HelperClasses.PreferencesManager;
import com.digitalsurveys.mohammadfaizan.digitalsurveys.R;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SurveyActivity extends AppCompatActivity {

    SharedPreferences sharedPreference;
    public static final String SHAREDPREF = "SHAREDPREF";
    TextView welcome_user, reference_number, date_time, coordinates, image_count;
    RadioGroup shop_nature, shop_status;
    EditText shop_number;
    Button save_outlet, see_my_location, camera;
    String username, ref_no, date_time_string, latitude = "24.242424", longitude = "54.43434", imageLocation;
    int imageCount = 0;
    Logging logging;
    PreferencesManager preferencesManager;
    DatabaseHandler databaseHandler;

    Bitmap finalBitmap;

    // tells us which camera to take a picture from
    private static int TAKE_PICTURE = 1;
    // empty variable to hold our image Uri once we store it
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_survey);

        try{
            attachControls();
            setControlValues();
            attachListeners();
            logging = new Logging(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void attachControls(){
        try {
            welcome_user = (TextView) findViewById(R.id.welcome_user);
            reference_number = (TextView) findViewById(R.id.reference_number);
            date_time = (TextView) findViewById(R.id.date_time);
            shop_nature = (RadioGroup) findViewById(R.id.shop_nature);
            shop_status = (RadioGroup) findViewById(R.id.shop_status);
            shop_number = (EditText) findViewById(R.id.shop_number);
            coordinates = (TextView) findViewById(R.id.coordinates);
            image_count = (TextView) findViewById(R.id.image_count);
            save_outlet = (Button) findViewById(R.id.save_outlet);
            see_my_location = (Button) findViewById(R.id.see_my_location);
            camera = (Button) findViewById(R.id.camera);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void attachListeners(){
        save_outlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveOutletData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        see_my_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(SurveyActivity.this, MapActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(getShopNumber() != 0) {
                        if (imageCount < 10) {
                            captureImage();
                        } else {
                            Toast.makeText(SurveyActivity.this, "You have reached maximum number of images", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(SurveyActivity.this, "Please enter Shop Number first", Toast.LENGTH_LONG).show();
                        shop_number.setError("Required");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void setControlValues(){
        try {
            preferencesManager = new PreferencesManager(getApplicationContext());

            username = preferencesManager.getStringPreference("username");
            ref_no = preferencesManager.getStringPreference("ref_no");
            date_time_string = getDateTimeString();

            welcome_user.setText("Welcome, " + username);
            reference_number.setText("Reference Number: " + ref_no);
            date_time.setText(date_time_string);
            coordinates.setText("fetching coordinates...");
            image_count.setText("Image Count: "+imageCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDateTimeString(){
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        DateFormat dftwo = new SimpleDateFormat("dd-MM-yyyy");
        String time = df.format(Calendar.getInstance().getTime());
        String date = dftwo.format(Calendar.getInstance().getTime());

        return date+"  "+time;
    }

    public String getRadioValue(RadioGroup radioGroup){
        String returnValue = null;
        try {
            for(int i=0; i<radioGroup.getChildCount(); i++){
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                if(radioButton.isChecked()){
                    returnValue = radioButton.getText().toString();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return returnValue;
    }

    public long getShopNumber(){
        long finalValue = 0;
        try {
            String value = shop_number.getText().toString();
            if(value.length() > 0 || value.equalsIgnoreCase(" ")){
                finalValue = Long.valueOf(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalValue;
    }

    public void saveOutletData(){
        try {
            logging.log(getRadioValue(shop_nature));
            databaseHandler = new DatabaseHandler(SurveyActivity.this);
            long value = databaseHandler.getSingleOutlet(Long.valueOf(ref_no), getShopNumber());
            if(value != 0){
                shop_number.setError("Already Exists");
            } else if (getShopNumber() == 0){
                shop_number.setError("Required");
            }
            else {
                databaseHandler = new DatabaseHandler(SurveyActivity.this);
                databaseHandler.addOutlet(new Outlet(
                        username
                        , Long.valueOf(ref_no)
                        , date_time_string
                        , getRadioValue(shop_nature)
                        , getShopNumber()
                        , getRadioValue(shop_status)
                        , latitude
                        , longitude
                        , getDateTimeString()
                        , imageCount
                        , imageLocation
                ));
                databaseHandler.printAllOutlets();

                DatabaseExporter databaseExporter = new DatabaseExporter(getApplicationContext());
                databaseExporter.fireDatabaseExport();

                Intent intent = new Intent(SurveyActivity.this, SurveyActivity.class);
                startActivity(intent);
                finish();
            }
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
            alertDialogBuilder.setMessage("Are you sure you want to logout?");
            alertDialogBuilder.setCancelable(false);

            alertDialogBuilder.setNeutralButton(R.string.ref_dialog_ok,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent intent = new Intent(SurveyActivity.this, MainActivity.class);
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

    public void captureImage() {
        try {
            // tell the phone we want to use the camera
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            // create a new temp file called pic.jpg in the "pictures" storage area of the phone
            File photo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/DigitalSurveys/", ref_no+"_"+getShopNumber()+"_"+getRadioValue(shop_status)+"_"+imageCount+"_image.jpg");
            imageLocation = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/DigitalSurveys/";
            // take the return data and store it in the temp file "pic.jpg"
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
            //System.out.println("PATH:" + photo.getPath());

            // stor the temp photo uri so we can find it later
            imageUri = Uri.fromFile(photo);

            // start the camera
            startActivityForResult(intent, TAKE_PICTURE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // call the parent
        try {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                // if the requestCode was equal to our camera code (1) then...
                case 1:
                    // if the user took a photo and selected the photo to use
                    if (resultCode == Activity.RESULT_OK) {
                        // get the image uri from earlier
                        Uri selectedImage = imageUri;
                        // notify any apps of any changes we make
                        getContentResolver().notifyChange(selectedImage, null);
                        // get the imageView we set in our view earlier
                        //ImageView imageView = (ImageView)findViewById(R.id.image_view_camera);
                        // create a content resolver object which will allow us to access the image file at the uri above
                        ContentResolver cr = getContentResolver();
                        // create an empty bitmap object
                        Bitmap bitmap;
                        try {
                            // get the bitmap from the image uri using the content resolver api to get the image
                            bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, selectedImage);
                            // set the bitmap to the image view
                            //shop_image.setImageBitmap(bitmap);
                            finalBitmap = bitmap;
                            //System.out.println("IMAGE PATH: "+imageUri.getPath());
                            //imageLocation = imageUri.getPath();
                            // notify the user
                            //Toast.makeText(getActivity().getApplicationContext(), selectedImage.toString() + " has been saved", Toast.LENGTH_LONG).show();
                            Toast.makeText(SurveyActivity.this, "Image has been saved", Toast.LENGTH_LONG).show();
                            imageCount++;
                            image_count.setText("Image Count: "+imageCount);
                            //shop_owner_pic.setBackgroundResource(R.drawable.journey_plan_with_blank);
                            //shop_owner_pic.setImageBitmap(finalBitmap);

                        } catch (Exception e) {
                            // notify the user
                            Toast.makeText(SurveyActivity.this, "Failed to save image", Toast.LENGTH_LONG).show();
                        }
                    }

                    if (resultCode == Activity.RESULT_CANCELED) {
                        //takePhoto(view, "", "");
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
