package osphi.blindrunner;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * Essa atividade representa a tela do "About" do jogo
 */

public class GetAccountActivity extends Activity {

	public static String texto = new String();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		StringBuilder buf = new StringBuilder();
	    InputStream input = null;
		try {
			input = getAssets().open("failSubmit.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		BufferedReader in=
	        new BufferedReader(new InputStreamReader(input));
	    String str;

	    try {
			while ((str=in.readLine()) != null) {
			  buf.append("  ");
			  buf.append(str);
			  buf.append('\n');
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		setContentView(R.layout.failsubmit);
		TextView textView = (TextView) findViewById(R.id.failsubmit);
		textView.setText(buf.toString().toCharArray(), 0, buf.length());
		Typeface typeFace = Typeface.createFromAsset(getAssets(), "font.ttf");
		textView.setTypeface(typeFace);	
		textView.setGravity(Gravity.CENTER);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		textView.setTextSize(Math.min(metrics.widthPixels, metrics.heightPixels)/20);
		
	}	
}



