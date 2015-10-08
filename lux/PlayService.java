package gpapp.hku.lux;

import java.io.IOException;

import gpapp.hku.lux.R;
import gpapp.hku.lux.PlayService;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class PlayService extends IntentService {


	public PlayService() {
		super("PlayService");
		// TODO Auto-generated constructor stub
	}
	private final IBinder mBinder = new PlayBinder();
	private int[] Kingdom_whenToPlay = {0,666,1332,1998,2664,3330,3996,4662,5328,6660,7992,9324,10656,11988,13320,14652,15984,16650,17316,18648,19314,19980,21312,21978,22644,23310,23976,24642,25308,25974,26640,27306,27972,28638,29304,29970,30636,31302,31968,32634,33300,33966,34632,35298,35964,36630};
	private int[] Kingdom_whatToPlay = {5,6,6,6,5,6,6,6,3,3,1,1,3,4,1,2,3,3,4,1,1,2,3,3,4,3,1,1,2,1,3,3,4,3,1,1,2,1,3,3,3,3,1,1,1,1};
	private int[] AirCord_whenToPlay = {3706,4167,4628,5089,5550,6011,6472,6933,7394,7855,8316,8777,9238,9699,10160,10621,11082,11543,12004,12465,12926,13387,13848,14309,14770,15230,15690,15920,16150,16610,17070,17530,17760,17990,18450,18910,19370,19600,19830,20290,20750,21210,21440,21670,22130,22590,22820,23280,23510,23970,24430,24660,25120,25350,25810,26270,26500,26960,27190,27650,28110,28340,28800,29030,29490,29950,30410,30640,30870,31330,31790,32250,32480,32710,33170,33630,34090,34320,34550,35010,35470,35930,36160,36390,36850,37080,37310,37540,38000,38230,38460,38690,38920,39150,39380,39840,40070,40300,40530,40760,40990,41220,41680,41910,42140,42370,42600,42830,43060,43520,43750,43980,44210,44440,44670,44900,45130,45360,45590,45820,46050,46280,46510,46740,46970,47200,47430,47660,47890,48120,48350,48580,48810,49040,49270,49500,49730,49960,50190,50420,50650,50880,51110,51340};
	private int[] AirCord_whatToPlay = {5,6,6,6,5,6,6,6,3,3,3,3,1,1,1,1,3,3,3,3,1,1,1,1,3,3,3,3,3,1,1,1,1,1,3,3,3,4,3,1,1,1,2,1,3,4,3,3,4,1,2,1,1,2,3,4,3,3,4,1,2,1,1,2,3,4,3,3,4,1,2,1,1,2,3,4,3,3,4,1,2,1,1,2,3,3,4,3,3,4,3,1,1,2,1,1,2,1,3,3,4,3,3,4,3,1,1,2,1,1,2,1,3,3,4,3,3,3,4,3,1,1,2,1,1,1,2,1,3,3,4,3,3,3,4,3,1,1,2,1,1,1,2,1};
	private int[] Elkland_whatToPlay = {5,6,6,6,5,6,6,6,3,3,3,3,1,1,1,1,3,3,3,3,1,1,1,1,4,3,3,3,2,1,1,1,3,4,3,4,1,2,1,2,3,4,3,4,1,2,1,2,3,3,4,3,3,3,4,3,1,1,2,1,1,1,2,1,3,3,4,3,3,3,4,3,1,1,2,1,1,1,2,1,3,3,3,3,3,1,1,1,1,1,3,3,3,3,3,1,1,1,1,1,3,3,3,3,3,3,3,3,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,1,1,1,1,1,1,1,1,3,3,3,3,3,4,1,1,1,1,1,2,3,3,3,3,3,4,1,1,1,1,1,2,3,3,4,3,4,3,1,1,2,1,2,1,3,3,4,3,4,3,1,1,2,1,2,1};
	private int[] Elkland_whenToPlay = {0,857,1714,2571,3428,4285,5142,5999,6856,7713,8570,9427,10284,11141,11998,12855,13712,14569,15426,16283,17140,17997,18854,19711,20568,21425,22282,23139,23996,24853,25710,26567,27424,28281,29138,29995,30852,31709,32566,33423,34280,35137,35994,36851,37708,38565,39422,40279,41136,41564,41992,42420,42848,43276,43704,44132,44560,44988,45416,45844,46272,46700,47128,47556,47984,48412,48840,49268,49696,50124,50552,50980,51408,51836,52264,52692,53120,53548,53976,54404,54832,55688,56544,56972,57400,58256,59112,59968,60396,60824,61680,62536,63392,63820,64248,65104,65960,66816,67244,67672,68528,68956,69384,69812,70240,70668,71096,71524,71952,72380,72808,73236,73664,74092,74520,74948,75376,75804,76232,76660,77088,77516,77944,78372,78800,79228,79656,80084,80512,80940,81368,81796,82224,82652,83080,83508,83936,84792,85648,86076,86504,86932,87360,88216,89072,89500,89928,90356,90784,91640,92496,92924,93352,93780,94208,95064,95920,96348,96776,97204,98488,98916,99344,99772,100200,100628,101912,102340,102768,103196,103624,104052,105336,105764,106192,106620,107048,107476,108760,109188};
	private int[] ArcOfDawn_whatToPlay ={5,6,6,6,5,6,6,6,3,1,3,1,3,1,3,1,3,3,1,1,3,3,1,1,3,3,1,1,3,3,1,1,4,4,2,2,4,4,2,2,4,4,2,2,4,4,2,2,3,3,3,1,1,1,3,3,3,1,1,1,3,3,1,1,3,3,1,1,3,3,3,1,1,1,3,3,3,1,1,1,3,3,1,1,3,3,1,1,3,3,4,3,3,4,1,1,2,1,1,2,3,3,4,3,3,4,1,1,2,1,1,2,3,3,4,3,3,4,1,1,2,1,1,2,3,3,4,3,3,4,1,1,2,1,1,2,4,4,2,2,4,4,2,2,4,4,2,2,4,4,2,2,3,4,3,4,1,2,1,2,3,4,3,4,1,2,1,2,3,4,3,4,1,2,1,2,3,4,3,4,1,2,1,2,3,3,4,3,3,4,1,1,2,1,1,2,3,3,4,3,3,4,1,1,2,1,1,2,3,3,4,3,3,4,1,1,2,1,1,2,3,3,4,3,3,4,1,1,2,1,1,2};
	private int[] ArcOfDawn_whenToPlay ={12036,12536,13036,13536,14036,14536,15036,15536,16036,18036,20036,22036,24036,26036,28036,30036,32036,33036,34036,35036,36036,37036,38036,39036,40036,41036,42036,43036,44036,45036,46036,47036,48536,49536,50536,51536,52536,53536,54536,55536,56536,57536,58536,59536,60536,61536,62536,63536,64036,64786,65536,66036,66786,67536,68036,68786,69536,70036,70786,71536,72036,73036,74036,75036,76036,77536,78036,79536,80036,80786,81536,82036,82786,83536,84036,84786,85536,86036,86786,87536,88036,89036,90036,91036,92036,93536,94036,95536,96036,96286,96536,97036,97286,97536,98036,98286,98536,99036,99286,99536,100036,100286,100536,101036,101286,101536,102036,102286,102536,103036,103286,103536,104036,104286,104536,105036,105286,105536,106036,106286,106536,107036,107286,107536,108036,108286,108536,109036,109286,109536,110036,110286,110536,111036,111286,111536,112536,113536,114536,115536,116536,117536,118536,119536,120536,121536,122536,123536,124536,125536,126536,127536,128036,128536,129036,129536,130036,130536,131036,131536,132036,132536,133036,133536,134036,134536,135036,135536,136036,136536,137036,137536,138036,138536,139036,139536,140036,140536,141036,141536,142036,142536,143036,143536,144036,144286,144536,145036,145286,145536,146036,146286,146536,147036,147286,147536,148036,148286,148536,149036,149286,149536,150036,150286,150536,151036,151286,151536,152036,152286,152536,153036,153286,153536,154036,154286,154536,155036,155286,155536,156036,156286,156536,157036,157286,157536,158036,158286,158536,159036,159286,159536};
	private int[] whenToPlay;
	private int[] whatToPlay;
	private int accuracy;
	private Handler mHandler = new Handler();
	private Handler removeHandler = new Handler();
	private long currentTime; 
	private long whenBegin;
	private MediaPlayer hihatPlayer;
	private MediaPlayer snarePlayer;
	private MediaPlayer beepPlayer;
	private MediaPlayer high_beepPlayer;
	private MediaPlayer kingdomPlayer;
	private MediaPlayer aircordPlayer;
	private MediaPlayer elklandPlayer;
	private MediaPlayer bgPlayer;
	private boolean isPlaying = false;
	private boolean TimeToShake = false;
	private boolean TimeToRotate = false;
	private boolean didShake = false;
	private boolean didRotate = false;
	private Button startBtn;
	private Dialog playingDialog;
	private int NextNote;
	private SensorManager mSensorManager;
	private MotionEventListener mSensorListener;
	private int ShakeCounter = 0;
	private int RotateCounter = 0;
	private int NotShakeCounter = 0;
	private int NotRotateCounter = 0;
	private MyApplication mApp;
	private Runnable initShake = new Runnable(){
		public void run(){
			didShake = false;
		}
	};
	private Runnable initRotate = new Runnable(){
		public void run(){
			didRotate = false;
		}
	};
	private Runnable playHighBeep = new Runnable(){
		@Override
		public void run() {
			if(isPlaying)
				high_beepPlayer.start();
			if(NextNote < whenToPlay.length -1){
				NextNote++;
			}else{
				mSensorManager.unregisterListener(mSensorListener);
				Log.i("FinalBefore", String.valueOf(accuracy));
				accuracy = accuracy/(ShakeCounter + RotateCounter);
				Log.i("Final", String.valueOf(accuracy));
				playingDialog.cancel();
				isPlaying = false;
			}
		}
		
	};
	private Runnable playBeep = new Runnable(){

		@Override
		public void run() {
			if(isPlaying)
			beepPlayer.start();
			if(NextNote < whenToPlay.length -1){
				NextNote++;
			}else{
				mSensorManager.unregisterListener(mSensorListener);
				Log.i("FinalBefore", String.valueOf(accuracy));
				accuracy = accuracy/(ShakeCounter + RotateCounter);
				Log.i("Final", String.valueOf(accuracy));
				playingDialog.cancel();
				isPlaying = false;
			}
		}
		
	};
	private Runnable playHihat = new Runnable(){

		@Override
		public void run() {
			if(isPlaying)
			hihatPlayer.start();
			if(NextNote < whenToPlay.length -1){
				NextNote++;
			}else{
				mSensorManager.unregisterListener(mSensorListener);
				Log.i("FinalBefore", String.valueOf(accuracy));
				accuracy = accuracy/(ShakeCounter + RotateCounter);
				Log.i("Final", String.valueOf(accuracy));
				playingDialog.cancel();
				isPlaying = false;
			}
		}
		
	};
	private Runnable playSnare = new Runnable(){

		@Override
		public void run() {
			if(isPlaying)
			snarePlayer.start();
			if(NextNote < whenToPlay.length -1){
				NextNote++;
			}else{
				mSensorManager.unregisterListener(mSensorListener);
				Log.i("FinalBefore", String.valueOf(accuracy));
				accuracy = accuracy/(ShakeCounter + RotateCounter);
				Log.i("Final", String.valueOf(accuracy));
				playingDialog.cancel();
				isPlaying = false;
			}
		}
		
	};
	private Runnable playerSnare = new Runnable(){

		@Override
		public void run() {
			new Thread(
					new Runnable(){
						public void run(){
			didRotate = false;
			if(isPlaying)
			TimeToRotate = true;
			while(!didRotate){
				
			}
			if(NextNote < whenToPlay.length - 1){
				NextNote++;
			}else{
				mSensorManager.unregisterListener(mSensorListener);
				//Toast.makeText(getBaseContext(), "NextNote: " + NextNote + " WhenToPlay:" + whenToPlay.length, Toast.LENGTH_SHORT).show();
				Log.i("FinalBefore", String.valueOf(accuracy));
				accuracy = accuracy/(ShakeCounter + RotateCounter);
				Log.i("Final", String.valueOf(accuracy));
				playingDialog.cancel();
				isPlaying = false;
			}
			
						}
					}
				).start();
				
		}
		
	};
	private Runnable playerHihat = new Runnable(){

		@Override
		public void run() {
			new Thread(
				new Runnable(){
					public void run(){
			didShake = false;
			if(isPlaying)
			TimeToShake = true;
			while(!didShake){
				
			}
			if(NextNote < whenToPlay.length -1){
				NextNote++;
			}else{
				mSensorManager.unregisterListener(mSensorListener);
				//Toast.makeText(getBaseContext(), "NextNote: " + NextNote + " WhenToPlay:" + whenToPlay.length, Toast.LENGTH_SHORT).show();
				Log.i("FinalBefore", String.valueOf(accuracy));
				accuracy = accuracy/(ShakeCounter + RotateCounter);
				Log.i("Final", String.valueOf(accuracy));
				Log.i("Shake+RotateCounter", String.valueOf(ShakeCounter + RotateCounter));
				playingDialog.cancel();
				isPlaying = false;
			}
					}
				}
			).start();
			
		}
		
	};
	private Runnable playerNotHihat = new Runnable(){

		@Override
		public void run() {
			if(isPlaying){
				TimeToShake = false;
				if(didShake == false && NextNote < whenToPlay.length){
					accuracy += 200;
					NotShakeCounter++;
					Log.i("NotHiHatCounter", String.valueOf(NotShakeCounter));
					Log.i("NotHihat", String.valueOf(accuracy));
					didShake = true;
					mHandler.postDelayed(initShake, 50);
				}else{
					didShake = false;
				}
				//NotShakeCounter++;
				//Log.i("NotHiHatCounter", String.valueOf(NotShakeCounter));
			}
		}
		
	};
	private Runnable playerNotSnare = new Runnable(){

		@Override
		public void run() {
			if(isPlaying){
				TimeToRotate = false;
				if(didRotate == false && NextNote < whenToPlay.length){
					accuracy += 200;
					NotRotateCounter++;
					Log.i("NotSnareCounter", String.valueOf(NotRotateCounter));
					Log.i("NotSnare", String.valueOf(accuracy));
					didRotate = true;
					mHandler.postDelayed(initRotate, 50);
				}else{
					didRotate = false;
				}
				//NotRotateCounter++;
				//Log.i("NotSnareCounter", String.valueOf(NotRotateCounter));
			}
		}
		
	};
	public int onStartCommand(Intent intent,int flags,int startId){
		super.onStartCommand(intent, flags, startId);
		return START_NOT_STICKY;
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		NextNote = 0;
		mApp = (MyApplication) this.getApplication();
		hihatPlayer = MediaPlayer.create(this, R.raw.hihat);
		if(hihatPlayer == null)
			Toast.makeText(getBaseContext(), "hihat sucks", Toast.LENGTH_SHORT).show();
		snarePlayer = MediaPlayer.create(this, R.raw.snare);
		if(snarePlayer == null)
			Toast.makeText(getBaseContext(), "snare sucks", Toast.LENGTH_SHORT).show();
		beepPlayer = MediaPlayer.create(this, R.raw.beep);
		high_beepPlayer = MediaPlayer.create(this, R.raw.beep_high);
		aircordPlayer = MediaPlayer.create(this, R.raw.aircord);
		kingdomPlayer = MediaPlayer.create(this, R.raw.ambientkingdom);
		elklandPlayer = MediaPlayer.create(this, R.raw.elkland);
		snarePlayer.setVolume(mApp.getSoundEffect(), mApp.getSoundEffect());
		hihatPlayer.setVolume(mApp.getSoundEffect(), mApp.getSoundEffect());
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorListener = new MotionEventListener();
		mSensorListener.setOnMotionListener(new MotionEventListener.OnMotionListener() {
			
			@Override
			public void onShake() {
				// TODO Auto-generated method stub
				//Toast.makeText(getBaseContext(), "Rotate! " + "The "+ ShakeCounter +" times", Toast.LENGTH_SHORT).show();
				hihatPlayer.start();
				if(shouldShake()){
					currentTime = System.currentTimeMillis();
					didShake = true;
					if(Math.abs(currentTime - (whenToPlay[NextNote] + whenBegin) )>= 200){
						accuracy += 200;
						Log.i("ShakeTimeOut", String.valueOf(accuracy));
					}else{
						//accuracy += 0;
						accuracy += Math.abs(currentTime - (whenToPlay[NextNote] + whenBegin));
						Log.i("ShakeInTime", String.valueOf(accuracy));
					}
					
					
					NotShake();
				}
			}

			@Override
			public void onRotate() {
				// TODO Auto-generated method stub
				//Toast.makeText(getBaseContext(), "Rotate! " + "The "+ RotateCounter +" times", Toast.LENGTH_SHORT).show();
				snarePlayer.start();
				if(shouldRotate()){
					currentTime = System.currentTimeMillis();
					didRotate = true;
					if(Math.abs(currentTime - (whenToPlay[NextNote] + whenBegin) )>= 200){
						accuracy += 200;
						Log.i("RotateTimeOut", String.valueOf(accuracy));
					}else{
						//accuracy += 0;
						accuracy += Math.abs(currentTime - (whenToPlay[NextNote] + whenBegin));
						Log.i("RotateInTime", String.valueOf(accuracy));
					}
					
					NotRotate();
				}
			}
		},mApp);
		return mBinder;
	}
	
	public class PlayBinder extends Binder{
		PlayService getService(Button start){
			startBtn = start;
			return PlayService.this;
		}
	}
	public void onDestroy(){
		hihatPlayer.release();
		snarePlayer.release();
		beepPlayer.release();
		high_beepPlayer.release();
		kingdomPlayer.release();
		aircordPlayer.release();
		elklandPlayer.release();
		bgPlayer.release();
		mSensorManager.unregisterListener(mSensorListener);
	}
	public boolean onUnbind(Intent intent){
		hihatPlayer.release();
		snarePlayer.release();
		beepPlayer.release();
		high_beepPlayer.release();
		kingdomPlayer.release();
		aircordPlayer.release();
		elklandPlayer.release();
		bgPlayer.release();
		mSensorManager.unregisterListener(mSensorListener);
		return false;
	}
	public void getDialog(Dialog playing){
		playingDialog = playing;
	}
	public int getAccuracy(){
		return accuracy;
	}
	public void NotShake(){
		TimeToShake = false;
	}
	public void NotRotate(){
		TimeToRotate = false;
	}
	public boolean shouldShake(){
		return TimeToShake;
	}
	public boolean shouldRotate(){
		return TimeToRotate;
	}
	public boolean isPlaying(){
		return isPlaying;
	}
	public void StopPlaying(){
		mHandler.removeCallbacksAndMessages(null);
		mSensorManager.unregisterListener(mSensorListener);
		if(bgPlayer.isPlaying()){
			bgPlayer.stop();
			bgPlayer.release();
		}
		isPlaying = false;
	}
	public void ResumePlaying(){
		mSensorManager.registerListener(mSensorListener,
		        mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
		        SensorManager.SENSOR_DELAY_GAME);
		isPlaying = true;
		whenBegin = SystemClock.currentThreadTimeMillis();
		for(int i = NextNote;i<whenToPlay.length;i++)
		{
			if(whatToPlay[i] == 1){
				mHandler.postDelayed(playerHihat, whenToPlay[i]);
				mHandler.postDelayed(playerNotHihat, whenToPlay[i] + 300);
			}else{
				if(whatToPlay[i] == 2){
					mHandler.postDelayed(playerSnare, whenToPlay[i]);
					mHandler.postDelayed(playerNotSnare, whenToPlay[i] + 300);
					
				}else{
					if(whatToPlay[i] == 3){
						mHandler.postDelayed(playHihat, whenToPlay[i]);
					}else{
						if(whatToPlay[i] == 4){
							mHandler.postDelayed(playSnare, whenToPlay[i]);
						}else{
							if(whatToPlay[i] == 5){
								mHandler.postDelayed(playHighBeep, whenToPlay[i]);
								
							}else{
								if(whatToPlay[i] == 6){
									mHandler.postDelayed(playBeep, whenToPlay[i]);
									
								}
							}
						}
					}
				}
			}
		}
	}
	public void onClick(String song){
		whenBegin = System.currentTimeMillis();
		NextNote = 0;
		ShakeCounter = 0;
		RotateCounter = 0;
		NotShakeCounter = 0;
		NotRotateCounter = 0;
		accuracy = 0;
		didShake = false;
		didRotate = false;
		mSensorManager.registerListener(mSensorListener,
		        mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
		        SensorManager.SENSOR_DELAY_GAME);
		if(song.equals("AirCord")){
			whenToPlay = AirCord_whenToPlay.clone();
			whatToPlay = AirCord_whatToPlay.clone();
			bgPlayer = MediaPlayer.create(this, R.raw.aircord);
			bgPlayer.setVolume(mApp.getBgMusic(), mApp.getBgMusic());
			bgPlayer.start();
		}else{
			if(song.equals("AmbientKingdom")){
				whenToPlay = Kingdom_whenToPlay.clone();
				whatToPlay = Kingdom_whatToPlay.clone();
				bgPlayer = MediaPlayer.create(this, R.raw.ambientkingdom);
				bgPlayer.setVolume(mApp.getBgMusic(), mApp.getBgMusic());
				bgPlayer.start();
			}else{
				if(song.equals("ElkLand")){
					whenToPlay = Elkland_whenToPlay.clone();
					whatToPlay = Elkland_whatToPlay.clone();
					bgPlayer = MediaPlayer.create(this, R.raw.elkland);
					bgPlayer.setVolume(mApp.getBgMusic(), mApp.getBgMusic());
					bgPlayer.start();
				}else{
					if(song.equals("ArcOfDawn")){
						whenToPlay = ArcOfDawn_whenToPlay.clone();
						whatToPlay = ArcOfDawn_whatToPlay.clone();
						bgPlayer = MediaPlayer.create(this, R.raw.arcofdawn);
						bgPlayer.setVolume(mApp.getBgMusic(), mApp.getBgMusic());
						bgPlayer.start();
						
					}
				}
			}
		}
		isPlaying = true;
		whenBegin = System.currentTimeMillis();
		for(int i = 0;i<whenToPlay.length;i++)
		{
			if(whatToPlay[i] == 1){
				mHandler.postDelayed(playerHihat, whenToPlay[i] - 200);
					mHandler.postDelayed(playerNotHihat, whenToPlay[i] + 200);
				ShakeCounter++;
			}else{
				if(whatToPlay[i] == 2){
					mHandler.postDelayed(playerSnare, whenToPlay[i] - 200);
						mHandler.postDelayed(playerNotSnare, whenToPlay[i] + 200);
					RotateCounter++;
				}else{
					if(whatToPlay[i] == 3){
						mHandler.postDelayed(playHihat, whenToPlay[i]);
					}else{
						if(whatToPlay[i] == 4){
							mHandler.postDelayed(playSnare, whenToPlay[i]);
						}else{
							if(whatToPlay[i] == 5){
								mHandler.postDelayed(playHighBeep, whenToPlay[i]);
								
							}else{
								if(whatToPlay[i] == 6){
									mHandler.postDelayed(playBeep, whenToPlay[i]);
									
								}
							}
						}
					}
				}
			}
		}
		Log.i("ShakeCounter",String.valueOf(ShakeCounter));
		Log.i("RotateCounter",String.valueOf(RotateCounter));
	}
	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		
	}
}
