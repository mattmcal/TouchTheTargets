package com.mattmcal.touchTheTargets;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EndActivity extends Activity
{
	private LinearLayout layout;
	private TextView scoreText;
	private Button homeBtn;
	private Button restartBtn;
	private ImageView image;
	private OnClickListener listener;
	
	private static int HOME_BTN = 0;
	private static int RESTART_BTN = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setGravity(Gravity.CENTER);
		layout.setBackgroundColor(Color.BLACK);
		
		image = new ImageView(this);
		image.setImageResource(R.drawable.target_large);
		
		scoreText = new TextView(this);
		scoreText.setTextColor(Color.WHITE);
		scoreText.setTextSize(32);
		scoreText.setGravity(Gravity.CENTER);
		scoreText.setPadding(0, 20, 0, 20);
		scoreText.setText("Final Score: " + getIntent().getIntExtra("score", -1));
		
		listener = new MyOnClickListener();
		
		homeBtn = new Button(this);
		homeBtn.setText("Home");
		homeBtn.setTextSize(24);
		homeBtn.setOnClickListener(listener);
		homeBtn.setId(HOME_BTN);
		
		restartBtn = new Button(this);
		restartBtn.setText("Restart");
		restartBtn.setTextSize(24);
		restartBtn.setOnClickListener(listener);
		restartBtn.setId(RESTART_BTN);
		
		layout.addView(image);
		layout.addView(scoreText);
		layout.addView(homeBtn);
		layout.addView(restartBtn);
		
		setContentView(layout);
	}
	
	private class MyOnClickListener implements OnClickListener
	{
		@Override
		public void onClick(View btn)
		{
			if (btn.getId() == HOME_BTN)
			{
				finish();
				Intent intent = new Intent(EndActivity.this, MainActivity.class);
				startActivity(intent);
			}
			else if (btn.getId() == RESTART_BTN)
			{
				finish();
				Intent intent = new Intent(EndActivity.this, GameActivity.class);
				startActivity(intent);
			}
		}
	}
}
