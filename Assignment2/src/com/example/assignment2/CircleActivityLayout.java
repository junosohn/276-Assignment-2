package com.example.assignment2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Random;
import android.view.View.OnClickListener;

public class CircleActivityLayout extends View {
		
	static int n;
	static String d;
	
	Bitmap circleBitmap[];
	int circle_x[], circle_y[], x[], y[];
	int circleHeight, circleWidth;	
	boolean clicked[];
	static int score = 0;
	static int missed = 0;
	int temp = 0;

	
	public CircleActivityLayout(Context context) {
		super(context);
		
		n = CircleActivity.getNum();
		d = CircleActivity.getDifficulty();
		
		// Set variables
		circle_x = new int[n];
		circle_y = new int[n];
		circleBitmap = new Bitmap[n];
		x = new int[n];
		y = new int[n];
		clicked = new boolean[n];
		
		for(int i=0; i<n; i++) {
			// Set starting coordinates
			circle_x[i] = randomNum();
			circle_y[i] = randomNum();
			
			// Set each circle as 'not clicked'
			clicked[i] = false;
		}
		
				
		// Difficulties (harder = faster movement & smaller circle size)
		if(d.matches("easy")) {
			for(int i=0; i<n; i++) {
				x[i]=1;
				y[i]=1;
				circleBitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.easy_circle);
			}
		}
		else if(d.matches("medium")) {
			for(int i=0; i<n; i++) {
				x[i]=5;
				y[i]=5;
				circleBitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.medium_circle);
			}
		}
		else if(d.matches("difficult")) {
			for(int i=0; i<n; i++) {
				x[i]=10;
				y[i]=10;
				circleBitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.difficult_circle);
			}
		}
	}

	// Random number generator
	private int randomNum() {
		int min = 0;
		int max = 500;
		Random rand = new Random();
		int randomNum = rand.nextInt((max-min) + 1) + min;
		return randomNum;
	}

	// Check if circle has been 'clicked'
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		float xTouch = event.getX();
		float yTouch = event.getY();
		
		switch(action) {
		case MotionEvent.ACTION_DOWN:
			for(int i=0; i<n; i++) {
				// Touched Bitmap
				if( xTouch >= circle_x[i] &&
					xTouch < circle_x[i] + circleBitmap[i].getWidth() && 
					yTouch >= circle_y[i] && 
					yTouch < circle_y[i] + circleBitmap[i].getHeight()) {
					
					// Set circle at 'i' as clicked==true
					clicked[i] = true;
					return true;
				}
			}
		}
		return false;
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
			
		// Movement + collision detection
		for(int i=0; i<n; i++) {
			if(circle_x[i] >= canvas.getWidth() - 100) {
				x[i] = Math.abs(x[i]) * -1;
				circle_x[i] += x[i];
			}
			else if(circle_x[i] <= 0) {
				x[i] = Math.abs(x[i]);
				circle_x[i] += x[i];
			}
			else if(circle_y[i] >= canvas.getHeight() - 100) {
				y[i] = Math.abs(y[i]) * -1;
				circle_y[i] += y[i];
			}
			else if(circle_y[i] <= 0) {
				y[i] = Math.abs(y[i]);
				circle_y[i] += y[i];
			}
			else {
				if(randomNum() > 250) {
					circle_x[i] += x[i];
				}
				else {
					circle_x[i] -= x[i];
				}
				
				if(randomNum() > 250) {
					circle_y[i] += y[i];
				}
				else {
					circle_y[i] -= y[i];
				}					
			}
		}
		
		// Create circles
		for(int i=0; i<n; i++) {
			if(clicked[i] == false) {
				canvas.drawBitmap(circleBitmap[i], circle_x[i], circle_y[i], null);
			}
		}

		// Set score to be passed back to CircleGameActivity
		setScore();
		
		// Redraw everything
		invalidate();
	}
	
	// Set score
	public void setScore() {
		temp = 0;
		for(int i=0; i<n; i++) {
			if(clicked[i] == true) {
				temp++;
			}
		}
		score = temp;
	}
	
	// Getter function for score
	public static int getScore() {
		return score;
	}

	
}
