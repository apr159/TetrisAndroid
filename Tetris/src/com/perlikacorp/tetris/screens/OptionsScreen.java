package com.perlikacorp.tetris.screens;

import java.util.Locale;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.perlikacorp.tetris.TetrisGame;
import com.perlikacorp.tetris.services.MusicManager.GameMusic;

/**
 * La pantalla de opciones
 */
public class OptionsScreen extends AbstractScreen
{
    private Label volumeValue;

    public OptionsScreen(
        TetrisGame game )
    {
        super( game );
    }

    @Override
    public void show()
    {
        super.show();

        Table table = super.getTable();
        

        Label titleLabel = new Label("Opciones",getSkin(),"title");
        table.defaults().spaceBottom( 30 );
        table.columnDefaults( 0 ).padRight( 20 );
        table.add( titleLabel ).colspan( 3 );

        // create the labels widgets
        final CheckBox soundEffectsCheckbox = new CheckBox( "", getSkin() );
        soundEffectsCheckbox.setChecked( game.getPreferencesManager().isSoundEnabled() );
        soundEffectsCheckbox.addListener( new ChangeListener() {
            @Override
            public void changed(
                ChangeEvent event,
                Actor actor )
            {
                boolean enabled = soundEffectsCheckbox.isChecked();
                game.getPreferencesManager().setSoundEnabled( enabled );
                game.getSoundManager().setEnabled( enabled );
            }
        } );
        table.row();
        table.add( "Sonidos" );
        table.add( soundEffectsCheckbox ).colspan( 2 ).left();

        final CheckBox musicCheckbox = new CheckBox( "", getSkin() );
        musicCheckbox.setChecked( game.getPreferencesManager().isMusicEnabled() );
        
        musicCheckbox.addListener( new ChangeListener() {
            @Override
            public void changed(
                ChangeEvent event,
                Actor actor )
            {
            	
                boolean enabled = musicCheckbox.isChecked();
                musicCheckbox.setChecked(enabled);
                game.getPreferencesManager().setMusicEnabled( enabled );
                game.getMusicManager().setEnabled( enabled );

                // if the music is now enabled, start playing the menu music
                if( enabled ) game.getMusicManager().play( GameMusic.GAME );
            }
        } );
        table.row();
        table.add( "Musica" );
        table.add( musicCheckbox ).colspan( 2 ).left();

        // range is [0.0,1.0]; step is 0.1f
        Slider volumeSlider = new Slider( 0f, 1f, 0.1f, false, getSkin() ,"default");
        volumeSlider.setValue( game.getPreferencesManager().getVolume() );
        volumeSlider.addListener( new ChangeListener() {
            @Override
            public void changed(
                ChangeEvent event,
                Actor actor )
            {
                float value = ( (Slider) actor ).getValue();
                game.getPreferencesManager().setVolume( value );
                game.getMusicManager().setVolume( value );
              //  game.getSoundManager().setVolume( value );
                updateVolumeLabel();
            }
        } );

        // create the volume label
        volumeValue = new Label( "", getSkin() );
        updateVolumeLabel();

        // add the volume row
        table.row();
        table.add( "Volumen" );
        table.add( volumeSlider );
        table.add( volumeValue ).width( 40 );

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
        table.add( backButton ).size( 300, 80 ).colspan( 3 );
    }

    /**
     * Updates the volume label next to the slider.
     */
    private void updateVolumeLabel()
    {
        float volume = ( game.getPreferencesManager().getVolume() * 100 );
        volumeValue.setText( String.format( Locale.US, "%1.0f%%", volume ) );
    }
    
    protected void backAction(){
    	game.setScreen( new MenuScreen( game ) );
    }
    

}