package com.perlikacorp.tetris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.perlikacorp.tetris.dialog.YesNoDialog;
import com.perlikacorp.tetris.services.MusicManager.GameMusic;
import com.perlikacorp.tetris.TetrisGame;

public class MenuScreen extends AbstractScreen {

		private YesNoDialog dialogo;
	
	    public MenuScreen(
	        TetrisGame game )
	    {
	        super( game );
	        dialogo = new YesNoDialog("",getSkin());
	    }

	    @Override
	    public void show()
	    {
	        super.show();
	        
	        setBackAction();
	        if (game.getPreferencesManager().isMusicEnabled()){
	        	game.getMusicManager().play(GameMusic.GAME);
	        }
	        Table table = super.getTable();
	        table.setBackground("background");
	        
	        
	        
	        Label titleLabel = new Label("El Tetris",getSkin(),"title");
	        table.add(titleLabel);
	        table.row();
	        
	        TextButton startGameButton = new TextButton( "Jugar", getSkin() );
	        
	        startGameButton.addListener( new ClickListener() {
	            @Override
	            public void clicked (InputEvent event, float x, float y)
	            {
	            	game.setScreen(new GameScreen(game));
	            }
	        } );
	        table.add( startGameButton ).size( 300, 80 );
	        table.row();

	        TextButton opcionesButton = new TextButton("Opciones",getSkin());
	        opcionesButton.addListener(new ClickListener(){
	            public void clicked (InputEvent event, float x, float y)
	            {
	            	game.setScreen(new OptionsScreen(game));
	            }

	        });
	        
	        table.add( opcionesButton ).size( 300, 80 );
	        table.row();
	        
	        TextButton leaderboardButton = new TextButton("Mejores",getSkin());
	        leaderboardButton.addListener(new ClickListener(){
	            public void clicked (InputEvent event, float x, float y)
	            {
	            	game.setScreen(new ScoresScreen(game));
	            }

	        });
	        
	        table.add( leaderboardButton ).size( 300, 80 );
	        table.row();
	        
	        TextButton instructionsButton = new TextButton("Instrucciones",getSkin());
	        instructionsButton.addListener(new ClickListener(){
	            public void clicked (InputEvent event, float x, float y)
	            {
	            	game.setScreen(new InstructionsScreen(game));
	            }

	        });
	        
	        table.add( instructionsButton ).size( 300, 80 );
	        table.row();
	        
	       final ClickListener yesListener = new ClickListener(){
	        	public void clicked (InputEvent event, float x, float y)
	            {
	            	Gdx.app.exit();
	            }
	        };
	       final ClickListener noListener = new ClickListener(){
	        	public void clicked (InputEvent event, float x, float y)
	            {
	            	dialogo.hide();
	            }
	        };
	        TextButton salirButton = new TextButton("Salir",getSkin());
	        salirButton.addListener(new ClickListener(){
	            public void clicked (InputEvent event, float x, float y)
	            {
	            	dialogo.show(stage, "Estas seguro?", yesListener, noListener);
	            }

	        });
	        
	        table.add( salirButton ).size( 300, 80 );
	        table.row();

	    }
	    
	    protected void backAction(){
		       final ClickListener yesListener = new ClickListener(){
		        	public void clicked (InputEvent event, float x, float y)
		            {
		            	Gdx.app.exit();
		            }
		        };
		       final ClickListener noListener = new ClickListener(){
		        	public void clicked (InputEvent event, float x, float y)
		            {
		            	dialogo.hide();
		            }
		        };

	    	dialogo.show(stage, "Estas seguro?", yesListener, noListener);
	    }
}
