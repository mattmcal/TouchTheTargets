package com.mattmcal.touchTheTargets;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().getDecorView().setBackgroundColor(Color.BLACK);		
		setContentView(R.layout.activity_main);
	}

	public void startGame(View view)
	{
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}
}
