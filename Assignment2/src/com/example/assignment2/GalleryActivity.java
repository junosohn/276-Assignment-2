package com.example.assignment2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

public class GalleryActivity extends Activity {

	GridView grid_view;
	public static final String IMAGE_ID = "IMG_ID";
	private final String TAG = "GalleryActivity";
	
	DatabaseHelper myDb;
	ImageView imageView;
	Button btn1, btn2, btn3, btn4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		
		btn1 = (Button)findViewById(R.id.button1);
		btn2 = (Button)findViewById(R.id.button2);
		btn3 = (Button)findViewById(R.id.button3);
		btn4 = (Button)findViewById(R.id.button4);


		myDb = new DatabaseHelper(this);
		imageView = (ImageView)findViewById(R.id.imageView);
		
		// Set image fetch to each button
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getImage(1);
			}
		});
		
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getImage(2);
			}
		});

		btn3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getImage(3);
			}
		});

		btn4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getImage(4);
			}
		});
	
	}

	// Get image by ID
	private void getImage(int i) {

		if (i == 1) {
			Drawable dbDrawable = getResources().getDrawable(R.drawable.image_0);
			myDb.insertImage(dbDrawable, IMAGE_ID);
		}
		else if (i == 2) {
			Drawable dbDrawable = getResources().getDrawable(R.drawable.image_1);
			myDb.insertImage(dbDrawable, IMAGE_ID);
		}
		else if (i == 3) {
			Drawable dbDrawable = getResources().getDrawable(R.drawable.image_2);
			myDb.insertImage(dbDrawable, IMAGE_ID);
		}
		else if (i == 4) {
			Drawable dbDrawable = getResources().getDrawable(R.drawable.image_3);
			myDb.insertImage(dbDrawable, IMAGE_ID);
		}
		new LoadImage().execute(0);				
	}

	// Loading message for images
	private class LoadImage extends AsyncTask <Integer, Integer, ImageAdapter> {
		private final ProgressDialog LoadImageProgressDialog = new ProgressDialog(GalleryActivity.this);
		
		// Loading dialog
		protected void onPreExecute() {
			this.LoadImageProgressDialog.setMessage("Loading Image from database:");
			this.LoadImageProgressDialog.show();
		}
		
		// Get image in background
		@Override
		protected ImageAdapter doInBackground(Integer...integers) {
			Log.d("LoadImage : background", "");
			return myDb.getImage(IMAGE_ID);
		}
		
		protected void onProgressUpdate(Integer...progress) {
		}
		
		// Finished loading
		protected void onPostExecute(ImageAdapter imageAdapter) {
			Log.d("LoadImage : onPostExecute - IMAGEID ", imageAdapter.getImageID());
			if(this.LoadImageProgressDialog.isShowing()) {
				this.LoadImageProgressDialog.dismiss();
			}
			setUpImage(imageAdapter.getImageByteArray());
		}
	}
	
	// Setup images in bitmap
	private void setUpImage(byte[] bytes) {
		Log.d(TAG, "Decoding bytes");
		Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		imageView.setImageBitmap(bitmap);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gallery, menu);
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
