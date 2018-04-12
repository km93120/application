package e.sofian.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static java.lang.System.out;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,Calcul {

    private GoogleMap GMap;
    private LatLng myposition = new LatLng(48.814486, 2.394652);
    private LatLng Centre =  new LatLng(48.857031, 2.345684);
    private Marker marker;


    @Override
    public double distance (double userLat, double userLong, double agenceLat, double agenceLong) {
        double deltaX = userLat - agenceLat;
        double deltaY = userLong - agenceLong;

        return  Math.sqrt(Math.pow(deltaX,2) - Math.pow(deltaY,2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        GMap = googleMap;
       // try {
            SQLiteOpenHelper agenceDatabaseHelper = new AgenceDatabaseHelper(this);     //instanciation de l'assistant de gestion de base de donnees
            SQLiteDatabase db = agenceDatabaseHelper.getReadableDatabase(); // creation d'un base de donnee et ajout des donnees a l'interieur
            Cursor cursor = db.query("AGENCE",              //on recupere tout le contenu dans la table
                    new String[]{"NOM", "LATITUDE","LONGITUDE"},
                    null,
                    null,
                    null,
                    null,
                    null
                    );
            double userLatitude = 48.814486;                //pour l'instant on fait nos tests avec des coordonees fixes
            double userLongitude = 2.394652;

            out.print(cursor.getCount());

            LatLng coordAgences [] = new LatLng[cursor.getCount()];              // tableau qui stocke les coordonnees des agences
            String nomAgences [] = new String [cursor.getCount()];          //tableau qui stocke le nom des agences
            int i = 0;
            cursor.moveToFirst();

        while (!cursor.isAfterLast() )
        {
            coordAgences[i] = new LatLng(cursor.getDouble(1),cursor.getDouble(2));       // remplissage des tableaux
            nomAgences[i] = cursor.getString(0);
            i++;
            cursor.moveToNext();
        }
                                                              //tant qu'on a pas atteint le dernier enregistrement du curseur.







        Marker [] addedMarkers = new Marker[cursor.getCount()];         // creation d'un tableau de Marker
        //on s'assure que l'objet map n'est pas vide
        if (GMap != null) {

            GMap.setTrafficEnabled(true);


            MarkerOptions [] markerOptions1 = new MarkerOptions[cursor.getCount()];     // creation d'un tableau contenant les objets qui contiennent les options a appliquer au marqueurs
            for(int j =0; j< coordAgences.length;j++)
            {
                markerOptions1[j] = new MarkerOptions();
               // markerOptions1[j].icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                if(distance(userLatitude,userLongitude,coordAgences[j].latitude,coordAgences[j].longitude) < 10)
                {
                    addedMarkers[j] = GMap.addMarker(markerOptions1[j].position(coordAgences[j]).title(nomAgences[j]).visible(true));

                }
                else
                {
                    addedMarkers[j] = GMap.addMarker(new MarkerOptions ().position(coordAgences[j]).title(nomAgences[j]).visible(false));
                }
            }
            //ajout du marqueur sur la carte
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title("My position");
            markerOptions.visible(true);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            marker = GMap.addMarker(markerOptions.position(myposition).title("My position").visible(true));
            marker.setTag(0);
            //zoom de la caméra sur la position qu'on désire afficher
            GMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Centre,52));
            //animation le zoom toute les 2000ms
            GMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
            cursor.close();
        }
      /*  }catch (SQLiteException e)
            {
                Toast toast = Toast.makeText(this,"Base de donnees non disponible",Toast.LENGTH_SHORT);
                toast.show();
            }*/
        }
    }

