package info.krakovsky.memorableplaces;


import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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
        mMap = googleMap;
        Integer index = getIntent().getIntExtra("index",0);
        final Locations locations = Locations.getInstance();
        final ArrayList<String> placesNames = locations.getPlacesNames();
        final ArrayList<Double> placesLat = locations.getPlacesLat();
        final ArrayList<Double> placesLon = locations.getPlacesLon();
        for(int i=0;i<placesNames.size();i++){
            LatLng selectLocation = new LatLng(placesLat.get(i), placesLon.get(i));
            mMap.addMarker(new MarkerOptions().position(selectLocation).title(placesNames.get(i)));
        }

        // move the camera to selected location
        Double mLat = placesLat.get(index);
        Double mLon = placesLon.get(index);
        LatLng selectLocation = new LatLng(mLat, mLon);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectLocation,15));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                String addressText="";
                try {
                    List<Address> addresses =geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
                    if (addresses!=null&&addresses.size()>0){
                        Log.i("address",addresses.get(0).toString());
                        Log.i("getMaxAddressLineIndex",String.valueOf(addresses.get(0).getMaxAddressLineIndex()));

                        for(int i=0;i<=addresses.get(0).getMaxAddressLineIndex();i++) {
                            addressText += addresses.get(0).getAddressLine(i)+"\n";
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (addressText.isEmpty()){
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                    addressText = sdf.format(new Date());
                }
                mMap.addMarker(new MarkerOptions().position(latLng).title(addressText));
                locations.addLocation(addressText,latLng.latitude,latLng.longitude);

            }
        });
    }
}
