package com.youtoolife.labyrinth.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.youtoolife.labyrinth.*;

public class DesktopLauncher {

	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = 800;
		cfg.height = 600;
		cfg.title = "Labyrinth";
		cfg.resizable = true;
		new LwjglApplication(new MainGame(), cfg);
	}
}