package com.example.assignment2;

import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class SignUpActivity extends Activity {

	DatabaseHelper myDb;
	EditText editFirstname, editLastname, editStudentnum, editPassword;
	Button btnAddData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		myDb = new DatabaseHelper(this);
		
		editFirstname = (EditText) findViewById(R.id.input_firstname);
		editLastname = (EditText) findViewById(R.id.input_lastname);
		editStudentnum = (EditText) findViewById(R.id.input_studentID);
		editPassword = (EditText) findViewById(R.id.input_password);
		btnAddData = (Button) findViewById(R.id.btn_register);
		AddData();
	}
	
	public void AddData() {
		btnAddData.setOnClickListener(new View.OnClickListener() {
			
			 // Password check (1 uppercase, 1 number, length 6)
			@Override
			public void onClick(View v) {
				if( (editPassword.getText().toString().length() == 6) && check(editPassword.getText().toString())){
					boolean isInserted = myDb.insertData(
							editFirstname.getText().toString(), 
							editLastname.getText().toString(), 
							editStudentnum.getText().toString(), 
							editPassword.getText().toString() );
					
					// Output success message
					if(isInserted = true)
						Toast.makeText(SignUpActivity.this, getString(R.string.signin_success), Toast.LENGTH_LONG).show();
					
					// Return to main menu
					Intent intent = new Intent(v.getContext(), EntryActivity.class);
					startActivity(intent);				

				}
				else	// Invalid password
					Toast.makeText(SignUpActivity.this, getString(R.string.invalid_pw), Toast.LENGTH_LONG).show();
			}
		});
	}
	
	// Check if password contains at least one number and one uppercase letter
	private boolean check(String p){
		if(Pattern.compile("[0-9]").matcher(p).find()  && 
		   Pattern.compile("[A-Z]").matcher(p).find() ){
			return true;
		}
		else{
			return false;
		}
	}

	
	public void showMessage(String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
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
