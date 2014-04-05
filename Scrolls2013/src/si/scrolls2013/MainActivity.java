package si.scrolls2013;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView listView1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		final Event weather_data[] = new Event[]
				{
				new Event(R.drawable.about),
				new Event(R.drawable.cnt),
				new Event(R.drawable.rules),
				new Event(R.drawable.query),
				new Event(R.drawable.topics),
				new Event(R.drawable.card),
				new Event(R.drawable.date),
				new Event(R.drawable.nav)
				};

		EventAdapter adapter = new EventAdapter(this, 
				R.layout.listview_item_row, weather_data);


		listView1 = (ListView)findViewById(R.id.list);

		//        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
		//        listView1.addHeaderView(header);

		listView1.setAdapter(adapter);
		
	
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//		Toast.makeText(getBaseContext(),Weather.Title[(arg2-1)], Toast.LENGTH_SHORT).show();
				System.out.println(arg2);
				if(Event.Title[arg2].equalsIgnoreCase("About")||Event.Title[arg2].equalsIgnoreCase("Rules")||
						Event.Title[arg2].equalsIgnoreCase("Contact")||Event.Title[arg2].equalsIgnoreCase("Dates")
						){
					Intent i = new Intent(MainActivity.this,Details.class);
					i.putExtra("tag", Event.Title[arg2]);
					startActivity(i);
				}else if(Event.Title[arg2].equalsIgnoreCase("Query")){
					if(haveNetworkConnection()){
					Intent i = new Intent(MainActivity.this,QueryActivity.class);
					startActivity(i);
					}
					else{
						Intent i = new Intent(MainActivity.this,CrashActivity.class);
						startActivity(i);
					}
				}else if(Event.Title[arg2].equalsIgnoreCase("Topics")){
					Intent i = new Intent(MainActivity.this,TopicsActivity.class);
					startActivity(i);
				}else if(Event.Title[arg2].equalsIgnoreCase("ID Card")){
					if(haveNetworkConnection()){
					Intent i = new Intent(MainActivity.this,IdCardActivity.class);
					startActivity(i);
					}else
					{
						Intent i = new Intent(MainActivity.this,CrashActivity.class);
						startActivity(i);
					}
				}else if(Event.Title[arg2].equalsIgnoreCase("Navigation")){
					if(haveNetworkConnection()){
					Intent i = new Intent(MainActivity.this,NavigationActivity.class);
					startActivity(i);
					}else{
						Intent i = new Intent(MainActivity.this,CrashActivity.class);
						startActivity(i);
					}
				}
			}
		});
	}

	public boolean haveNetworkConnection() { 
	    boolean haveConnectedWifi = false;
	    boolean haveConnectedMobile = false;
	 
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo) {
	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	            if (ni.isConnected()){
	                haveConnectedWifi = true;
	                Log.d("tag", "wifi");}
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	            if (ni.isConnected()){
	                haveConnectedMobile = true;
	                Log.d("tag", "mobile");
	            }
	    } 
	    return haveConnectedWifi || haveConnectedMobile;
	}
}