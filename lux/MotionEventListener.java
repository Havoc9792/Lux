package gpapp.hku.lux;

import gpapp.hku.lux.MotionEventListener.OnMotionListener;
import android.annotation.TargetApi;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class MotionEventListener  implements SensorEventListener {	/** Minimum movement force to consider. */
	  private static float MIN_SHAKE_FORCE = 20;
	  private static float MIN_ROTATE_FORCE = 10;
	  private static float MAX_SHAKE_FORCE = 5;
	  private static float MAX_ROTATE_FORCE = 7;

	  /**
	   * Minimum times in a shake gesture that the direction of movement needs to
	   * change.
	   */
	  private static final int MIN_DIRECTION_CHANGE = 2;
	  private static final int MIN_ROTATION_DIRECTION_CHANGE = 2;

	  /** Maximum pause between movements. */
	  private static final int MAX_PAUSE_BETHWEEN_DIRECTION_CHANGE = 150;

	  /** Maximum allowed time for shake gesture. */
	  private static final int MAX_TOTAL_DURATION_OF_SHAKE = 300;

	  /** Time when the gesture started. */
	  private long mFirstShakeDirectionChangeTime = 0;
	  private long mFirstRotateDirectionChangeTime;
	  /** Time when the last movement started. */
	  private long mLastShakeDirectionChangeTime;
	  private long mLastRotateDirectionChangeTime;
	  private long lastUpdate = 0;
	  private long mLastShakeTime = 0;
	  private long mLastRotateTime = 0;
	  /** How many movements are considered so far. */
	  private int mShakeDirectionChangeCount = 0;
	  private int mRotateDirectionChangeCount = 0;
	  /** The last x position. */
	  /** The last y position. */
	  public float lastY = 0;
	  /** The last z position. */
	  public float lastZ = 0;
	  public float lastX = 0;
	  public float x = 0;
	  public float y = 0;
	  public float z = 0;
	  public float TotalX = 0;
	  public float TotalY = 0;
	  public float TotalZ = 0;
	  public int count = 0;
	  public float[] mOrientValues = new float[3];
	  private MyApplication mApp;

	  /** OnShakeListener that is called when shake is detected. */
	  private OnMotionListener mMotionListener;
	private int shakeCount = 0;
	private int rotateCount = 0;

	  /**
	   * Interface for shake gesture.
	   */
	  public interface OnMotionListener {

	    /**
	     * Called when shake gesture is detected.
	     */
	    void onShake();
	    void onRotate();
	  }

	  public void setOnMotionListener(OnMotionListener listener,MyApplication app) {
	    mMotionListener = listener;
	    mApp = app;
	  }
	  
	  @Override
	  public void onSensorChanged(SensorEvent se) {
	    // get sensor data
	   // x = se.values[SensorManager.DATA_X];
	   // y = se.values[SensorManager.DATA_Y];
	   // z = se.values[SensorManager.DATA_Z];
	    //mMotionListener.onValue();
	    // calculate movement
	   // float totalMovement = Math.abs(x + y + z - lastX - lastY - lastZ);
/*	    
	    if(lastY - y > 0.0 && x - lastX > 0.0 && z - lastZ > 0.0){
	    	//shake
	    	lastY = y;
	    	lastX = x;
	    	lastZ  =z;
	    	shakeCount  += 1;
	    	if(shakeCount >= 2){
	    		mMotionListener.onShake();
	    		resetShakeParameters();
	    		shakeCount = 0;
	    	}
	    }else{
	    	if(lastX - x > 0.0 && z - lastZ >0.0){
	    		//tilt
		    	lastY = y;
		    	lastX = x;
		    	lastZ  =z;
		    	rotateCount  += 1;
		    	if(rotateCount >= 2){
		    		mMotionListener.onRotate();
		    		resetShakeParameters();
		    		rotateCount = 0;
		    	}
	    	}else{
	    		resetShakeParameters();
	    		rotateCount = 0;
	    		shakeCount = 0;
	    	}
	    } 
	    */
/*
		  if(se.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){
			   long curTime = System.currentTimeMillis();
			   if(curTime - lastUpdate > 50){
				   //dont take data too frequently
				   if(curTime - lastUpdate >1000){
					   //no movement for too long
			           lastUpdate = System.currentTimeMillis();
			           resetRotationParameters();
				   }else{
					   float[] rotationMatrix = new float[16];
			           SensorManager.getRotationMatrixFromVector(rotationMatrix,se.values);
			           SensorManager.getOrientation(rotationMatrix, mOrientValues);
			           for(int i=0; i<3; i++)
			              mOrientValues[i]=(float)Math.toDegrees(mOrientValues[i])+180.0f;//orientation in degrees
			           //0: azimuth,rotation around Z axis,short end
			           //1 pitch,rotation around X axis,face of phone
			           //2 roll,rotation around Y axis,long end
			           z = mOrientValues[0];
			           x = mOrientValues[1];
			           y = mOrientValues[2];
			           lastUpdate = System.currentTimeMillis();
			           if(lastX == 0){
			        	   lastX = x;
			        	   lastY = y;
			        	   lastZ = z;
			        	   count = 1;
			           }else{ 
			        	   		TotalX += (x - lastX);
			        	   		TotalY += (y - lastY);
			        	   		TotalZ += (z - lastZ);
			        	   		count += 1;
			        	      float totalShakeMovement = TotalX;
			        	      float totalRotationMovement = TotalY ;
			        	      if(totalShakeMovement >= 10 && count >=2){
			        	    	  // get time
			        	    				  mMotionListener.onShake();
			        	    				  resetShakeParameters();

			        	    	  }else{
			        	    		  if(totalRotationMovement >= 10 && count >= 2){
		        	    				  	mMotionListener.onRotate();
		        	    				  	resetRotationParameters();
			        	    		  }else{
			        	    			  lastX = x;
			        	    			  lastY = y;
			        	    			  lastZ = z;
			        	    		  }
		        	    				  	
			        	    	  }
			           }
			        
				   }
			   }
		  }
				*/   
				
		    if(se.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){  
	           float[] rotationMatrix = new float[16];
	           SensorManager.getRotationMatrixFromVector(rotationMatrix,se.values);
	           SensorManager.getOrientation(rotationMatrix, mOrientValues);
	           for(int i=0; i<3; i++)
	              mOrientValues[i]=(float)Math.toDegrees(mOrientValues[i])+180.0f;//orientation in degrees
	           //0: azimuth,rotation around Z axis,short end
	           //1 pitch,rotation around X axis,face of phone
	           //2 roll,rotation around Y axis,long end
	           z = mOrientValues[0];
	           x = mOrientValues[1];
	           y = mOrientValues[2];
	           float totalShakeMovement = Math.abs( x - lastX);
	   	       float totalRotationMovement = Math.abs(y - lastY) ;
	   	       MIN_SHAKE_FORCE = mApp.getMinSnakeForce();
	   	       MIN_ROTATE_FORCE = mApp.getMinRotateForce();
	   	       if(totalShakeMovement >= MIN_SHAKE_FORCE){
	   	    	// get time
	   	    	long now = System.currentTimeMillis();
	   	      

	   	    	// store first movement time
	   	    	if (mFirstShakeDirectionChangeTime == 0) {
	   	    		mFirstShakeDirectionChangeTime = now;
	   	    		mLastShakeDirectionChangeTime = now;
	   	      		}

	   	    	// check if the last movement was not long ago
	   	    	long lastChangeWasAgo = now - mLastShakeDirectionChangeTime;
	   	    	if (lastChangeWasAgo < MAX_PAUSE_BETHWEEN_DIRECTION_CHANGE) {

	   	    		// store movement data
	   	    		mLastShakeDirectionChangeTime = now;
	   	    		mShakeDirectionChangeCount++;

	   	    		// store last sensor data 
	   	    		lastY = y;
	   	    		lastZ = z;
	   	    		lastX = x;
	   	    		// check how many movements are so far
	   	    		if (mShakeDirectionChangeCount >= MIN_DIRECTION_CHANGE) {
	   	        	
	   	    			// check total duration
	   	    			long totalDuration = now - mFirstShakeDirectionChangeTime;
	   	    			if (totalDuration < MAX_TOTAL_DURATION_OF_SHAKE) {
	   	    				mMotionListener.onShake();
	   	    				mLastShakeTime = now;
	   	    				resetShakeParameters();
	   	    			}
	   	    		}

	   	    	} else {
	   	    		resetShakeParameters();
	   	    	}
	   	       }else{
	   	    	   if(totalRotationMovement >= MIN_ROTATE_FORCE){
	   	    		// get time
	   		    	long now = System.currentTimeMillis();
	   		      
	   		    	// store first movement time
	   		    	if (mFirstRotateDirectionChangeTime == 0) {
	   		    		mFirstRotateDirectionChangeTime = now;
	   		    		mLastRotateDirectionChangeTime = now;
	   		      		}

	   		    	// check if the last movement was not long ago
	   		    	long lastChangeWasAgo = now - mLastRotateDirectionChangeTime;
	   		    	if (lastChangeWasAgo < MAX_PAUSE_BETHWEEN_DIRECTION_CHANGE) {

	   		    		// store movement data
	   		    		mLastRotateDirectionChangeTime = now;
	   		    		mRotateDirectionChangeCount++;

	   		    		// store last sensor data 
	   		    		lastZ = z;
	   		    		lastY = y;
	   		    		lastX = x;

	   		    		// check how many movements are so far
	   		    		if (mRotateDirectionChangeCount >= MIN_ROTATION_DIRECTION_CHANGE) {
	   		        	
	   		    			// check total duration
	   		    			long totalDuration = now - mFirstRotateDirectionChangeTime;
	   		    			if (totalDuration < MAX_TOTAL_DURATION_OF_SHAKE) {
	   		    				mMotionListener.onRotate();
	   		    				mLastRotateTime = now;
	   		    				resetRotationParameters();
	   		    			}
	   		    		}

	   		    	} else {
	   		    		resetRotationParameters();
	   		    	}
	   	    	   }
	   	       }
		  }
	    
	    
	   /*
	    
	    float totalShakeMovement = Math.abs( y - lastY);
	    float totalRotationMovement = Math.abs(z - lastZ) ;
	    MIN_SHAKE_FORCE = mApp.getMinSnakeForce();
	    MIN_ROTATE_FORCE = mApp.getMinRotateForce();
	    MAX_ROTATE_FORCE = mApp.getMaxSnakeForce();
	    MAX_SHAKE_FORCE = mApp.getMaxRotateForce();
	    if (totalShakeMovement >= MIN_SHAKE_FORCE && totalRotationMovement < MAX_ROTATE_FORCE) {
	    	//hihat
		    TotalX += Math.abs( x - lastX);
		    TotalY += Math.abs( y - lastY);
		    TotalZ += Math.abs( z - lastZ);
	    	// get time
	    	long now = System.currentTimeMillis();
	      

	    	// store first movement time
	    	if (mFirstShakeDirectionChangeTime == 0) {
	    		mFirstShakeDirectionChangeTime = now;
	    		mLastShakeDirectionChangeTime = now;
	      		}

	    	// check if the last movement was not long ago
	    	long lastChangeWasAgo = now - mLastShakeDirectionChangeTime;
	    	if (lastChangeWasAgo < MAX_PAUSE_BETHWEEN_DIRECTION_CHANGE) {

	    		// store movement data
	    		mLastShakeDirectionChangeTime = now;
	    		mShakeDirectionChangeCount++;

	    		// store last sensor data 
	    		lastY = y;
	    		lastZ = z;
	    		lastX = x;
	    		// check how many movements are so far
	    		if (mShakeDirectionChangeCount >= MIN_DIRECTION_CHANGE) {
	        	
	    			// check total duration
	    			long totalDuration = now - mFirstShakeDirectionChangeTime;
	    			if (totalDuration < MAX_TOTAL_DURATION_OF_SHAKE) {
	    				TotalX = TotalX / mShakeDirectionChangeCount;
	    				TotalY = TotalY / mShakeDirectionChangeCount;
	    				TotalZ = TotalZ / mShakeDirectionChangeCount;
	    				mMotionListener.onShake();
	    				mLastShakeTime = now;
	    				resetShakeParameters();
	    			}
	    		}

	    	} else {
	    		resetShakeParameters();
	    	}
	      
	    }else{
	    	if(totalRotationMovement >= MIN_ROTATE_FORCE && totalShakeMovement < MAX_SHAKE_FORCE){
	    		//Snare
	    		// get time
		    	long now = System.currentTimeMillis();
		      
		    	// store first movement time
		    	if (mFirstRotateDirectionChangeTime == 0) {
		    		mFirstRotateDirectionChangeTime = now;
		    		mLastRotateDirectionChangeTime = now;
		      		}

		    	// check if the last movement was not long ago
		    	long lastChangeWasAgo = now - mLastRotateDirectionChangeTime;
		    	if (lastChangeWasAgo < MAX_PAUSE_BETHWEEN_DIRECTION_CHANGE) {

		    		// store movement data
		    		mLastRotateDirectionChangeTime = now;
		    		mRotateDirectionChangeCount++;

		    		// store last sensor data 
		    		lastZ = z;
		    		lastY = y;
		    		lastX = x;

		    		// check how many movements are so far
		    		if (mRotateDirectionChangeCount >= MIN_ROTATION_DIRECTION_CHANGE) {
		        	
		    			// check total duration
		    			long totalDuration = now - mFirstRotateDirectionChangeTime;
		    			if (totalDuration < MAX_TOTAL_DURATION_OF_SHAKE) {
		    				mMotionListener.onRotate();
		    				mLastRotateTime = now;
		    				resetRotationParameters();
		    			}
		    		}

		    	} else {
		    		resetRotationParameters();
		    	}
	    	}else{
	    		
	    	}
	    }
	    
	    
	   */
	    
	  }
		
	 /* public void setHihatForce(int factor){
		  if(factor >= 50){
			  MIN_SHAKE_FORCE -= MIN_SHAKE_FORCE *(factor - 50) / 100;
			  MAX_ROTATE_FORCE += MAX_ROTATE_FORCE * (factor - 50) / 100;
		  }else{
			  MIN_SHAKE_FORCE += MIN_SHAKE_FORCE *(50 - factor) / 100;
			  MAX_ROTATE_FORCE -= MAX_ROTATE_FORCE * (50 - factor) / 100;
		  }
	  }
	  public void setSnareForce(int factor){
		  if(factor >= 50){
			  MIN_ROTATE_FORCE -= MIN_ROTATE_FORCE *(factor - 50) / 100;
			  MAX_SHAKE_FORCE += MAX_SHAKE_FORCE * (factor - 50) / 100;
		  }else{
			  MIN_ROTATE_FORCE += MIN_ROTATE_FORCE *(50 - factor) / 100;
			  MAX_SHAKE_FORCE -= MAX_SHAKE_FORCE * (50 - factor) / 100;
		  }
		  
	  } */
	  private void resetRotationParameters() {
		  mFirstRotateDirectionChangeTime = 0;
		    mRotateDirectionChangeCount = 0;
		    mLastRotateDirectionChangeTime = 0;
		    lastX = 0;
		    lastZ = 0;
		    lastY = 0;
		    TotalX = 0;
		    TotalZ = 0;
		    TotalY = 0;
		    count = 0;
	}

	/**
	   * Resets the shake parameters to their default values.
	   */
	  private void resetShakeParameters() {
	    mFirstShakeDirectionChangeTime = 0;
	    mShakeDirectionChangeCount = 0;
	    mLastShakeDirectionChangeTime = 0;
	    lastX = 0;
	    lastY = 0;
		lastZ = 0;
	    TotalX = 0;
	    TotalZ = 0;
	    TotalY = 0;
		count = 0;
	  }
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}


}
