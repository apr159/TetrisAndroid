package com.perlikacorp.tetris.screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.perlikacorp.tetris.services.MusicManager.GameMusic;
import com.perlikacorp.tetris.TetrisGame;
import com.perlikacorp.tetris.services.TetrisSound;


/**
 * Pantalla splash ( se encarga de cargar los recursos)
 * @author apasos
 *
 */
public class SplashScreen extends AbstractScreen {

    public SplashScreen(TetrisGame game){
        super( game );
    }

    @Override
    public void show()
    {
    	super.show();
    	
        // Mostrar imagen de inicio
        Table table = super.getTable();
        Texture back = new Texture( "splash.jpg" );
        back.setFilter( TextureFilter.Linear, TextureFilter.Linear );
    	TextureRegion backRegion = new TextureRegion( back, 0, 0, 480, 800 );
        table.setBackground(new TextureRegionDrawable(backRegion));
        
        //2. Cargar todas las cosas necesarias para el juego
	    game.manager.load("game.atlas", com.badlogic.gdx.graphics.g2d.TextureAtlas.class);
	    game.manager.load("default-32.fnt", BitmapFont.class);
	//    game.manager.load("default-16.fnt", BitmapFont.class);
	    game.getAssetManager().load(TetrisSound.DESTROY.getFileName(), Sound.class);
	    game.getAssetManager().load(TetrisSound.DOWN.getFileName(), Sound.class);
	    game.getAssetManager().load(GameMusic.GAME.getFileName(), Music.class);


	    super.resize(800,480);
    }

    @Override
   public void render(float delta){
	   super.render(delta);
	   //cuando se terminen de cargar las cosas ir a pantalla de menu
	   if (game.manager.update()){
		   stage.addAction( Actions.sequence( Actions.delay( 0.25f ), 
				   							  Actions.fadeOut( 0.75f ),
									            new Action() {
									                @Override
									                public boolean act(
									                    float delta )
									                {
									                	game.loadWorld();
									                	game.setScreen(new MenuScreen(game));
									                    return true;
									                }
									            } ) );	   
	   }
	   
   }
  

    @Override
    public void dispose()
    {
        super.dispose();
    }
}
