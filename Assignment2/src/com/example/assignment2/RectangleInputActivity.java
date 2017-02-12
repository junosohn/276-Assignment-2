package com.example.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RectangleInputActivity extends Activity {

	EditText INPUT;
	Button btnCreateGrid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rectangle_input);
		
		INPUT = (EditText)findViewById(R.id.input);
		btnCreateGrid = (Button) findViewById(R.id.btn_creategrid);
		checkInput();
		
	}

	// Check if n >= 15, output AlertDialog message
	private void checkInput() {
		btnCreateGrid.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String i = INPUT.getText().toString();
				int num = Integer.parseInt(i);
				
				if(num > 15) {
					Toast.makeText(RectangleInputActivity.this, "n>15", Toast.LENGTH_SHORT).show();
				}
				else {
					
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rectangle_input, menu);
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
