package com.example.assignment2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CircleGameActivity extends Activity {

	CircleActivityLayout LayoutView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		LayoutView = new CircleActivityLayout(this);
		setContentView(LayoutView);
	}

	// AlertDialog to show final score
	public void showMessage(String title, String message, int score) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message + String.valueOf(score) + "\n" 
						+ getString(R.string.missed) + (CircleActivity.getNum() - score));		
		builder.setCancelable(false);
		
		// EXIT Button
		builder.setNegativeButton(getResources().getString(R.string.exit), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish(); // Exit
			}
		});
		
		builder.show();

	}
	
	// Back button returns to CircleActivity
	public void onBackPressed() {
		int score = CircleActivityLayout.getScore();
		showMessage(getString(R.string.scoremessage),getString(R.string.score), score);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.circle_game, menu);
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
}
