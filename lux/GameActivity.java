package gpapp.hku.lux;

import java.util.ArrayList;




import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class GameActivity extends Activity { 
	private static int currentBar = 0;
	private static ArrayList<Bar> barList;
	private static RectF rectF;
	private static Paint p;
	private static int startDraw = 0;
	private static int endDraw = 0;
	private static boolean onDrawFinished = true;
	private static boolean lastOneFinished = true;
	private myView view;
	private static int degree;
	private SurfaceHolder viewHolder;
	private int[] Kingdom_Bar = {4,8,10,12,14,16,19,22,26,30,34,38,42,46};
	private int[] Kingdom_whenToPlay = {0,666,1332,1998,2664,3330,3996,4662,5328,6660,7992,9324,10656,11988,13320,14652,15984,16650,17316,18648,19314,19980,21312,21978,22644,23310,23976,24642,25308,25974,26640,27306,27972,28638,29304,29970,30636,31302,31968,32634,33300,33966,34632,35298,35964,36630};
	private int[] Kingdom_whatToPlay = {5,6,6,6,5,6,6,6,3,3,1,1,3,4,1,2,3,3,4,1,1,2,3,3,4,3,1,1,2,1,3,3,4,3,1,1,2,1,3,3,3,3,1,1,1,1};
	private int[] AirCord_Bar = {4,8,12,16,20,24,29,34,39,44,49,54,59,64,69,74,79,84,91,98,105,112,120,128,136,144};
	private int[] AirCord_whenToPlay = {3706,4167,4628,5089,5550,6011,6472,6933,7394,7855,8316,8777,9238,9699,10160,10621,11082,11543,12004,12465,12926,13387,13848,14309,14770,15230,15690,15920,16150,16610,17070,17530,17760,17990,18450,18910,19370,19600,19830,20290,20750,21210,21440,21670,22130,22590,22820,23280,23510,23970,24430,24660,25120,25350,25810,26270,26500,26960,27190,27650,28110,28340,28800,29030,29490,29950,30410,30640,30870,31330,31790,32250,32480,32710,33170,33630,34090,34320,34550,35010,35470,35930,36160,36390,36850,37080,37310,37540,38000,38230,38460,38690,38920,39150,39380,39840,40070,40300,40530,40760,40990,41220,41680,41910,42140,42370,42600,42830,43060,43520,43750,43980,44210,44440,44670,44900,45130,45360,45590,45820,46050,46280,46510,46740,46970,47200,47430,47660,47890,48120,48350,48580,48810,49040,49270,49500,49730,49960,50190,50420,50650,50880,51110,51340};
	private int[] AirCord_whatToPlay = {5,6,6,6,5,6,6,6,3,3,3,3,1,1,1,1,3,3,3,3,1,1,1,1,3,3,3,3,3,1,1,1,1,1,3,3,3,4,3,1,1,1,2,1,3,4,3,3,4,1,2,1,1,2,3,4,3,3,4,1,2,1,1,2,3,4,3,3,4,1,2,1,1,2,3,4,3,3,4,1,2,1,1,2,3,3,4,3,3,4,3,1,1,2,1,1,2,1,3,3,4,3,3,4,3,1,1,2,1,1,2,1,3,3,4,3,3,3,4,3,1,1,2,1,1,1,2,1,3,3,4,3,3,3,4,3,1,1,2,1,1,1,2,1};
	private int[] Elkland_Bar = {4,8,12,16,20,24,28,32,36,40,44,48,56,64,69,74,79,84,92,100,108,116,122,128,134,140,146,152,158,164};
	private int[] Elkland_whatToPlay = {5,6,6,6,5,6,6,6,3,3,3,3,1,1,1,1,3,3,3,3,1,1,1,1,4,3,3,3,2,1,1,1,3,4,3,4,1,2,1,2,3,4,3,4,1,2,1,2,3,3,4,3,3,3,4,3,1,1,2,1,1,1,2,1,3,3,4,3,3,3,4,3,1,1,2,1,1,1,2,1,3,3,3,3,3,1,1,1,1,1,3,3,3,3,3,1,1,1,1,1,3,3,3,3,3,3,3,3,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,1,1,1,1,1,1,1,1,3,3,3,3,3,4,1,1,1,1,1,2,3,3,3,3,3,4,1,1,1,1,1,2,3,3,4,3,4,3,1,1,2,1,2,1,3,3,4,3,4,3,1,1,2,1,2,1};
	private int[] Elkland_whenToPlay = {0,857,1714,2571,3428,4285,5142,5999,6856,7713,8570,9427,10284,11141,11998,12855,13712,14569,15426,16283,17140,17997,18854,19711,20568,21425,22282,23139,23996,24853,25710,26567,27424,28281,29138,29995,30852,31709,32566,33423,34280,35137,35994,36851,37708,38565,39422,40279,41136,41564,41992,42420,42848,43276,43704,44132,44560,44988,45416,45844,46272,46700,47128,47556,47984,48412,48840,49268,49696,50124,50552,50980,51408,51836,52264,52692,53120,53548,53976,54404,54832,55688,56544,56972,57400,58256,59112,59968,60396,60824,61680,62536,63392,63820,64248,65104,65960,66816,67244,67672,68528,68956,69384,69812,70240,70668,71096,71524,71952,72380,72808,73236,73664,74092,74520,74948,75376,75804,76232,76660,77088,77516,77944,78372,78800,79228,79656,80084,80512,80940,81368,81796,82224,82652,83080,83508,83936,84792,85648,86076,86504,86932,87360,88216,89072,89500,89928,90356,90784,91640,92496,92924,93352,93780,94208,95064,95920,96348,96776,97204,98488,98916,99344,99772,100200,100628,101912,102340,102768,103196,103624,104052,105336,105764,106192,106620,107048,107476,108760,109188};
	private int[] ArcOfDawn_Bar = {4,8,9,10,11,12,13,14,15,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,51,54,57,60,62,64,66,68,71,74,77,80,82,84,86,88,94,100,106,112,118,124,130,136,138,140,142,144,146,148,150,152,156,160,164,168,172,176,180,184,190,196,202,208,214,220,226,232};
	private int[] ArcOfDawn_whatToPlay ={5,6,6,6,5,6,6,6,3,1,3,1,3,1,3,1,3,3,1,1,3,3,1,1,3,3,1,1,3,3,1,1,4,4,2,2,4,4,2,2,4,4,2,2,4,4,2,2,3,3,3,1,1,1,3,3,3,1,1,1,3,3,1,1,3,3,1,1,3,3,3,1,1,1,3,3,3,1,1,1,3,3,1,1,3,3,1,1,3,3,4,3,3,4,1,1,2,1,1,2,3,3,4,3,3,4,1,1,2,1,1,2,3,3,4,3,3,4,1,1,2,1,1,2,3,3,4,3,3,4,1,1,2,1,1,2,4,4,2,2,4,4,2,2,4,4,2,2,4,4,2,2,3,4,3,4,1,2,1,2,3,4,3,4,1,2,1,2,3,4,3,4,1,2,1,2,3,4,3,4,1,2,1,2,3,3,4,3,3,4,1,1,2,1,1,2,3,3,4,3,3,4,1,1,2,1,1,2,3,3,4,3,3,4,1,1,2,1,1,2,3,3,4,3,3,4,1,1,2,1,1,2};
	private int[] ArcOfDawn_whenToPlay ={12036,12536,13036,13536,14036,14536,15036,15536,16036,18036,20036,22036,24036,26036,28036,30036,32036,33036,34036,35036,36036,37036,38036,39036,40036,41036,42036,43036,44036,45036,46036,47036,48536,49536,50536,51536,52536,53536,54536,55536,56536,57536,58536,59536,60536,61536,62536,63536,64036,64786,65536,66036,66786,67536,68036,68786,69536,70036,70786,71536,72036,73036,74036,75036,76036,77536,78036,79536,80036,80786,81536,82036,82786,83536,84036,84786,85536,86036,86786,87536,88036,89036,90036,91036,92036,93536,94036,95536,96036,96286,96536,97036,97286,97536,98036,98286,98536,99036,99286,99536,100036,100286,100536,101036,101286,101536,102036,102286,102536,103036,103286,103536,104036,104286,104536,105036,105286,105536,106036,106286,106536,107036,107286,107536,108036,108286,108536,109036,109286,109536,110036,110286,110536,111036,111286,111536,112536,113536,114536,115536,116536,117536,118536,119536,120536,121536,122536,123536,124536,125536,126536,127536,128036,128536,129036,129536,130036,130536,131036,131536,132036,132536,133036,133536,134036,134536,135036,135536,136036,136536,137036,137536,138036,138536,139036,139536,140036,140536,141036,141536,142036,142536,143036,143536,144036,144286,144536,145036,145286,145536,146036,146286,146536,147036,147286,147536,148036,148286,148536,149036,149286,149536,150036,150286,150536,151036,151286,151536,152036,152286,152536,153036,153286,153536,154036,154286,154536,155036,155286,155536,156036,156286,156536,157036,157286,157536,158036,158286,158536,159036,159286,159536};
	private int[] whenToPlay;
	private int[] whatToPlay;
	private int[] Bar;
	private int accuracy;
	private Handler mHandler = new Handler();
	private long currentTime; 
	private long whenBegin;
	private MediaPlayer hihatPlayer;
	private MediaPlayer snarePlayer;
	private MediaPlayer hihatPlayer1;
	private MediaPlayer snarePlayer1;
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
	
	public class Bar{
		public ArrayList<Pie> pies;
		Bar(){
			pies = new ArrayList<Pie>();
		}
	}
	public class Pie{
		public int startAngle;
		public int sweepAngle;
		public String colorCode;
		public boolean isUser;
		Pie(int start,int sweep,String code,boolean user){
			startAngle = start;
			sweepAngle = sweep;
			colorCode = code;
			isUser = user;
		}
	}
	public void setCircle(){
		 for(int i = 2;i<Bar.length;i++){
			 int startAngle = 270;
			 Bar bar = new Bar();
			 int degree = 360;
			 int sweepAngle = 0;
			 for(int j = Bar[i-1]  ;j < Bar[i] ;j++){
				 if(j == Bar[Bar.length - 1] - 1)
					 sweepAngle = degree;
				 else
					 sweepAngle = Math.round(((whenToPlay[j+1] - whenToPlay[j]) /(float)(whenToPlay[4]-whenToPlay[0]) * 360));
				 if(whatToPlay[j] == 3){
					 Pie pie = new Pie(startAngle,sweepAngle,"#0066CC",false);
					 startAngle += sweepAngle;
					 degree -= sweepAngle;
					 bar.pies.add(pie);
					 Log.i("barSize",String.valueOf(bar.pies.size()));
				 }else{
					 if(whatToPlay[j] == 1){
						 Pie pie = new Pie(startAngle,sweepAngle,"#0066CC",true);
						 startAngle += sweepAngle;
						 degree -= sweepAngle;
						 bar.pies.add(pie);
						 Log.i("barSize",String.valueOf(bar.pies.size()));
					 }else{
						 if(whatToPlay[j] == 4){
							 Pie pie = new Pie(startAngle,sweepAngle,"#EEEEEE",false);
							 startAngle += sweepAngle;
							 degree -= sweepAngle;
							 bar.pies.add(pie);
							 Log.i("barSize",String.valueOf(bar.pies.size()));
						 }else{
							 if(whatToPlay[j] == 2){
								 Pie pie = new Pie(startAngle,sweepAngle,"#EEEEEE",true);
								 startAngle += sweepAngle;
								 degree -= sweepAngle;
								 bar.pies.add(pie);
								 Log.i("barSize",String.valueOf(bar.pies.size()));
							 }
						 }
					 }
				 }			 
			 }
			 barList.add(bar);
			 Log.i("barListSize",String.valueOf(barList.size()));
		 }
	}
	
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
				accuracy = accuracy/(ShakeCounter + RotateCounter);
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
				accuracy = accuracy/(ShakeCounter + RotateCounter);
				isPlaying = false;
			}
		}
		
	};
	private Runnable playHihat = new Runnable(){

		@Override
		public void run() {
			new Thread(
					new Runnable(){
						public void run(){
							while(lastOneFinished == false){
								
							}
							lastOneFinished = false;
			if(isPlaying)
			hihatPlayer.start();
			Log.i("playHihat",String.valueOf(NextNote));
			Log.i("currentBar in Runnable",String.valueOf(currentBar));

			//Canvas canvas = viewHolder.lockCanvas();
			//view.draw(canvas);
			//viewHolder.unlockCanvasAndPost(canvas);
			//onDrawFinished = false;
			while(onDrawFinished == false){
				
			}
			view.postInvalidate();
	/*		if(endDraw >= barList.get(currentBar).pies.size() - 1)
				endDraw = 0;
			else */
			endDraw++; 
			Log.i("afterInvalidate","afterInvalidate");
			if(currentBar+2 <Bar.length - 1){
				if(currentBar % 2 != 0 && degree == 360)
					startDraw = endDraw = 0;
				if(NextNote == Bar[currentBar+2] - 1)
					currentBar++;
			}
			if(NextNote < whenToPlay.length -1){
				NextNote++;
			}else{
				mSensorManager.unregisterListener(mSensorListener);
				accuracy = accuracy/(ShakeCounter + RotateCounter);
				isPlaying = false;
				lastOneFinished = true;
				Intent intent = new Intent(getBaseContext(),PlayActivity.class);
				intent.putExtra("gpapp.hku.lux.Acc",accuracy);
				startActivity(intent);
			}
			lastOneFinished = true;
						}
					}
					).start();
		}
		
	};
	private Runnable playSnare = new Runnable(){

		@Override
		public void run() {
			new Thread(
					new Runnable(){
						public void run(){
							while(lastOneFinished == false){
								
							}
							lastOneFinished = false;
			if(isPlaying)
			snarePlayer.start();
			Log.i("playSnare",String.valueOf(NextNote));
			Log.i("currentBar in Runnable",String.valueOf(currentBar));

			//Canvas canvas = viewHolder.lockCanvas();
			//view.draw(canvas);
			//viewHolder.unlockCanvasAndPost(canvas);
			//onDrawFinished = false;
			while(onDrawFinished == false){
				
			}
			view.postInvalidate();
	/*		if(endDraw >= barList.get(currentBar).pies.size() - 1)
				endDraw = 0;
			else */
			endDraw++; 
			Log.i("afterInvalidate","afterInvalidate");
			if(currentBar+2 <Bar.length - 1){
				if(currentBar % 2 != 0 && degree == 360)
					startDraw = endDraw = 0;
				if(NextNote == Bar[currentBar+2] - 1)
					currentBar++;
			}
			if(NextNote < whenToPlay.length -1){
				NextNote++;
			}else{
				mSensorManager.unregisterListener(mSensorListener);
				accuracy = accuracy/(ShakeCounter + RotateCounter);
				isPlaying = false;
				lastOneFinished = true;
				Intent intent = new Intent(getBaseContext(),PlayActivity.class); 				intent.putExtra("gpapp.hku.lux.Acc",accuracy);
				startActivity(intent);
			}
						lastOneFinished = true;
						}
					}
					).start();
		}
		
	};
	private Runnable playerSnare = new Runnable(){

		@Override
		public void run() {
			new Thread(
					new Runnable(){
						public void run(){
							while(lastOneFinished == false){
								
							}
							lastOneFinished = false;
			didRotate = false;
			if(isPlaying)
			TimeToRotate = true;
			Log.i("playerSnare",String.valueOf(NextNote));
			Log.i("currentBar in Runnable",String.valueOf(currentBar));
			while(!didRotate){
				
			}
			//Canvas canvas = viewHolder.lockCanvas();
			//view.draw(canvas);
			//viewHolder.unlockCanvasAndPost(canvas);
			Log.i("afterInvalidate","afterInvalidate");
			//onDrawFinished = false;
			while(onDrawFinished == false){
			}
			view.postInvalidate();
			if(startDraw >= barList.get(currentBar).pies.size() - 1)
				startDraw = 0;
			else
			startDraw++;
			if(currentBar+2 <Bar.length - 1){
				if(currentBar % 2 != 0 && degree == 360)
					startDraw = endDraw = 0;
				if(NextNote == Bar[currentBar+2] - 1)
					currentBar++;
			}
			if(NextNote < whenToPlay.length - 1){
				NextNote++;
			}else{
				mSensorManager.unregisterListener(mSensorListener);
				//Toast.makeText(getBaseContext(), "NextNote: " + NextNote + " WhenToPlay:" + whenToPlay.length, Toast.LENGTH_SHORT).show();
				//Log.i("FinalBefore", String.valueOf(accuracy));
				accuracy = accuracy/(ShakeCounter + RotateCounter);
				Log.i("Final", String.valueOf(accuracy));
				isPlaying = false;
				lastOneFinished = true;
				Intent intent = new Intent(getBaseContext(),PlayActivity.class); 				intent.putExtra("gpapp.hku.lux.Acc",accuracy);
				startActivity(intent);
			}
						lastOneFinished = true;
			
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
						while(lastOneFinished == false){
							
						}
						lastOneFinished = false;
			didShake = false;
			if(isPlaying)
			TimeToShake = true;
			Log.i("playerHihat",String.valueOf(NextNote));
			Log.i("currentBar in Runnable",String.valueOf(currentBar));
			while(!didShake){
				
			}
		//	Canvas canvas = viewHolder.lockCanvas();
		//	view.draw(canvas);
		//	viewHolder.unlockCanvasAndPost(canvas);
			Log.i("afterInvalidate","afterInvalidate");
			//onDrawFinished = false;
			while(onDrawFinished == false){
				
			}
			view.postInvalidate();
			if(startDraw >= barList.get(currentBar).pies.size() - 1)
				startDraw = 0;
			else
			startDraw++;
			if(currentBar+2 <Bar.length - 1){
				if(currentBar % 2 != 0 && degree == 360)
					startDraw = endDraw = 0;
				if(NextNote == Bar[currentBar+2] - 1)
					currentBar++;
			}
			if(NextNote < whenToPlay.length -1){
				NextNote++;
			}else{
				mSensorManager.unregisterListener(mSensorListener);
				//Toast.makeText(getBaseContext(), "NextNote: " + NextNote + " WhenToPlay:" + whenToPlay.length, Toast.LENGTH_SHORT).show();
				//Log.i("FinalBefore", String.valueOf(accuracy));
				accuracy = accuracy/(ShakeCounter + RotateCounter);
				//Log.i("Final", String.valueOf(accuracy));
				//Log.i("Shake+RotateCounter", String.valueOf(ShakeCounter + RotateCounter));
				//playingDialog.cancel();
				isPlaying = false;
				lastOneFinished = true;
				Intent intent = new Intent(getBaseContext(),PlayActivity.class); 				intent.putExtra("gpapp.hku.lux.Acc",accuracy);
				startActivity(intent);
			}
					lastOneFinished = true;
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
					//Log.i("NotHiHatCounter", String.valueOf(NotShakeCounter));
					//Log.i("NotHihat", String.valueOf(accuracy));
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
					//Log.i("NotSnareCounter", String.valueOf(NotRotateCounter));
					//Log.i("NotSnare", String.valueOf(accuracy));
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new myView(this);
		view.setBackgroundResource(R.drawable.lux_bg);
		viewHolder = view.getHolder();
		mApp = (MyApplication) this.getApplication();
		p = new Paint();
		setContentView(view);
		int width = getResources().getDisplayMetrics().widthPixels;
		int height = getResources().getDisplayMetrics().heightPixels;
		rectF = new RectF((width / (float) 2) - 240, (height / (float) 2) - 400, (width / (float) 2) + 240, (height / (float) 2) + 80);
		NextNote = 0;
		barList = new ArrayList<Bar>();
		Intent intent = getIntent();
		hihatPlayer = MediaPlayer.create(this, R.raw.hihat);
		hihatPlayer1 = MediaPlayer.create(this, R.raw.hihat);
		if(hihatPlayer == null)
			Toast.makeText(getBaseContext(), "hihat sucks", Toast.LENGTH_SHORT).show();
		snarePlayer = MediaPlayer.create(this, R.raw.snare);
		snarePlayer1 = MediaPlayer.create(this, R.raw.snare);
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
		if(mSensorManager == null){
			Toast.makeText(getBaseContext(), "manager is null", Toast.LENGTH_SHORT).show();
		}
		mSensorListener = new MotionEventListener();
		mSensorListener.setOnMotionListener(new MotionEventListener.OnMotionListener() {
			
			@Override
			public void onShake() {
				// TODO Auto-generated method stub
				//Toast.makeText(getBaseContext(), "Rotate! " + "The "+ ShakeCounter +" times", Toast.LENGTH_SHORT).show();
				MediaPlayer soundPlayer;
				if(hihatPlayer.isPlaying()){
					soundPlayer = hihatPlayer1;
				}else{
					soundPlayer = hihatPlayer;
				}
				soundPlayer.start();
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
				MediaPlayer soundPlayer;
				if(hihatPlayer.isPlaying()){
					soundPlayer = snarePlayer1;
				}else{
					soundPlayer = snarePlayer;
				}
				soundPlayer.start();
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
		Log.i("Game",intent.getStringExtra("gpapp.hku.lux.Song"));
		Log.i("Game",intent.getExtras().getString("gpapp.hku.lux.Song"));
		String song = intent.getStringExtra("gpapp.hku.lux.Song");
		onClick(song);
	}
	private static class myView extends SurfaceView{

		public myView(Context context) {
			super(context);
			setWillNotDraw(false);
			// TODO Auto-generated constructor stub
		}
		public void onDraw(Canvas canvas){
			onDrawFinished = false;
			super.onDraw(canvas);
			Log.i("onDraw","onDraw is called");
			//canvas.drawColor(Color.CYAN);
			Log.i("currentBar",String.valueOf(currentBar));
			Log.i("size",String.valueOf(barList.get(currentBar).pies.size()));
			Log.i("startDraw",String.valueOf(startDraw));
			Log.i("endDraw",String.valueOf(endDraw));
			degree = 0;
			Pie pie;
		//	if(startDraw >= barList.get(currentBar).pies.size())
		//		startDraw = 0;
			for(int i = 0;i < barList.get(currentBar).pies.size();i++){
				pie = barList.get(currentBar).pies.get(i);
				p.setColor(Color.parseColor(pie.colorCode));
				Log.i("Color",pie.colorCode);
				Log.i("StartAngle",String.valueOf(pie.startAngle));
				Log.i("SweepAngle",String.valueOf(pie.sweepAngle));
				p.setStrokeWidth(1);
				p.setStyle(Style.FILL_AND_STROKE);
				if(currentBar % 2 != 0){
					if(i<= startDraw)
					degree += pie.sweepAngle;
				}else
					degree += pie.sweepAngle;
				Log.i("Degree",String.valueOf(degree));
				if(i >= startDraw && i< endDraw)
					canvas.drawArc (rectF, pie.startAngle, pie.sweepAngle, true, p);
				p.setColor(Color.BLACK);
				p.setStyle(Style.STROKE);
				if(i >= startDraw && i< endDraw)
					canvas.drawArc (rectF, pie.startAngle, pie.sweepAngle, true, p);
				
			}
				pie = barList.get(currentBar).pies.get(startDraw);
				if(pie.isUser){
					p.setColor(Color.RED);
					p.setStyle(Style.STROKE);
					p.setStrokeWidth(2);
					canvas.drawArc (rectF, pie.startAngle, pie.sweepAngle, true, p);
				}
			canvas.save();
			onDrawFinished = true;
		}
	}
	public void onDestroy(){
		super.onDestroy();
		StopPlaying();
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
		while(onDrawFinished == false){
			
		}
		lastOneFinished = true;
		mHandler.removeCallbacksAndMessages(null);
		mSensorManager.unregisterListener(mSensorListener);
		if(bgPlayer.isPlaying()){
			bgPlayer.stop();
			bgPlayer.release();
		}
		isPlaying = false;
	}
	public void onClick(String song){
		whenBegin = System.currentTimeMillis();
		NextNote = 0;
		ShakeCounter = 0;
		RotateCounter = 0;
		NotShakeCounter = 0;
		NotRotateCounter = 0;
		accuracy = 0;
		startDraw = 0;
		endDraw = 0;
		onDrawFinished = true;
		lastOneFinished = true;
		currentBar = 0;
		didShake = false;
		didRotate = false;
		if(mSensorManager.registerListener(mSensorListener,
		        mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
		        SensorManager.SENSOR_DELAY_GAME) == false)
			Toast.makeText(getBaseContext(), "Sensor doesnt work!", Toast.LENGTH_SHORT).show();
		if(song.equals("AirCord")){
			whenToPlay = AirCord_whenToPlay.clone();
			whatToPlay = AirCord_whatToPlay.clone();
			Bar = AirCord_Bar.clone();
			bgPlayer = MediaPlayer.create(this, R.raw.aircord);
			bgPlayer.setVolume(mApp.getBgMusic(), mApp.getBgMusic());
			bgPlayer.start();
		}else{
			if(song.equals("AmbientKingdom")){
				whenToPlay = Kingdom_whenToPlay.clone();
				whatToPlay = Kingdom_whatToPlay.clone();
				Bar = Kingdom_Bar.clone();
				bgPlayer = MediaPlayer.create(this, R.raw.ambientkingdom);
				bgPlayer.setVolume(mApp.getBgMusic(), mApp.getBgMusic());
				bgPlayer.start();
			}else{
				if(song.equals("ElkLand")){
					whenToPlay = Elkland_whenToPlay.clone();
					whatToPlay = Elkland_whatToPlay.clone();
					Bar = Elkland_Bar.clone();
					bgPlayer = MediaPlayer.create(this, R.raw.elkland);
					bgPlayer.setVolume(mApp.getBgMusic(), mApp.getBgMusic());
					bgPlayer.start();
				}else{
					if(song.equals("ArcOfDawn")){
						whenToPlay = ArcOfDawn_whenToPlay.clone();
						whatToPlay = ArcOfDawn_whatToPlay.clone();
						Bar = ArcOfDawn_Bar.clone();
						bgPlayer = MediaPlayer.create(this, R.raw.arcofdawn);
						bgPlayer.setVolume(mApp.getBgMusic(), mApp.getBgMusic());
						bgPlayer.start();
						
					}
				}
			}
		}
		setCircle();
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
	}
}
