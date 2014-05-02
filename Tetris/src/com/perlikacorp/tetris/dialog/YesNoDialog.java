package com.perlikacorp.tetris.dialog;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class YesNoDialog extends Dialog{
	private Skin skin;
	public YesNoDialog(String title, Skin skin) {
		super(title, skin);
		this.skin = skin;

	}
	
	

	public Dialog show(Stage stage, String text, ClickListener yesListener,ClickListener noListener){
		this.clear();
		
		Label label = new Label(text,skin);
		label.setAlignment(Align.center);
		label.setWrap(true);        

        TextButton yesButton = new TextButton("Si",skin);
        yesButton.addListener( yesListener);
        
        TextButton noButton = new TextButton("No",skin);
        noButton.addListener( noListener);


        
        this.add(label).padTop(20).fillX().expandX().center();
        this.row();
        this.add(yesButton).padRight(100);
        this.add(noButton).padBottom(20);

		return super.show(stage);
	}

	


}
