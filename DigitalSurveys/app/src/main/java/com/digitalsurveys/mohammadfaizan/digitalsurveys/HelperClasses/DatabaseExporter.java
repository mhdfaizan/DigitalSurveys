package com.digitalsurveys.mohammadfaizan.digitalsurveys.HelperClasses;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;

import com.digitalsurveys.mohammadfaizan.digitalsurveys.Database.DatabaseHandler;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by mohammad.faizan on 2/29/2016.
 */
public class DatabaseExporter {

    Context context;
    DatabaseHandler databaseHandler;

    public DatabaseExporter(Context context) {
        this.context = context;
    }

    public void fireDatabaseExport() {
        try {
            databaseHandler = new DatabaseHandler(context);
            Cursor cursor = databaseHandler.getAllReferenceNo();
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    File dbFile = context.getDatabasePath(DatabaseHandler.DATABASE_NAME);
                    File exportDir = new File(Environment.getExternalStorageDirectory() + "/DigitalSurveys/"+cursor.getString(0), "");
                    if (!exportDir.exists()) {
                        System.out.println("" + exportDir);
                        exportDir.mkdirs();
                    }
                    File outletFile = new File(exportDir, "OutletsData.csv");
                    if(outletFile.exists()){
                        outletFile.delete();
                        File newOutletFile = new File(exportDir, "OutletsData.csv");
                        System.out.println("Old outlet file deleted and new created!");
                        exportData(newOutletFile, cursor.getLong(0));
                    } else {
                        System.out.println("Already new!");
                        exportData(outletFile, cursor.getLong(0));
                    }

                    System.out.println("ENVPATH: " + Environment.getExternalStorageDirectory());
                    cursor.moveToNext();
                }
            }
//            File dbFile = context.getDatabasePath(DatabaseHandler.DATABASE_NAME);
//            File exportDir = new File(Environment.getExternalStoragePublicDirectory("Download") + "/DigitalSurveys/", "");
//            if (!exportDir.exists()) {
//                System.out.println("" + exportDir);
//                exportDir.mkdirs();
//            }
//            File outletFile = new File(exportDir, "OutletsData.csv");
//            if(outletFile.exists()){
//                outletFile.delete();
//                File newOutletFile = new File(exportDir, "OutletsData.csv");
//                System.out.println("Old outlet file deleted and new created!");
////                exportData(newOutletFile);
//            } else {
//                System.out.println("Already new!");
////                exportData(outletFile);
//            }
//
//            System.out.println("ENVPATH: " + Environment.getExternalStoragePublicDirectory("Download"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportData(File file, long ref_no) {
        try {
            file.createNewFile();
            CSVWriter csvWriteOutlet = new CSVWriter(new FileWriter(file));
            databaseHandler = new DatabaseHandler(context);

            Cursor cursor = databaseHandler.getAllOutletsForReferenceNo(ref_no);

            ArrayList<String> list = new ArrayList<>();

            list.add("ID");
            list.add("USERNAME");
            list.add("REf NUMBER");
            list.add("REF NUMBER DATE AND TIME");
            list.add("SHOP NATURE");
            list.add("SHOP NUMBER");
            list.add("SHOP STATUS");
            list.add("LATITUDE");
            list.add("LONGITUDE");
            list.add("SHOP SAVE DATE AND TIME");
            list.add("IMAGE COUNT");
            list.add("IMAGE LOCATION");

            csvWriteOutlet.writeNext(list.toArray(new String[list.size()]));

            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    ArrayList<String> dataList = new ArrayList<>();
                    for(int i=0; i<cursor.getColumnCount(); i++){
                        dataList.add(cursor.getString(i));
                    }
                    csvWriteOutlet.writeNext(dataList.toArray(new String[dataList.size()]));
                    cursor.moveToNext();
                }
            }
            csvWriteOutlet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
