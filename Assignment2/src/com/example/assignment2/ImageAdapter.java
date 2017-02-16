package com.example.assignment2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter {

	private String imageID;
	private byte[] imageByteArray;
	
	// Getter for imageID
	public String getImageID() {
		return imageID;
	}

	// Setter for imageID
	public void setImageId(String imageID) {
		this.imageID = imageID;
	}
	
	// Getter for the byte array
	public byte[] getImageByteArray() {
		return imageByteArray;
	}
	
	// Setter for the byte array
	public void setImageByteArray(byte[] imageByteArray) {
		this.imageByteArray = imageByteArray;
	}
	
}
