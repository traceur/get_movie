package Q.So;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MovieSoActivity extends Activity implements PostNeeder{
    /** Called when the activity is first created. */
	public static String MoName=null;
	public static String PassWD=null;
	int posting=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        
        final EditText MNEdit=(EditText)findViewById(R.id.MoName);
        final Button	SearchB=(Button)findViewById(R.id.SearchButton);
        
        SearchB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MoName=MNEdit.getText().toString();
				PassWD="123456";//与php端通信的密码
				ArrayList<NameValuePair> nameValupairs=new ArrayList<NameValuePair>();
				nameValupairs.add(new BasicNameValuePair("movie",URLEncoder.encode(MoName)));
				nameValupairs.add(new BasicNameValuePair("passwd",PassWD));
				NetPost netPost=new NetPost(nameValupairs,ConfigAct.getHostAdd()+"movie.php",MovieSoActivity.this,1);
				netPost.RunPost();
				
				/*Intent intent=new Intent();
				intent.setClass(MovieSoActivity.this,TitleList.class);
				MovieSoActivity.this.startActivity(intent);*/
			}
		});
        
        
        /*ArrayList<NameValuePair> nameValupairs=new ArrayList<NameValuePair>();
		nameValupairs.add(new BasicNameValuePair("student_id",UserID));
		nameValupairs.add(new BasicNameValuePair("password",UserPD));
		NetPost netPost=new NetPost(nameValupairs,ConfigAct.getHostAdd()+"/check.php",MovieSoActivity.this,1);
		netPost.RunPost();*/
    }

	@Override
	public void OnPostFinish(int i, NetPost netPost) {
		// TODO Auto-generated method stub
		posting--;
		if(i==1)
		{
			String Res=netPost.getResultString();
			Log.i("LOGCAT",Res);
			Intent intent=new Intent();
			intent.setClass(MovieSoActivity.this,TitleList.class);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MovieSoActivity.this.startActivity(intent);
			MovieSoActivity.this.finish();
			
		}
	}

	@Override
	public void PrePostExe() {
		// TODO Auto-generated method stub
		posting++;
	}
}
