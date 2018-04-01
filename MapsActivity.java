package com.example.alexa.projettest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap GMap;
    private LatLng myposition = new LatLng(48.814486, 2.394652);
    private LatLng Centre =  new LatLng(48.857031, 2.345684);
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        /**GMap = googleMap;

         Add a marker in Sydney and move the camera
         LatLng sydney = new LatLng(-34, 151);
         mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
         mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

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

            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

            //ajout du marqueur sur la carte
            marker = GMap.addMarker(markerOptions.position(myposition).title("My position").visible(true));
            marker.setTag(0);
            //zoom de la caméra sur la position qu'on désire afficher
            GMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Centre,7));
            //animation le zoom toute les 2000ms
            GMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        }
    }
}
