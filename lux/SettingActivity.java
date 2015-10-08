package gpapp.hku.lux;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import gpapp.hku.lux.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SettingActivity extends Activity implements OnSeekBarChangeListener{
	
	private SeekBar sb1,sb2,sb3,sb4;
	private TextView tv, tv1, tv2, tv3,textview1,textview2,textview3,textview4; 
	private Button returnBtn;
	private MyApplication mApp;
	private MediaPlayer returnPlayer;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		mApp = (MyApplication) this.getApplication();
		sb1= (SeekBar)findViewById(R.id.seekBar1);
		sb2= (SeekBar)findViewById(R.id.seekBar2);
		sb3= (SeekBar)findViewById(R.id.seekBar3);
		sb4= (SeekBar)findViewById(R.id.seekBar4);
		tv = (TextView)this.findViewById(R.id.tv);
		tv1 = (TextView)findViewById(R.id.tv1);
		tv2 = (TextView)findViewById(R.id.tv2);
		tv3 = (TextView)findViewById(R.id.tv3);
		textview1 = (TextView)findViewById(R.id.textView1);
		textview2 = (TextView)findViewById(R.id.textView2);
		textview3 = (TextView)findViewById(R.id.textView3);
		textview4 = (TextView)findViewById(R.id.textView4);
		//tv1.setText(String.valueOf(mApp.getSoundEffect()*100));
		//tv2.setText(String.valueOf(mApp.getBgMusic()*100));
		//tv3.setText(String.valueOf(mApp.getSoundEffect()*100));
		returnBtn = (Button) findViewById(R.id.returns);
		returnBtn.setBackgroundResource(R.drawable.res);
		returnPlayer = MediaPlayer.create(this, R.raw.return_music);
		returnPlayer.setVolume(mApp.getSoundEffect(), mApp.getSoundEffect());
        OnClickListener return_button_on_click_listener = new OnClickListener(){
			public void onClick(View v){	
				Intent intent = new Intent(getBaseContext(), MainActivity.class);
				returnPlayer.start();
				startActivity(intent);
			}
        };
        returnBtn.setOnClickListener(return_button_on_click_listener);
		sb1.setProgress(Math.round(mApp.getHihatBar()));
		sb2.setProgress(Math.round(mApp.getSnareBar()));
		sb3.setProgress(Math.round(mApp.getBgMusic()*100));
		sb4.setProgress((int)(mApp.getSoundEffect()*100));
		tv.setText(Integer.toString(sb1.getProgress()));
		tv1.setText(Integer.toString(sb2.getProgress()));
		tv2.setText(Integer.toString(sb3.getProgress()));
		tv3.setText(Integer.toString(sb4.getProgress()));
		tv.setTextColor(Color.WHITE);
		tv1.setTextColor(Color.WHITE);
		tv2.setTextColor(Color.WHITE);
		tv3.setTextColor(Color.WHITE);
		textview1.setTextColor(Color.WHITE);
		textview2.setTextColor(Color.WHITE);
		textview3.setTextColor(Color.WHITE);
		textview4.setTextColor(Color.WHITE);
		sb1.setOnSeekBarChangeListener(this);
		sb2.setOnSeekBarChangeListener(this);
		sb3.setOnSeekBarChangeListener(this);
		sb4.setOnSeekBarChangeListener(this);
	}
	
	protected void onDestory(){
		super.onDestroy();  
	}

	@Override
	public void onProgressChanged(SeekBar seekbar, int progress,boolean fromUser) {
		// TODO Auto-generated method stub
		if(tv == null)
			Toast.makeText(getBaseContext(), "tv", Toast.LENGTH_SHORT).show();
		if(tv1 == null)
			Toast.makeText(getBaseContext(), "tv1", Toast.LENGTH_SHORT).show();
		if(seekbar==sb1){ 
			tv.setText(Integer.toString(progress)); 
			mApp.setHihatForce(progress);
		}
		if(seekbar==sb2){ 
			tv1.setText(Integer.toString(progress)); 
			mApp.setSnareForce(progress);
		}
		if(seekbar==sb3){ 
			tv2.setText(Integer.toString(progress)); 
			mApp.setBgMusic(progress);
		}
		if(seekbar==sb4){ 
			tv3.setText(Integer.toString(progress)); 
			mApp.setSoundEffect(progress);
		}
	
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	
	
}
