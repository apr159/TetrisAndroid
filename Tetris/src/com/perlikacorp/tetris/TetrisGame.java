package com.perlikacorp.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.perlikacorp.tetris.game.World;
import com.perlikacorp.tetris.game.WorldRenderer;
import com.perlikacorp.tetris.screens.SplashScreen;
import com.perlikacorp.tetris.services.MusicManager;
import com.perlikacorp.tetris.services.PreferencesManager;
import com.perlikacorp.tetris.services.ProfileManager;
import com.perlikacorp.tetris.services.SoundManager;

public class TetrisGame extends Game {

	/**
	 * Usado para logs
	 */
	public static final String LOG = TetrisGame.class.getSimpleName();

	
	/**
	 * Bandera para ejecutar en modo de prueba
	 */
	public static final boolean DEV_MODE = false;
	
	/**
	 * Factores para manejar multiresolucion
	 */
	public float factorX, factorY;

	/**
	 * Referencia al modelo del juego
	 */
	public World world;
	
	/**
	 * Referencia a la vista del juego
	 */
	public WorldRenderer renderer;
	

    
    /**
     * Administrador de los recursos
     */
    public AssetManager manager = new AssetManager();
    
    /**
     * Administrador de preferencias de usuario
     */
    private PreferencesManager preferencesManager;
    
    /**
     * Administrador de sonidos
     */
    private SoundManager soundManager;
    
    /**
     * Administrador de musica
     */
    private MusicManager musicManager;

    private GoogleInterface googleInterface;

    private ProfileManager profileManager;
    
    /**
     * Constructor
     */
    public TetrisGame(GoogleInterface googleInterface){
    	this.googleInterface = googleInterface;
    }
    


    public AssetManager getAssetManager(){
    	return manager;
    }
    
    /**
     * @return el administrador de preferencias
     */
    public PreferencesManager getPreferencesManager()
    {
        return preferencesManager;
    }

    /**
     * @return el administrador de musica
     */
    public MusicManager getMusicManager()
    {
        return musicManager;
    }


    /**
     * @return el administrador de sonidos
     */
    public SoundManager getSoundManager()
    {
        return soundManager;
    }
    
    public ProfileManager getProfileManager(){
    	return profileManager;
    }
    
    /**
     * @return la interface de google
     */
    public GoogleInterface getGoogleInterface(){
    	return googleInterface;
    }

    @Override
    public void create()
    {
    	Gdx.app.log( TetrisGame.LOG, "Creando juego en " + Gdx.app.getType() );
        // 1. crea el administrador de preferencias
        preferencesManager = new PreferencesManager();
        profileManager = new ProfileManager();
        
        // 2. crea el administrador de musica
        musicManager = new MusicManager(manager);
        musicManager.setVolume( preferencesManager.getVolume() );
        musicManager.setEnabled( preferencesManager.isMusicEnabled() );

        // 3. crea el administrador de sonidos
        soundManager = new SoundManager(manager);
        soundManager.setVolume( preferencesManager.getVolume() );
        soundManager.setEnabled( preferencesManager.isSoundEnabled() );

        googleInterface.Login();
    }

    /**
     * Crea un nuevo modelo y vista
     */
    public void loadWorld(){
	    world = new World(profileManager.retrieveProfile());
	    renderer = new WorldRenderer(world, manager);

    }
    
    @Override
    public void resize(
        int width,
        int height )
    {
    	super.resize( width, height );
        Gdx.app.log( TetrisGame.LOG, "Cambiando resolucion a : " + width + " x " + height );

        // 1. Adjust factors
        factorX = 800f/(float)(width);
    	factorY = 480f/(float)(height);

        
        // 2. First load the splash screen
        if( getScreen() == null ) {
        	
            setScreen( new SplashScreen( this ) );
        }
   }

    @Override
    public void render()
    {
    	 super.render();
    }

    @Override
    public void pause()
    {
    	super.pause();
        Gdx.app.log( TetrisGame.LOG, "Pausa" );
    }

    @Override
    public void resume()
    {
    	super.resume();
        Gdx.app.log( TetrisGame.LOG, "Retornar" );
    }
    
    @Override
    public void setScreen(
        Screen screen )
    {
        super.setScreen( screen );
        Gdx.app.log( TetrisGame.LOG, "Moviendo a pantalla: " + screen.getClass().getSimpleName() );
    }


    @Override
    public void dispose()
    {
    	super.dispose();
        Gdx.app.log( TetrisGame.LOG, "Salir del juego" );
    }
}
