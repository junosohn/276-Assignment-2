package com.example.assignment2;

import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class LoginActivity extends Activity {

	DatabaseHelper myDb;
	
	DatabaseHelper helper = new DatabaseHelper(this);
	
	EditText editFirstname, editLastname, editStudentnum, editPassword;
	Button btnsignin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);	
				
		editFirstname = (EditText) findViewById(R.id.input_firstname);
		editLastname = (EditText) findViewById(R.id.input_lastname);
		editStudentnum = (EditText) findViewById(R.id.input_studentID);
		editPassword = (EditText) findViewById(R.id.input_password);
		btnsignin = (Button) findViewById(R.id.btn_help);
		signIn();
	}	
	
	
	public void signIn(){
		btnsignin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String password = helper.searchPass(
						editFirstname.getText().toString(),
						editLastname.getText().toString(),
						editStudentnum.getText().toString());
				
				// Check input for spaces & special characters
				if(check(editPassword.getText().toString())){
					showMessage(getString(R.string.error),getString(R.string.loginError));
				}
				// Password is correct!
				else if(editPassword.getText().toString().equals(password)){
						Intent intent = new Intent(v.getContext(), FlowActivity.class);
						startActivity(intent);				
				}
				else // Incorrect password
					Toast.makeText(LoginActivity.this, getString(R.string.wrongpassword), Toast.LENGTH_LONG).show();
			}
		});
	}
	
	// Check if password contains spaces or special characters
	private boolean check(String p){
		if(Pattern.compile(" ").matcher(p).find()  || 
		   Pattern.compile("[_$&+,:;=?@#|'<>.^*()%!-]").matcher(p).find() ){
			return true;
		}
		else{
			return false;
		}
	}
	
		
	// AlertDialog for invalid password
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
		getMenuInflater().inflate(R.menu.login, menu);
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
