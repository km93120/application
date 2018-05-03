package com.example.khafi.myapplications;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sofian on 09/04/2018.
 */

public class AgenceDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Agence";
    private static final int DB_VERSION = 1;

    AgenceDatabaseHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);

    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE AGENCE (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NOM TEXT,"
                + "LATITUDE INTEGER,"
                + "LONGITUDE INTEGER);");

        insertAgence(db, "ORPI Etoile Immobilier", 48.812847, 2.391069);
        insertAgence(db, "Agence Immobilière Ivry-sur-Seine Nestenn", 48.819446, 2.378151);
        insertAgence(db, "CENTURY 21 Riva Immobilier", 48.943840,2.355707);
        insertAgence(db, "FONCIA Chadefaux Lecoq", 48.911109, 2.409927);
        insertAgence(db, "Kaufman Broad", 48.820233,  2.257561);




    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {}

    public static void insertAgence(SQLiteDatabase db, String name, double latitude, double longitude)
    {
        ContentValues AgenceValues = new ContentValues();
        AgenceValues.put("NOM",name);
        AgenceValues.put("LATITUDE",latitude);
        AgenceValues.put("LONGITUDE",longitude);
        db.insert("AGENCE",null,AgenceValues);
    }
}