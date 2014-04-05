package si.scrolls2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	// constructor
	public JSONParser() {

	}

	// function get json from url
	// by making HTTP POST or GET mehtod
	 public JSONObject makeHttpRequest(String url, String method,
             List<NameValuePair> params) {

         // Making HTTP request 
         try { 

             // check for request method 
             if(method == "POST"){
                 // request method is POST 
                 // defaultHttpClient 
                 DefaultHttpClient httpClient = new DefaultHttpClient();
                 HttpPost httpPost = new HttpPost(url);
                 httpPost.setEntity(new UrlEncodedFormEntity(params));

                 HttpResponse httpResponse = httpClient.execute(httpPost);
                 HttpEntity httpEntity = httpResponse.getEntity();
                 is = httpEntity.getContent();

             }else if(method == "GET"){
            	 Log.d("CHECK","1");
                 // request method is GET 
                 DefaultHttpClient httpClient = new DefaultHttpClient();
                 Log.d("CHECK","2");
                 String paramString = URLEncodedUtils.format(params, "utf-8");
                 Log.d("CHECK","3");
                 url += "?" + paramString;
                 Log.d("CHECK","4");
                 HttpGet httpGet = new HttpGet(url);
                 Log.d("CHECK","5");
                 HttpResponse httpResponse = httpClient.execute(httpGet);
                 Log.d("CHECK","6");
                 HttpEntity httpEntity = httpResponse.getEntity();
                 Log.d("CHECK","7");
                 is = httpEntity.getContent();
             }            

             Log.d("URL :",url);
             
             BufferedReader reader = new BufferedReader(new InputStreamReader(
                     is, "iso-8859-1"), 8);
             StringBuilder sb = new StringBuilder();
             String line = null;
             Log.d("loder","line");
             while ((line = reader.readLine()) != null) {
                 Log.d("load",line);
                 sb.append(line + "\n");
             } 
             Log.d("toast",url);
             is.close();
             json = sb.toString();

         // try parse the string to a JSON object 
             jObj = new JSONObject(json);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        } 

         Log.d("tag", json);
         json = "mold"; 

         // return JSON String 
         if(jObj != null){
             Log.d("tag", "msg");
             return jObj;
         }else 
         { 
             Log.d("lol", url);
             return null; 
         } 

     } 
}