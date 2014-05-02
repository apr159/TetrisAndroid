package com.perlikacorp.tetris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.perlikacorp.tetris.TetrisGame;

/**
 * Clase para definir la estructura general de una pantalla
 * @author apasos
 *
 */
public class AbstractScreen implements Screen{
	
	/**
	 * Tamaño de la pantalla
	 */
    public static final int VIEWPORT_WIDTH = 480, VIEWPORT_HEIGHT = 800;
	
    /**
     * Color de fondo
     */
    protected Color color = new Color(0f, 0.0f,0f,1f);
    
    /**
     * Referencia a la clase base del juego
     */
	 protected final TetrisGame game;
	 
	 /**
	  * Objecto para colocar todos los compoenentes graficos
	  */
	 protected final Stage stage;

	 
	 /**
	  *  Objeto para asignar los graficos
	  */
	 private Skin skin;
	 
	 /**
	  * El atlas de las imagenes a usar
	  */
	 private TextureAtlas atlas;
	 
	 /**
	  * Elemento superior en la jeararquia UI
	  */
	 private Table table;
	 
	 

	 /**
	  * Constructor base para todas las pantallas
	  * @param game referencia al objeto juego
	  */
	public AbstractScreen(
	        TetrisGame game )
	{
	     this.game = game;
	     
	     int width = ( isGameScreen() ? VIEWPORT_WIDTH : VIEWPORT_WIDTH );
	     int height = ( isGameScreen() ? VIEWPORT_HEIGHT : VIEWPORT_HEIGHT );
	     this.stage = new Stage( width, height, true );
	     
	     
	}

	/**
	 * Verificar el nombre de la clase
	 * @return el nombre de la clase como string
	 */
	 protected String getName()
	 {
	     return getClass().getSimpleName();
	 }

	 /**
	  * Verifca si es una clase juego
	  * @return true si es una clase juego
	  */
	 protected boolean isGameScreen()
	 {
		 return false;    
	 }

	 
	 /**
	  * Inicializa el skin (si no esta definido) y lo retorna
	  * @return el skin
	  */
	 protected Skin getSkin()
	    {
	        if( skin == null ) {
	            skin = new Skin( Gdx.files.internal( "uiskin.json" ), new TextureAtlas( "uiskin.atlas" ) );
	            skin.getFont("default-font").getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	            skin.getFont("text-font").getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	            
	        }
	        return skin;
	    }

	/**
	 * @return la tabla base
	 */
	protected Table getTable()
	 {
	     if( table == null ) {
             table = new Table( getSkin() );          
             table.setFillParent( true );
	         if( TetrisGame.DEV_MODE ) {
	             table.debug();
	         }
	         stage.addActor( table );
	     }
	     return table;
	 }
	 
	 @Override
	 public void show()
	 {
	     Gdx.app.log( TetrisGame.LOG, "Mostrar pantalla: " + getName() );
	     Gdx.input.setInputProcessor( stage );
	 }

	@Override
	 public void resize(
	     int width, 
	     int height )
	 {
        Gdx.app.log( TetrisGame.LOG, "Redimensionar pantalla: " + getName() + " a: " + width + " x " + height );
	 }

	 @Override
	 public void render(
	     float delta )
	 {
		 	//1. Run stage
		 	stage.act( delta );

		 	//2. Clear screen
	        Gdx.gl.glClearColor( color.r, color.g, color.b, 1f );
	        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
	        
	        //3. Draw stage
	        stage.draw();
	        if (TetrisGame.DEV_MODE) Table.drawDebug(stage);
	 }

	 @Override
	 public void hide()
	 {
	     Gdx.app.log( TetrisGame.LOG, "Ocultar pantalla: " + getName() );
	     dispose();
	 }

	 @Override
	 public void pause()
	 {
	     Gdx.app.log( TetrisGame.LOG, "Pausa pantalla: " + getName() );
	 }

	 @Override
	 public void resume()
	 {
	     Gdx.app.log( TetrisGame.LOG, "Retorna pantalla: " + getName() );
	 }

	 @Override
	 public void dispose()
	 {
	     Gdx.app.log( TetrisGame.LOG, "Salir de pantalla: " + getName() );
    //   stage.dispose();
	     if (skin!=null) skin.dispose();
	     if (atlas!=null) atlas.dispose();

	 }
	 
	 protected void setBackAction(){
		 InputMultiplexer multiplexer = new InputMultiplexer();
		 multiplexer.addProcessor(stage);
		 multiplexer.addProcessor(new MyInputProcessor());
		 Gdx.input.setInputProcessor(multiplexer);	
	     Gdx.input.setCatchBackKey(true);

	 }
	 protected void backAction(){}
	 class MyInputProcessor implements InputProcessor{
			@Override
			public boolean keyDown(int keycode) {
				return true;
			}

			@Override
			public boolean keyUp(int keycode) {
				
				if (keycode==Keys.BACK || keycode==Keys.ENTER){
					  backAction();
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
