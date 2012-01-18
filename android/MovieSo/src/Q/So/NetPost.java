package Q.So;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NetPost {
	public ArrayList<NameValuePair> nameValuePairs;
	public String URL;
	public InputStream is;
	public String ResultStr;
	public PostNeeder activity;
	AlertDialog dialog;
	int PostThreadId;
	
	public InputStream getInputStream()
	{
		return is;
	}
	public String getResultString()
	{
		//将HttpEntity转化为String  
		if(ResultStr!=null)
		{
			return ResultStr;
		}
		StringBuilder sb = null;
		try{  if(is==null)
			{
				return "";
			}
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is));  
		        sb = new StringBuilder();  
		        String line = null;  
		        while ((line = reader.readLine()) != null) {  
		               if(!line.equals(""))
		               {
		            	   sb.append(line);
		            	   Log.i("LOGCAT", line); 
		               }
		        }
		        Log.i("LOGCAT", "result:"+sb.toString()); 
		   
		        ResultStr=sb.toString();
		        //new AlertDialog.Builder(ActivityLYB.this).setMessage(result).setPositiveButton("确定", null).show();
			}catch(Exception e){ 
		        Log.e("LOGCAT", "Error converting result "+e.toString());  
			}
		return ResultStr;
	}
	public NetPost(ArrayList<NameValuePair> nvp,String url,PostNeeder act,int id)
	{
		activity=act;
		URL=url;
		nameValuePairs=nvp;
		is=null;
		ResultStr=null;
		PostThreadId=id;
	}
	public void RunPost()
	{
		LayoutInflater inflater = ((Activity)activity).getLayoutInflater();
		   View layout = inflater.inflate(R.layout.laprogressbar,
		     (ViewGroup)((Activity)activity).findViewById(R.id.progressbardialog));
		   dialog= new AlertDialog.Builder((Activity)activity).setTitle("Loading").setView(layout)
		     .show();
		NetPostAsyncTask nettask=new NetPostAsyncTask(URL,this,nameValuePairs);
		nettask.execute();
		/*while(is==null)
		{
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/		 

	}
	public void OnFinish(InputStream in) {
		// TODO Auto-generated method stub
		is=in;
		dialog.dismiss();
		activity.OnPostFinish(PostThreadId, NetPost.this);
	}
	public void OnStart()
	{
		activity.PrePostExe();
	}
	
	
}
