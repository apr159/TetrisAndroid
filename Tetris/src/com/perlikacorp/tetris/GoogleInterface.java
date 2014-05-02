package com.perlikacorp.tetris;




/**
 * GoogleInterface
 * ---------------------------------------
 * Define la conexion con google game services para
 * manejar las puntuaciones
 * 
 */

public interface GoogleInterface {

	/**
	 * Intenta un login en google game services
	 */
	public void Login();
	
	/**
	 * Intenta un logout en google game services
	 */
	public void LogOut();
	
	/**
	 * Checa si el cliente esta conectado
	 * @return true si esta conectado
	 */
	public boolean getSignedIn();
	
	/**
	 * sube una puntuacion a la tabla de puntuaciones
	 * @param score los puntos
	 */
	public void submitScore(int score);
	
	/**
	 * Obtiene los puntos y los dibuja en la pantalla base de android
	 */
	public void getScores();
	
	/**
	 * Retorna los logros obtenidos por el usuario y lo dibuja en la pantalla base de android
	 */
	public void getAchievements();
	
	
	/**
	 * retorna la puntuacion obtendia
	 */
	public void getScoresData();
	
	/**
	 * Desbloquea un logro
	 * @param ach el id del logro a desbloquear
	 */
	public void unlockAchievement(String ach);
	

}
