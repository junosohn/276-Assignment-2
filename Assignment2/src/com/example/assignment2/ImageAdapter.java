package com.example.assignment2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context CTX;
	private Integer image_id[] = {R.drawable.image_0, R.drawable.image_1, 
			R.drawable.image_2, R.drawable.image_3, 
			R.drawable.image_4, R.drawable.image_5, 
			R.drawable.image_6, R.drawable.image_7
	};
	
	public ImageAdapter(Context CTX) {
		this.CTX = CTX;
		
	}
	
	@Override
	public int getCount() {
		return image_id.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView img;
		if(convertView == null){
			img = new ImageView(CTX);
			img.setLayoutParams(new GridView.LayoutParams(300,300));
			img.setScaleType(ImageView.ScaleType.CENTER_CROP);
			img.setPadding(8, 8, 8, 8);
		}
		else{
			img = (ImageView) convertView;
		}
		
		img.setImageResource(image_id[position]);
		return img;
	}

}
