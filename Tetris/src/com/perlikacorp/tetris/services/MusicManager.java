package com.perlikacorp.tetris.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;
import com.perlikacorp.tetris.TetrisGame;

/**
 * Controlador de la musica.
 */
public class MusicManager
    implements
        Disposable
{
    /**
     * Los archivos de musica en el juego
     */
    public enum GameMusic
    {
        GAME( "sounds/music.mp3" );

        private String fileName;
        private Music musicResource;

        private GameMusic(
            String fileName )
        {
            this.fileName = fileName;
        }

        public String getFileName()
        {
            return fileName;
        }

        public Music getMusicResource()
        {
            return musicResource;
        }

        public void setMusicResource(
            Music musicBeingPlayed )
        {
            this.musicResource = musicBeingPlayed;
        }
    }

    /**
     * Referncia de la musica que se esta reproduciendo actualmente.
     */
    private GameMusic musicBeingPlayed;

    /**
     * The volume to be set on the music.
     */
    private float volume = 1f;

    /**
     * Whether the music is enabled.
     */
    private boolean enabled = true;

    
    private AssetManager manager;
    /**
     * Creates the music manager.
     */
    public MusicManager(AssetManager manager)
    {
    	this.manager = manager;
    }

    /**
     * Plays the given music (starts the streaming).
     * <p>
     * If there is already a music being played it is stopped automatically.
     */
    public void play(
        GameMusic music )
    {
        if( ! enabled ) return;

        if( musicBeingPlayed == music ) return;

        Gdx.app.log( TetrisGame.LOG, "Reproduciendo musica: " + music.name() );

        stop();

        Music musicResource = manager.get(music.getFileName());;
        if (musicResource==null) return;

        musicResource.setVolume( volume );
        musicResource.setLooping( true );
        musicResource.play();

        musicBeingPlayed = music;
        musicBeingPlayed.setMusicResource( musicResource );
    }

    /**
     * Detiene la musica
     */
    public void stop()
    {
        if( musicBeingPlayed != null ) {
            Gdx.app.log( TetrisGame.LOG, "Musica detenida" );
            Music musicResource = musicBeingPlayed.getMusicResource();
            musicResource.stop();
          //  musicResource.dispose();
            musicBeingPlayed = null;
        }
    }
    
    

    /**
     * Cambia el volumen
     */
    public void setVolume(
        float volume )
    {
        Gdx.app.log( TetrisGame.LOG, "Adjusting music volume to: " + volume );

        if( volume < 0 || volume > 1f ) {
            throw new IllegalArgumentException( "Volumen fuera de rango: [0,1]" );
        }
        this.volume = volume;

        if( musicBeingPlayed != null ) {
            musicBeingPlayed.getMusicResource().setVolume( volume );
        }
    }

    /**
     * Habbilita o deshabilita musica
     */
    public void setEnabled(
        boolean enabled )
    {
        this.enabled = enabled;

        if( ! enabled ) {
            stop();
        }
    }

    /**
     * Baja de memoria el administrador de musica
     */
    public void dispose()
    {
        Gdx.app.log( TetrisGame.LOG, "Apagando adminisgtrador de musica" );
        stop();
    }
}