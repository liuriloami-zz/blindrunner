package osphi.blindrunner;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class RankingActivity extends Activity {

	private ArrayList<String> arrayList1, arrayList2;
	private ArrayAdapter<String> arrayAdapter1, arrayAdapter2;
	private ListView ranking1, ranking2;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.ranking);
	    
	    
	    ranking1 = (ListView) findViewById(R.id.list_ranking1);
	    arrayList1 = new ArrayList<String>();
        arrayAdapter1 = new ArrayAdapter<String>(this, 
                android.R.layout.simple_list_item_1, arrayList1);
        ranking1.setAdapter(arrayAdapter1);
        ranking1.setBackgroundColor(Color.rgb(240, 200, 200));
        

        Parse.initialize(this, "Cr0SXNNmmGnwAYerZF9TOy4xsCxjwFaLVqHaKDzV", "qsdq10QJeav0hPICoTWAwXBEjC7p8mwMS1UJbMtU");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("vitoria");
        query.orderByAscending("tempo");
        query.findInBackground(new FindCallback<ParseObject>() {
		@Override
		public void done(List<ParseObject> objects, ParseException e) {
			while (objects.size() != 0) {
				arrayList1.add(String.format("%-10d %s", objects.get(0).getInt("tempo"), objects.get(0).getString("email")));
				objects.remove(0);
			}
			arrayAdapter1.notifyDataSetChanged();
		}
        });
        
        ranking2 = (ListView) findViewById(R.id.list_ranking2);
	    arrayList2 = new ArrayList<String>();
        arrayAdapter2 = new ArrayAdapter<String>(this, 
                android.R.layout.simple_list_item_1, arrayList2);
        ranking2.setAdapter(arrayAdapter2);
        ranking2.setBackgroundColor(Color.rgb(200, 240, 200));
        
        query = ParseQuery.getQuery("derrota");
        query.orderByDescending("tempo");
        query.findInBackground(new FindCallback<ParseObject>() {
		@Override
		public void done(List<ParseObject> objects, ParseException e) {
			while (objects.size() != 0) {
				arrayList2.add(String.format("%-10d %s", objects.get(0).getInt("tempo"), objects.get(0).getString("email")));
				objects.remove(0);
			}
			arrayAdapter2.notifyDataSetChanged();
		}
        });
        
        ParseQuery.clearAllCachedResults();
        
	}	
}

