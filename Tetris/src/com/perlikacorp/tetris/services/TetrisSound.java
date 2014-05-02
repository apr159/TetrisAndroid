package com.perlikacorp.tetris.services;

/**
 * The available sound files.
 */
public enum TetrisSound
{
    DESTROY( "sounds/destroy.mp3" ),
    DOWN( "sounds/down.mp3" );

    private final String fileName;

    private TetrisSound(
        String fileName )
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
}