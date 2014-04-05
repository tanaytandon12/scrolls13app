package si.scrolls2013;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class Details extends Activity {

	String value;
	WebView text;
	TextView title;
	String res,s="";
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		setContentView(R.layout.activity_details);
		
		Bundle extra = getIntent().getExtras();
		if(extra == null){
			return;
		}
	
	text = (WebView) findViewById(R.id.webView1);
	text.setBackgroundColor(0);
	//	title = (TextView) findViewById(R.id.textView2);
	
	value = extra.getString("tag");
	
	examineXMLFile();
	

	}

protected void examineXMLFile() {
		
		try{
				    if(value.equalsIgnoreCase("contact")){
				value = "     Contact Us";
//				s = "<table style='margin: auto;'>";
//                s += "<tbody><tr>";
//                s += "          <td colspan='2' style='color: Maroon; font-weight: bold;'>";
//                s += "              PATRON<br>";
//                s += "           </td>";
//                s += "      </tr>";
//                s += "        <tr>";
//                		s += "            <td style='width: 200px;'>";
//                				s += " Mr. Arun kumar Aggrawal";
//                						s += "     </td>";
//                								s += "    <td style='padding-left: 50px;'>";
//                								s += "    President, GMA";
//                								s += "    </td>";
//                								s += " </tr>";
//                								s += "   <tr>";
//                								s += " <td colspan='2' style='color: Maroon; font-weight: bold;'>";
//                								s += "       ADVISORY HEAD<br>";
//                								s += "     </td>";
//                								s += " </tr>";
//                								s += " <tr>";
//                								s += " <td>";
//                								s += "Dr. R.K. Agarwal";
//                								s += "</td>";
//
//
//                                s += "<td style='padding-left: 50px;'>";
//                            s += " Director,AKGEC";
//                                s += "</td>";
//                                s += "  </tr>";
//                        s += " <tr>";
//                        s += "  <td colspan='2' style='color: Maroon; font-weight: bold;'>";
//                            s += "  ORGANISING HEAD<br>";
//                                s += " </td>";
//                            s += " </tr>";
//                        s += "  <tr>";
//                        s += " <td style='width:100%;'>";
//                            s += " Prof.&nbsp;V.K.&nbsp;Parashar";
//                                s += "</td>";
//                               s += " <td style='padding-left: 50px;'>";
//                            s += "  DSW,AKGEC";
//                                s += " </td>";
//                                s += "</tr>";
//                        s += " <tr>";
//                        s += " <td colspan='2' style='color: Maroon; font-weight: bold;'>";
//                            s += "  STUDENT CONVENORS<br>";
//                                s += "</td>";
//                            s += "  <td>";
//                            s += " </td></tr><tr>";
//                                s += "  <td>";
//                                    s += "   Arpit&nbsp;Singh";
//                                        s += " </td>";
//                                        s += " <td style='padding-left: 50px;'>";
//                                    s += "  09990810717";
//                                        s += "    </td>";
//                                        s += " </tr>";
//                                s += " <tr>";
//                                s += "  <td>";
//                                    s += "  </td>";
//                                    s += "  <td style='padding-left: 50px;'>";
//                                    s += "  <a href='mailto:rjcoolarpit15@gmail.com?Subject=scrolls13' target='_top'>rjcoolarpit15@gmail.com</a>";
//                                        s += "  </td>";
//                                    s += "  </tr>";
//                                s += " <tr>";
//                                s += " <td>";
//                                    s += " Shashwat&nbsp;Prakash";
//                                        s += " </td>";
//                                        s += "  <td style='padding-left: 50px;'>";
//                                    s += "  09891306984";
//                                        s += " </td>";
//                                        s += "  </tr>";
//                                s += " <tr>";
//                                s += "  <td>";
//                                		
//                                    s += "    </td>";
//                                    s += "  <td style='padding-left: 50px;'>";
//                                    s += "     <a href='mailto:shashwatprakash@yahoo.com?Subject=scrolls13' target='_top'>shashwatprakash@yahoo.com</a>";
//                                        s += "  </td>";
//                                    s += " </tr>";
//                                s += " <tr>";
//                                s += " <td>";
//                                    s += "      Ajit&nbsp;Jain";
//                                        s += "  </td>";
//                                        s += " <td style='padding-left: 50px;'>";
//                                    s += "   07838484796";
//                                        s += "  </td>";
//                                        s += " </tr>";
//                                s += "  <tr>";
//                                s += "  <td>";
//                                    s += " </td>";
//                                    s += " <td style='padding-left: 50px;'>";
//                                    s += "     <a href='mailto:ajit.jain004@gmail.com?Subject=scrolls13' target='_top'>ajit.jain004@gmail.com</a>";
//                                        s += " </td>";
//                                    s += " </tr>";
//                                s += " <tr>";
//                                s += " <td colspan='2' style='color: Maroon; font-weight: bold;'>";
//                                    s += "     WEB MASTER";
//                                        s += "  </td>";
//                                        s += " </tr>";
//                                s += "<tr>";
//                                s += " <td>";
//                                    s += "    Dhruv&nbsp;Singhal";
//                                        s += " </td>";
//                                        s += "<td style='padding-left: 50px;'>";
//                                    s += " 9871330141";
//                                        s += " </td>";
//                                        s += "</tr>";
//                                s += "<tr>";
//                                s += " <td>";
//                                    s += "</td>";
//                                    s += "<td style='padding-left: 50px;'>";
//                                    s += "<a href='mailto:blackhatd@gmail.com?Subject=scrolls13%20website' target='_top'>blackhatd@gmail.com</a>";
//                                        s += " </td>";
//                                    s += "</tr>";
//                                s += "<tr>";
//                                s += "<td colspan='2' style='color: Maroon; font-weight: bold;'>";
//                                s += "ORGANIZING TEAM";
//                                s += " </td>";
//                                
//                                s += " </tr>";
//                                s += "            <tr><td style='text-transform:capitalize;'>Aman Srivastava";
//                                		s += "</td><td></td></tr>";
//
//s += " <tr><td style='text-transform:capitalize;'>Aman&nbsp;Singh";
//		   s += "</td><td></td></tr>";
//s += "   <tr><td style='text-transform:capitalize;'>Harshit&nbsp;Kumar";
//		   s += "</td><td></td></tr>";
//s += "  <tr><td style='text-transform:capitalize;'>Laxit&nbsp;Goyal";
//		   s += "</td><td></td></tr>";
//s += "  <tr><td style='text-transform:capitalize;'>Varun&nbsp;Bhardwaj";
//		   s += "</td><td></td></tr>";
//s += "  <tr><td style='text-transform:capitalize;'>Dushyant&nbsp;Shah";
//		   s += "</td><td></td></tr>";
//s += "  <tr><td style='text-transform:capitalize;'>Abhishek&nbsp;Agrawal";
//		   s += "</td><td></td></tr>";
//s += "  <tr><td style='text-transform:capitalize;'>Rohit&nbsp;Verma";
//		   s += "</td><td></td></tr>";
//s += "  <tr><td style='text-transform:capitalize;'>Saurabh&nbsp;Tewari";
//		   s += "</td><td></td></tr>";
//s += "<tr><td style='text-transform:capitalize;'>Udbhav&nbsp;Pandey";
//s += "</td><td></td></tr>";
//
//s += "<tr>";
//s += "<td colspan='2' style='color: Maroon; font-weight: bold;text-transform:uppercase;'>Web Team</td>";
//s += "</tr>";
//s += "<tr><td>Anurag&nbsp;Arora</td><td></td></tr>";
//s += "<tr><td>Priyanshu&nbsp;Agarwal</td><td></td></tr>";
//s += "<tr><td colspan='2' style='color: Maroon; font-weight: bold;text-transform:uppercase;'>Designing Team</td>";
//s += "</tr><tr><td style='text-transform:capitalize;'>Shubh&nbsp;Jaiswal</td><td></td></tr>";
//s += "<tr><td style='text-transform:capitalize;'>Sufiyan&nbsp;Ansari</td><td></td></tr>";
//s += "<tr><td style='text-transform:capitalize;'>Vipul&nbsp;Pandey</td><td></td></tr>";
//               s +=     "</tbody></table>";
			}else if(value.equalsIgnoreCase("Rules")){
				value = "  Rules & Regulations";
				s = "<ul align='justify' style='color:#440c0c'><li>The competition will be open to all bona fide students of Engineering and Management Colleges. A bona fide student certificate to be submitted as per the suggested format, by the students along with the hard copy of their paper Maximum of 3 authors per paper.</li>";
                s += "<li>For presentation, a time slot of 7 minutes + 3 minutes (for Q&A) will be given to each team.</li>";
                s += "</li><li>Extended Abstract of 500 words max to be sent by laid down date. Based on the abstract, an experts committee will select the papers for inclusion in the final presentation.</li>";
                s += "<li>A soft copy of the final paper is to be sent before the designated last date.A hard copy of the paper in accordance with laid down format shall have to be handed over to organizing secretary along with Bonafide student certificate prior to the actual presentation.";
                s += "</li><li>The paper to be typed in single space, single column format, using Times New Roman font and size 12. maximum number of pages to be 10 and the page should have no numbers. A margin of 1” all around to be left.";
                s += "</li><li>First page to include Title, Name(s) of authors, their college name, telenos as well as e-mail addresses. This info to be included with extended abstract also.";
                s += "</li><li>An LCD projector shall be provided for conducting the presentation.</li></ul>";
		    }else if(value.equalsIgnoreCase("About")){
				value = "         Event";
				s += "<p style = 'color: maroon;font-size: 20px;font-weight: bold;text-align: center;}'>If you can imagine something that is beyond all the limitations, Then you have the urge to conquer the world.</p>";
				s += "<p style = 'text-align: justify; color:#440c0c'>We believe that Imagination is not only the unique human capacity to envision the unperceived and undiscovered , but also the foundation of all inventions and innovations. If one has to reach for the ultimate then one must be able to think beyond the conventions.<br> Keeping the above in mind, we here at AKGEC organize, an annually held, National-level   Technical Paper Presentation event called SCROLLS(Students creative and Oratory  Learning Skills) which aspires to augment excellence in the sphere of technology";
                s += " to promote the technocrats of tomorrow. This event provides a platform to the budding";
                s += " professionals who can channelize their talent into creativity and give their imagination";
                s += " a cutting edge.<br> Using INNOVATION as a tag word for the event, we describe it";
                s += " as an art of perceiving the world in a totally different manner which can bring";
                s += " laurels to conceiver and add to the advancement of the society.<br> We invite budding";
                s +=  " pioneers from Management and various engineering domains to this event. It is an";
                s +=   " honest effort to widen the horizon of the technophiles to partake their knowledge";
                s +=  " for a better future.<br> Let us unite together and explore all the unimaginable";
                s +=  " and untouched avenues in this world of science, surmounting the peak of engineering";
                s +=  " and management, giving them a chance for the best future they can hope of.</p>";
		    }else if(value.equalsIgnoreCase("Dates")){
		    	value = " Important Dates";
		    	s = "<ul align='justify' style='color:#440c0c'><li>Registration Begin: 4th September </li>";
                s += "<li>Registration closes &amp; last date for submission of synopsis:<b>24th September</b> .</li>";
                s += "<li>Notification Of Acceptance:<b> 28th September</b> .</li>";
                s += "<li>Last date of submission of final paper:<b> 3rd October </b>.</li>";
                s += "<li>Main Event is scheduled on <b>5th October 2013</b>.</li>";
                s += "</ul>";
		    }
		    
		    
		    
		    
		    res = "<html><body <h1 style='color:maroon;font-size:25px;'>"+ value+"</h1><hr/>";
		    res += s + "</body></html>";
		    // should be done... let's display our results
		    text.getSettings().setJavaScriptEnabled(true);
		    if(value.equalsIgnoreCase("     Contact Us")){
		    	text.loadUrl("file:///android_asset/abc.html");
		    }else{
		    text.loadData(res,"text/html",null);   }
		}catch (Exception e) {
			  text.loadData(e.getMessage(), "text/html", "UTF-8");  
		    }
	}
	
@Override
public void onBackPressed() {
    super.onBackPressed();
    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
}

}
