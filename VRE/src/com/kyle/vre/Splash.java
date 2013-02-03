package com.kyle.vre;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

public class Splash extends Activity {

	private final static int SPLASH_TIME = 3000;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spash);
		
	}
	
	
	@Override
	protected void onResume(){
		super.onResume();
		
		Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		int orientation = display.getRotation();

		if(orientation==Surface.ROTATION_180) {
		      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
		}
		
		
		new Handler().postDelayed(new Runnable(){
			
			public void run() {
				Splash.this.finish();
				
				Intent mainIntent = new Intent(Splash.this, MainActivity.class);
				Splash.this.startActivity(mainIntent);
			}
					
		}, SPLASH_TIME);
		
		
	}
	


}
