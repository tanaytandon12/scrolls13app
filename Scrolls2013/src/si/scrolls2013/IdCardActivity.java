package si.scrolls2013;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IdCardActivity extends Activity {

	EditText editText1;
	Button button1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  overridePendingTransition(R.anim.fadein, R.anim.fadeout);  
		    setContentView(R.layout.activity_main);
	        
	        editText1 = (EditText) findViewById(R.id.editText1);
	        button1 = (Button) findViewById(R.id.button1);
	 
	        
	        button1.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					
					if(haveNetworkConnection() == false){
			        	Intent i =new Intent(IdCardActivity.this,CrashActivity.class);
			        	startActivity(i);
			        	finish();
			        			}
					else
					{
						Intent i = new Intent(IdCardActivity.this,GetCardDetailsActivity.class);
						i.putExtra("email", editText1.getText().toString());
						startActivity(i);
					}
					}
			});
	        
	    	}
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}

	public boolean haveNetworkConnection() { 
	    boolean haveConnectedWifi = false;
	    boolean haveConnectedMobile = false;
	 
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo) {
	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	            if (ni.isConnected())
	                haveConnectedWifi = true;
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	            if (ni.isConnected())
	                haveConnectedMobile = true;
	    } 
	    return haveConnectedWifi || haveConnectedMobile;
	}
}
