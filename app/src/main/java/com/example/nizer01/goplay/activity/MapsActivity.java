package com.example.nizer01.goplay.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.nizer01.goplay.dao.EventDao;
import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.domain.Local;
import com.example.nizer01.goplay.utility.PlaceAutoCompleteAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.nizer01.goplay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "MapsActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int REQUEST_CODE = 1603;
    private static final float DEFAULT_ZOOM = 17f;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(new LatLng(-40, -168), new LatLng(71, 136));

    private AutoCompleteTextView mSeatchText;

    private GoogleMap mMap;
    Address address;
    private boolean mLocationPermissionGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private PlaceAutoCompleteAdapter placeAutoCompleteAdapter;
    private GoogleApiClient mGoogleApiClient;

    private EventDao database;

    public MapsActivity() {
        database = new EventDao();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    protected void onStart(){
        super.onStart();

        if(!user.isUserLoggedIn()) {
            menuPrimary.goMain();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        menuPrimary.setMenuActive(R.id.mn_maps);
        menuPrimary.unsetMenuClickable(R.id.mn_maps);

        mSeatchText = (AutoCompleteTextView) findViewById(R.id.input_search);

        /*
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        */
        getLocationPermission();
    }

    private void init() {

        mMap.getUiSettings().setMapToolbarEnabled(false);

        mMap.getUiSettings().setZoomControlsEnabled(false);

        mMap.getUiSettings().setZoomGesturesEnabled(false);

        mMap.getUiSettings().setTiltGesturesEnabled(false);

        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap.setPadding(0,160,0,150);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Event ev = (Event) marker.getTag();
                Bundle bn = new Bundle();
                bn.putString("id", ev.getId());
                menuPrimary.goEvent(bn);
            }
        });

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                for (Event ev : database.getEventsByBounds(mMap.getProjection().getVisibleRegion().latLngBounds)) {

                    Local lc = ev.getLocal();
                    MarkerOptions mo = new MarkerOptions()
                            .position(new LatLng(lc.getLatitude(), lc.getLongitude()))
                            .title(ev.getName())
                            .snippet(ev.getDescription());

                    Marker mk = mMap.addMarker(mo);

                    mk.setTag(ev);
                }
            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(Places.GEO_DATA_API).addApi(Places.PLACE_DETECTION_API).enableAutoManage(this, this).build();

        placeAutoCompleteAdapter = new PlaceAutoCompleteAdapter(this, mGoogleApiClient, LAT_LNG_BOUNDS, null);

        mSeatchText.setAdapter(placeAutoCompleteAdapter);

        mSeatchText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<Address> list = new ArrayList<>();
                String searchString = placeAutoCompleteAdapter.getItem(position).getFullText(null).toString();
                Geocoder geocoder = new Geocoder(MapsActivity.this);
                try {
                    list = geocoder.getFromLocationName(searchString, 1);

                    if (list.size() > 0) {
                        address = list.get(0);
                        System.out.println("Found location!: " + address.toString());
                        moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM, address.getAddressLine(0));
                    }  else {
                        System.out.println("Location not found");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    private void geolocate() {
        String searchString = mSeatchText.getText().toString();
        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException e) {
            System.out.println(e);
        }
        if (list.size() > 0) {
            address = list.get(0);
            System.out.println("Found location!: " + address.toString());
            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM, address.getAddressLine(0));
        } else {
            System.out.println("Location not found");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: Map is ready!");
        mMap = googleMap;
        if (mLocationPermissionGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            mMap.setMyLocationEnabled(true);

            init();
        }
    }

    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPermissionGranted) {
                final Task locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            android.location.Location mLastKnownLocation = (android.location.Location) task.getResult();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-26.91621, -48.6641), DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                            Toast.makeText(MapsActivity.this, "Unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            System.out.println(TAG + " getDeviceLocation: SecurityExeption: " + e.getMessage());
        }
    }

    private void moveCamera(LatLng latlng, float zoom, String addressline) {
        System.out.println(TAG + " moveCamera: moving the camera to: Lat: " + latlng.latitude + ", Lon: " + latlng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoom));
        if (!addressline.equals("MyLocation")) {
            mMap.addMarker(new MarkerOptions().position(latlng).title(addressline));
        }



        System.out.println(mMap.getProjection().getVisibleRegion().latLngBounds.northeast);
        System.out.println(mMap.getProjection().getVisibleRegion().latLngBounds.southwest);
    }

    public void queryMarkers(LatLngBounds bounds) {

    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);
    }

    private void getLocationPermission() {
        System.out.println("Checking for location permissions...");
        String[] permissions = {FINE_LOCATION, COURSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;
                    initMap();
                }
            }
        }
    }

    public void onClickCreateEvent(View v) {
        Intent intent = new Intent(this, CreateEventActivity2.class);

        String citySelected = "";
        String addressSelected = "";

        if(!addressSelected.isEmpty()) {
            addressSelected = address.getAddressLine(0);
            citySelected = address.getSubAdminArea();
        }

        intent.putExtra("City", citySelected);
        intent.putExtra("Local", addressSelected);
        startActivity(intent);
        finish();
    }
}
