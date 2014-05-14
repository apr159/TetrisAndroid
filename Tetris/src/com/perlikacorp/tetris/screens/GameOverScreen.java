package com.perlikacorp.tetris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.perlikacorp.tetris.GoogleInterface;
import com.perlikacorp.tetris.TetrisGame;

public class GameOverScreen extends AbstractGameScreen {

	public GameOverScreen(final TetrisGame game) {
		super(game);
        GoogleInterface gi = game.getGoogleInterface();
		gi.submitScore(GoogleInterface.PUNTOS_LEADERBOARD, world.state.score);
		gi.submitScore(GoogleInterface.TIEMPO_LEADERBOARD, (int)world.state.time);
		gi.submitScore(GoogleInterface.LINEAS_LEADERBOARD, world.state.lines);
	
		Table table = super.getTable();
        
        Label label = new Label("Fin de Juego",getSkin(),"title");
        Label ptsLabel = new Label("Obtuviste " + world.state.lines + " lineas",getSkin(),"title");
        Label mejorLabel = new Label("Mejor: " + world.topScores.lineas + " lineas",getSkin(),"title");
        
        if (world.state.lines>world.topScores.lineas){
        	world.topScores.lineas=world.state.lines;
        	game.getProfileManager().persist();
        	mejorLabel.setText("Maximas lineas");
        }
        
        TextButton botonReinicio = new TextButton("Reiniciar",getSkin());
        
        botonReinicio.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y)
            {
            	world.reiniciar();
            	game.setScreen(new GameScreen(game));
            }
 
        });
        TextButton botonBack = new TextButton("Regresar",getSkin());
        
        botonBack.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y)
            {
            	
            	game.setScreen(new MenuScreen(game));
            }
 
        });
 
        table.add(label);
        table.row();
        table.add(ptsLabel);
        table.row();
        table.add(mejorLabel);
        table.row();

        table.add(botonReinicio);
        table.row();
        table.add(botonBack);
        

	}
	
	@Override
	public void render(float delta){
        stage.act( delta );

        Gdx.gl.glClearColor( 0.5f,0,0, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        
        if (world!=null) {
            renderer.render(delta);
        }
        
        // update and draw the stage actors
        stage.draw();
        Table.drawDebug(stage);

    }

}
