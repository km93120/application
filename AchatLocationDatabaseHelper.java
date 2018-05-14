package com.example.alexa.projettest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sofian on 12/04/2018.
 */

public class AchatLocationDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "achat_location";
    private static final int DB_VERSION = 1;

    AchatLocationDatabaseHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);

    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE LOCATION (_id_location INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "LOYER INTEGER,"
                + "ADRESSE TEXT,"
                + "APERCU BLOB);");
        db.execSQL("CREATE TABLE ACHAT (_id_achat INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "PRIX INTEGER,"
                + "ADRESSE TEXT,"
                + "APERCU BLOB);");

        insertLocation(db, 500, "4 Road",R.drawable.image1);
        insertLocation(db, 500, "Road",R.drawable.image2);
        insertLocation(db, 500, "4 Road",R.drawable.image3);
        insertLocation(db, 500, "Road",R.drawable.image4);
        insertLocation(db, 500, "Road",R.drawable.imagep);






    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {}

    public static void insertLocation(SQLiteDatabase db,double loyer, String adresse,int id_image)
    {
        ContentValues locationValues = new ContentValues();
        locationValues.put("LOYER",loyer);
        locationValues.put("ADRESSE",adresse);
        locationValues.put("APERCU",id_image);
        db.insert("LOCATION",null,locationValues);
    }

    public static void insertOffreAchat(SQLiteDatabase db)
    {

    }


}