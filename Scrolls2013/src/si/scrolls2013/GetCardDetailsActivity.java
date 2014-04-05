package si.scrolls2013;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class GetCardDetailsActivity extends ListActivity {

	int success;
	
	ArrayList<HashMap<String, String>> dataList;
	
	String email;
	private static final String get_detials = "http://117.55.241.59:7711/db_card.php";

	JSONObject json;
	JSONParser jsonParser = new JSONParser();
	JSONObject jsonObj;
	private ProgressDialog pDialog;
	JSONArray card = null;
	
	
	String name;
	String teamId;
	String teamName;
	String domainName;
	String topicName;
	String collegeName;
	String id;
	
	int available=-1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);  
		  setContentView(R.layout.card_details);
		
		//declare the ArrayList
		 dataList = new ArrayList<HashMap<String, String>>();
		 
		//get the Intent
		Intent i = getIntent();
		email = i.getStringExtra("email");
		
		new GetDetails().execute();
		
	//	Log.d("available",Integer.toString(available));
		
		
		ListView lv = getListView();
		
		Toast.makeText(GetCardDetailsActivity.this, "CLICK ON YOUR TEAM TO GET DETAILS", Toast.LENGTH_LONG).show();
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(GetCardDetailsActivity.this,IdCardDetails.class);
				collegeName = ((TextView) arg1.findViewById(R.id.collegeName)).getText().toString();
				domainName = ((TextView) arg1.findViewById(R.id.domainName)).getText().toString();
				name = ((TextView) arg1.findViewById(R.id.name)).getText().toString();
				teamName = ((TextView) arg1.findViewById(R.id.teamName)).getText().toString();
				teamId = ((TextView) arg1.findViewById(R.id.teamId)).getText().toString();
				topicName = ((TextView) arg1.findViewById(R.id.topicName)).getText().toString();
			//	email = ((TextView) arg1.findViewById(R.id.email)).getText().toString();
				i.putExtra("collegeName", collegeName);
				i.putExtra("domainName", domainName);
				i.putExtra("name", name);
				i.putExtra("teamName", teamName);
				i.putExtra("teamId", teamId);
				i.putExtra("topicName", topicName);
				i.putExtra("id", email);
				
				Log.d("id",email);
				startActivity(i);
				}
					
			
		});
		}		
		
	
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}
	
	class GetDetails extends AsyncTask<String, String, String>
	{
		
		@Override
		protected void onPreExecute(){
			 super.onPreExecute();
	            pDialog = new ProgressDialog(GetCardDetailsActivity.this);
	            pDialog.setMessage("Loading your teams. Please wait...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			try
			{
				List<NameValuePair> para = new ArrayList<NameValuePair>();
				para.add(new BasicNameValuePair("email", email));
				
				json = jsonParser.makeHttpRequest(get_detials, "GET", para);
				Log.d("json",email);
				success = json.getInt("success");
			
				if(success == 1){
					available = json.getInt("available");
					if(available == 1)
					{
					card = json.getJSONArray("data");
					
					for(int i=0;i<card.length();i++){
						jsonObj = card.getJSONObject(i);
						
						String name = jsonObj.getString("Name");
						String teamId = jsonObj.getString("TeamId");
						String teamName = jsonObj.getString("TeamName");
						String domainName = jsonObj.getString("DomainName");
						String topicName = jsonObj.getString("TopicName");
						String collegeName = jsonObj.getString("CollegeName");
						String id = email;
						
						 HashMap<String,String> map = new HashMap<String,String>();
						 
//						 ArrayList<String[]> newList = new ArrayList<String[]>();
//						 newList.add(new String[]{name,teamId,domainName,topicName,CollegeName,id});
						 
						 map.put("teamName", teamName);
						 map.put("name", name);
						 map.put("teamId", teamId);
						 map.put("domainName", domainName);
						 map.put("topicName", topicName);
						 map.put("collegeName", collegeName);
						 map.put("email", email);
						 
						 Log.d("id",email);
						 
						 dataList.add(map);
					}
					}
				}
			}catch(JSONException e){
				e.printStackTrace();
			}catch(NullPointerException e){
				e.printStackTrace();
			}
			return null;
			
	}
		
	@Override
	protected void onPostExecute(String url)
	{
		pDialog.dismiss();
		if(success == 1&& available == 1)
		{
		ListAdapter adapter = new SimpleAdapter(GetCardDetailsActivity.this, dataList, R.layout.list_items,
				new String[]{"teamName","name","teamId","domainName","topicName","collegeName",
				"email"}, new int[]{R.id.teamName,R.id.name,R.id.teamId,R.id.domainName,
				R.id.topicName,R.id.collegeName,R.id.email});
		setListAdapter(adapter);
		}else if(success ==0){
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					GetCardDetailsActivity.this);
	 
				// set title
				alertDialogBuilder.setTitle("Error in Card generation");
	 
				// set dialog message
				alertDialogBuilder
					.setMessage("Check your referal id or your internet connection")
					.setCancelable(false)
					.setPositiveButton("Back",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, close
							// current activity
							GetCardDetailsActivity.this.finish();
						}
					 });
	 
				
				
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();
		}else if(available == 0){
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					GetCardDetailsActivity.this);
	 
				// set title
				alertDialogBuilder.setTitle("CARD NOT GENERATED");
	 
				// set dialog message
				alertDialogBuilder
					.setMessage("THE ID CARD WILL BE GENERATED ONCE THE RESULTS ARE DECLARED")
					.setCancelable(false)
					.setPositiveButton("Back",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, close
							// current activity
							GetCardDetailsActivity.this.finish();
						}
					 });
	 
				
				
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();
		}
		}
	}
	
	}
