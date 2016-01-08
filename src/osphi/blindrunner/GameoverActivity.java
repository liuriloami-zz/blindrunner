package osphi.blindrunner;

import com.parse.Parse;
import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

public class GameoverActivity extends Activity{
	
	private ParseObject object;
	private String email;
	private int tempo;
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gameover);
		TextView textView = (TextView) findViewById(R.id.game_over);
		Typeface typeFace = Typeface.createFromAsset(getAssets(), "font2.ttf");
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		textView.setTextSize(Math.min(metrics.widthPixels, metrics.heightPixels)/24.0f);
		textView.setTypeface(typeFace);
		textView.setGravity(Gravity.CENTER);
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		tempo = (int)getIntent().getExtras().getLong("tempo");
		textView.setText("You lose!\nScore: " + Integer.toString(tempo));
		
		Parse.initialize(this, "Cr0SXNNmmGnwAYerZF9TOy4xsCxjwFaLVqHaKDzV", "qsdq10QJeav0hPICoTWAwXBEjC7p8mwMS1UJbMtU");
        object = new ParseObject("vitoria");
        email = UserEmailFetcher.getEmail(getBaseContext());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				if (email == null){
		        	Intent intent = new Intent(getBaseContext(), GetAccountActivity.class);
		       	 	startActivity(intent);
		        	finish();
		        }
		        else {
		        	object.put("email", email);
		            object.put("tempo", tempo);
		            object.saveInBackground();
		        }
			}
        }, 2000);
        
	}
}
