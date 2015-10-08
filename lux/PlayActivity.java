package gpapp.hku.lux;





import java.util.Random;

import gpapp.hku.lux.MotionEventListener;
import gpapp.hku.lux.PlayService.PlayBinder;
import gpapp.hku.lux.R;
import gpapp.hku.lux.PlayService;
import android.app.Dialog;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.GestureDetectorCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

public class PlayActivity extends Activity implements OnGestureListener {
	
	private SeekBar sb1,sb2;
	private TextView tv1, tv0;
	ImageView chosenSongView; 
	private int hihat_sensa, snare_sensa;
	private RadioGroup rgp;
	private RadioButton cb1,cb2,cb3,cb4;
	private String played_song ="1";
	private RadioButton clickedbtn;
	private MediaPlayer hihatPlayer;
	private MediaPlayer snarePlayer;
	private PlayService mService;
	private boolean is_bound = false;
	private Button play_button;
	private Dialog playing;
	private int chosenSongNumber = 0;
	private String chosenSong = "AirCord";
	private GestureDetectorCompat mDetector;
	private MediaPlayer startPlayer;
	private MediaPlayer returnPlayer;
	private MyApplication mApp;
    static private final Uri stat_provider = CreditContract.Credits.CONTENT_URI;
    static private String log_tag = "lux";
	
	/*	private ServiceConnection mConnection = new ServiceConnection(){


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
		setContentView(R.layout.play);
/*		rgp = (RadioGroup)findViewById(R.id.rgp);
		cb1= (RadioButton)findViewById(R.id.cb1);
		cb2= (RadioButton)findViewById(R.id.cb2);
		cb3= (RadioButton)findViewById(R.id.cb3);
		cb4= (RadioButton)findViewById(R.id.cb4); */
		//
		hihat_sensa = snare_sensa = 50;
		//tv1= (TextView)findViewById(R.id.tv1);
		chosenSongView = (ImageView)findViewById(R.id.chosenSong);
		//tv0= (TextView)findViewById(R.id.tv0);
		//set name of songs
/*		cb1.setText("AirCord");
		cb2.setText("AmbientKingdom");
		cb3.setText("ElkLand");
		cb4.setText("ArcOfDawn");
		cb1.setTextColor(Color.WHITE);
		cb2.setTextColor(Color.WHITE);
		cb3.setTextColor(Color.WHITE);
		cb4.setTextColor(Color.WHITE);
		tv1.setTextColor(Color.WHITE); */
		mApp = (MyApplication)this.getApplication();
		mDetector = new GestureDetectorCompat(this,this);
		chosenSongView.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				mDetector.onTouchEvent(arg1);
				return true;
			}
			
		});
	/*	Intent intent = new Intent(getBaseContext(),PlayService.class);
		if(!bindService(intent,mConnection,Context.BIND_AUTO_CREATE)){
			Toast.makeText(getBaseContext(), "Bind Service fail", Toast.LENGTH_SHORT).show();
		}*/
		hihatPlayer = MediaPlayer.create(this, R.raw.hihat);
		if(hihatPlayer == null){
			Toast.makeText(getBaseContext(), "Create hihatPlayer fail", Toast.LENGTH_SHORT).show();
		}
		snarePlayer = MediaPlayer.create(this, R.raw.snare);
		if(snarePlayer == null){
			Toast.makeText(getBaseContext(), "Create snarePlayer fail", Toast.LENGTH_SHORT).show();
		}
		startPlayer = MediaPlayer.create(this, R.raw.start_music);
		returnPlayer = MediaPlayer.create(this, R.raw.return_music);
		returnPlayer.setVolume(mApp.getSoundEffect(), mApp.getSoundEffect());
		startPlayer.setVolume(mApp.getSoundEffect(), mApp.getSoundEffect());
		//click play button
		play_button = (Button) findViewById(R.id.play);
		play_button.setBackgroundResource(R.drawable.start);
		OnClickListener play_button_on_click_listener = new OnClickListener()
        {     	
			public void onClick(View v){
				Intent intent = new Intent(getBaseContext(), GameActivity.class);
				intent.putExtra("gpapp.hku.lux.Acc", 0);
				startPlayer.start();
				intent.putExtra("gpapp.hku.lux.Song", chosenSong);
				Log.i("Intent",intent.getStringExtra("gpapp.hku.lux.Song"));
				startActivity(intent);
	/*		if(rgp.getCheckedRadioButtonId()!=-1){
				//get the name of the song
				clickedbtn = (RadioButton)findViewById(rgp.getCheckedRadioButtonId());
				played_song = clickedbtn.getText().toString();
				if(!mService.isPlaying()){
					startPlayer.start();
					mService.onClick(played_song);
					playing = new Dialog(PlayActivity.this);
					playing.setContentView(R.layout.playing_dialog);
					playing.setTitle(played_song);
					//mSensorManager.unregisterListener(mSensorListener);
					Button stopBtn = (Button) playing.findViewById(R.id.dialog_StopBtn);
					stopBtn.setText("Stop");
					TextView dialogTextView = (TextView) playing.findViewById(R.id.dialog_TextView);
					dialogTextView.setText("Repeat after the computer in each bar");
					stopBtn.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							//mService.StopPlaying();
							playing.cancel();
						}
						
					});
					playing.setOnCancelListener(new OnCancelListener(){

						@Override
						public void onCancel(DialogInterface arg0) {
							// TODO Auto-generated method stub
							float percent = (Math.abs(200-mService.getAccuracy()) / (float)200) * 100;
							int accuracy = mService.getAccuracy();
							mService.StopPlaying();
							//int percent = 20;
							//Random random = new Random();
							//percent = 20 + random.nextInt(70);
							Log.i("percentage",String.valueOf(percent));
							Toast.makeText(getBaseContext(), "Your value is " + Integer.toString(accuracy), Toast.LENGTH_LONG).show();
							Toast.makeText(getBaseContext(), "Your accuracy is " + Integer.toString((int)percent) + "% !", Toast.LENGTH_LONG).show();
							
						}
						
					});
					mService.getDialog(playing);
					playing.show();
					
				}else{
					Toast.makeText(getBaseContext(), "Please select a song!", Toast.LENGTH_SHORT).show();
				}
				//Intent intent = new Intent(getBaseContext(), PlayActivity.class);
				//startActivity(intent);
			} */
				
				
				
			}
				
			
        };
        play_button.setOnClickListener(play_button_on_click_listener);
        
        
        //click return button
        Button return_button = (Button) findViewById(R.id.returns);
        return_button.setBackgroundResource(R.drawable.res);
        OnClickListener return_button_on_click_listener = new OnClickListener(){
			public void onClick(View v){	
				Intent intent = new Intent(getBaseContext(), MainActivity.class);
				returnPlayer.start();
				startActivity(intent);
			}
        };
        return_button.setOnClickListener(return_button_on_click_listener);
      
        Intent intent = getIntent();
        Bundle getAcc = intent.getExtras();
        if(getAcc!=null){
    	String acc ="";
    	acc = getAcc.getString("gpapp.hku.lux.Acc");
		String cid="";
		cid = chosenSong.toString();
		Cursor cursor = find(cid);
	
		if((cursor == null) || (cursor.getCount() <= 0)){
			insert(acc,cid);
			Toast.makeText(getBaseContext(), "It is the best result!!", Toast.LENGTH_SHORT).show();
		}
		else{
				if(cursor.moveToFirst()){	
					do{
				final String bestacc =cursor.getString(cursor.getColumnIndex(CreditContract.Credits.Acc));
				if(Integer.valueOf(acc) >= Integer.valueOf(bestacc)){	
						//update
						acc = bestacc;
						update(acc,cid);
					//	Toast.makeText(getBaseContext(), num_of_rows_updated + " "+ acc +" updated ", Toast.LENGTH_SHORT).show();
								};
								//display
				Toast.makeText(getBaseContext(),"The best  accuracy of " + cid + "is: " + bestacc, Toast.LENGTH_SHORT).show();
					}
				while(cursor.moveToNext());
				}
		}
		
		tv0 = (TextView)findViewById(R.id.BestAcc);    
			tv0.setText(acc);
			
        }
	}	
	
	
	 protected void onDestroy(){
			//mSensorManager.unregisterListener(mSensorListener);
	        super.onDestroy();   
	     }
	protected void onResume(){
		super.onResume();
		/*mSensorManager.registerListener(mSensorListener,
		        mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
		        SensorManager.SENSOR_DELAY_GAME);*/
	}
	protected void onPause(){
		//mSensorManager.unregisterListener(mSensorListener);
		super.onPause();
	}


	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if(e2.getX() < e1.getX()){
			//swipe to right
			chosenSongNumber ++;
			if(chosenSongNumber == 1){
				chosenSong = "AmbientKingdom";
				chosenSongView.setImageResource(R.drawable.ambientkingdom);
			}
			if(chosenSongNumber == 2){
				chosenSong = "ElkLand";
				chosenSongView.setImageResource(R.drawable.ekland);
			}
			if(chosenSongNumber == 3){
				chosenSong = "ArcOfDawn";
				chosenSongView.setImageResource(R.drawable.arcofdawn);
			}
		}else{
			//swipe to left
			if(chosenSongNumber > 0)
				chosenSongNumber --;
			if(chosenSongNumber == 0){
				chosenSong = "AirCord";
				chosenSongView.setImageResource(R.drawable.aircord);
			}
			if(chosenSongNumber == 1){
				chosenSong = "AmbientKingdom";
				chosenSongView.setImageResource(R.drawable.ambientkingdom);
			}
			if(chosenSongNumber == 2){
				chosenSong = "ElkLand";
				chosenSongView.setImageResource(R.drawable.ekland);
			}
		}
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}


private Cursor find(String cid){
	Cursor cursor = null;
	if(TextUtils.isEmpty(cid)){
		cursor = null;
	}
	else{
    	String selection_clause = CreditContract.Credits.cID + " = ?";
    	String[] selection_args = {""};
    	selection_args[0] = cid;
    	String sortOrder = CreditContract.Credits.Acc + " DESC";
    	
		cursor = getContentResolver().query(stat_provider, null, selection_clause, selection_args, sortOrder);
	}
	
	return cursor;
}

private int insert(String accu_rate, String songname)
{
	int num_rows_updated = 0;
	
	ContentValues content_values = new ContentValues();
	content_values.put(CreditContract.Credits.Acc, accu_rate);
	content_values.put(CreditContract.Credits.cID, songname);
	
	try
	{
		getContentResolver().insert(stat_provider, content_values);
		num_rows_updated++;
	}
	catch(Exception e)
	{
		final String error_message = "error in inserting " + songname;
		Toast.makeText(getBaseContext(), error_message, Toast.LENGTH_SHORT).show();
		Log.e(log_tag, error_message);
	}
	
	return num_rows_updated;
}

private int update(String acc, String cid)
{
	Cursor cursor = find(cid);
	
	int num_rows_updated = 0;
	
	if((cursor == null) || (cursor.getCount() <= 0))
	{
		if(TextUtils.isEmpty(cid))
		{
			
		}
		else
		{
			num_rows_updated = insert(acc, cid);
		}
	}
	else
	{
		ContentValues update_values = new ContentValues();
		update_values.put(CreditContract.Credits.Acc, acc);
		update_values.put(CreditContract.Credits.cID, cid);

		try
		{
			String selection_clause = CreditContract.Credits.cID + " = ?";
			String[] selection_args = {""};
			selection_args[0] = cid;

			num_rows_updated = getContentResolver().update(stat_provider, update_values, selection_clause, selection_args);
		}
		catch(Exception e)
		{
			final String error_message = "error in updating " + cid;
			Toast.makeText(getBaseContext(), error_message, Toast.LENGTH_SHORT).show();
			Log.e(log_tag, error_message);
		}
	}
	
	return num_rows_updated;
}
}
