package si.scrolls2013;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class IdCardDetails extends Activity {

	String teamName;
	String teamId;
	String name;
	String collegeName;
	String topicName;
	String domainName;
	String id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);  
		setContentView(R.layout.activity_id_card_details);
		
		Intent i = getIntent();
		id = i.getStringExtra("id");
		teamId = i.getStringExtra("teamId");
		name = i.getStringExtra("name");
		domainName = i.getStringExtra("domainName");
		teamName = i.getStringExtra("teamName");
		collegeName = i.getStringExtra("collegeName");
		topicName = i.getStringExtra("topicName");
		
		TextView idtext =  (TextView)findViewById(R.id.id);
		TextView teamidtext =  (TextView)findViewById(R.id.teamId);
		TextView nametext =  (TextView)findViewById(R.id.name);
		TextView domainnametext =  (TextView)findViewById(R.id.domainName);
		TextView teamnametext =  (TextView)findViewById(R.id.teamName);
		TextView collegenametext =  (TextView)findViewById(R.id.collegeName);
		TextView topicnametext =  (TextView)findViewById(R.id.topicName);
		
		idtext.setText(id);
		teamidtext.setText("SCROLLS"+teamId);
	    nametext.setText(name);
		domainnametext.setText(domainName);
		teamnametext.setText(teamName);
		collegenametext.setText(collegeName);
		topicnametext.setText(topicName);
		
	}

	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}
}