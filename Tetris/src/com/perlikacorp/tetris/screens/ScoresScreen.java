package com.perlikacorp.tetris.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.perlikacorp.tetris.GoogleInterface;
import com.perlikacorp.tetris.TetrisGame;

/**
 * La pantalla de opciones
 */
public class ScoresScreen extends AbstractScreen
{

    public ScoresScreen(
        TetrisGame game )
    {
        super( game );
    }

    @Override
    public void show()
    {
        super.show();

        Table table = super.getTable();
        

        Label titleLabel = new Label("Lideres",getSkin(),"title");
        table.defaults().spaceBottom( 30 );
        table.columnDefaults( 0 ).padRight( 20 );
        table.add(titleLabel);
        
        // register the back button
        TextButton pontsButton = new TextButton( "Puntos", getSkin() );
        pontsButton.addListener( new ClickListener() {
        	 @Override
	         public void clicked (InputEvent event, float x, float y)
	         {
                game.getGoogleInterface().getScores(GoogleInterface.PUNTOS_LEADERBOARD);
            }
        } );
        table.row();
        table.add( pontsButton ).size( 200, 60 ).colspan( 3 );

        // register the back button
        TextButton linesButton = new TextButton( "Lineas", getSkin() );
        linesButton.addListener( new ClickListener() {
        	 @Override
	         public void clicked (InputEvent event, float x, float y)
	         {
                 game.getGoogleInterface().getScores(GoogleInterface.LINEAS_LEADERBOARD);
            }
        } );
        table.row();
        table.add( linesButton ).size( 200, 60 ).colspan( 3 );

        // register the back button
        TextButton timeButton = new TextButton( "Tiempo", getSkin() );
        timeButton.addListener( new ClickListener() {
        	 @Override
	         public void clicked (InputEvent event, float x, float y)
	         {
                 game.getGoogleInterface().getScores(GoogleInterface.TIEMPO_LEADERBOARD);

            }
        } );
        table.row();
        table.add( timeButton ).size( 200, 60 ).colspan( 3 );

        
        // register the back button
        TextButton tiempo1Button = new TextButton( "Tiempo Para L1", getSkin() );
        tiempo1Button.addListener( new ClickListener() {
        	 @Override
	         public void clicked (InputEvent event, float x, float y)
	         {
                 game.getGoogleInterface().getScores(GoogleInterface.TIEMPO_LINEA_1_LEADERBOARD);
            }
        } );
        table.row();
        table.add( tiempo1Button ).size( 200, 60 ).colspan( 3 );
        
        
        // register the back button
        TextButton backButton = new TextButton( "Back", getSkin() );
        backButton.addListener( new ClickListener() {
        	 @Override
	         public void clicked (InputEvent event, float x, float y)
	         {
                game.setScreen( new MenuScreen( game ) );
            }
        } );
        table.row();
        table.add( backButton ).size( 200, 60 ).colspan( 3 );
    }


    
    protected void backAction(){
    	game.setScreen( new MenuScreen( game ) );
    }
    

}