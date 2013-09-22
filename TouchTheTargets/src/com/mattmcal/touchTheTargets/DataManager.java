package com.mattmcal.touchTheTargets;

import java.util.Random;

public class DataManager
{
	private int score;
	private int time;
	private int target;
	private int prevTarget;
	private Random randomGen;
	
	DataManager()
	{
		randomGen = new Random();
		score = 0;
		time = 30;
		prevTarget = -1;
		target = randomGen.nextInt(24);
	}
	
	public int getScore() { return score; }
	public int incScore() { return ++score; }
	public int getTime() { return time;	}
	public int decTime() { return --time; }
	public int getPrevTarget() { return prevTarget; }
	public int getCurTarget() { return target; }
	
	public int getNewTarget() 
	{ 
		prevTarget = target;
		return target = randomGen.nextInt(24);
	}
}
