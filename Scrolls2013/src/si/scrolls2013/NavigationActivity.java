package si.scrolls2013;


import java.util.ArrayList;

import org.w3c.dom.Document;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class NavigationActivity extends FragmentActivity {

	// Google Map
	private GoogleMap googleMap;
	GMapV2Direction md;
	
	//Document
	Document doc;
	
	//location
	GPSTracker gps;
	
	LatLng fromPosition;
	LatLng toPosition = new LatLng(28.675751, 77.502818);
	double latitude;
	double longitude;

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

		md = new GMapV2Direction();
		googleMap = ((SupportMapFragment)getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		
		gps = new GPSTracker(NavigationActivity.this);

		// check if GPS enabled		
		if(gps.canGetLocation()){

			latitude = gps.getLatitude();
			longitude = gps.getLongitude();
			//Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	


		}else{
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}

        	fromPosition= new LatLng(latitude, longitude);

		LatLng coordinates = new LatLng(latitude, longitude);		
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 14));

		googleMap.addMarker(new MarkerOptions().position(fromPosition).title("Start"));
		googleMap.addMarker(new MarkerOptions().position(toPosition).title("End"));
		
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if(currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB)
		{
		new MapDraw().execute();
		}
		else {
			
		}
			
	}
	



	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}
	
	class MapDraw extends AsyncTask<String, String, String>
	{
		
		

		@Override
		protected String doInBackground(String... params) {
			doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
			md.getDurationValue(doc);
			md.getDistanceText(doc);
			md.getStartAddress(doc);
			md.getCopyRights(doc);

			return null;
		}
		
		protected void onPostExecute(String url)
		{
			ArrayList<LatLng> directionPoint = md.getDirection(doc);
			PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);

			for(int i = 0 ; i < directionPoint.size() ; i++) {			
				rectLine.add(directionPoint.get(i));
			}

			googleMap.addPolyline(rectLine);

		}
	}

}