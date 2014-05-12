package com.perlikacorp.tetris.client;

import com.perlikacorp.tetris.GoogleInterface;

public class MyGoogleInterface implements GoogleInterface{

	@Override
	public void Login() {
	}

	@Override
	public void LogOut() {
	}

	@Override
	public boolean getSignedIn() {
		return false;
	}

	@Override
	public void submitScore(String leaderboard, int score) {
	}

	@Override
	public void getScores(String leaderboard) {
	}

	@Override
	public void getAchievements() {
	}


	@Override
	public void unlockAchievement(String ach) {
	}

}
