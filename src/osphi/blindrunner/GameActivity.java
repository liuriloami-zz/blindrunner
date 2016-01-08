package osphi.blindrunner;

import java.io.IOException;

import osphi.blindrunner.mecanica.Mecanica;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;

public class GameActivity extends Activity implements OnTouchListener{
	
	private View headphone, gameScreen;
	private Mecanica mecanica;
	private double x1, y1, x2, y2;
	private boolean running;
	private int execucao;
	private long tempo;
	private float alpha;
	private Handler handler;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gameview);
		
		gameScreen = findViewById(R.id.game_screen);
		
		gameScreen.setOnTouchListener(this);
		
		headphone = findViewById(R.id.headphone);
		headphone.setScaleX(0.5f);
		headphone.setScaleY(0.5f);
		alpha = 1f;
		
		running = true;
		
		int level = getIntent().getExtras().getInt("level");
		try {
			mecanica = new Mecanica(this, level);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onStart () {
		super.onStart();
		handler = new Handler();
		tempo = System.currentTimeMillis();
        handler.post(new Runnable(){
        	int counter = 0;
        	
	        @Override
			public void run() {
	        	if (running) {
	        		execucao = mecanica.executar();
	        		if (execucao == 1)
	        		{
	        			headphone.setAlpha(0f);
	        			Intent gameover = new Intent(getBaseContext(), GameoverActivity.class);
	        			Bundle bundle = new Bundle();
	        			tempo = System.currentTimeMillis() - tempo;
	        			bundle.putLong("tempo", tempo);
	        			gameover.putExtras(bundle);
	        			startActivity(gameover);
	        			running = false;
	        			finish();
	        		} 
	        		else if (execucao == 2) 
	        		{
	        			headphone.setAlpha(0f);
	        			Intent victory = new Intent(getBaseContext(), VictoryActivity.class);
	        			Bundle bundle = new Bundle();
	        			tempo = System.currentTimeMillis() - tempo;
	        			bundle.putLong("tempo", tempo);
	        			victory.putExtras(bundle);
	        			startActivity(victory);
	        			running = false;
	        			finish();
	        		}
	        		if (counter == 60)
	        		{
		        		headphone.setAlpha(alpha);
		        		headphone.animate()
		        		.alpha(1f - alpha)
		        		.setDuration(1000)
		        		.setListener(null);
		        		alpha = 1f - alpha;
		        		counter = 0;
	        		}
	        		counter++;
	        	}
	        	handler.postDelayed(this,1000/60);
	       }
       });
	}
	
	public class GameView extends View {
		
		public GameView(Context context) {
			super(context);
		}
	}

	@Override
	public void onDestroy () {
		try {
        	this.running = false;
			MySoundPool.stop();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		super.onDestroy();
	}

	@Override
    protected void onStop() {
		try {
        	this.running = false;
			MySoundPool.stop();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		finish();
        super.onStop();
    }
	
	@Override
    protected void onPause() {
		try {
        	this.running = false;
			MySoundPool.stop();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		finish();
        super.onPause();
    }
	
	public void onBackPressed() {
        	this.running = false;
			try {
				MySoundPool.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.onBackPressed();
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x1 = event.getX();
			y1 = event.getY();
			break;
		case MotionEvent.ACTION_UP:
			x2 = event.getX();
			y2 = event.getY();
			
			if (y2-y1 > 100)
				mecanica.andar(-1);
			else if (y1-y2 > 100)
				mecanica.andar(1);
			
			if (x2-x1 > 100)
				mecanica.girar(-90);
			else if (x1-x2 > 100)
				mecanica.girar(90);
            break;
		}
		return true;
	}
}
