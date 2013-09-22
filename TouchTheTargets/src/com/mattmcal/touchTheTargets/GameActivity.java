package com.mattmcal.touchTheTargets;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameActivity extends Activity
{
	// display
	private TableLayout table;
	private TableRow[] rows;
	private ImageButton[] buttons;
	private TextView timeText;
	private TextView scoreText;
	private ColorFilter filter;
	
	// data
	private DataManager dataManager;
	
	// constants
	private static final String TIME = "Time Remaining: ";
	private static final String SCORE = "Score: ";
	private static final int NUM_ROWS = 6;
	private static final int NUM_COLS = 4;
	private static final int NUM_BTNS = NUM_ROWS * NUM_COLS;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		dataManager = new DataManager();
		
		//initialize table and background
		table = new TableLayout(this);
		filter = new LightingColorFilter( Color.BLACK, Color.BLACK);
		getWindow().getDecorView().setBackgroundColor(Color.BLACK);
		
		// initialize buttons 
		buttons = new ImageButton[NUM_BTNS];
		for (int i = 0; i < NUM_BTNS; i++)
		{
			buttons[i] = new ImageButton(this);
			buttons[i].setBackgroundColor(Color.TRANSPARENT);
			buttons[i].setImageResource(R.drawable.small_target);
			buttons[i].setId(i);
			buttons[i].setColorFilter(filter);
			buttons[i].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					handleClick(v.getId());
				}
			});
			
			// start game timer
			new CountDownTimer(31000, 1000){
				@Override
				public void onFinish() {
					endGame();
				}
				@Override
				public void onTick(long millisUntilFinished) {
					timeText.setText(TIME + millisUntilFinished / 1000);
				}
			}.start();
		}
		
		// add buttons to table rows, add rows to table
		rows = new TableRow[NUM_ROWS];
		for (int row = 0; row < NUM_ROWS; row++)
		{
			rows[row] = new TableRow(this);
			rows[row].setGravity(Gravity.CENTER);
			for (int col = 0; col < NUM_COLS; col++)
				rows[row].addView(buttons[NUM_COLS * row + col]);
			table.addView(rows[row]);
		}
		
		// initialize text 
		timeText = new TextView(this);
		timeText.setText(TIME + dataManager.getTime());
		timeText.setGravity(Gravity.CENTER);
		timeText.setTextSize(20);
		timeText.setTextColor(Color.WHITE);
		scoreText = new TextView(this);
		scoreText.setText(SCORE + dataManager.getScore());
		scoreText.setGravity(Gravity.CENTER);
		scoreText.setTextSize(20);
		scoreText.setTextColor(Color.WHITE);
		
		table.addView(scoreText);
		table.addView(timeText);
		setContentView(table);
		
		paintTarget();
	}
	
	private void handleClick(int id)
	{
		if (id == dataManager.getCurTarget())
		{
			dataManager.getNewTarget();
			paintTarget();
			dataManager.incScore();
			scoreText.setText(SCORE + dataManager.getScore());
		}
	}
	
	private void paintTarget()
	{
		// cover old target
		if (dataManager.getPrevTarget() != -1)
			buttons[dataManager.getPrevTarget()].setColorFilter(filter);
		
		// unveil new target
		buttons[dataManager.getCurTarget()].clearColorFilter();
	}
	
	private void endGame()
	{
		timeText.setText(TIME + 0);
		finish();
		Intent intent = new Intent(this, EndActivity.class);
		intent.putExtra("score", dataManager.getScore());
		startActivity(intent);
	}
}
