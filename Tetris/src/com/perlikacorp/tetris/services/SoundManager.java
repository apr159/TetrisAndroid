package com.perlikacorp.tetris.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.perlikacorp.tetris.TetrisGame;

/**
 *Administradro de efectos de sonido.
 */
public class SoundManager
{
   

    /**
     * Volumen
     */
    private float volume = 1f;

    /**
     * Si el sonido esta habilitado
     */
    private boolean enabled = true;

     private AssetManager manager;
     
    /**
     * Crea el administrador de sonidos.
     */
    public SoundManager(AssetManager manager)
    {
    	this.manager = manager;
    }

    /**
     * Reproduce el sonido especificado
     */
    public void play(
    		TetrisSound sound )
    {
        if( ! enabled ) return;

        Sound soundToPlay = manager.get(sound.getFileName());
        if (soundToPlay==null) return;
        
        Gdx.app.log( TetrisGame.LOG, "Reproduciendo sonido: " + sound.name() );
        soundToPlay.play( volume );
    }

    /**
     *Cambia el volumne
     */
    public void setVolume(
        float volume )
    {
        Gdx.app.log( TetrisGame.LOG, "Ajustando volumen a : " + volume );

        // check and set the new volume
        if( volume < 0 || volume > 1f ) {
            throw new IllegalArgumentException( "El volumen esta fuera de rango: [0,1]" );
        }
        this.volume = volume;
    }

    /**
     * Habilita o deshabilita el sonido
     */
    public void setEnabled(
        boolean enabled )
    {
        this.enabled = enabled;
    }

}
