package si.scrolls2013;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;

public class CrashActivity extends Activity {
	
	 private static int SPLASH_TIME_OUT = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crash);
		 new Handler().postDelayed(new Runnable() {
			 
	            /*
	             * Showing splash screen with a timer. This will be useful when you
	             * want to show case your app logo / company
	             */
	 
	            @Override
	            public void run() {
	                // This method will be executed once the timer is over
	                // Start your app main activity
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							CrashActivity.this);
			 
						// set title
						alertDialogBuilder.setTitle("No Network Connection");
			 
						// set dialog message
						alertDialogBuilder
							.setMessage("Check net connection")
							.setCancelable(false)
							.setPositiveButton("Goto Previous Page",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									Intent i = new Intent(CrashActivity.this, MainActivity.class);
					                startActivity(i);
					                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
					        		// close this activity
					                finish();
					            }
							 });
			 
							// create alert dialog
							AlertDialog alertDialog = alertDialogBuilder.create();
			 
							// show it
							alertDialog.show();
	
	            	
	           	            }
	        }, SPLASH_TIME_OUT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crash, menu);
		return true;
	}

}
