package com.perlikacorp.tetris.game;

import java.util.Random;

public class GameState implements Pieces{

											
	public static final float INITIAL_TIME = 1f;
	public static final float REDUCE_TIME = 0.1f;
	public static Random random = new Random();
	public static final int FILAS = 10;
	public static final int COLUMNAS = 20;
	
	boolean[][] tablero;
	
	Piece currentPiece = new Piece();
	int rotation;
	int piece;
	public int score;
	float timeStep;
	
	public GameState(){
		tablero = new boolean[FILAS][COLUMNAS];
		rotation = 0;
		piece = random.nextInt(NUM_PIECES);
		currentPiece.startPiece(PIECES[piece][rotation]);
		timeStep = INITIAL_TIME;
		score = 0;
	}
	
	public void rotateLeft(){
		rotation++;
		if (rotation>=4) rotation = 0;
		currentPiece.setPiece(PIECES[piece][rotation]);
	}
	
	public void rotateRight(){
		rotation--;
		if (rotation<0) rotation = 3;
		currentPiece.setPiece(PIECES[piece][rotation]);

	}
	
	public void moveLeft(){
		if (!currentPiece.collisionLeft(currentPiece.x-1)){
			currentPiece.x = currentPiece.x-1;
			
		}
	}
	
	public void moveRight(){
		if (!currentPiece.collisionRight(currentPiece.x+1)){
			currentPiece.x = currentPiece.x+1;
			
		}
	}
	
	
	public void moveDown(){
		currentPiece.y--;
	}
	
	public void setPiece(){
		rotation = 0;
		piece = random.nextInt(NUM_PIECES);
		currentPiece.startPiece(PIECES[piece][rotation]);

	}
	
}
