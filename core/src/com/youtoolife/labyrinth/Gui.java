package com.youtoolife.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.player.Player;
import com.youtoolife.labyrinth.utils.Assets;

public class Gui {
	Sprite gui;
	Texture hearth;
	Player player1;
	Player player2;

	public Gui(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		gui = new Sprite(Assets.getTexture("gui"));
		hearth = Assets.getTexture("hearth");
		gui.setPosition(-MainGame.w/2, MainGame.h/2-gui.getHeight());
	}

	public void draw(SpriteBatch batch) {
		
		gui.draw(batch);
		
		for(int i = 0; i<player1.hp;i++)
			batch.draw(hearth, 100-MainGame.w/2+i*50, MainGame.h/2-gui.getHeight());
		for(int i = 0; i<player2.hp;i++)
			batch.draw(hearth, MainGame.w/2-150-i*50, MainGame.h/2-gui.getHeight());
	}

}
