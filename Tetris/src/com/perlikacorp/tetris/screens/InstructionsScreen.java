package com.perlikacorp.tetris.screens;

import java.util.Locale;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.perlikacorp.tetris.TetrisGame;
import com.perlikacorp.tetris.game.World;
import com.perlikacorp.tetris.services.MusicManager.GameMusic;

/**
 * La pantalla de opciones
 */
public class InstructionsScreen extends AbstractScreen
{
	protected World world;

    public InstructionsScreen(
        TetrisGame game )
    {
        super( game );
        world = game.world;
        
    }

    @Override
    public void show()
    {
        super.show();

        Table table = super.getTable();
        

        Label titleLabel = new Label("Instrucciones",getSkin(),"title");
        table.defaults().spaceBottom( 30 );
        table.columnDefaults( 3 );
        System.out.println(world.topScores.curSlide);
        Image image = new Image(game.getAssetManager().get("game.atlas",TextureAtlas.class).findRegion("slide" + world.topScores.curSlide));
        table.setBackground(image.getDrawable());
        
        table.add( titleLabel ).colspan(3).padBottom(600);
        


        
        table.row();

        if (world.topScores.curSlide>1){
            // register the back button
            TextButton nextButton = new TextButton( "Anterior", getSkin() );
            nextButton.addListener( new ClickListener() {
            	 @Override
    	         public void clicked (InputEvent event, float x, float y)
    	         {
            		world.topScores.curSlide--;
                    game.setScreen( new InstructionsScreen( game ) );
                }
            } );
            table.add( nextButton ).size( 100, 80 );
        	
        }


        // register the back button
        TextButton backButton = new TextButton( "Back", getSkin() );
        backButton.addListener( new ClickListener() {
        	 @Override
	         public void clicked (InputEvent event, float x, float y)
	         {
                game.setScreen( new MenuScreen( game ) );
            }
        } );
        table.add( backButton ).size( 100, 80 );

        
        if (world.topScores.curSlide<world.topScores.slides){
            // register the back button
            TextButton prevButton = new TextButton( "Siguiente", getSkin() );
            prevButton.addListener( new ClickListener() {
            	 @Override
    	         public void clicked (InputEvent event, float x, float y)
    	         {
             		world.topScores.curSlide++;

                    game.setScreen( new InstructionsScreen( game ) );
                }
            } );
            table.add( prevButton ).size( 100, 80 );
        	
        }

        if (world.topScores.curSlide+1<=world.topScores.slides){
        
	        table.addAction(Actions.delay(3f, new Action(){
	
				@Override
				public boolean act(float delta) {
	         		world.topScores.curSlide++;
	
	                game.setScreen( new InstructionsScreen( game ) );
	                return true;
				}
	        	
	        }));
	        
        }
    }

    
    protected void backAction(){
    	game.setScreen( new MenuScreen( game ) );
    }
    

}