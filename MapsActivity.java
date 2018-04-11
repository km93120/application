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
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
       /* SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);    */                                                      //provoque une erreur




    @Override
    public void onMapReady(GoogleMap googleMap) {

       // try {
            SQLiteOpenHelper agenceDatabaseHelper = new AgenceDatabaseHelper(this);
            SQLiteDatabase db = agenceDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("Agence",
                    new String[]{"NOM", "LATITUDE","LONGITUDE"},
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
            double userLatitude = 48.814486;
            double userLongitude = 2.394652;

            Marker [] addedMarkers = new Marker[cursor.getCount()];

            LatLng agences [] = new LatLng[cursor.getCount()];
            String nomAgences [] = new String [cursor.getCount()];
            int i = 0;
            do {


                agences[i] = new LatLng(cursor.getDouble(1),cursor.getDouble(2));
                nomAgences[i] = cursor.getString(0);
                i++;


            }while(cursor.moveToNext());






        GMap = googleMap;

        //on s'assure que l'objet map n'est pas vide
        if (GMap != null) {
            //mode d'affichage de la carte
            GMap.setTrafficEnabled(true);

            //on autorise l'api à afficher le bouton pour accéder à notre position courante
            /*if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }*/
            //GMap.setMyLocationEnabled(true);

            //définition du marqueur qui va se positionner sur le point qu'on désire afficher
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title("My position");
            markerOptions.visible(true);
            MarkerOptions [] markerOptions1 = new MarkerOptions[cursor.getCount()];
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            for(int j =0; j< agences.length;j++)
            {
                markerOptions1[j] = new MarkerOptions();
                //markerOptions1[j].icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                if(distance(userLatitude,userLongitude,agences[j].latitude,agences[j].longitude) < 895555)
                {
                    addedMarkers[j] = googleMap.addMarker(markerOptions1[j].position(agences[j]).title(nomAgences[j]).visible(true));

                }
                else
                {
                    addedMarkers[j] = googleMap.addMarker(new MarkerOptions ().position(agences[j]).title(nomAgences[j]).visible(false));
                }
            }
            //ajout du marqueur sur la carte
            marker = GMap.addMarker(markerOptions.position(myposition).title("My position").visible(true));
            marker.setTag(0);
            //zoom de la caméra sur la position qu'on désire afficher
            GMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Centre,7));
            //animation le zoom toute les 2000ms
            GMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        }
      /*  }catch (SQLiteException e)
            {
                Toast toast = Toast.makeText(this,"Base de donnees non disponible",Toast.LENGTH_SHORT);
                toast.show();
            }*/
        }
    }


