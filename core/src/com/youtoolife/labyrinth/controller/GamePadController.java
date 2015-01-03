package com.youtoolife.labyrinth.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;

public class GamePadController extends Controller {

	public static final int[] GamePad = { 2, 3 };

	float dl = 0, dr = 0, du = 0, dd = 0, dsa = 0, dna = 0;

	final float delta = 0.04f;
	
	@Override
	public void update() {
		if (Controllers.getControllers().size > 0) {
			com.badlogic.gdx.controllers.Controller c = Controllers
					.getControllers().get(0);
			PovDirection value = c.getPov(0);

			dl -= Gdx.graphics.getDeltaTime();
			dr -= Gdx.graphics.getDeltaTime();
			du -= Gdx.graphics.getDeltaTime();
			dd -= Gdx.graphics.getDeltaTime();
			dna -= Gdx.graphics.getDeltaTime();
			dsa -= Gdx.graphics.getDeltaTime();

			if (value == PovDirection.north)
				if (du <= 0) {
					queue.add(Action.Up);
					du = delta;
				}

			if (value == PovDirection.south)
				if (dd <= 0) {
					queue.add(Action.Down);
					dd = delta;
				}
			if (value == PovDirection.east)
				if (dr <= 0) {
					queue.add(Action.Right);
					dr = delta;
				}
			if (value == PovDirection.west)
				if (dl <= 0) {
					queue.add(Action.Left);
					dl = delta;
				}

			if (value == PovDirection.northEast)
				if (du <= 0 && dr <= 0) {
					queue.add(Action.Up);
					queue.add(Action.Right);
					du = delta;
					dr = delta;
				}
			if (value == PovDirection.northWest)
				if (du <= 0 && dl <= 0) {
					queue.add(Action.Up);
					queue.add(Action.Left);
					du = delta;
					dl = delta;
				}
			if (value == PovDirection.southEast)
				if (dd <= 0 && dr <= 0) {
					queue.add(Action.Down);
					queue.add(Action.Right);
					dd = delta;
					dr = delta;
				}
			if (value == PovDirection.southWest)
				if (dd <= 0 && dr <= 0) {
					queue.add(Action.Down);
					queue.add(Action.Left);
					dd = delta;
					dl = delta;
				}

			if (c.getButton(GamePad[0]))
				if (dna <= 0) {
					queue.add(Action.NormAction);
					dna = delta;
				}
			if (c.getButton(GamePad[1]))
				if (dsa <= 0) {
					queue.add(Action.SpecAction);
					dsa = delta;
				}
		}
	}
}
