package parquesoft.migeocundinamarcaii;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements View.OnClickListener{

    private LocationManager locManager;
    private LocationListener locListener;
    private double latitud;
    private double longitud;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private CameraUpdate mCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        setUpMapIfNeeded();


        // Creamos Markers para añadirlos a nuestro Mapa.
        // setMarker(new LatLng(4.599216, -74.072614), "Punto Referencia", "Descripcion del Marker", 0.9F, 0.1F, 0.1F, R.drawable.cafeteria);
        setMarker(new LatLng(4.599216, -74.072614), "Referencia", "Marker de Referencia", 0.9F, 0.1F, 0.1F, R.drawable.fall);

        Button BtnHibrido = (Button) findViewById(R.id.BtnHibrido); //Definimos el boton
        Button BtnNuevo = (Button) findViewById(R.id.BtnNuevo);
        Button BtnTierra = (Button) findViewById(R.id.BtnTierra);
        //Button BtnSatelite = (Button) findViewById(R.id.BtnSatelite);
        BtnHibrido.setOnClickListener(this);
        BtnNuevo.setOnClickListener(this);
        //BtnSatelite.setOnClickListener(this);
        BtnTierra.setOnClickListener(this);


            }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.MenuOpcion1:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); // Establecemos el mapa normal
                return true;

            case R.id.MenuOpcion2:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); // Establecemos el mapa satelite
                return true;

            case R.id.MenuOpcion3:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); // Establecemos el mapa terrestre
                return true;

            case R.id.MenuOpcion4:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); // Establecemos el mapa hibrido
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link ()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMyLocationEnabled(true); // Habilitamos nuestra localización en el mapa
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); // Inicializamos la app con el mapa hibrido
                setUpMap(4.599216, -74.072614);
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap(double lat, double lon) {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(4.599216, -74.072614)).title("Mi Marcador Principla").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).snippet("Esta es una prueba de descripcion del marcador inicial"));
        mCamera = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 7);
        mMap.animateCamera(mCamera);


    }

//metodo para personalizar un marcador
    private void setMarker(LatLng position, String title, String info, float opacity, float dimension1, float dimension2, int icon){
        // Agregamos un marcador para indicar sitios de interes.
        mMap.addMarker(new MarkerOptions()
                .position(position)     // Posicion del marcador
                .title(title)           // Agrega titulo al marcador
                .snippet(info)          // Agrega información detalle relacionada con el marcador
                .alpha(opacity)         // Opacidad del icono
                .anchor(dimension1, dimension2)     // Tamaño del icono (alto y ancho)
                .icon(BitmapDescriptorFactory.fromResource(icon)));
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.BtnHibrido) {
            /*Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Se pulso boton Hibrido", Toast.LENGTH_SHORT);

            toast1.show();*/
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

       /* } else if (v.getId() == R.id.BtnSatelite) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);*/
        } else if (v.getId() == R.id.BtnTierra) {
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        } else if (v.getId() == R.id.BtnNuevo) {


            comenzarLocalizacion();

            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        }

    }

    private void comenzarLocalizacion() {

        //Obtenemos una referencia al LocationManager
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Obtenemos la última posición conocida
        Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //Mostramos la última posición conocida
        //mostrarPosicion(loc);

        //Nos registramos para recibir actualizaciones de la posición
        locListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                mostrarPosicion(location);
            }

            public void onProviderDisabled(String provider) {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(), "GPS desactivado", Toast.LENGTH_SHORT);

                toast1.show();
            }

            public void onProviderEnabled(String provider) {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(), "GPS Activado", Toast.LENGTH_SHORT);

                toast1.show();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.i("", "Provider Status: " + status);
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "se ha cambiado el status ", Toast.LENGTH_SHORT);

                toast1.show();

            }
        };

        locManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 15000, 0, locListener);

        if (loc != null) {
            latitud=loc.getLatitude();
            longitud= loc.getLongitude();
            // Log.i("", String.valueOf(loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
            setMarker(new LatLng(loc.getLatitude(), loc.getLongitude()), "Marcador 2", "Coordenadas: " + String.valueOf(loc.getLatitude()) + " ; " + String.valueOf(loc.getLongitude()), 0.9F, 0.1F, 0.1F, R.drawable.down);
            setUpMap(latitud, longitud);
            Intent i=new Intent(this,NuevoSitioActivity.class);
            i.putExtra("latitud",String.valueOf(latitud ));
            i.putExtra("longitud",String.valueOf(longitud));
            startActivity(i);

        } else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Provider Status: Sin datos", Toast.LENGTH_SHORT);
            toast1.show();
        }

        locManager.removeUpdates(locListener);




    }

    private void mostrarPosicion(Location loc) {
        if (loc != null) {

            Log.i("", String.valueOf(loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "latitud: " + String.valueOf(loc.getLatitude()) + "longitud: " + String.valueOf(loc.getLongitude()), Toast.LENGTH_SHORT);

            toast1.show();

            locManager.removeUpdates(locListener);
        } else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Provider Status: Sin datos", Toast.LENGTH_SHORT);
        }
    }

    public void nuevo(View v) {
        Intent i=new Intent(this,NuevoSitioActivity.class);
        i.putExtra("latitud",String.valueOf(latitud ));
        i.putExtra("longitud",String.valueOf(longitud));
        startActivity(i);
    }

}
