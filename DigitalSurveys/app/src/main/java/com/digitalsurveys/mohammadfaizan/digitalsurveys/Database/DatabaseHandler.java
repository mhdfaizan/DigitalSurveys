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
            insertAllUsers(db);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertAllUsers(SQLiteDatabase db){
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('admin', '123');");
        db.execSQL("INSERT INTO " + TABLE_USERS + " VALUES('test', 'abc');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_001', 'N0z0Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_002', 'J7t0P');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_003', 'P4d8O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_004', 'I9q3V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_005', 'D2w3U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_006', 'P1b7A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_007', 'J3g3Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_008', 'H0z5X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_009', 'J2p4A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_010', 'V9z9C');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_011', 'C7a2Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_012', 'J3m7X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_013', 'V3a9G');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_014', 'B9k0M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_015', 'L3d5T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_016', 'H9m9U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_017', 'C6b8Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_018', 'Q3k5G');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_019', 'D2c5A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_020', 'K9c7R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_021', 'K0u5W');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_022', 'N4j6M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_023', 'C0y1F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_024', 'W3x7A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_025', 'I4h8I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_026', 'M2p0O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_027', 'S7l2E');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_028', 'P9z4E');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_029', 'T1a3L');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_030', 'P4t1V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_031', 'Y5g7Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_032', 'E5g6X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_033', 'F5f7B');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_034', 'W9r5E');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_035', 'R9e1P');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_036', 'X1n3A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_037', 'G8x6C');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_038', 'K1f6M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_039', 'W1f5X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_040', 'N2k4A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_041', 'F5b6M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_042', 'O8s8O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_043', 'Q6e3Q');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_044', 'I5t5C');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_045', 'Q1a3T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_046', 'X5b0J');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_047', 'Q7h6Q');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_048', 'Q6k9T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_049', 'E6y9Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_050', 'N7y8F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_051', 'X5j7B');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_052', 'R0l3U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_053', 'K4k6M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_054', 'P0c3Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_055', 'X0k8R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_056', 'O4r5W');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_057', 'B4f6P');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_058', 'Y3q7O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_059', 'U9q6V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_060', 'G5o2S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_061', 'Q2r6Q');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_062', 'S4a6U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_063', 'S1z9Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_064', 'I3k4O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_065', 'T5q6S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_066', 'R1x2G');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_067', 'E7w2J');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_068', 'E2a2X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_069', 'F0d1I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_070', 'H3j1Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_071', 'S1s4O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_072', 'R0n7F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_073', 'X9w1A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_074', 'Y7z8O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_075', 'N7t9X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_076', 'B9v0Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_077', 'N1p6D');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_078', 'I3p1G');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_079', 'I9q9V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_080', 'R4m8A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_081', 'V5z5H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_082', 'T5p2W');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_083', 'J8e5F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_084', 'W3n3I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_085', 'T3x8E');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_086', 'S6h0P');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_087', 'D8d2S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_088', 'M8l2L');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_089', 'I4k1V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_090', 'C1g1V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_091', 'F6z6Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_092', 'S6q0N');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_093', 'F7j3P');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_094', 'N5u9O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_095', 'R1t9X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_096', 'H0q6H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_097', 'N1n1Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_098', 'D5u3S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_099', 'D6f0R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_100', 'U2z5S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_101', 'C2w1M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_102', 'K1d9X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_103', 'R9o7P');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_104', 'X0l4X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_105', 'X6j1A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_106', 'B6j3K');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_107', 'H0z0M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_108', 'O1c2R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_109', 'K1q9F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_110', 'S7q3U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_111', 'W9c4S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_112', 'L9a0S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_113', 'M3h9M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_114', 'I2s0D');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_115', 'M8a6Q');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_116', 'Y0o2J');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_117', 'A7x6Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_118', 'L0h1N');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_119', 'I6f5D');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_120', 'C9q8U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_121', 'K2i2Q');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_122', 'B9j0Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_123', 'E7c8H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_124', 'V1f9N');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_125', 'B2d7J');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_126', 'D3m4S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_127', 'N5g1R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_128', 'C9o9L');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_129', 'Q8q6K');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_130', 'F2f8K');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_131', 'C4h4Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_132', 'H9v3T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_133', 'L9l4R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_134', 'W0r9F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_135', 'A2x3K');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_136', 'H6c5R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_137', 'L0r2Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_138', 'Y5c0G');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_139', 'E3r4C');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_140', 'V2o2H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_141', 'B4v2I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_142', 'V2q8E');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_143', 'J0p9J');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_144', 'L1y6N');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_145', 'O2v2I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_146', 'Z0f7T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_147', 'N6b9L');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_148', 'G7p7U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_149', 'A7r8G');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_150', 'S0j7Q');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_151', 'N3u9T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_152', 'Z3j9R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_153', 'Q4t8B');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_154', 'S7i1H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_155', 'K9z8R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_156', 'F4e7H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_157', 'H8r8I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_158', 'W9a3U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_159', 'I4j3I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_160', 'X5a8X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_161', 'V3l7K');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_162', 'H6h7L');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_163', 'G3h8H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_164', 'U6q6Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_165', 'P2s5I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_166', 'O5g3N');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_167', 'M1c6M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_168', 'Y0x2Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_169', 'F5l7Q');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_170', 'L8x0N');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_171', 'V0j8O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_172', 'T3h1H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_173', 'A8p7V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_174', 'J0d6H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_175', 'M9q7R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_176', 'V6y8Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_177', 'O9d5U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_178', 'L8c5Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_179', 'K3l2T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_180', 'N3v5V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_181', 'Z4m3B');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_182', 'Z3y2V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_183', 'X2v9Q');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_184', 'V2g3U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_185', 'Y7u2H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_186', 'T5k4E');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_187', 'D9a2O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_188', 'S7h2Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_189', 'O4k4Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_190', 'T8g8U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_191', 'Q1x1C');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_192', 'O5v5R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_193', 'B6x1N');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_194', 'F9j2Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_195', 'R8k5B');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_196', 'I4t8S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_197', 'U5b2O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_198', 'Z5c3Q');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_199', 'M3y4Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_200', 'E0u2K');");

        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_201', 'N2w8Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_202', 'J7v5X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_203', 'A2v6B');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_204', 'R7s6M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_205', 'H3w2F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_206', 'O3q8C');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_207', 'K4a7N');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_208', 'V8q1D');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_209', 'D4p5J');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_210', 'B5l8B');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_211', 'X1e4U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_212', 'S6x1H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_213', 'V7g8L');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_214', 'C5s0F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_215', 'T2a5R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_216', 'Q0g7Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_217', 'S0u9S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_218', 'W5i1J');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_219', 'I7q4N');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_220', 'H6w1J');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_221', 'Z8i6O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_222', 'A9v2U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_223', 'B2t1T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_224', 'G7s0L');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_225', 'N6z7Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_226', 'L5u2X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_227', 'L0o5T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_228', 'L6j3O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_229', 'K8i3B');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_230', 'K7e8W');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_231', 'D5k7Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_232', 'R4r9P');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_233', 'J7k2I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_234', 'C4s6D');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_235', 'J0r2F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_236', 'J3s9N');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_237', 'N5w3I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_238', 'K8r8Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_239', 'D3h1C');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_240', 'T0r5O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_241', 'A4i6I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_242', 'E7h3R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_243', 'B7q3G');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_244', 'D8f1Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_245', 'F1g1Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_246', 'L2o6S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_247', 'W1j3Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_248', 'A6o0T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_249', 'H3h6S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_250', 'D0u0R');");

        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_251', 'W6s4M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_252', 'K0x7P');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_253', 'R7r3J');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_254', 'Z2v6V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_255', 'O1a1F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_256', 'K5x0A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_257', 'R0e4A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_258', 'S9s4K');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_259', 'C7v4J');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_260', 'V8i3W');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_261', 'M4g8G');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_262', 'O9h0D');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_263', 'Y4s7C');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_264', 'C8v5H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_265', 'V1x4F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_266', 'T8t5S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_267', 'C7t0K');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_268', 'L4r4R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_269', 'K7i9W');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_270', 'E5j1X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_271', 'Q7h1G');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_272', 'F9w9L');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_273', 'C2g1O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_274', 'E9i7Q');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_275', 'C0r2K');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_276', 'G7r9F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_277', 'G2g2V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_278', 'X1q5G');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_279', 'L4b3D');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_280', 'N2t1W');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_281', 'F1p9Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_282', 'R5w2Q');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_283', 'I1h5S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_284', 'N7g5L');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_285', 'T6g1V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_286', 'J0k4F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_287', 'T2v0Q');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_288', 'G0v1T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_289', 'F9i6O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_290', 'M1d3H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_291', 'M9z3Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_292', 'K1a8G');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_293', 'Z6a2N');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_294', 'S3h4L');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_295', 'D6m7T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_296', 'C6d1W');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_297', 'Q0y4F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_298', 'K2w1P');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_299', 'H6b8T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_300', 'I8o9W');");


        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_301', 'H4e7J');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_302', 'D3n7N');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_303', 'J8e8T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_304', 'Z1s0Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_305', 'V0x6T');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_306', 'Z6q4V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_307', 'P6b4W');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_308', 'T8v8Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_309', 'A9p6C');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_310', 'Y6y1C');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_311', 'B6z1M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_312', 'G4a0P');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_313', 'B5u9Q');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_314', 'W7x6U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_315', 'G3z7Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_316', 'G5e4O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_317', 'B5w0V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_318', 'W2r9A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_319', 'H7c8I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_320', 'P0v4S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_321', 'L6c4X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_322', 'X6l8M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_323', 'J4y5Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_324', 'Z4x3A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_325', 'D6h9E');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_326', 'M3x9C');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_327', 'X8i7K');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_328', 'P3f4P');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_329', 'L2b8C');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_330', 'I1j0X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_331', 'M8p0N');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_332', 'T9z1O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_333', 'P4d2A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_334', 'X0y2J');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_335', 'A4c8D');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_336', 'O9d8V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_337', 'J4u9A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_338', 'P4z6I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_339', 'X7i0H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_340', 'I0u4P');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_341', 'N4l4Z');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_342', 'S2m9V');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_343', 'L2p5R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_344', 'U8m1E');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_345', 'U3j3I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_346', 'K0h5J');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_347', 'K6w9D');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_348', 'X5r3H');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_349', 'C9r5B');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_350', 'Z0t6H');");

        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_351', 'L6j8M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_352', 'O6y0R');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_353', 'X4j1F');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_354', 'V0l6G');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_355', 'B6o2G');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_356', 'L2m9C');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_357', 'I7b2M');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_358', 'Y6t1W');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_359', 'H9j7A');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_360', 'W1y0S');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_361', 'V2s8K');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_362', 'K7v1Y');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_363', 'L2w3X');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_364', 'O5x1G');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_365', 'G8q0O');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_366', 'J7y6L');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_367', 'G6l1U');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_368', 'A3m6I');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_369', 'F9u8N');");
        db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('SV_SIS_370', 'P5k6D');");
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
    public Cursor getAllOutletsForReferenceNo(String ref_no) {
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("SELECT * FROM "+TABLE_OUTLETS + " WHERE "+REF_NO+" = '"+ ref_no +"'");
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_OUTLETS + " WHERE " + REF_NO + " = '"+ ref_no +"'", null);
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
    public long getSingleOutlet(String ref_no, long shop_number) {
        long value = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("SELECT * FROM "+TABLE_OUTLETS+" WHERE "+REF_NO+"="+ref_no+" AND "+SHOP_NUMBER+"="+shop_number);
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_OUTLETS+" WHERE "+REF_NO+"='"+ref_no+"' AND "+SHOP_NUMBER+"="+shop_number, null);
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
