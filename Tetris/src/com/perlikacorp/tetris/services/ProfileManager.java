package com.perlikacorp.tetris.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.perlikacorp.tetris.TetrisGame;
import com.perlikacorp.tetris.game.TopScores;

/**
 * Profile operations.
 */
public class ProfileManager
{
    // the location of the profile data file
    private static final String PROFILE_DATA_FILE = "data/top-scores.json";

    // the loaded profile (may be null)
    private TopScores topScores;

    /**
     * Creates the profile manager.
     */
    public ProfileManager()
    {
    }

    /**
     * Retrieves the player's profile, creating one if needed.
     */
    public TopScores retrieveProfile()
    {
    	
        if( topScores != null ) return topScores;

        Json json = new Json();
        FileHandle profileDataFile = Gdx.files.local( PROFILE_DATA_FILE );
        if( profileDataFile.exists() ) {
            try {
                String profileAsText = profileDataFile.readString().trim();
                topScores = json.fromJson( TopScores.class, profileAsText );
            } catch( Exception e ) {
                Gdx.app.error( TetrisGame.LOG, "Unable to parse existing profile data file", e );
                topScores = new TopScores();
                persist( topScores );
            }
        } else {
        	topScores = new TopScores();
            persist( topScores );
        }
        return topScores;
    }

    /**
     * Persists the given profile.
     */
    protected void persist(
        TopScores ts )
    {
        FileHandle profileDataFile = Gdx.files.local( PROFILE_DATA_FILE );
        Gdx.app.log( TetrisGame.LOG, "Persisting profile in: " + profileDataFile.path() );
        Json json = new Json();
        String profileAsText = json.toJson( ts );
        profileDataFile.writeString( profileAsText, false );
    }

    /**
     * Persists the player's profile.
     * <p>
     * If no profile is available, this method does nothing.
     */
    public void persist()
    {
        if( topScores != null ) {
            persist( topScores );
        }
    }
}