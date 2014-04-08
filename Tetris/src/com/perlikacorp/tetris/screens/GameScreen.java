package com.perlikacorp.tetris.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.perlikacorp.tetris.TetrisGame;
import com.perlikacorp.tetris.game.World.GameListener;

public class GameScreen extends AbstractGameScreen
implements GameListener{

	public GameScreen(TetrisGame game) {
		super(game);
		
		world.addGameListener(this);
		
        Table table = super.getTable();
        table.setBackground("background");
        
        
        
        
        Button izquierdaButton = new Button( getSkin(),"izquierda" );
        
        izquierdaButton.addListener( new ClickListener() {
        	 @Override
         	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
             	super.touchDown(event, x, y, pointer, button);
             	world.izquierdaPressed();
             	return true;
             }
             
             @Override
         	public void touchUp (InputEvent event, float x, float y, int pointer, int button){
         		world.izquierdaDrop();
         	}
        } );
        
        Button derechaButton = new Button( getSkin(),"derecha" );
        
        derechaButton.addListener( new ClickListener() {
        	 @Override
         	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
             	super.touchDown(event, x, y, pointer, button);
             	world.derechaPressed();
             	return true;
             }
             
             @Override
         	public void touchUp (InputEvent event, float x, float y, int pointer, int button){
         		world.derechaDrop();
         	}       
        } );
        
        Button rotarButton = new Button( getSkin(),"rotar" );
        
        rotarButton.addListener( new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y)
            {
            	world.rotar();
            }
            
        } );
        
        Button abajoButton = new Button( getSkin(),"abajo" );
        
        abajoButton.addListener( new ClickListener() {
            @Override
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	super.touchDown(event, x, y, pointer, button);
            	world.rapido();
            	return true;
            }
            
            @Override
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button){
        		world.normal();
        	}
        });
        
        
        
        table.add(izquierdaButton).size( 64, 64 ).expand().bottom().left();
        table.add(derechaButton).size( 64, 64 ).expand().bottom().right();

        table.row();
        table.add(rotarButton).size( 64, 64 ).bottom().left();
        table.add(abajoButton).size( 64, 64 ).bottom().right();

        

	}

	@Override
	public void endGame() {
		game.setScreen(new GameOverScreen(game));
		
	}

}
