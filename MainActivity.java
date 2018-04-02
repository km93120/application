package com.example.camilia.louer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class MainActivity extends AppCompatActivity {
private Button Agence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Agence = findViewById(R.id.button6);
        Agence.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMapsActivity();
            }
        });

    }

    public void openMapsActivity() {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);

    }

    public class Agence {

        private int id_agence;
        private String nom_agence;
        private String ville_agence;

        // Constructeur
        public Agence (int id,String nom, String ville) {
            this.id_agence=id;
            this.nom_agence=nom;
            this.ville_agence=ville;
        }

        public int getId_agence() {
            return id_agence;
        }

        public void setId_agence(int id) {
            this.id_agence = id;
        }

        public String getNom_agence() {
            return nom_agence;
        }

        public void setNom_agence(String nom) {
            this.nom_agence = nom;
        }
        public String getVille_agence() {
            return ville_agence;
        }

        public void setVille_agence(String ville) {
            this.ville_agence = ville;
        }

    }

    public class AgenceManager {

        private static final String TABLE_NAME = "agence";
        public static final String KEY_ID_AGENCE="id_agence";
        public static final String KEY_NOM_AGENCE="nom_agence";
        public static final String KEY_VILLE_AGENCE="ville_agence";
        public static final String CREATE_TABLE_AGENCE = "CREATE TABLE "+TABLE_NAME+
                " (" +
                " "+KEY_ID_AGENCE+" INTEGER primary key," +
                " "+KEY_NOM_AGENCE+" TEXT" +
                " "+KEY_VILLE_AGENCE+ "TEXT"+
                ");";
        private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
        private SQLiteDatabase db;

        // Constructeur
        public AgenceManager(Context context)
        {
            maBaseSQLite = MySQLite.getInstance(context);
        }

        public void open()
        {
            //on ouvre la table en lecture/écriture
            db = maBaseSQLite.getWritableDatabase();
        }

        public void close()
        {
            //on ferme l'accès à la BDD
            db.close();
        }

        public long addAgence(Agence agence) {
            // Ajout d'un enregistrement dans la table

            ContentValues values = new ContentValues();
            values.put(KEY_NOM_AGENCE, agence.getNom_agence());

            // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
            return db.insert(TABLE_NAME,null,values);
        }

        public int modAgence(Agence agence) {
            // modification d'un enregistrement
            // valeur de retour : (int) nombre de lignes affectées par la requête

            ContentValues values = new ContentValues();
            values.put(KEY_NOM_AGENCE, agence.getNom_agence());

            String where = KEY_ID_AGENCE+" = ?";
            String[] whereArgs = {agence.getId_agence()+""};

            return db.update(TABLE_NAME, values, where, whereArgs);
        }

        public int supAgence(Agence agence) {
            // suppression d'un enregistrement
            // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

            String where = KEY_ID_AGENCE+" = ?";
            String[] whereArgs = {agence.getId_agence()+""};

            return db.delete(TABLE_NAME, where, whereArgs);
        }

        public Agence getAgence(int id) {
            // Retourne l'agence dont l'id est passé en paramètre

            Agence a=new Agence(0,"","");

            Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_AGENCE+"="+id, null);
            if (c.moveToFirst()) {
                a.setId_agence(c.getInt(c.getColumnIndex(KEY_ID_AGENCE)));
                a.setNom_agence(c.getString(c.getColumnIndex(KEY_NOM_AGENCE)));
                a.setVille_agence(c.getString(c.getColumnIndex(KEY_VILLE_AGENCE)));
                c.close();
            }

            return a;
        }

        public Cursor getAgences() {
            // sélection de tous les enregistrements de la table
            return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        }

    } // class AgenceManager


    public static class MySQLite extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "db.sqlite";
        private static final int DATABASE_VERSION = 1;
        static MySQLite sInstance;

        public static synchronized MySQLite getInstance(Context context) {
            if (sInstance == null) { sInstance = new MySQLite(context); }
            return sInstance;
        }

        private MySQLite(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            // Création de la base de données
            // on exécute ici les requêtes de création des tabless
            sqLiteDatabase.execSQL(AgenceManager.CREATE_TABLE_AGENCE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            // Mise à jour de la base de données
            // méthode appelée sur incrémentation de DATABASE_VERSION
            // on peut faire ce qu'on veut ici, comme recréer la base :
            onCreate(sqLiteDatabase);
        }

    } // class MySQLite

    public void main (String args[]) {
            AgenceManager m = new AgenceManager(this); //
            m.open(); // ouverture de la table en lecture/écriture

// insertion. L'id sera attribué automatiquement par incrément
            m.addAgence(new Agence(0,"immo","ivry"));

// modification du nom de l'agence dont l'id est 1
            Agence a=m.getAgence(1);
            a.setNom_agence("Immo");
            m.modAgence(a);

// suppression
            m.supAgence(a);

// Listing des enregistrements de la table
            Cursor c = m.getAgences();
            if (c.moveToFirst())
            {
                do {
                    Log.d("test",
                            c.getInt(c.getColumnIndex(AgenceManager.KEY_ID_AGENCE)) + "," +
                                    c.getString(c.getColumnIndex(AgenceManager.KEY_NOM_AGENCE))
                    );
                }
                while (c.moveToNext());
            }
            c.close(); // fermeture du curseur

// fermeture du gestionnaire
            m.close();
        }
}

