package dorian.since;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class MainActivity extends Activity {

	private static TextView tv;
	private static Button button;
	private int year;
	private int month;
	private int day;
	private long days;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment(), "dialog").commit();
		}
		
		final Calendar c = Calendar.getInstance();
	    year = c.get(Calendar.YEAR);
	    month = c.get(Calendar.MONTH);
	    day = c.get(Calendar.DAY_OF_MONTH);
		days = 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == 99) {
			return new DatePickerDialog(this, new OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int y, int m,
						int d) {
					Calendar c1 = Calendar.getInstance();
					Calendar c = Calendar.getInstance();
					c1.set(Calendar.YEAR, y);
					c1.set(Calendar.MONTH, m);
					c1.set(Calendar.DATE, d);
					
					long diff = c.getTimeInMillis() - c1.getTimeInMillis();
					days = diff/(1000 * 60 * 60 * 24);
//					PlaceholderFragment pf = new PlaceholderFragment();
//					pf.updateText(Long.toString(days));
					Toast.makeText(getApplicationContext(), "It is " + days + " days!!!",
						     Toast.LENGTH_SHORT).show();
				}
			}, year, month, day);
		} else if (id == 1) { 
			return new Dialog(this);
		} else {
			return null;
		}
	}
	
	void showDialogs() {
//	    mStackLevel++;
//
//	    // DialogFragment.show() will take care of adding the fragment
//	    // in a transaction.  We also want to remove any currently showing
//	    // dialog, so make our own transaction and take care of that here.
//	    FragmentTransaction ft = getFragmentManager().beginTransaction();
//	    Fragment prev = getFragmentManager().findFragmentByTag("dialog");
//	    if (prev != null) {
//	        ft.remove(prev);
//	    }
//	    ft.addToBackStack(null);
//
//	    // Create and show the dialog.
//	    PlaceholderFragment newFragment = PlaceholderFragment.newInstance(1);
//	    newFragment.show(ft, "dialog");
		
		showDialog(99);
		
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends DialogFragment implements OnDateSetListener {

		public PlaceholderFragment() {
		}

		public void updateText(String text) {
			
		}
		
		public static PlaceholderFragment newInstance(int num) {
			PlaceholderFragment f = new PlaceholderFragment();

			// Supply num input as an argument.
			Bundle args = new Bundle();
			args.putInt("num", num);
			f.setArguments(args);

			return f;	
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			
			setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo);
		}
		
//		@Override
//		public Dialog onCreateDialog(Bundle savedInstanceState) {
//			// Use the current date as the default date in the picker
//		    
//
//		    // Create a new instance of DatePickerDialog and return it
//		    return new DatePickerDialog(getActivity(), this, year, month, day);
//		    
//		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			tv.setText(new StringBuilder().append(year).append("-").append(monthOfYear).append("-").append(dayOfMonth));
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			button = (Button) rootView.findViewById(R.id.button1);
			tv = (TextView) rootView.findViewById(R.id.textView1);
			tv.setText("Hi shine!");
			button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Log.d("wh","button clicked");
					((MainActivity)getActivity()).showDialogs();
				}
			});
			
			return rootView;
		}
	}

}
