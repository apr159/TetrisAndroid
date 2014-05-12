package com.perlikacorp.tetris;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;

public class MainActivity extends AndroidApplication
implements GoogleInterface,GameHelperListener{
	
    public static final int REQUEST_ACHIEVEMENTS = 100;
    public static final int REQUEST_LEADERBOARD = 102;

	/**
	 * Game helper (para google play services)
	 */
	private GameHelper gameHelper;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		gameHelper = new GameHelper(this,1);
		
    	gameHelper.setup(this);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        
        initialize(new TetrisGame(this), cfg);
    }

	@Override
	public void Login() {
		try {
    		runOnUiThread(new Runnable(){

    			//@Override
    			public void run(){
    				gameHelper.beginUserInitiatedSignIn();
    			}
    		});
    	}catch (final Exception ex){

    	}
		
	}

	@Override
	public void LogOut() {
		try {
    		runOnUiThread(new Runnable(){

    			//@Override
    			public void run(){
    				gameHelper.signOut();
    			}
    		});
    	}catch (final Exception ex){

    	}

		
	}

	@Override
	public boolean getSignedIn() {
		return (gameHelper.isSignedIn());
	}

	@Override
	public void submitScore(String leaderboard, int score) {
		if (!getSignedIn()) return;
		Games.Leaderboards.submitScore(gameHelper.getApiClient(), leaderboard, score);
    	
		
	}

	@Override
	public void getScores(String leaderboard) {
		if (!getSignedIn()) return;
		startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), leaderboard),REQUEST_LEADERBOARD);
    	
		
	}

	@Override
	public void getAchievements() {
		startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()),REQUEST_ACHIEVEMENTS);

		
	}


	@Override
	public void unlockAchievement(String ach) {
		Games.Achievements.unlock(gameHelper.getApiClient(), ach);
		
	}

	@Override
	public void onSignInFailed() {
    	System.out.println("sign in failed");

		
	}

	@Override
	public void onSignInSucceeded() {
    	System.out.println("sign in suceed");

		
	}
}