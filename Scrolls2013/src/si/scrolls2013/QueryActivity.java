package si.scrolls2013;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class QueryActivity extends Activity {

	EditText textEmail;
	EditText textQuery;

	private ProgressDialog pDialog;
	JSONParser jsonParser;

	String email;
	String query;
	private static String url_create_product = "http://117.55.241.59:7711/db_query.php";
	int success;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		setContentView(R.layout.activity_query);
		Button button = (Button)findViewById(R.id.button1);

		textEmail = (EditText)findViewById(R.id.textView1);
		textQuery = (EditText)findViewById(R.id.textView2);

		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(haveNetworkConnection())
				{
					email = textEmail.getText().toString();
					query = textQuery.getText().toString();
					if(!email.equals("")&&!query.equals(""))
					{
						new Submit().execute();
					}
					else {
						Context context = getApplicationContext();
						CharSequence text = "Please Fill Your Query";
						int duration = Toast.LENGTH_SHORT;

						Toast toast = Toast.makeText(context, text, duration);
						toast.show();
					}
					//	finish();
				}
				else
				{
					Intent i =new Intent(QueryActivity.this,CrashActivity.class);
					startActivity(i);
					finish();
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


	class Submit extends AsyncTask<String, String, String>

	{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(QueryActivity.this);
			pDialog.setMessage("Submiting your query..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {

			jsonParser = new JSONParser();

			//Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("query", query));
			params.add(new BasicNameValuePair("email",email));
			//	params.add(new BasicNameValuePair("description", query));

			//getting JSON Object
			JSONObject json = jsonParser.makeHttpRequest(url_create_product, "POST", params);

			//Log.d("JSON:",json.toString());

			try
			{
				success = json.getInt("success");

				if(success == 1){
					Log.d("Query","Successful"+query+email);
				}else{
					Log.d("Query","Unsuccessful");
				}
			}catch(JSONException e){
				Log.d("JSON:","EXCEPTION");
			}

			return null;
		}

		@Override
		protected void onPostExecute(String url){
			pDialog.dismiss();
			if(success == 1)
			{
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						QueryActivity.this);

				// set title
				alertDialogBuilder.setTitle("SUCCESS");

				// set dialog message
				alertDialogBuilder
				.setMessage("your query was successfully submitted")
				.setCancelable(false)
				.setPositiveButton("Goto Home Page",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						QueryActivity.this.finish();
					}
				});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();

			}
			else
			{
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						QueryActivity.this);

				// set title
				alertDialogBuilder.setTitle("FAILURE");

				// set dialog message
				alertDialogBuilder
				.setMessage("your query was not successfully submitted")
				.setCancelable(false)
				.setPositiveButton("Check Details",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
					}
				});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();

			}
			//	finish();
		}
	}

}
