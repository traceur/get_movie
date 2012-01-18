package Q.So;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConfigAct extends Activity{
	private static String HostAdd=null;
	private EditText editHostAdd;
	private Button buttonOK;
	private Button buttonCancle;
	public static void setHostAdd(String S1)
	{
		
		
		String S2=null;
		if(S1.regionMatches(0,"http://",0, 7))
		{
			S2=S1;
		}
		else
		{
			S2="http://"+S1;
		}
		HostAdd=S2+"/";
	}
	public static String getHostAdd()
	{
		return HostAdd;
	}
	private void OnConfOK()
	{
		String S1 =editHostAdd.getText().toString();
		String S2=null;
		if(S1.regionMatches(0,"http://",0, 7))
		{
			S2=S1;
		}
		else
		{
			S2="http://"+S1;
		}
		SharedPreferences settings= this.getSharedPreferences("confxml",0);
		SharedPreferences.Editor localEditor=settings.edit();
		localEditor.putString("HostAdd",S1);
		localEditor.commit();
		HostAdd=S2+"/";
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.laconf);
		editHostAdd=(EditText)findViewById(R.id.EditHostAdd);
		buttonOK=(Button)findViewById(R.id.ButtonConfOk);
		buttonCancle=(Button)findViewById(R.id.ButtonConfCancle);
		SharedPreferences settings= this.getSharedPreferences("confxml",0);
		editHostAdd.setText(settings.getString("HostAdd",""));
		MyOnClickListener myOnClickListener=new MyOnClickListener();
		buttonOK.setOnClickListener(myOnClickListener);
		buttonCancle.setOnClickListener(myOnClickListener);
	}
	class MyOnClickListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{
			case R.id.ButtonConfOk:
				ConfigAct.this.OnConfOK();
				finish();
				return;
			case R.id.ButtonConfCancle:
				finish();
				return;
			}
		}
	}

}
