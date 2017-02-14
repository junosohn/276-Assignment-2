package com.example.assignment2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class RectangleActivity extends Activity {
		
	EditText INPUT;
	Button btnCreateGrid;
	int num;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rectangle);
		
		INPUT = (EditText)findViewById(R.id.input);
		btnCreateGrid = (Button) findViewById(R.id.btn_creategrid);
		checkInput();
	}
	
	// Getter function for value num (n)
	public int getNum() {
		return num;
	}

	// Check if n >= 15, output AlertDialog message
	private void checkInput() {
		btnCreateGrid.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String i = INPUT.getText().toString();
				
				if(!i.matches("")) { // if not Empty
					num = Integer.parseInt(i);
					if(num > 15 || num == 0) { // n > 15
						showMessage(getString(R.string.rError), getString(R.string.rectangleerror));
					}
					else { // VALID INPUT			
						Toast.makeText(RectangleActivity.this, getString(R.string.click), Toast.LENGTH_SHORT).show();
						
						// Opens fragment for Rectangle Game
						FragmentManager FM = getFragmentManager();
						FragmentTransaction FT = FM.beginTransaction();
						RectangleFragment rfrag = new RectangleFragment();
						FT.add(R.id.fragmentrectangle, rfrag);
						FT.commit();
					}
				}
				else { // Empty input message
					Toast.makeText(RectangleActivity.this, getString(R.string.emptyinput), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	// AlertDialog for n>15 input
	public void showMessage(String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setCancelable(false);
		
		// CONTINUE Button
		builder.setPositiveButton(getResources().getString(R.string.cont), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Do nothing
			}
		});
		// EXIT Button
		builder.setNegativeButton(getResources().getString(R.string.exit), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish(); // Exit
			}
		});
		
		builder.show();
	}

	// Back button returns to MenuActivity
	public void onBackPressed() {
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rectangle, menu);
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

