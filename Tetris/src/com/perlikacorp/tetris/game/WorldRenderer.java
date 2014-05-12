package com.perlikacorp.tetris.game;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * 
 * WorldRenderer
 * -------------------------------------
 * Implementa la vista del juego
 * @author apasos
 *
 */
public class WorldRenderer {
	
    private static final String[] COLORS = {"white","green","blue","orange","pink","purple","red","gray"};
    /**
     * Referencia al modelo
     */
    World world;
    
    /**
     * La camara a dibujar
     */
    OrthographicCamera  cam;
    
    /**
     * El atlas (las imagenes)
     */
    public TextureAtlas atlas;
    
    /**
     * El objeto que dibuja las cosas en pantalla
     */
    SpriteBatch batch;    
    
    BitmapFont font;
    
    
    private int i,j;
    /**
     *  constructor
     * @param world referencia a el modelo
     * @param manager reference al recoletor de recursos
     */
    public WorldRenderer(World world, AssetManager manager) {
        this.world = world;
        this.cam = new OrthographicCamera(480, 800);
        this.cam.position.set(240, 400,0f);
        this.cam.update();
        this.atlas = manager.get("game.atlas", TextureAtlas.class);
        this.batch = new SpriteBatch();
        this.batch.setProjectionMatrix(cam.combined);
        this.font = manager.get("default-32.fnt",BitmapFont.class);
    }
    

    /**
     * Main method to draw objects
     * @param delta
     */
    public void render(float delta) {

    	batch.begin();
    	
    	
    	
    	font.draw(batch, "Puntos: " + world.state.score, 0, 780);

    	font.draw(batch, "Nivel: " + world.state.nivel, 300, 780);

    	font.draw(batch, "Siguiente: ", 0, 740);
    	
    	//Dibujar tablero
    	for (i=0;i<GameState.COLUMNAS;i++){
    		for (j=0;j<GameState.FILAS;j++){
    			batch.draw(atlas.findRegion(COLORS[world.state.tablero[j][i]]), 80+32*j, 64+32*i,32,32);
    		}
    	}
        
    	Piece piece = world.state.currentPiece;
    	//Dibujar pieza
    	for (i=0;i<4;i++){
    		for(j=0;j<4;j++){
    			if (world.state.currentPiece.type[i][j]){
    				batch.draw(atlas.findRegion(COLORS[world.state.currentPiece.typePiece+1]), 80+32*(piece.x+(j-2)),64+(piece.y+(i-2))*32,32,32);
    			}//else{
    				//batch.draw(atlas.findRegion("white"), 80+32*(piece.x+(j-2)),64+(piece.y+(i-2))*32,32,32);
    			//}
    		}
    	}
    	
    	//Dibujar pieza
    	for (i=0;i<4;i++){
    		for(j=0;j<4;j++){
    			if (world.state.nextPiece.type[i][j]){
    				batch.draw(atlas.findRegion(COLORS[world.state.nextPiece.typePiece+1]), 200+16*((j-2)),730+((i-2))*16,16,16);
    			}//else{
    				//batch.draw(atlas.findRegion("white"), 80+32*(piece.x+(j-2)),64+(piece.y+(i-2))*32,32,32);
    			//}
    		}
    	}
    	font.draw(batch, "Objetivo: " + world.state.puntosObjetivo, 0, 700);
    	font.draw(batch, "Velocidad: " + world.state.timeStep, 0, 650);
    	font.draw(batch, "Lineas: " + world.state.lines, 0, 600);
    	font.draw(batch, "Tiempo: " + world.state.time, 0, 550);
    	
        batch.end();
        
    }
  
}
