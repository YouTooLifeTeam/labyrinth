package com.youtoolife.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.player.Name1Player;
import com.youtoolife.labyrinth.player.Name2Player;
import com.youtoolife.labyrinth.player.Player;
import com.youtoolife.labyrinth.utils.Assets;

public class Gui {
	Sprite gui;
	Texture hearth, mine, arrow;
	Name1Player player1;
	Name2Player player2;

	public Gui(Player player1, Player player2) {
		this.player1 = (Name1Player) player1;
		this.player2 = (Name2Player) player2;
		gui = new Sprite(Assets.getTexture("gui/gui"));
		hearth = Assets.getTexture("gui/hearth");
		mine = Assets.getTexture("gui/mine");
		arrow = Assets.getTexture("gui/arrow");
		gui.setPosition(-MainGame.w / 2, MainGame.h / 2 - gui.getHeight());
	}

	public void draw(SpriteBatch batch) {

		gui.draw(batch);

		for (int i = 0; i < player1.hp; i++)
			batch.draw(hearth, 100 - MainGame.w / 2 + i * 50, MainGame.h / 2
					- gui.getHeight());
		for (int i = 0; i < player2.hp; i++)
			batch.draw(hearth, MainGame.w / 2 - 150 - i * 50, MainGame.h / 2
					- gui.getHeight());

			float scale = (player1.COOLDOWN-player1.mine_coolDown) / player1.COOLDOWN;
			float coef = player1.mine_coolDown / player1.COOLDOWN;
			batch.draw(mine, -100 + 25 * coef, MainGame.h / 2 - gui.getHeight()
					+ 25 * coef, 50 * scale, 50 * scale);

			scale = (player2.COOLDOWN - player2.arrow_cooldown) / player2.COOLDOWN;
			coef = player2.arrow_cooldown / player2.COOLDOWN;
			batch.draw(arrow, 50 + 25 * coef, MainGame.h / 2 - gui.getHeight()
					+ 25 * coef, 50 * scale, 50 * scale);
	}
}
