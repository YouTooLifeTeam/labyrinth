package com.youtoolife.labyrinth.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Assets {
	
	public static Array<Texture> textures;
	private static Array<String> textureNames;
	
	//public static Music music01, music02, music03, music04;
	//public static Sound sound;
	
	//public static boolean online = false;
	//public static String login = System.getProperty("user.name"), passWord = "toor";
	
	private static void loadTextures() {
		
		textures = new Array<Texture>();
		textureNames = new Array<String>();
		
		FileHandle dir = Gdx.files.internal("textures/");
		String[] files = dir.readString().split("\n");
		
		for (String file: files) {	
			if(file.lastIndexOf('.')==-1)
				getSubDir(file);
			if (file.contains(".png")
					||file.contains(".jpg")
					||file.contains(".PNG")
					||file.contains(".JPG")) {
				
				Texture texture = new Texture(Gdx.files.internal("textures/"+file));
				textures.add(texture);
				textureNames.add(file.substring(0, file.lastIndexOf(".")));
			}
		}
	}
	
	public static Texture getTexture(String name) {
		Texture texture;
		texture = textures.get(textureNames.indexOf(name, false));
		return texture;
	}

	private static void getSubDir(String s){
		FileHandle dir = Gdx.files.internal("textures/"+s);
		String[] files = dir.readString().split("\n");
		for (String file: files) {	
			if(Gdx.files.internal("textures/"+file).isDirectory())
				getSubDir(s+"/"+file);
			if (file.contains(".png")
					||file.contains(".jpg")
					||file.contains(".PNG")
					||file.contains(".JPG")) {
				
				Texture texture = new Texture(Gdx.files.internal("textures/"+s+"/"+file));
				textures.add(texture);
				textureNames.add(s+"/"+file.substring(0, file.lastIndexOf(".")));
			}
		}
	}
	
	public static void load () {
		//System.out.println("Loading textures...");
		loadTextures();
	}
}
