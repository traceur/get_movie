package Q.So;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class TitleList extends Activity implements PostNeeder{
	
	int posting=0;
	
	final private List<String> titleList=new ArrayList<String>();
	final private List<String> urlList=new ArrayList<String>();
	final private List<String> showList=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.latitle);
		
		ArrayList<NameValuePair> nameValupairs=new ArrayList<NameValuePair>();
		NetPost netPost=new NetPost(nameValupairs,ConfigAct.getHostAdd()+"movie_choice.php",TitleList.this,1);
		netPost.RunPost();
		
		Button buttonref=(Button)findViewById(R.id.refbutton);
		buttonref.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				titleList.clear();
				urlList.clear();
				showList.clear();
				ArrayList<NameValuePair> nameValupairs=new ArrayList<NameValuePair>();
				NetPost netPost=new NetPost(nameValupairs,ConfigAct.getHostAdd()+"movie_choice.php",TitleList.this,1);
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
			String str=null;
			int index=0;
			BufferedReader reader = new BufferedReader(new InputStreamReader(netPost.getInputStream()));
			try
			{
			while((str=reader.readLine())!=null)
			{
				index=str.indexOf("=");
				titleList.add(str.substring(0,index));
				urlList.add(str.substring(index+1));
				showList.add(str.substring(str.indexOf("|||")+3,index));
			}
			}
			catch(Exception e)
			{
				Log.e("LOGCAT",e.toString());
			}
			ListView list=(ListView)findViewById(R.id.titlelist);
			ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,showList);
			list.setAdapter(adapter);
			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					String title=titleList.get((int)arg3);
					String url=urlList.get((int)arg3);
					Intent intent=new Intent();
					intent.setClass(TitleList.this,MovieDown.class);
					intent.putExtra("title",title);
					intent.putExtra("url",url);
					TitleList.this.startActivity(intent);
				}
			});
		}
	}

	@Override
	public void PrePostExe() {
		// TODO Auto-generated method stub
		posting++;
	}

}
