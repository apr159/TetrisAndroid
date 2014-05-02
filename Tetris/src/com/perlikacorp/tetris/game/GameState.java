package com.perlikacorp.tetris.game;

import java.util.Random;

public class GameState implements Pieces{

											
	public static final float INITIAL_TIME = 1f;
	public static final float REDUCE_TIME = 0.1f;
	public static final int INCREASE_POINTS = 50;
	public static final int POINTS = 100;

	public static Random random = new Random();
	public static final int FILAS = 10;
	public static final int COLUMNAS = 20;
	
	int[][] tablero;
	
	Piece currentPiece = new Piece();
	Piece nextPiece = new Piece();
	//int rotation;
	//int piece;
	public int score;
	int puntosObjetivo;
	float timeStep;
	int nivel;
	boolean pausa;
	
	public GameState(){
		tablero = new int[FILAS][COLUMNAS];
		int rotation = 0;
		int piece = random.nextInt(NUM_PIECES);
		currentPiece.startPiece(piece,rotation);
		piece = random.nextInt(NUM_PIECES);
		nextPiece.startPiece(piece,rotation);
		pausa = false;
		
		timeStep = INITIAL_TIME;
		score = 0;
		nivel = 1;
		puntosObjetivo = INCREASE_POINTS;
	}
	
	public void rotateLeft(){
		currentPiece.rotateLeft();
	}
	
	public void rotateRight(){
		currentPiece.rotateRight();
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
	
	public void nextPiece(){
		currentPiece.startPiece(nextPiece.typePiece,0);
		int piece = random.nextInt(NUM_PIECES);
		nextPiece.startPiece(piece,0);

	}
	
	
}
