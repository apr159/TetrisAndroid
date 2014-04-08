package com.perlikacorp.tetris.game;

public class World {

	public GameState state;
	boolean derechaPressed, izquierdaPressed;
	
	public interface GameListener{
		public void endGame();
	}
	GameListener listener;
	public void addGameListener(GameListener listener){
		this.listener = listener;
	}
	
	
	public World(){
		state = new GameState();
		time = 0;
		pressTime = 0;
	}
	private float time = 0;
	private float pressTime = 0;
	private static final float MAX_PRESS_TIME = 0.1f;
	
	public void update(float delta){
		time+=delta;
		if (time>=state.timeStep){
			time = 0;
			state.moveDown();
			
			checarColision();

		}
		pressTime+=delta;
		if (pressTime>=MAX_PRESS_TIME){
			pressTime = 0;
			if (izquierdaPressed){
				state.moveLeft();
				if (checarColisionLados())
					state.moveRight();
			}
			if (derechaPressed){
				state.moveRight();
				if (checarColisionLados())
					state.moveLeft();
			}
		}
	}
	
	private boolean checarColisionLados(){
		if (state.currentPiece.collidesBoard(state.tablero)){
			return true;
		}else{
			return false;
		}
	}
	
	private void checarColision(){
		if (state.currentPiece.collidesBoard(state.tablero)){
			state.currentPiece.pegarATablero(state.tablero);
			checarLinea();
			state.setPiece();
			if (state.currentPiece.collidesBoard(state.tablero)){
				if (listener!=null) listener.endGame();
			}
		}
	}
	
	private void checarLinea(){
		for (int i=0;i<GameState.COLUMNAS;i++){
			boolean lleno = true;
			for (int j=0;j<GameState.FILAS;j++){
	//			System.out.print(tablero[j][i] + " " );
				if (!state.tablero[j][i]) lleno = false;
				
				
				
			}
	//		System.out.println();
			if (lleno){
				vaciarFila(i);
				aplicarGravedad(i);
				i = i-1;
			}
		}
	}
	
	private void vaciarFila(int fila){
		state.score = state.score + 100;
		for (int i=0;i<GameState.FILAS;i++){
			state.tablero[i][fila] = false;
		}
	}
	
	private void aplicarGravedad(int fila){
		for (int i=fila;i<GameState.COLUMNAS-1;i++){
			for (int j=0;j<GameState.FILAS;j++){
				state.tablero[j][i] = state.tablero[j][i+1];
			}
		}
	}

	

	
	public void derechaPressed(){
		derechaPressed = true;
	}
	
	public void izquierdaPressed(){
		izquierdaPressed = true;
	}
	
	public void derechaDrop(){
		derechaPressed = false;
	}
	
	public void izquierdaDrop(){
		izquierdaPressed = false;
	}
	
	public void rotar(){
		state.rotateLeft();
		if (state.currentPiece.collidesBoard(state.tablero)){
			state.rotateRight();
		}
	}
	float oldSpeed;
	public void rapido(){
		oldSpeed = state.timeStep;
		state.timeStep = 0.1f;
	}
	
	public void normal(){
		state.timeStep = oldSpeed;
	}
	
	public void reiniciar(){
		state = new GameState();
	}
}
