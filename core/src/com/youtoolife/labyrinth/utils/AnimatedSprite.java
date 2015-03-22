package com.youtoolife.labyrinth.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedSprite {

	private float x;
	private float y;
	private float angle = 0;
	private float width;
	private float height;

	private Sprite[] img = null;
	private boolean AnimActive = true;
	private int AnimStart = 0;
	private int AnimStop = 0;
	private int AnimNow = 0;
	private int AnimCount = 0;
	private float AnimDelta = 0;
	private float PreferedDelta = 200;
	private boolean isFlipped = false;
	
	private Texture src;
	
	public void draw(SpriteBatch batch) {
		img[AnimNow].setPosition(x, y);
		img[AnimNow].draw(batch);
	}

	private void CutImage(Texture src) {
		for (int i = 0; i <= src.getWidth() / width - 1; i++)
			img[i] = new Sprite(new TextureRegion(src, (int) (i * width), 0,
					(int) width, (int) height));
	}

	public AnimatedSprite(float x, float y, float width, float height,
			Texture img, float angle) {
		this.img = new Sprite[(int) (img.getWidth() / width)];
		this.width = width;
		this.height = height;
		this.src = img;
		AnimStart = 0;
		AnimStop = this.img.length - 1;
		AnimCount = (int) (img.getWidth() / width);
		this.x = x;
		this.y = y;
		CutImage(img);
		setRotation(angle);
		if (this.img.length == 1)
			AnimActive = false;
	}

	public void update(float delta) {
		if (AnimActive) {
			AnimDelta += delta;
			if (AnimDelta >= PreferedDelta) {
				AnimDelta -= PreferedDelta;
				AnimNow++;
				if (AnimNow > AnimStop)
					AnimNow = AnimStart;
			}
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void setRotation(float angle) {
		for (int i = 0; i <= img.length - 1; i++) {
			img[i].setOrigin(img[i].getWidth() / 2, 0);
			img[i].setRotation(angle);
		}
	}

	public boolean isAnimActive() {
		return AnimActive;
	}

	public void setAnimActive(boolean AnimActive) {
		this.AnimActive = AnimActive;
	}

	public float getRotation() {
		return angle;
	}

	public float getHeigth() {
		return height;
	}

	public float getWidth() {
		return width;
	}

	public int getAnimStop() {
		return AnimStop;
	}

	public int getAnimStart() {
		return AnimStart;
	}

	public void setAnimStart(int AnimStart) {
		if (this.AnimStart != AnimStart) {
			this.AnimStart = AnimStart;
			AnimNow = AnimStart;
		}
	}

	public void setAnimStop(int AnimStop) {
		this.AnimStop = AnimStop;
	}

	public int getAnimNow() {
		return AnimNow;
	}

	public void setPreferedDelta(float delta) {
		PreferedDelta = delta;
	}

	public float getPreferedDelta() {
		return PreferedDelta;
	}

	public void setFlipped(boolean state){
		if(state!=isFlipped){
			isFlipped = state;
			for(Sprite a:img)
				a.flip(true, false);
		}
	}
	
	public boolean getFlipped(){
		return isFlipped;
	}
	
	public void setSize(float width, float height){
		for(Sprite a:img)
			a.setSize(width, height);
	}
	
	public int getAnimCount() {
		return AnimCount;
	}
	
	public Texture getTexture(){
		return src;
	}
	
}
