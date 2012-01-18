package Q.So;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StateActivity extends Activity implements PostNeeder{

	int posting=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lastate);
		Button button=(Button)findViewById(R.id.refstatebutton);
		
		ArrayList<NameValuePair> nameValupairs=new ArrayList<NameValuePair>();
		NetPost netPost=new NetPost(nameValupairs,ConfigAct.getHostAdd()+"movie_state.txt",StateActivity.this,1);
		netPost.RunPost();
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ArrayList<NameValuePair> nameValupairs=new ArrayList<NameValuePair>();
				NetPost netPost=new NetPost(nameValupairs,ConfigAct.getHostAdd()+"movie_state.txt",StateActivity.this,1);
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
			TextView text=(TextView)findViewById(R.id.textstate);
			text.setText(netPost.getResultString());
		}
	}

	@Override
	public void PrePostExe() {
		// TODO Auto-generated method stub
		posting++;
	}

}
