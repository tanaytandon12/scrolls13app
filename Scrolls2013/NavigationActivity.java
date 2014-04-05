package si.scrolls2013;


import java.util.ArrayList;

import org.w3c.dom.Document;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class NavigationActivity extends FragmentActivity {

	// Google Map
	private GoogleMap googleMap;
	si.scrolls2013.GMapV2Direction md;
	LatLng fromPosition = new LatLng(13.687140112679154, 100.53525868803263);
	LatLng toPosition = new LatLng(13.683660045847258, 100.53900808095932);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_activity);

		try {
			// Loading map
			initilizeMap();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * function to load map. If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		    	if (googleMap == null) {
		    		googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(
		    				R.id.map)).getMap();
		
		    		// check if map is created successfully or not
		    		if (googleMap == null) {
		    			Toast.makeText(getApplicationContext(),
		    					"Sorry! unable to create maps", Toast.LENGTH_SHORT)
		    					.show();
		    		}
		    	}
		    	// latitude and longitude
		    	double latitude = 28.675751;
		    	double longitude = 77.502818;
		
		    	// create marker
		    	MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("AKGEC ");
		
		    	// adding marker
		    	googleMap.addMarker(marker);
		    	CameraPosition cameraPosition = new CameraPosition.Builder().target(
		    			new LatLng(28.675751, 77.502818)).zoom(12).build();
		
		
		    	googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

		/*md = new GMapV2Direction();
		googleMap = ((SupportMapFragment)getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		LatLng coordinates = new LatLng(13.685400079263206, 100.537133384495975);		
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 16));

		googleMap.addMarker(new MarkerOptions().position(fromPosition).title("Start"));
		googleMap.addMarker(new MarkerOptions().position(toPosition).title("End"));

		Document doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
		md.getDurationValue(doc);
		md.getDistanceText(doc);
		md.getStartAddress(doc);
		md.getCopyRights(doc);

		ArrayList<LatLng> directionPoint = md.getDirection(doc);
		PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);

		for(int i = 0 ; i < directionPoint.size() ; i++) {			
			rectLine.add(directionPoint.get(i));
		}

		googleMap.addPolyline(rectLine);*/
	}



	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}

}