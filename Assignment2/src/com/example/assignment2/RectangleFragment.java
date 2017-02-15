package com.example.assignment2;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

// Use fragment inside RectangleActivity for the grid
public class RectangleFragment extends Fragment {

	TableLayout table;
	static int n;
	Button buttons[][];

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_rectangle_fragment, container, false);
		table = (TableLayout)v.findViewById(R.id.tableForButtons);
		createGrid();
		return v;
	}
	
	// Create grid
	private void createGrid() {
		n = ((RectangleActivity)getActivity()).getNum();
		buttons = new Button[n][n];

		for(int row = 0; row<n; row++) {
			TableRow tableRow = new TableRow(RectangleFragment.this.getActivity());
			// Scale row
			tableRow.setLayoutParams(new TableLayout.LayoutParams(
					TableLayout.LayoutParams.MATCH_PARENT,
					TableLayout.LayoutParams.MATCH_PARENT,
					1.0f));
			table.addView(tableRow);			
			
			for(int col = 0; col<n; col++) {
				final int FINAL_COL = col;
				final int FINAL_ROW = row;
				
				Button button = new Button(RectangleFragment.this.getActivity());
				// Scale column
				button.setLayoutParams(new TableRow.LayoutParams(
						TableRow.LayoutParams.MATCH_PARENT,
						TableRow.LayoutParams.MATCH_PARENT,
						1.0f));
				
				button.setPadding(0, 0, 0, 0);
				
				// Programatically 'click' the buttons diagonally
				button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int i = 0;
						for(int row = n-1; row >= 0; row--) {
							for(int col = n-1; col >= i; col--) {
								colourButton(col, row);
							}
							i++;
						}
					}
				});
				
				tableRow.addView(button);
				buttons[row][col] = button;
			}
		}
	}

	// Change button colour 
	private void colourButton(int col, int row) {
		Button button = buttons[row][col];
		lockButtonSizes();
		
		// Scale image to button
		int newWidth = button.getWidth();
		int newHeight = button.getHeight();
		Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.red);
		Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
		Resources resource = getResources();
		button.setBackground(new BitmapDrawable(resource, scaledBitmap));
	}

	// Lock size of button
	private void lockButtonSizes() {
		for(int row = 0; row<n; row++) {
			for(int col = 0; col<n; col++) {
				Button button = buttons[row][col];
				
				int width = button.getWidth();
				button.setMinWidth(width);
				button.setMaxWidth(width);
				
				int height = button.getHeight();
				button.setMinHeight(height);
				button.setMaxHeight(height);
			}
		}		
	}
	
}
