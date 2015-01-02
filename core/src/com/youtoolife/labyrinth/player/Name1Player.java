package com.youtoolife.labyrinth.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.utils.AnimatedSprite;
import com.youtoolife.labyrinth.utils.Assets;

public class Name1Player extends Player {

	public final float COOLDOWN = 5;
	public float mine_coolDown = COOLDOWN;

	public Name1Player(int ChunkX, int ChunkY, Controller control) {
		super(ChunkX, ChunkY, control);
		sprite = new AnimatedSprite(0, 0, 50, 50, new Sprite(
				Assets.getTexture("player2")), 0);
		sprite.setPreferedDelta(0.15f);
		sprite.setAnimStart(0);
		sprite.setAnimStop(3);
		hp = 3;
	}

	@Override
	public void update() {
		super.update();
		mine_coolDown -= Gdx.graphics.getDeltaTime();
		if(mine_coolDown<0)
			mine_coolDown = 0;
	}

	@Override
	public void useAbility() {
		if(mine_coolDown<=0){
			mine_coolDown = COOLDOWN;
		}
	}

}
