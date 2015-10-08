package gpapp.hku.lux;

import gpapp.hku.lux.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StatActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stat);
	
    Button return_button = (Button) findViewById(R.id.returns);
    return_button.setBackgroundResource(R.drawable.res);
    OnClickListener return_button_on_click_listener = new OnClickListener()
    {
		public void onClick(View v)
		{
			Intent intent = new Intent(getBaseContext(), MainActivity.class);
			startActivity(intent);
		}
    };
    return_button.setOnClickListener(return_button_on_click_listener);
	
	}
    
	protected void onDestroy()
	    {
	        super.onDestroy();   
	     }
    
}
