package Q.So;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectDo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lasel);
		
		final TextView	conf=(TextView)findViewById(R.id.conf);
		SharedPreferences settings= this.getSharedPreferences("confxml",0);
        String HA=settings.getString("HostAdd","");
        
        if(HA.equals(""))
        {
        	ConfigAct.setHostAdd("http://Yousite/");//设置Yousite
        	SharedPreferences settings1= this.getSharedPreferences("confxml",0);
    		SharedPreferences.Editor localEditor=settings1.edit();
    		localEditor.putString("HostAdd","Yousite");
    		localEditor.commit();
        }
        else
        {
        	ConfigAct.setHostAdd(HA);
        }
        conf.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(SelectDo.this,ConfigAct.class);
				SelectDo.this.startActivity(intent);
			}
		});
        Button buttonCommit=(Button)findViewById(R.id.buttonso);
        Button buttonSList=(Button)findViewById(R.id.buttonseelist);
        Button buttonState=(Button)findViewById(R.id.buttonseestate);
        buttonCommit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(SelectDo.this,MovieSoActivity.class);
				SelectDo.this.startActivity(intent);
			}
		});
        buttonSList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(SelectDo.this,TitleList.class);
				SelectDo.this.startActivity(intent);
			}
		});
        buttonState.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(SelectDo.this,StateActivity.class);
				SelectDo.this.startActivity(intent);
			}
		});
        
	}
	

}
