package osphi.blindrunner;


import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

/**
 * This activity presents the name of the game
 */

public class FirstActivity extends Activity {
	
	private View mContentView;
	private MediaPlayer mp;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.elements);
		
        // Load views
        mContentView = findViewById(R.id.icon);
        
        mp = MediaPlayer.create(getBaseContext(), R.raw.blind_runner);
        
        mContentView.setAlpha(0f);
	    
        mContentView.animate()
    	.alpha(1f)
    	.setDuration(4000)
    	.setListener(null);

        Handler handler = new Handler();
        
        handler.postDelayed(new Runnable() {
        	public void run() {
                mp.start();
        	}}, 4000);
         
        handler.postDelayed(new Runnable() { 
             public void run() { 
            	 mContentView.animate()
             	.alpha(0f)
             	.setDuration(2000)
             	.setListener(null);
                 
             } 
        }, 6000);
	 
    handler.postDelayed(new Runnable() { 
         public void run() { 
             mp.release();
        	 Intent intent = new Intent(getBaseContext(), MainActivity.class);
        	    startActivity(intent);
        	    finish();
         } 
    }, 7500);
}
}
