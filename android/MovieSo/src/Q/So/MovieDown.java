package Q.So;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDown extends Activity implements PostNeeder{
	int posting=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lamovdown);
		TextView textTitle=(TextView)findViewById(R.id.texttitle);
		TextView textUrl=(TextView)findViewById(R.id.texturl);
		TextView textSiz=(TextView)findViewById(R.id.textsiz);
		Intent intent=getIntent();
		final String url=intent.getStringExtra("url");
		String str=intent.getStringExtra("title");
		int index=str.indexOf("|||");
		textTitle.setText(str.substring(index+3));
		textSiz.setText(str.substring(0,index));
		textUrl.setText(url);
		Button buttonDown=(Button)findViewById(R.id.buttondown);
		buttonDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*Time t=new Time();
				t.setToNow();
				int hour=t.hour;
				int minute=t.minute;
				int second=t.second;
				minute++;
				if(minute>=60)
				{
					minute-=60;
					hour++;
					if(hour>=24)
					{
						hour-=24;
					}
				}
				String SH=null;
				String SM=null;
				String SS=null;
				if(hour<10)
				{
					SH="0"+Integer.toString(hour);
				}
				else
				{
					SH=Integer.toString(hour);
				}
				if(minute<10)
				{
					SM="0"+Integer.toString(minute);
				}
				else
				{
					SM=Integer.toString(minute);
				}
				if(second<10)
				{
					SS="0"+Integer.toString(second);
				}
				else
				{
					SS=Integer.toString(second);
				}
				String TM=SH+":"+SM+":"+SS;*/
				//Toast.makeText(getApplicationContext(), TM,Toast.LENGTH_SHORT).show();
				ArrayList<NameValuePair> nameValupairs=new ArrayList<NameValuePair>();
				nameValupairs.add(new BasicNameValuePair("value",url));
				NetPost netPost=new NetPost(nameValupairs,ConfigAct.getHostAdd()+"down.php",MovieDown.this,1);
				netPost.RunPost();
			}
		});
	}

	@Override
	public void OnPostFinish(int i, NetPost netPost) {
		// TODO Auto-generated method stub
		posting--;
		if(i==1)
		{
			Intent intent=new Intent();
			intent.setClass(MovieDown.this,StateActivity.class);
			MovieDown.this.startActivity(intent);
			MovieDown.this.finish();
		}
	}
	
	@Override
	public void PrePostExe() {
		// TODO Auto-generated method stub
		posting++;
	}
	
}
