package com.perlikacorp.tetris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.perlikacorp.tetris.GoogleInterface;
import com.perlikacorp.tetris.TetrisGame;
import com.perlikacorp.tetris.dialog.YesNoDialog;
import com.perlikacorp.tetris.game.World.GameListener;
import com.perlikacorp.tetris.services.ScreenshotFactory;
import com.perlikacorp.tetris.services.TetrisSound;

public class GameScreen extends AbstractGameScreen
implements GameListener{

	private YesNoDialog dialogo;
	
	public GameScreen(TetrisGame game) {
		super(game);
		dialogo = new YesNoDialog("",getSkin());
		world.addGameListener(this);
	}
	
	@Override
	public void show(){
		super.show();
		
		world.startGame();
		
		 InputMultiplexer multiplexer = new InputMultiplexer();
		 multiplexer.addProcessor(stage);
		 multiplexer.addProcessor(new MyInputProcessor());
		 Gdx.input.setInputProcessor(multiplexer);	
	     Gdx.input.setCatchBackKey(true);
		
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
        
        final TextButton opcionesButton = new TextButton("Pantallazo",getSkin());
        opcionesButton.addListener(new ClickListener(){
        	@Override
            public void clicked (InputEvent event, float x, float y)
            {
     	       ScreenshotFactory.saveScreenshot();

            }

        });
        
        table.add(opcionesButton).size(200,50).padTop(50).padLeft(270);
        table.row();

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

	@Override
	public void explosion() {
		if (world.state.lines==1){
			game.getGoogleInterface().submitScore(GoogleInterface.TIEMPO_LINEA_1_LEADERBOARD, (int)world.state.time*1000);
		}
		game.getSoundManager().play(TetrisSound.DESTROY);
		
	}

	@Override
	public void pegadoATablero() {
		game.getSoundManager().play(TetrisSound.DOWN);
		
	}
	
	 protected void backAction(){
	       final ClickListener yesListener = new ClickListener(){
	        	public void clicked (InputEvent event, float x, float y)
	            {
	            	game.setScreen(new MenuScreen(game));
	            }
	        };
	       final ClickListener noListener = new ClickListener(){
	        	public void clicked (InputEvent event, float x, float y)
	            {
	        		world.retornar();
	            	dialogo.hide();
	            }
	        };
	world.pausa();
  	dialogo.show(stage, "Salir del juego  \n Estas seguro?", yesListener, noListener);
  }
	
	 @Override
	public void render(float delta){
	        stage.act( delta );

	        Gdx.gl.glClearColor( 0,0,0, 1f );
	        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

	        // update and draw the stage actors
	        stage.draw();
	        Table.drawDebug(stage);
	        
	        if (world!=null && !world.enPausa()) {
	            world.update(delta);
	            renderer.render(delta);
	        }
	 }
	 
	 
	 class MyInputProcessor implements InputProcessor{
			@Override
			public boolean keyDown(int keycode) {
				if (keycode==Keys.LEFT){
					world.izquierdaPressed();
				}
				if (keycode==Keys.RIGHT){
					world.derechaPressed();
				}
				if (keycode==Keys.DOWN){
					world.rapido();
				}
				
				return true;
			}

			@Override
			public boolean keyUp(int keycode) {
				
				if (keycode==Keys.BACK || keycode==Keys.ENTER){
					  backAction();
					}
				
				if (keycode==Keys.LEFT){
					world.izquierdaDrop();
				}
				if (keycode==Keys.RIGHT){
					world.derechaDrop();
				}
				if (keycode==Keys.DOWN){
					world.normal();
				}
				if (keycode==Keys.UP){
					world.rotar();
				}
				
				return true;
			}

			@Override
			public boolean keyTyped(char character) {
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer,
					int button) {
				return true;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer,
					int button) {		
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY,
					int pointer) {
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {return false;}

			@Override
			public boolean scrolled(int amount) {return false;}
		}

}
