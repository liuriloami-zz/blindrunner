package osphi.blindrunner;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;

/**
 * This activity is the main menu of the game
 */

public class MainActivity extends Activity implements OnTouchListener {

	private Path path;
	private double x, y;
	private int typeMenu = 0;
	private View view;
	private MediaPlayer mp;
	private Handler handler;
	private Runnable run1, run2;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		view = new MainView(this);
		setContentView(view);
		view.setOnTouchListener((OnTouchListener) this);
		path = new Path(); 
		
		try {
			MySoundPool.init(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
        
	}
	
	protected void onDestroy() {
		MySoundPool.release();
		super.onDestroy();
	}
	
	protected void onResume() {
		super.onResume();
		
		 mp = MediaPlayer.create(getBaseContext(), R.raw.tap_to_start);
		 run1 = new Runnable() {
         	public void run() {
        		mp.release();
        		mp = MediaPlayer.create(getBaseContext(), R.raw.slide_to_more_options);
        		mp.start();
        	}
        };
        run2 = new Runnable() {
        	public void run() {
        		mp.start();
                handler.postDelayed(run1, 1500);
        	}
        };
	        handler = new Handler();
	        handler.postDelayed(run2, 1000);
	}

	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode)
            {
            case KeyEvent.KEYCODE_BACK:
            	this.handler.removeCallbacks(run1);
            	this.handler.removeCallbacks(run2);
            	finish();
            }

        }
        return super.onKeyDown(keyCode, event);
    }
	
	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		switch(arg1.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x = arg1.getX();
			y = arg1.getY(); 
			break;
		case MotionEvent.ACTION_UP:
			x = arg1.getX() - x;
			y = arg1.getY() - y;

			if (Math.abs(x) > arg0.getWidth()/4)
			{
				if (x > 0)
				{
					typeMenu--;
					if (typeMenu < 0)
						typeMenu = 2;
					view.invalidate();
				}
				else
				{
					typeMenu++;
					if (typeMenu == 3)
						typeMenu = 0;
					view.invalidate();
				}
				switch(typeMenu) {
				case 0:
	            	this.handler.removeCallbacks(run1);
	            	this.handler.removeCallbacks(run2);
					mp.release();
	        		mp = MediaPlayer.create(getBaseContext(), R.raw.tap_to_start);
	        		mp.start();
					break;
				case 1:
	            	this.handler.removeCallbacks(run1);
	            	this.handler.removeCallbacks(run2);
					mp.release();
	        		mp = MediaPlayer.create(getBaseContext(), R.raw.tap_to_see_the_ranking);
	        		mp.start();
					break;
				case 2:
	            	this.handler.removeCallbacks(run1);
	            	this.handler.removeCallbacks(run2);
					mp.release();
	        		mp = MediaPlayer.create(getBaseContext(), R.raw.tap_to_know_more);
	        		mp.start();
					break;
				}
			}
			else
			{
            	this.handler.removeCallbacks(run1);
            	this.handler.removeCallbacks(run2);
				mp.release();
				switch (typeMenu) {
				case 0:
					// Go to LevelSelect					
					Intent levelselect = new Intent(this, LevelSelectActivity.class);
					startActivity(levelselect);
					break;
				case 1:
					// Ranking
					Intent ranking = new Intent(this, RankingActivity.class);
					startActivity(ranking);
					break;
				case 2:
					// About
					Intent about = new Intent(this, AboutActivity.class);
					startActivity(about);
					break;
				}
			}
			break;
		}
		return true;
	}

	public class MainView extends View {

		private Paint paint = new Paint();

		private RectF rect = new RectF();
		public MainView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			int x = getWidth();
			int y = getHeight();
			int radius;
			final int fontSize = 8;
			radius = Math.min(x, y)/4;

			rect.set(x / 2 - radius*1.7f, y / 2 - radius/2.5f, x/2 + radius*1.7f, y/2 + radius/2.5f);
			
			paint.setAntiAlias(true);
			paint.setFilterBitmap(true);

			paint.setStyle(Paint.Style.FILL);

			paint.setColor(Color.BLACK);
			canvas.drawRect(0, 0, x, y, paint);

			paint.setStyle(Paint.Style.STROKE);
			paint.setColor(Color.rgb(250, 0, 0));
			paint.setStrokeWidth(5);
			canvas.drawRoundRect( rect, radius/4.0f, radius/4.0f, paint);

			
			canvas.drawLine(2*x/8, y/8, x/2, y/8, paint);
			canvas.drawLine(6*x/8, y*7/8, x/2, 7*y/8, paint);

			path.reset();
			path.lineTo(Math.min(x,y)/16,Math.max(x,y)/64);
			path.lineTo(0,Math.max(x,y)/32);
			path.close();
			path.offset(6*x/8,  7*y/8 - Math.max(x, y)/64);
			canvas.drawPath(path, paint);

			path.reset();
			path.lineTo(-Math.min(x,y)/16,Math.max(x,y)/64);
			path.lineTo(0,Math.max(x,y)/32);
			path.close();
			path.offset(2*x/8,  y/8 - Math.max(x, y)/64);
			canvas.drawPath(path, paint);

			paint.setStyle(Paint.Style.FILL);
			
			paint.setColor(Color.rgb(255, 255, 255));
			paint.setTextSize(Math.min(x, y)/fontSize);

			Typeface typeFace = Typeface.createFromAsset(getAssets(), "font.ttf");
			paint.setTypeface(typeFace);

			switch (typeMenu)
			{
			case 0:
				canvas.drawText("START!", x/2 - paint.measureText("START!")/2, y/2 + 0.3f*(Math.min(x, y)/fontSize), paint);
				break;
			case 1:
				canvas.drawText("Ranking", x/2 - paint.measureText("Ranking")/2, y/2 + 0.3f*(Math.min(x, y)/fontSize), paint);
				break;
			case 2:
				canvas.drawText("How to Play", x/2 - paint.measureText("How to Play")/2, y/2 + 0.3f*(Math.min(x, y)/fontSize), paint);
				break;
			}
		}
	}
}
