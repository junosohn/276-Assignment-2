package com.example.assignment2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CircleActivity extends Activity {

	EditText inputNum, inputDifficulty;
	Button btnStart;
	static int num = 0;
	static String difficulty = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_circle);
		inputNum = (EditText)findViewById(R.id.input);
		inputDifficulty = (EditText)findViewById(R.id.inputDifficulty);
		btnStart = (Button) findViewById(R.id.btnStart);
		checkInput();
	}

	// Getter function for value num (n)
	public static int getNum() {
		return num;
	}

	// Setter function for value num
	public static void setNum(int num) {
		CircleActivity.num = num;
	}

	// Getter function for difficulty
	public static String getDifficulty() {
		return difficulty;
	}

	// Setter function for difficulty
	public static void setDifficulty(String difficulty) {
		CircleActivity.difficulty = difficulty;
	}
	
	// Check if n >= 10, output AlertDialog message
	private void checkInput() {
		btnStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String n = inputNum.getText().toString();
				difficulty = inputDifficulty.getText().toString();

				if(!n.matches("")) { // if not Empty
					num = Integer.parseInt(n);
					
					if(num > 10 || num == 0) { // n > 10
						showMessage(getString(R.string.rError), getString(R.string.circleerror));
					}
					else if(!difficulty.matches("easy") && !difficulty.matches("medium") && !difficulty.matches("difficult")) {
						showMessage(getString(R.string.rError), getString(R.string.dError));
					}
					else { // VALID INPUT -> CircleGameActivity
						setNum(num);
						setDifficulty(difficulty);
						Intent intent = new Intent(v.getContext(), CircleGameActivity.class);
						startActivity(intent);
					}
				}
				else { // Empty input message
					Toast.makeText(CircleActivity.this, getString(R.string.emptyinput), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	
	// AlertDialog for n>10 input
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
		getMenuInflater().inflate(R.menu.circle, menu);
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
