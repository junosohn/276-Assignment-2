package com.example.assignment2;

import java.io.ByteArrayOutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "RegInfo2.db";
	
	// RegInfo Table
	public static final String TABLE_NAME = "RegInfo2";
	public static final String COL_ID = "ID";
	public static final String COL_FIRSTNAME = "FIRSTNAME";
	public static final String COL_LASTNAME = "LASTNAME";
	public static final String COL_STUDENTNUM = "STUDENTNUM";
	public static final String COL_PASSWORD = "PASSWORD";

	// Images Table
	public static final String TABLE_IMAGE = "Images2";
	public static final String COL_ID2 = "col_id";
	public static final String COL_IMAGE_ID = "image_id";
	public static final String COL_IMAGE_BITMAP = "image_bitmap";
	
	// Create tables
	public static final String TABLE_CREATE = "create table RegInfo2 (ID INTEGER PRIMARY KEY not null auto_increment , " 
				+ "FIRSTNAME text not null , LASTNAME text not null, STUDENTNUM text not null, PASSWORD text not null);";
	public static final String TABLE_CREATEIMAGE = "create table Images2 (col_id INTEGER PRIMARY KEY not null auto_increment , "
				+ "image_id text not null , image_bitmap text not null);";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Create database table
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT, LASTNAME TEXT, STUDENTNUM TEXT, PASSWORD TEXT)" );
		db.execSQL("create table " + TABLE_IMAGE + " (col_id INTEGER PRIMARY KEY AUTOINCREMENT, image_id TEXT, image_bitmap TEXT)" );
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE);
		onCreate(db);
	}
	
	// Insert items into database
	public boolean insertData(String firstname, String lastname, String studentnum, String password){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		
		String query = "select * from RegInfo2";
		Cursor cursor = db.rawQuery(query, null);
		int count = cursor.getCount();
		
		contentValues.put(COL_ID, count);
		contentValues.put(COL_FIRSTNAME, firstname);
		contentValues.put(COL_LASTNAME, lastname);
		contentValues.put(COL_STUDENTNUM, studentnum);
		contentValues.put(COL_PASSWORD, password);
		long result = db.insert(TABLE_NAME, null, contentValues);
		db.close();
		if(result == -1){
			return false;
		}
		else
			return true;
	}
	
	// Insert images into database
	public void insertImage(Drawable dbDrawable, String imageID){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cvalues = new ContentValues();
	
		String query = "select * from Images2";

		cvalues.put(COL_IMAGE_ID, imageID);
		Bitmap bm = ((BitmapDrawable)dbDrawable).getBitmap();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
		cvalues.put(COL_IMAGE_BITMAP, stream.toByteArray());
		db.insert(TABLE_IMAGE, null, cvalues);
		db.close();
	}

	// Get images from database
	public ImageAdapter getImage(String imageID) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor c = db.query(TABLE_IMAGE, new String[] {COL_ID2, COL_IMAGE_ID, COL_IMAGE_BITMAP}, 
				COL_IMAGE_ID + " LIKE '" + imageID + "%'", null, null, null, null);
		
		ImageAdapter imageAdapter = new ImageAdapter();
		
		if(c.moveToFirst()) {
			do {
				imageAdapter.setImageId(c.getString(1));
				imageAdapter.setImageByteArray(c.getBlob(2));
			} while(c.moveToNext());
		}
		
		c.close();
		db.close();
		return imageAdapter;		
	}
	
	// Searches database for matching first name, last name, student number, and password
	public String searchPass(String fname, String lname, String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "select FIRSTNAME, LASTNAME, STUDENTNUM, PASSWORD from " + TABLE_NAME;
		Cursor cursor = db.rawQuery(query, null);
		String a,b,c,d;
		d = "NOT FOUND";
		
		if(cursor.moveToFirst()){
			do{
				a = cursor.getString(0);
				b = cursor.getString(1);
				c = cursor.getString(2);
				
				if(a.equals(fname) && b.equals(lname) && c.equals(id)){
					d = cursor.getString(3);
					break;
				}
			}while(cursor.moveToNext());
		}
		
		return d;
	}
	
	// Get data
	public Cursor getAllData() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
		return res;
	}
	
	// Delete data
	public Integer deleteData(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
	}
	
}
