package gpapp.hku.lux;


import gpapp.hku.lux.R;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private MediaPlayer bgPlayer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        Button play_button = (Button) findViewById(R.id.play);
        play_button.setBackgroundResource(R.drawable.start);
        OnClickListener play_button_on_click_listener = new OnClickListener()
        {
			public void onClick(View v)
			{
				Intent intent = new Intent(getBaseContext(), PlayActivity.class);
				bgPlayer.stop();
				startActivity(intent);
			}
        };
        play_button.setOnClickListener(play_button_on_click_listener);
	
        Button stat_button = (Button) findViewById(R.id.freep);
        stat_button.setBackgroundResource(R.drawable.freeplay);
        OnClickListener stat_button_on_click_listener = new OnClickListener()
        {
			public void onClick(View v)
			{
				Intent intent = new Intent(getBaseContext(), FreeModeActivity.class);
				bgPlayer.stop();
				startActivity(intent);
			}
        };
        stat_button.setOnClickListener(stat_button_on_click_listener);	

        Button setting_button = (Button) findViewById(R.id.setting);
        setting_button.setBackgroundResource(R.drawable.setting);
        OnClickListener setting_button_on_click_listener = new OnClickListener()
        {
			public void onClick(View v)
			{
				Intent intent = new Intent(getBaseContext(), SettingActivity.class);
				bgPlayer.stop();
				startActivity(intent);
			}
        };
        setting_button.setOnClickListener(setting_button_on_click_listener);	

        Button credit_button = (Button) findViewById(R.id.credit);
        credit_button.setBackgroundResource(R.drawable.credit);
        OnClickListener credit_button_on_click_listener = new OnClickListener()
        {
			public void onClick(View v)
			{
				Intent intent = new Intent(getBaseContext(), CreditActivity.class);
				bgPlayer.stop();
				startActivity(intent);
			}
        };
        credit_button.setOnClickListener(credit_button_on_click_listener);
	
        
	}

	protected void onResume(){
		super.onResume();
        bgPlayer = MediaPlayer.create(this, R.raw.arcofdawn);
        bgPlayer.start();

	}
	protected void onPause(){

		super.onPause();
		bgPlayer.stop();
	}
	
	   protected void onDestroy()
	    {
	        super.onDestroy();   
	     }
}
