package gpapp.hku.lux;

import gpapp.hku.lux.PlayService.PlayBinder;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class FreeModeActivity extends Activity {
	
	private SeekBar sb1,sb2;
	private TextView tv, tv0; 
	private int hihat_sensa, snare_sensa;
	private MediaPlayer hihatPlayer;
	private MediaPlayer hihatPlayer1;
	private MediaPlayer snarePlayer;
	private MediaPlayer snarePlayer1;
	private MediaPlayer kickPlayer;
	private MediaPlayer kickPlayer1;
	private MediaPlayer startPlayer;
	private MediaPlayer returnPlayer;
	private PlayService mService;
	private SensorManager mSensorManager;
	private MotionEventListener mSensorListener;
	private int ShakeCounter = 0;
	private int RotateCounter = 0;
	private boolean is_bound = false;
	private Button play_button;
	private Dialog playing;
	private MyApplication mApp;
	private boolean pressed;
	
	
	
	/*private ServiceConnection mConnection = new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			PlayBinder binder = (PlayBinder) service;
			mService = binder.getService(play_button);
			is_bound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			is_bound = false;
		}
    	
    };*/
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.freemode);
		mApp = (MyApplication) this.getApplication();
		pressed = false;
		//
		//tv= (TextView)findViewById(R.id.tv);
		//tv0= (TextView)findViewById(R.id.tv0);
		mApp = (MyApplication) this.getApplication();
		/*Intent intent = new Intent(getBaseContext(),PlayService.class);
		if(!bindService(intent,mConnection,Context.BIND_AUTO_CREATE)){
			Toast.makeText(getBaseContext(), "Bind Service fail", Toast.LENGTH_SHORT).show();
		}*/
		hihatPlayer = MediaPlayer.create(this, R.raw.hihat);
		hihatPlayer1 = MediaPlayer.create(this, R.raw.hihat);
		if(hihatPlayer == null){
			Toast.makeText(getBaseContext(), "Create hihatPlayer fail", Toast.LENGTH_SHORT).show();
		}
		snarePlayer = MediaPlayer.create(this, R.raw.snare);
		snarePlayer1 = MediaPlayer.create(this, R.raw.snare);
		kickPlayer = MediaPlayer.create(this, R.raw.kick);
		kickPlayer1 = MediaPlayer.create(this, R.raw.kick);
		startPlayer = MediaPlayer.create(this, R.raw.start_music);
		returnPlayer = MediaPlayer.create(this, R.raw.return_music);
		snarePlayer.setVolume(mApp.getSoundEffect(), mApp.getSoundEffect());
		hihatPlayer.setVolume(mApp.getSoundEffect(), mApp.getSoundEffect());
		kickPlayer.setVolume(mApp.getSoundEffect(), mApp.getSoundEffect());
		startPlayer.setVolume(mApp.getSoundEffect(), mApp.getSoundEffect());
		returnPlayer.setVolume(mApp.getSoundEffect(), mApp.getSoundEffect());
		if(snarePlayer == null){
			Toast.makeText(getBaseContext(), "Create snarePlayer fail", Toast.LENGTH_SHORT).show();
		}
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorListener = new MotionEventListener();
		mSensorListener.setOnMotionListener(new MotionEventListener.OnMotionListener() {
			
			@Override
			public void onShake() {
				// TODO Auto-generated method stub
				//ShakeCounter++;
				//Toast.makeText(getBaseContext(), "shake! " + "The "+ String.valueOf(ShakeCounter) +" times", Toast.LENGTH_SHORT).show();
				if(pressed == true){
				MediaPlayer soundPlayer;
				if(hihatPlayer.isPlaying()){
					soundPlayer = hihatPlayer1;
				}else{
					soundPlayer = hihatPlayer;
				}
				soundPlayer.start();
				}
			}

			@Override
			public void onRotate() {
				// TODO Auto-generated method stub
				//RotateCounter++;
				//Toast.makeText(getBaseContext(), "Rotate! " + "The "+ String.valueOf(RotateCounter) +" times", Toast.LENGTH_SHORT).show();
				if(pressed == true){
				MediaPlayer soundPlayer;
				if(snarePlayer.isPlaying()){
					soundPlayer = snarePlayer1;
				}else{
					soundPlayer = snarePlayer;
				}
				soundPlayer.start();
				}
			}
		},mApp);

		RelativeLayout rtl = (RelativeLayout)findViewById(R.id.rtl);
		OnTouchListener rtl_otl = new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				//give a sound of kick
				if(pressed == true){
					MediaPlayer soundPlayer;
					if(kickPlayer.isPlaying()){
						soundPlayer = kickPlayer1;
					}else{
						soundPlayer = kickPlayer;
					}
					soundPlayer.start();
				}
				return false;
			}
			
		};
		rtl.setOnTouchListener(rtl_otl);
		
		//click play button
		play_button = (Button) findViewById(R.id.play);
		play_button.setBackgroundResource(R.drawable.start);
		OnClickListener play_button_on_click_listener = new OnClickListener()
        {     	
			public void onClick(View v){
				if(pressed == false){
					play_button.setBackgroundResource(R.drawable.stop);
					mSensorManager.registerListener(mSensorListener,
					        mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
					        SensorManager.SENSOR_DELAY_GAME);
					pressed = true;
				}else{
					play_button.setBackgroundResource(R.drawable.start);
					mSensorManager.unregisterListener(mSensorListener);
					pressed = false;
					ShakeCounter = 0;
					RotateCounter = 0;
				}
				startPlayer.start();
				
			}
        };
        play_button.setOnClickListener(play_button_on_click_listener);
        
	
    Button return_button = (Button) findViewById(R.id.returns);
    return_button.setBackgroundResource(R.drawable.res);
    OnClickListener return_button_on_click_listener = new OnClickListener()
    {
		public void onClick(View v)
		{
			Intent intent = new Intent(getBaseContext(), MainActivity.class);
			returnPlayer.start();
			startActivity(intent);
		}
    };
    return_button.setOnClickListener(return_button_on_click_listener);
    Toast.makeText(getBaseContext(), "Test the sensitivity of Hihat & Snare in here", Toast.LENGTH_SHORT).show();
    Toast.makeText(getBaseContext(), "Tap the screen to make kick", Toast.LENGTH_SHORT).show();
    Toast.makeText(getBaseContext(), "Press start!", Toast.LENGTH_SHORT).show();
	
	}
    
	 protected void onDestroy(){
			mSensorManager.unregisterListener(mSensorListener);
	        super.onDestroy();   
	     }
	protected void onResume(){
		super.onResume();

	}
	protected void onPause(){
		super.onPause();
	}


    
}
