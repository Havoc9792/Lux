package gpapp.hku.lux;
import android.app.Application;


public class MyApplication extends Application {
	//  private static float MIN_SHAKE_FORCE = 4;
	 // private static float MIN_ROTATE_FORCE = 6;
	  private static float MIN_SHAKE_FORCE = 20;
	  private static float MIN_ROTATE_FORCE = 10;
	  private static float MAX_SHAKE_FORCE = 5;
	  private static float MAX_ROTATE_FORCE = 7;
	  private static float BgMusic = 1;
	  private static float SoundEffect = 1;
	  public float getMinSnakeForce(){
		  return MIN_SHAKE_FORCE;
	  }
	  public float getMaxSnakeForce(){
		  return MAX_SHAKE_FORCE;
	  }
	  public float getMinRotateForce(){
		  return MIN_ROTATE_FORCE;
	  }
	  public float getMaxRotateForce(){
		  return MAX_ROTATE_FORCE;
	  }
	  public float getHihatBar(){
		  
		  /*
		  if((MAX_ROTATE_FORCE - 7) > 0){
			  return 50*(1+(MAX_ROTATE_FORCE-7)/4);
		  }else{
			  return 50*(1-(7-MAX_ROTATE_FORCE)/4);
		  }
		  */
		  return MIN_SHAKE_FORCE;
	  }
	  public float getSnareBar(){
		  /*
		  
		  if((MAX_SHAKE_FORCE - 5) > 0){
			  return 50*(1+(MAX_SHAKE_FORCE-5)/4);
		  }else{
			  return 50*(1-(5-MAX_SHAKE_FORCE)/4);
		  }
		  
		  */
		  return MIN_ROTATE_FORCE;
	  }
	  public void setHihatForce(int factor){
		  
		  /*
		  
		  MIN_SHAKE_FORCE = 4;
		  MAX_ROTATE_FORCE = 7;
		  if(factor >= 50){
			  MIN_SHAKE_FORCE -= MIN_SHAKE_FORCE *((float) (factor - 50) / 100);
			  MAX_ROTATE_FORCE += MAX_ROTATE_FORCE * ((float) (factor - 50) / 100);
		  }else{
			  MIN_SHAKE_FORCE += MIN_SHAKE_FORCE *((float)(50 - factor) / 100);
			  MAX_ROTATE_FORCE -= MAX_ROTATE_FORCE * ((float)(50 - factor) / 100);
		  }
		  
		  */
		  MIN_SHAKE_FORCE = factor;
	  }
	  public void setSnareForce(int factor){
		  
		  /*
		  MIN_ROTATE_FORCE = 6;
		  MAX_SHAKE_FORCE = 5;
		  if(factor >= 50){
			  MIN_ROTATE_FORCE -= MIN_ROTATE_FORCE *((float) (factor - 50) / 100);
			  MAX_SHAKE_FORCE += MAX_SHAKE_FORCE * ((float) (factor - 50) / 100);
		  }else{
			  MIN_ROTATE_FORCE += MIN_ROTATE_FORCE *((float)(50 - factor) / 100);
			  MAX_SHAKE_FORCE -= MAX_SHAKE_FORCE * ((float)(50 - factor) / 100);
		  }
		  
		  */
		  MIN_ROTATE_FORCE = factor;
	  }
	  
	  public void setBgMusic(int factor){
		  BgMusic = 1;
		  BgMusic = (float)(BgMusic*factor) / 100;
		  //BgMusic = factor;
	  }
	  
	  public void setSoundEffect(int factor){
		  SoundEffect = 1;
		  SoundEffect = (float)(SoundEffect*factor) / 100;
		  //SoundEffect = factor;
	  }
	  public float getBgMusic(){
		  return BgMusic;
	  }
	  public float getSoundEffect(){
		  return SoundEffect;
	  }

}
