package com.kyle.vre;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView updateText;

	private Handler guiThread;
	private ScheduledExecutorService scheduler;
	private ScheduledFuture schedulerHandle;
	private Runnable updateTask;



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViews();
		
		
		setupThreading();
		setListeners();

	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		
		schedulerHandle = scheduler.scheduleAtFixedRate(updateTask, 10, 20, TimeUnit.SECONDS);
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.about:
			startActivity(new Intent(this, About.class));
			return true;

		}

		return false;

	}
	
	@Override
	public void onPause(){
		super.onPause();
		schedulerHandle.cancel(true);
		
	}

	private void findViews(){

		updateText = (TextView) findViewById(R.id.updateTime);

	}

	private void setupThreading(){

		Log.d("VRE", "init threading");
		
		guiThread = new Handler();

		scheduler = Executors.newScheduledThreadPool(1);

		updateTask = new Runnable() {
			public void run() {
				InputStream response;
				
				Log.d("VRE", "Hitting web service");
				
				try {
					String v = getResources().getString(R.string.vehicle_position_url);
					response = new URL(v).openStream();
						
					
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
		
		//schedulerHandle = scheduler.scheduleAtFixedRate(updateTask, 10, 20, TimeUnit.SECONDS);
				
		
		
	}

	private void setListeners(){

			
		
	}


}
