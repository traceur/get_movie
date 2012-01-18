package Q.So;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class NetPostAsyncTask extends AsyncTask<Integer, Integer, String>{
	
	String URL;
	NetPost netPost;
	ArrayList<NameValuePair> nameValuePairs;
	HttpEntity entity;
	public InputStream is;
	
	public NetPostAsyncTask(String url,NetPost netpost,ArrayList<NameValuePair> namevaluePairs){
		URL=url;
		netPost=netpost;
		nameValuePairs=namevaluePairs;
	}

	protected String doInBackground(Integer... param) {
		// TODO Auto-generated method stub  
		
		Log.i("LOGCAT","URL:"+URL);
			/*if(AndBookLogIn.Session.length()==26)
			{
				Log.i("LOGCAT",AndBookLogIn.Session);
				nameValuePairs.add(new BasicNameValuePair("session_id",AndBookLogIn.Session));
			}*/
			BasicHttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
			HttpConnectionParams.setSoTimeout(httpParams, 1000);
			HttpClient httpclient = new DefaultHttpClient(httpParams);  
	        HttpPost httppost = new HttpPost(URL);  
	        try {
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				Log.i("LOGCAT",e.toString());
				e.printStackTrace();
				return null;
			}  
			Log.i("LOGCAT","httppost:"+httppost.toString());
	        HttpResponse response = null;
			try {
				response = httpclient.execute(httppost);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				Log.i("LOGCAT",e.toString());
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.i("LOGCAT",e.toString());
				e.printStackTrace();
				return null;
			}   
	        entity = response.getEntity();  
	        Log.i("LOGCAT","entity:"+entity.toString());
	        try {
				is = entity.getContent();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				Log.i("LOGCAT",e.toString());
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.i("LOGCAT",e.toString());
				e.printStackTrace();
				return null;
			}  
		//netPost.is=is;
			Log.i("LOGCAT","NetNearFinish");
		return null;		
	}
	

	protected void onPostExecute(String result) {
		Log.i("LOGCAT","onPostExecute");
		netPost.OnFinish(is);
	}


	protected void onPreExecute() {
		Log.e("LOGCAT","onPreExecute");
		netPost.OnStart();
	}


	protected void onProgressUpdate(Integer... values) {
		Log.e("LOGCAT","_!_!_!__!_!_!_!_!_!_!_!_!");
	}


}
