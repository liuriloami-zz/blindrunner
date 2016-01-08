package osphi.blindrunner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.media.AudioManager;
import android.media.MediaPlayer;
@SuppressLint("DrawAllocation")
public class LevelSelectActivity extends Activity implements OnTouchListener{
	
	private int nLevel = 0;
	private String levelString;
	private double x, y;
	private View view;
	private Handler handler;
	private MediaPlayer mp;
	private Runnable run1, run2, run3;
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		levelString = "Level 1";
		view = new LevelView(this);
		view.setBackgroundColor(Color.BLACK);
		setContentView(view);
		view.setOnTouchListener((OnTouchListener) this);
		mp = MediaPlayer.create(getBaseContext(), R.raw.choose_your_level);
        handler = new Handler();
        run1 = new Runnable() {
        	public void run() {
        		mp.release();
        		mp = MediaPlayer.create(getBaseContext(), R.raw.slide_to_more_options);
        		mp.start();
        	}
        };
        run2 = new Runnable() {
        	public void run() {
        		handler.postDelayed(run1, 2000);
        		mp.release();
        		mp = MediaPlayer.create(getBaseContext(), R.raw.tap_to_level_one);
        		mp.start();
        	}
        };
        run3 = new Runnable() {
        	public void run() {
        		mp.start();
                handler.postDelayed(run2, 2000);
        	}
        };
        handler.postDelayed(run3, 1000);
	}
	
	public class LevelView extends View {

		private Paint paint = new Paint();
		
		public LevelView(Context context) {
			super(context);
		}
		
		Bitmap gate = BitmapFactory.decodeResource(getResources(), R.drawable.gate);
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			int x = getWidth();
			int y = getHeight();
			
			// Set draw properties
			paint.setAntiAlias(true);
			paint.setColor(Color.rgb(255, 255, 255));
			
			float w = gate.getWidth();
			float h = gate.getHeight();
			float ratio = x/w;
			gate = Bitmap.createScaledBitmap(gate, x, (int)(ratio*h), false);
			canvas.drawBitmap(gate, 0.0f,  (float)((y - ratio*h)/2), paint);
			
			// Draw text
			Typeface typeFace = Typeface.createFromAsset(getAssets(), "font.ttf");

			paint.setTextSize((int)(Math.min(x, y)/7.5f));
			paint.setTypeface(typeFace);
			canvas.drawText(levelString, x/2  - 0.9f*paint.measureText(levelString)/2, y/2 - y/6.2f, paint);
		}
		
	}
	
	private boolean headsetConnected = false;
	
	

	
	
	 @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if(event.getAction() == KeyEvent.ACTION_DOWN){
	            switch(keyCode)
	            {
	            case KeyEvent.KEYCODE_BACK:
	            	this.handler.removeCallbacks(run1);
	        		this.handler.removeCallbacks(run2);
	        		this.handler.removeCallbacks(run3);
	            	break;
	            }
	        }
	        return super.onKeyDown(keyCode, event);
	    }
	 
	
	public void onReceive(Context context, Intent intent) {
		if (intent.hasExtra("state")) {
			if (headsetConnected && intent.getIntExtra("state", 0) == 0) {
				headsetConnected = false;
			} else if (!headsetConnected && intent.getIntExtra("state", 0) == 1) {
				headsetConnected = true;
			}
		}
	}
	
	private AlertDialog alertDialog;
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		AudioManager audio = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
		switch(arg1.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x = arg1.getX();
			y = arg1.getY(); 
			break;
		case MotionEvent.ACTION_UP:
			x = arg1.getX() - x;
			y = arg1.getY() - y;


        	this.handler.removeCallbacks(run1);
        	this.handler.removeCallbacks(run2);
        	this.handler.removeCallbacks(run3);
        	
			if (Math.abs(x) > arg0.getWidth()/4) {

				if (x > 0)
					nLevel--;
				else 
					nLevel++;

				if (nLevel == 5)
					nLevel = 0;
				else if (nLevel == -1)
					nLevel = 4;

				levelString = "Level " + (nLevel+1);
				view.invalidate();
				
				handler.postDelayed(new Runnable() {
		        	public void run() {
		        		
					switch(nLevel) {
					case 0:
						mp.release();
		        		mp = MediaPlayer.create(getBaseContext(), R.raw.tap_to_level_one);
		        		mp.start();
		        		break;
					case 1:
						mp.release();
		        		mp = MediaPlayer.create(getBaseContext(), R.raw.tap_to_level_tw);
		        		mp.start();
		        		break;
					case 2:
						mp.release();
		        		mp = MediaPlayer.create(getBaseContext(), R.raw.tap_to_level_three);
		        		mp.start();
		        		break;
					case 3:
						mp.release();
		        		mp = MediaPlayer.create(getBaseContext(), R.raw.tap_to_level_four);
		        		mp.start();
		        		break;
					case 4:
						mp.release();
		        		mp = MediaPlayer.create(getBaseContext(), R.raw.tap_to_level_five);
		        		mp.start();
		        		break;
					}
		        }}, 400);
			}
			else if (!audio.isBluetoothA2dpOn() || !audio.isWiredHeadsetOn()){
				Intent game = new Intent(this, GameActivity.class);
				Bundle extras = new Bundle();
				switch (nLevel) {
				case 0:
					extras.putInt("level", 1);
					break;
				case 1:
					extras.putInt("level", 2);
					break;
				case 2:
					extras.putInt("level", 3);
					break;
				case 3:
					extras.putInt("level", 4);
					break;
				case 4:
					extras.putInt("level", 5);
					break;
				}
				game.putExtras(extras);
				startActivity(game);
				finish();
			} else {
				alertDialog = new AlertDialog.Builder(this)
					.setPositiveButton("OK", null)
					.setMessage("You must plug your phones to play this game.")
					.setTitle("Plug your phones!")
					.create();
				alertDialog.show();
        		mp.release();
        		mp = MediaPlayer.create(getBaseContext(), R.raw.plug_your_phones);
        		mp.start();
        		Handler handler = new Handler();
        		handler.postDelayed(new Runnable() {
        			public void run() {
        				alertDialog.dismiss();
        			}
        		}, 4500);
			}
			break;
		}
		return true;
	}
}