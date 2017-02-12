package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Database name, version, table, and column names
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "RegInfo.db";
	public static final String TABLE_NAME = "RegInfo";
	public static final String COL_ID = "ID";
	public static final String COL_FIRSTNAME = "FIRSTNAME";
	public static final String COL_LASTNAME = "LASTNAME";
	public static final String COL_STUDENTNUM = "STUDENTNUM";
	public static final String COL_PASSWORD = "PASSWORD";

	public static final String TABLE_CREATE = "create table RegInfo (ID INTEGER PRIMARY KEY not null auto_increment , " + "FIRSTNAME text not null , LASTNAME text not null, STUDENTNUM text not null, PASSWORD text not null);";
			
			
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Create database table
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT, LASTNAME TEXT, STUDENTNUM TEXT, PASSWORD TEXT)" );
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	// Insert items into database
	public boolean insertData(String firstname, String lastname, String studentnum, String password){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		
		String query = "select * from RegInfo";
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
