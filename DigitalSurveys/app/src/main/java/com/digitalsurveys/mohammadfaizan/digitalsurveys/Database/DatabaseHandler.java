package com.digitalsurveys.mohammadfaizan.digitalsurveys.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.digitalsurveys.mohammadfaizan.digitalsurveys.Models.Outlet;
import com.digitalsurveys.mohammadfaizan.digitalsurveys.Models.User;

/**
 * Created by mohammad.faizan on 2/29/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "digitalSurveys";

    // table name
    private static final String TABLE_USERS = "users";
    private static final String TABLE_OUTLETS = "outlets";

    // User Table Columns names
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    // Outlets Table Columns names
    private static final String OUTLET_ID = "id";
    private static final String OUTLET_USERNAME = "username";
    private static final String REF_NO = "ref_number";
    private static final String REF_NO_TIME_DATE = "ref_number_time_date";
    private static final String SHOP_NATURE = "shop_nature";
    private static final String SHOP_NUMBER = "shop_number";
    private static final String SHOP_STATUS = "shop_status";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String SHOP_SAVE_TIME_DATE = "shop_save_time_date";
    private static final String IMAGE_COUNT = "image_count";
    private static final String IMAGE_LOCATION = "image_location";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_USERS_TABLE = "CREATE TABLE "
                    + TABLE_USERS + "("
                    + USERNAME + " TEXT PRIMARY KEY,"
                    + PASSWORD + " TEXT)";

            String CREATE_OUTLETS_TABLE = "CREATE TABLE "
                    + TABLE_OUTLETS + "("
                    + OUTLET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + OUTLET_USERNAME + " TEXT,"
                    + REF_NO + " TEXT,"
                    + REF_NO_TIME_DATE + " TEXT,"
                    + SHOP_NATURE + " TEXT,"
                    + SHOP_NUMBER + " NUMBER,"
                    + SHOP_STATUS + " TEXT,"
                    + LATITUDE + " TEXT,"
                    + LONGITUDE + " TEXT,"
                    + SHOP_SAVE_TIME_DATE + " TEXT,"
                    + IMAGE_COUNT + " NUMBER,"
                    + IMAGE_LOCATION + " TEXT"
                    + ")";


            db.execSQL(CREATE_USERS_TABLE);
            db.execSQL(CREATE_OUTLETS_TABLE);

            db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('admin', '123');");
            db.execSQL("INSERT INTO " + TABLE_USERS + " VALUES('test', 'abc');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_OUTLETS);

            // Create tables again
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Adding new user
    public void addUser(User user) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(USERNAME, user.getUsername()); // Username
            values.put(PASSWORD, user.getPassword()); // Password

            // Inserting Row
            db.insert(TABLE_USERS, null, values);
            db.close(); // Closing database connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getting single user
    public User getUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + USERNAME + "='" + username + "' AND " + PASSWORD + "='" + password + "'", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            user = new User(cursor.getString(0),
                    cursor.getString(1));
        } else {
            user = new User(null, null);
        }
        // return user
        return user;
    }


    // Adding new outlet
    public void addOutlet(Outlet outlet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OUTLET_USERNAME, outlet.getUsername());
        values.put(REF_NO, outlet.getRef_number());
        values.put(REF_NO_TIME_DATE, outlet.getRef_number_time_date());
        values.put(SHOP_NATURE, outlet.getShop_nature());
        values.put(SHOP_NUMBER, outlet.getShop_number());
        values.put(SHOP_STATUS, outlet.getShop_status());
        values.put(LATITUDE, outlet.getLatitude());
        values.put(LONGITUDE, outlet.getLongitude());
        values.put(SHOP_SAVE_TIME_DATE, outlet.getShop_save_time_date());
        values.put(IMAGE_COUNT, outlet.getImage_count());
        values.put(IMAGE_LOCATION, outlet.getImage_location());

        // Inserting Row
        db.insert(TABLE_OUTLETS, null, values);
        db.close(); // Closing database connection
    }

    // Printing all outlets
    public void printAllOutlets() {
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("SELECT * FROM "+TABLE_OUTLETS);
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_OUTLETS, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String[] mcolumnNames = cursor.getColumnNames();
            while (!cursor.isAfterLast()) {
                for (int i = 0; i < mcolumnNames.length; i++) {
                    System.out.println(mcolumnNames[i] + " - " + cursor.getString(cursor.getColumnIndex(mcolumnNames[i])));
                }
                cursor.moveToNext();
            }
        }
    }

    // Getting all outlets
    public Cursor getAllOutlets() {
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("SELECT * FROM "+TABLE_OUTLETS);
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_OUTLETS, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String[] mcolumnNames = cursor.getColumnNames();
            while (!cursor.isAfterLast()) {
                for (int i = 0; i < mcolumnNames.length; i++) {
                    System.out.println(mcolumnNames[i] + " - " + cursor.getString(cursor.getColumnIndex(mcolumnNames[i])));
                }
                cursor.moveToNext();
            }
        }
        return cursor;
    }

    // Getting all outlets for reference number
    public Cursor getAllOutletsForReferenceNo(long ref_no) {
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("SELECT * FROM "+TABLE_OUTLETS + " WHERE "+REF_NO+" = "+ ref_no);
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_OUTLETS + " WHERE " + REF_NO + " = " + ref_no, null);
        /*if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String[] mcolumnNames = cursor.getColumnNames();
            while (!cursor.isAfterLast()) {
                for (int i = 0; i < mcolumnNames.length; i++) {
                    System.out.println(mcolumnNames[i] + " - " + cursor.getString(cursor.getColumnIndex(mcolumnNames[i])));
                }
                cursor.moveToNext();
            }
        }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }

    // Getting single outlet
    public long getSingleOutlet(long ref_no, long shop_number) {
        long value = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("SELECT * FROM "+TABLE_OUTLETS+" WHERE "+REF_NO+"="+ref_no+" AND "+SHOP_NUMBER+"="+shop_number);
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_OUTLETS+" WHERE "+REF_NO+"="+ref_no+" AND "+SHOP_NUMBER+"="+shop_number, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            value = cursor.getLong(cursor.getColumnIndex(REF_NO));
            String[] mcolumnNames = cursor.getColumnNames();
            while (!cursor.isAfterLast()) {
                cursor.moveToFirst();
                for (int i = 0; i < mcolumnNames.length; i++) {
                    System.out.println(mcolumnNames[i] + " - " + cursor.getString(cursor.getColumnIndex(mcolumnNames[i])));
                }
                cursor.moveToNext();
            }
        }
        return value;
    }

    // Getting all reference numbers
    public Cursor getAllReferenceNo() {
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("SELECT DISTINCT "+REF_NO+" FROM "+TABLE_OUTLETS);
        Cursor cursor = db.rawQuery("SELECT DISTINCT "+REF_NO+" FROM "+TABLE_OUTLETS, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String[] mcolumnNames = cursor.getColumnNames();
            while (!cursor.isAfterLast()) {
                for (int i = 0; i < mcolumnNames.length; i++) {
                    System.out.println(mcolumnNames[i] + " - " + cursor.getString(cursor.getColumnIndex(mcolumnNames[i])));
                }
                cursor.moveToNext();
            }
        }
        return cursor;
    }

}
