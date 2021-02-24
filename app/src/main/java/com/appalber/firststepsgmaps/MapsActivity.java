package com.appalber.firststepsgmaps;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    Spinner spn_ciudades;
    Button btn_mostrar;
    Marker marcador;
    Context contexto = this;
    private List<Ciudad> ciudades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mi_layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        spn_ciudades=findViewById(R.id.spn_ciudades);
        btn_mostrar=findViewById(R.id.btn_mostrar);
        ciudades = cargarCiudades();
        List<String> nombre_ciudad = new ArrayList<>();
        for (Ciudad c : ciudades){
            nombre_ciudad.add(c.getCity());
        }

        spn_ciudades.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, nombre_ciudad));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
         SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btn_mostrar.setOnClickListener(this::onClick);
    }

    private List<Ciudad> cargarCiudades() {
        ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
        Ciudad madrid = new Ciudad();
        madrid.setCity("Madrid");
        madrid.setLat(40.323382);
        madrid.setLng(-3.712165);
        Ciudad bilbao = new Ciudad();
        bilbao.setCity("Bilbao");
        bilbao.setLat(43.266953);
        Ciudad sevilla = new Ciudad();
        sevilla.setCity("Sevilla");
        sevilla.setLat(37.377197);
        sevilla.setLng(-5.986893);
        ciudades.add(madrid);
        ciudades.add(bilbao);
        ciudades.add(sevilla);
        return ciudades;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }

    @Override
    public void onClick(View v) {
        int opcion = spn_ciudades.getSelectedItemPosition();
        Ciudad c = ciudades.get(opcion);
        cambiarMapa(c);
    }

    private void cambiarMapa(Ciudad c) {
        mMap.clear();
        LatLng ciudad = new LatLng(c.getLat(), c.getLng());
        marcador =  mMap.addMarker(new MarkerOptions().position(ciudad).title("Marker in "+c.getCity()));
        marcador.setTag(c.getCity());

        mMap.moveCamera(CameraUpdateFactory.newLatLng(ciudad));
        //Añadimos un toast
        GoogleMap.OnMarkerClickListener oyente_marcador = new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String etiqueta = (String) marker.getTag();
                Toast.makeText(contexto,"has clicado en " +etiqueta, Toast.LENGTH_LONG).show();
                return true;
            }
        };
        mMap.setOnMarkerClickListener(oyente_marcador);


    }
}