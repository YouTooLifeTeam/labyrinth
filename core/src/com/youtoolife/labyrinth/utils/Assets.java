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
		getSubDir(Gdx.files.local("bin/textures/"));
	}
	
	public static Texture getTexture(String name) {
		Texture texture = null;
		try{
		texture = textures.get(textureNames.indexOf("bin/textures/"+name, false));
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Can't find image "+name);
		}
		return texture;
	}

	private static void getSubDir(FileHandle s){
		FileHandle dir = s;
		FileHandle[] files = dir.list();
		for (FileHandle file: files) {	
			if(file.isDirectory())
				getSubDir(file);
			if (file.name().contains(".png")
					||file.name().contains(".jpg")
					||file.name().contains(".PNG")
					||file.name().contains(".JPG")) {
				
				Texture texture = new Texture(file);
				textures.add(texture);
				textureNames.add(s+"/"+file.nameWithoutExtension());
			}
		}
	}
	
	public static void load () {
		loadTextures();
	}
}
