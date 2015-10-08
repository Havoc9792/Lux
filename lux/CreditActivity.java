package gpapp.hku.lux;

import gpapp.hku.lux.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CreditActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.credit);
	
	TextView tv = (TextView)findViewById(R.id.textView1);
	tv.setText("Producers:\nTino\nSunny\nChiu\n\nhihat + Kick\nhttp://www.iwantthatsound.com/free-samples/\nTHATSOUND\n\nsnare\nhttp://eternitysound.com/index.php?option=com_content&view=article&id=56&Itemid=63\nEternitySound\n\nmusic\nhttp://www.nosoapradio.us/\nDeceased Superior Technician\n\nBackground Image (galaxy)\nOriginal: jronaldlee@Flickr (Modified to this dimmer version)\nLicense: http://creativecommons.org/licenses/by/2.5/\n\nLuX Logo Font:\nFerrum (Modified using Paint.NET)\nhttp://www.fontspace.com/arro/ferrum\n\nOld Deep Blood Red Button Font:\nMecha\nhttp://www.fontspace.com/captain-falcon/mecha\n\nNew White Button Font:\nExo-light\nhttp://www.fontsquirrel.com/fonts/exo\n\nSound effect\nsfxr - sound effect generator, DrPetter\nhttp://www.cyd.liu.se/~tompe573/hp");
	tv.setTextColor(Color.WHITE);	
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
