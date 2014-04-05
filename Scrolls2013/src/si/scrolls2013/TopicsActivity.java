package si.scrolls2013;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
 
public class TopicsActivity extends Activity {
 
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        
 
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        expListView.setGroupIndicator(null);
 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
 
        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {
 
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                    int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });
 
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
 
            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
            }
        });
 
        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
 
            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();
 
            }
        });
 
        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
//                Toast.makeText(
//                        getApplicationContext(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                        listDataHeader.get(groupPosition)).get(
//                                        childPosition), Toast.LENGTH_SHORT)
//                        .show();
                return false;
            }
        });
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() { 
            int previousGroup = -1;
     
            @Override 
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                   expListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            } 
        }); 
    }
 
    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader.add("Mechanical Engineering");
        listDataHeader.add("Computer Science Engineering and Information Technology");
        listDataHeader.add("Electronics and Communication Engineering");
        listDataHeader.add("Electrical and Electronics Engineering");
        listDataHeader.add("Management Sciences");
        
        // Adding child data
        List<String> me = new ArrayList<String>();
        me.add("Application of Nano technology in health monitory treatment.");
        me.add("Power generation using nano-carbon tubes.");
        me.add("Gear fault detection in gear box using oil conditioning monitoring.");
        me.add("Improvisation during the last two decades in passenger cars.");
        me.add("Advanced radiator concepts and smart materials.");
        me.add("Recent trends in mechanical engineering.");
        me.add("Kinematics/dynamics of robotic manipulators.");
        me.add("Robotic application present & future.");
        me.add("Hydraulic and pneumatic actuators.");
        me.add("Automatic transmission in automobiles.");
        me.add("Application of CAD software (Catia,ProE) and designing softwares.");
        me.add("Cryogenic engines.");
 
        List<String> cs = new ArrayList<String>();
        cs.add("Green Computing");
        cs.add("Embedded Systems");
        cs.add("Augmented Reality");
        cs.add("Natural Language Processing");
        cs.add("Parallel Digital Image Processing");
        cs.add("CUDA (Compute Unified Device Architecture) and Application");
        cs.add("Learning process of Automation Testing Tools.");
        cs.add("Genetic Engineering");
        cs.add("Cloud Computing");
        cs.add("Bio-Inspired Computing");
        cs.add("Web Services");
        cs.add("Advance Computing");
        cs.add("Mobile Computing Learning and Data Intensive Computing");
        cs.add("Computer Human Interaction");
        cs.add("Ubiquitous Computing");
         
        List<String> ec = new ArrayList<String>();
        ec.add("4G Technology");
        ec.add("Graphene");
        ec.add("Spintronics");
        ec.add("Photonics");
        ec.add("Mobile Satellite Communication");
        ec.add("Optoelectronics");
        ec.add("Future of Mobile Technology");
        ec.add("Nanotechnology");
        ec.add("VLSI");
 
        List<String> en = new ArrayList<String>();
        en.add("Static Transformer Technology");
        en.add("AC Power  Transmission over Long Distance");
        en.add("Hybrid Vehicle");
        en.add("Electricity Theft  Detection");
        en.add("Wind Power Development");
        en.add("SCADA in Power System");
        en.add("Power System Stability Enhancement Using UPFC");
        en.add("Smart Grid Technologies");
        en.add("MEMS Sensor/Actuators");
        en.add("Solar Power Development");
        en.add("Drives in Power  Mills");
        en.add("Drives in Steel  Mills");
        
        List<String> mg = new ArrayList<String>();
        mg.add("Virtual Marketing : A need of Era");
        mg.add("Can the return and accompanied compromise of Narayan Murthy impact the growth of INFOSYS");
        mg.add("Downward slide of Rupees Vs Dollar-Opportunities and Challenges");
        mg.add("Food Security Bills: Its implications to the economic growth");
        mg.add("Agrarian economy in India-boon or bane");
        mg.add("Which is more important –Value of customer or Value to the customer");
        mg.add("Land Acquisition Bill: Its application to the economic growth");
        mg.add("FDI in Indian Retail Sector: Thumbs up or Thumbs down?");
        
        listDataChild.put(listDataHeader.get(0), me); // Header, Child data
        listDataChild.put(listDataHeader.get(1), cs);
        listDataChild.put(listDataHeader.get(2), ec);
        listDataChild.put(listDataHeader.get(3), en);
        listDataChild.put(listDataHeader.get(4), mg);
        }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
    }
