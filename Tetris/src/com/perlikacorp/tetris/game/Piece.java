package com.perlikacorp.tetris.game;

public class Piece implements Pieces{

	public boolean[][] type;
	public int x,y;
	int typePiece;
	int rotation;
	
	public void setPiece(int typePiece, int rotation){
		this.typePiece = typePiece;
		this.rotation = rotation;
		this.type = PIECES[typePiece][rotation];
			
	}
	
	public void startPiece(int typePiece, int rotation){
		this.typePiece = typePiece;
		this.rotation = rotation;

		x = 5;
		y = 18;
		this.type =  PIECES[typePiece][rotation];
			
	}
	
	public void rotateLeft(){
		rotation++;
		if (rotation>=4) rotation = 0;
		setPiece(typePiece,rotation);

	}
	
	public void rotateRight(){
		rotation--;
		if (rotation<0) rotation = 3;
		setPiece(typePiece,rotation);

	}
	
	public void fall(){
		y--;
	}
	
	
	public boolean collisionLeft(int x){
		boolean result = false;
		if (x>=2) return false;
		for (int i=0;i<4;i++){
			if (type[i][1-x]) result = true;
		}
		
		return result;
	}

	public boolean collisionRight(int x){
		
		
		boolean result = false;
		if (x<=8) return false;
		for (int i=0;i<4;i++){
			if (type[i][3-(x-9)]) result = true;
		}
		
		return result;
	}

	
	public boolean collidesBoard(int[][] board){
		
		for (int i=0;i<4;i++){
			for (int j=0;j<4;j++){
				if (type[i][j]){
					if (getGlobalY(i)<0) return true;
					if (getGlobalX(j)<0) return true;
					if (getGlobalX(j)>=10) return true;
					if (board[getGlobalX(j)][getGlobalY(i)]!=0){
						return true;

					}
				}
			}
		}
		
		return false;
	}
	
	public void pegarATablero(int[][] board, int piece){
		y = y+1;
		for (int i=0;i<4;i++){
			for (int j=0;j<4;j++){
				if (type[i][j] && getGlobalY(i)>=0){
					board[getGlobalX(j)][getGlobalY(i)] =  piece;
				}
			}
		}
		
	}
	
	private int getGlobalX(int px){
		return x-2+px;
	}
	
	private int getGlobalY(int py){
		return y-2+py;
	}

}
