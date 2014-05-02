package com.perlikacorp.tetris;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication
implements GoogleInterface{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        
        initialize(new TetrisGame(this), cfg);
    }

	@Override
	public void Login() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getSignedIn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void submitScore(int score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getScores() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAchievements() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getScoresData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlockAchievement(String ach) {
		// TODO Auto-generated method stub
		
	}
}