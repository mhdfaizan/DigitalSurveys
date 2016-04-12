package com.digitalsurveys.mohammadfaizan.digitalsurveys.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.digitalsurveys.mohammadfaizan.digitalsurveys.HelperClasses.PreferencesManager;
import com.digitalsurveys.mohammadfaizan.digitalsurveys.Models.Outlet;
import com.digitalsurveys.mohammadfaizan.digitalsurveys.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class SurveyActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    TextView welcome_user, reference_number, number_of_outlets, date_time, coordinates, image_count;
    RadioGroup shop_nature, shop_status;
    EditText shop_number;
    Button save_outlet, see_my_location, camera;
    String username, ref_no, date_time_string, imageLocation;
    int imageCount = 0;
    Logging logging;
    PreferencesManager preferencesManager;
    DatabaseHandler databaseHandler;
    Bitmap finalBitmap;

    // tells us which camera to take a picture from
    private static int TAKE_PICTURE = 1;
    // empty variable to hold our image Uri once we store it
    public Uri imageUri;

    //Define a request code to send to Google Play services
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_survey);

//        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "menlo.ttf");
//        fontChanger.replaceFonts((ViewGroup) this.findViewById(R.id.holder));
        try {
            attachControls();
            setControlValues();
            attachListeners();
            logging = new Logging(true);
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    // The next two lines tell the new client that “this” current class will handle connection stuff
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                            //fourth line adds the LocationServices API endpoint from GooglePlayServices
                    .addApi(LocationServices.API)
                    .build();

            // Create the LocationRequest object
            mLocationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                    .setFastestInterval(1 * 1000); // 1 second, in milliseconds


//            new Timer().scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//                    try {
//                        if (ActivityCompat.checkSelfPermission(SurveyActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SurveyActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                            // TODO: Consider calling
//                            //    ActivityCompat#requestPermissions
//                            // here to request the missing permissions, and then overriding
//                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                            //                                          int[] grantResults)
//                            // to handle the case where the user grants the permission. See the documentation
//                            // for ActivityCompat#requestPermissions for more details.
//                            return;
//                        }
//                        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//                        if(location != null){
//                            onConnected(savedInstanceState);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, 0, 5000);//put here time 1000 milliseconds=1 second
            timer.schedule(new MyTimerTask(savedInstanceState), 0,20000);

//            Typeface myTypeface = Typeface.createFromAsset(getAssets(), "ubuntu.ttf");
//            welcome_user.setTypeface(myTypeface);
//            date_time.setTypeface(myTypeface);
//            save_outlet.setTypeface(myTypeface);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MyTimerTask extends TimerTask{

        Bundle innerBundle;
        public MyTimerTask(Bundle bundle) {
            innerBundle = bundle;
        }

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (ActivityCompat.checkSelfPermission(SurveyActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SurveyActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

                        if (location != null) {
                            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, SurveyActivity.this);

                        } else {
                            //If everything went fine lets get latitude and longitude
                            currentLatitude = location.getLatitude();
                            currentLongitude = location.getLongitude();

//                Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
                            logging.log("onConnected: " + currentLatitude + " WORKS " + currentLongitude);
                            coordinates.setText(currentLatitude + ", " + currentLongitude);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void attachControls() {
        try {
            welcome_user = (TextView) findViewById(R.id.welcome_user);
            reference_number = (TextView) findViewById(R.id.reference_number);
            number_of_outlets = (TextView) findViewById(R.id.number_of_outlets);
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

    public void attachListeners() {
        save_outlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    databaseHandler = new DatabaseHandler(SurveyActivity.this);
                    long value = databaseHandler.getSingleOutlet(ref_no, getShopNumber());
                    if (value != 0) {
                        shop_number.setError("Already Exists");
                    } else if (getShopNumber() == 0) {
                        shop_number.setError("Required");
                    } else if (imageCount == 0) {
                        Toast.makeText(SurveyActivity.this, "You need to take at least one picture", Toast.LENGTH_LONG).show();
                    } else if (coordinates.getText().toString().contains("fetching")) {
                        Toast.makeText(SurveyActivity.this, "You need to wait until coordinates are fetched", Toast.LENGTH_LONG).show();
                    } else if (currentLatitude == 0 || currentLongitude == 0) {
                        Toast.makeText(SurveyActivity.this, "You need to wait until coordinates are fetched", Toast.LENGTH_LONG).show();
                    } else {
                        showConfirmationDialog();
                    }
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
                    intent.putExtra("latitude", currentLatitude);
                    intent.putExtra("longitude", currentLongitude);
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
                    if (getShopNumber() != 0) {
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

    public void setControlValues() {
        try {
            preferencesManager = new PreferencesManager(getApplicationContext());

            username = preferencesManager.getStringPreference("username");
            ref_no = preferencesManager.getStringPreference("ref_no");
            date_time_string = getDateTimeString();

            welcome_user.setText("Welcome, " + username);
            reference_number.setText("Reference Number: " + ref_no);
            number_of_outlets.setText("Number of Outlets: " + getNumberOfOutlets(ref_no));
            date_time.setText(date_time_string);
            image_count.setText("Image Count: " + imageCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getNumberOfOutlets(String ref_no) {
        int count = 0;
        try {
            databaseHandler = new DatabaseHandler(SurveyActivity.this);
            Cursor cursor = databaseHandler.getAllOutletsForReferenceNo(ref_no);
            if (cursor != null) {
                count = cursor.getCount();
                System.out.println("cursor.getCount():"+ count);
            }
            //logging.log("count: "+count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public String getDateTimeString() {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        DateFormat dftwo = new SimpleDateFormat("dd-MM-yyyy");
        String time = df.format(Calendar.getInstance().getTime());
        String date = dftwo.format(Calendar.getInstance().getTime());

        return date + "  " + time;
    }

    public String getRadioValue(RadioGroup radioGroup) {
        String returnValue = null;
        try {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                if (radioButton.isChecked()) {
                    returnValue = radioButton.getText().toString();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return returnValue;
    }

    public long getShopNumber() {
        long finalValue = 0;
        try {
            String value = shop_number.getText().toString();
            if (value.length() > 0 || value.equalsIgnoreCase(" ")) {
                finalValue = Long.valueOf(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalValue;
    }

    public void saveOutletData() {
        try {
            logging.log(getRadioValue(shop_nature));

            databaseHandler = new DatabaseHandler(SurveyActivity.this);
            databaseHandler.addOutlet(new Outlet(
                    username
                    , ref_no
                    , date_time_string
                    , getRadioValue(shop_nature)
                    , getShopNumber()
                    , getRadioValue(shop_status)
                    , String.valueOf(currentLatitude)
                    , String.valueOf(currentLongitude)
                    , getDateTimeString()
                    , imageCount
                    , imageLocation
            ));
            databaseHandler.printAllOutlets();

            DatabaseExporter databaseExporter = new DatabaseExporter(getApplicationContext());
            databaseExporter.fireDatabaseExport();

            Toast.makeText(SurveyActivity.this, "Data saved successfully!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    public void showConfirmationDialog() {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Are you sure you save this data?");
            alertDialogBuilder.setCancelable(false);

            alertDialogBuilder.setNeutralButton(R.string.ref_dialog_ok,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            saveOutletData();
                            Intent intent = new Intent(SurveyActivity.this, SurveyActivity.class);
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
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            // create a new temp file called pic.jpg in the "pictures" storage area of the phone
            File photo = new File(Environment.getExternalStorageDirectory() + "/DigitalSurveys/" + ref_no, ref_no + "_" + getShopNumber() + "_" + getRadioValue(shop_status) + "_" + (imageCount + 1) + "_image.jpg");
            File folder = new File(Environment.getExternalStorageDirectory() + "/DigitalSurveys/" + ref_no);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            imageLocation = Environment.getExternalStorageDirectory() + "/DigitalSurveys/" + ref_no;
//            imageUriString = String.valueOf(Uri.fromFile(photo));

            // store the temp photo uri so we can find it later
            imageUri = Uri.fromFile(photo);
            logging.log("imageUri: " + imageUri.toString());
            // take the return data and store it in the temp file "pic.jpg"
            String manufacturer = android.os.Build.MANUFACTURER.toLowerCase(Locale.ENGLISH);
            if (!(manufacturer.contains("samsung"))) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
            }

            //System.out.println("PATH:" + photo.getPath());

            // start the camera
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, TAKE_PICTURE);
            }
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
                        Uri selectedImage;
                        Bitmap bitmap;

                        String manufacturer = android.os.Build.MANUFACTURER.toLowerCase(Locale.ENGLISH);
                        logging.log("manufacturer: " + manufacturer);
                        if ((manufacturer.contains("samsung"))) {
                            bitmap = (Bitmap) data.getExtras().get("data");
                            selectedImage = data.getData();
                            logging.log("selectedImage: " + selectedImage);
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};

                            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            //file path of captured image
                            String filePath = cursor.getString(columnIndex);
                            //file path of captured image
                            File f = new File(filePath);
                            String filename = ref_no + "_" + getShopNumber() + "_" + getRadioValue(shop_status) + "_" + (imageCount + 1) + "_image.jpg";

                            logging.log("Your Path:" + filePath);
                            logging.log("Your Filename:" + filename);
                            cursor.close();

                            saveFile(filename, filePath);
                        } else {
                            selectedImage = imageUri;
                            logging.log("selectedImage: " + selectedImage);

                            ContentResolver cr = getContentResolver();

                            bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, selectedImage);

                            imageCount = Integer.valueOf(image_count.getText().toString().substring(13, 14));
                            imageCount++;
                            changeText("Image Count: " + imageCount);
                            logging.log("Image Count: " + image_count.getText());
                        }
                        // set the bitmap to the image view
                        //shop_image.setImageBitmap(bitmap);
                        finalBitmap = bitmap;
                        logging.log("finalBitmap: " + finalBitmap);
                        try {
                            /*// create an empty bitmap object
                            Bitmap bitmap = null;
                            bitmap = (Bitmap) data.getExtras().get("data");
                            // get the image uri from earlier
//                        file:///storage/emulated/0/DigitalSurveys/123/123_3_Open_1_image.jpg
//                        Uri selectedImage = Uri.parse("file:///"+Environment.getExternalStorageDirectory() + "/DigitalSurveys/"+ref_no+"/"+ref_no + "_" + getShopNumber() + "_" + getRadioValue(shop_status) + "_" + (imageCount+1) + "_image.jpg");
                            Uri selectedImage = getImageUri(SurveyActivity.this, bitmap);
                            logging.log("selectedImage: " + selectedImage);
                            getContentResolver().notifyChange(selectedImage, null);
                            // create a content resolver object which will allow us to access the image file at the uri above
                            ContentResolver cr = getContentResolver();
                            // get the bitmap from the image uri using the content resolver api to get the image
                            bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, selectedImage);
                            if(bitmap != null){
                                *//*String inputPath = selectedImage.getPath()+"/";
                                String inputFile = ref_no + "_" + getShopNumber() + "_" + getRadioValue(shop_status) + "_" + (imageCount+1) + "_image.jpg";
                                String outputPath = Environment.getExternalStorageDirectory() + "/DigitalSurveys/"+ref_no+"/";
                                logging.log("inputPath: "+inputPath);
                                logging.log("inputFile: "+inputFile);
                                logging.log("outputPath: "+outputPath);
                                moveFile(inputPath, inputFile, outputPath);*//*
                                imageCount++;
                                image_count.setText("Image Count: " + imageCount);
                                logging.log("Image Count: " + imageCount);
                            }*/
                            Toast.makeText(SurveyActivity.this, "Image has been saved", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            // notify the user
                            Toast.makeText(SurveyActivity.this, "Failed to save image", Toast.LENGTH_LONG).show();
                        }
                    }

                    if (resultCode == Activity.RESULT_CANCELED) {
                        return;
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFile(String name, String path) {
        File direct = new File(Environment.getExternalStorageDirectory() + "/DigitalSurveys/" + ref_no);
        File file = new File(Environment.getExternalStorageDirectory() + "/DigitalSurveys/" + ref_no + "/" + name);

        if (!direct.exists()) {
            direct.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
                FileChannel src = new FileInputStream(path).getChannel();
                FileChannel dst = new FileOutputStream(file).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();

                imageCount = Integer.valueOf(image_count.getText().toString().substring(13, 14));
                imageCount++;
                changeText("Image Count: " + imageCount);
                logging.log("Image Count: " + image_count.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeText(String mText) {
        TextView textView = (TextView) findViewById(R.id.image_count);
        textView.setText(mText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Now lets connect to the API
        try {
            mGoogleApiClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");
        try {
            //Disconnect from API onPause()
            if (mGoogleApiClient.isConnected()) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
                mGoogleApiClient.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            if (location == null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

            } else {
                //If everything went fine lets get latitude and longitude
                currentLatitude = location.getLatitude();
                currentLongitude = location.getLongitude();

//                Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
                logging.log("onConnected: " + currentLatitude + " WORKS " + currentLongitude);
                coordinates.setText(currentLatitude + ", " + currentLongitude);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location myCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//            currentLatitude = location.getLatitude();
//            currentLongitude = location.getLongitude();
            currentLatitude = myCurrentLocation.getLatitude();
            currentLongitude = myCurrentLocation.getLongitude();

            logging.log("onLocationChanged: "+currentLatitude + " WORKS " + currentLongitude);
            coordinates.setText(currentLatitude + ", " + currentLongitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
/*
             * Google Play services can resolve some errors it detects.
             * If the error has a resolution, try sending an Intent to
             * start a Google Play services activity that can resolve
             * error.
             */
        try {
            if (connectionResult.hasResolution()) {
                try {
                    // Start an Activity that tries to resolve the error
                    connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                    /*
                     * Thrown if Google Play services canceled the original
                     * PendingIntent
                     */
                } catch (IntentSender.SendIntentException e) {
                    // Log the error
                    e.printStackTrace();
                }
            } else {
                /*
                 * If no resolution is available, display a dialog to the
                 * user with the error.
                 */
                Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
