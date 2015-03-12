package com.youtoolife.labyrinth.chunk;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.youtoolife.labyrinth.GameObjects.GameObject;
import com.youtoolife.labyrinth.chunk.Chunk.Exits;
import com.youtoolife.labyrinth.events.InvokeEvent;
import com.youtoolife.labyrinth.renderer.Light;

public class ChunkGenerator {

	private static Vector<Chunk> chunks = new Vector<Chunk>();

	public static Chunk getChunk(Exits type) {
		Vector<Chunk> correct = new Vector<Chunk>();
		for (int i = 0; i < chunks.size(); i++)
			if (chunks.get(i).type == type)
				correct.add(chunks.get(i));
		return correct.get((new Random()).nextInt(correct.size())).copy();
	}

	public static void init() {
		FileHandle dir = Gdx.files.internal("bin/chunks/");
		FileHandle[] files = dir.list();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		for (FileHandle file : files) {
			if (file.name().contains(".chunk")) {
				DocumentBuilder db;
				try {
					db = dbf.newDocumentBuilder();
					Document dom = db.parse(file.file());
					Element chunk = dom.getDocumentElement();
					addChunk(chunk);
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void addChunk(Element chunk) {
		String name = chunk.getAttribute("name");
		Exits type = Exits.valueOf(chunk.getAttribute("type"));
		
		GameObject[][] map = new GameObject[Chunk.SIZE][Chunk.SIZE];
		NodeList blocks = chunk.getElementsByTagName("Block");
		for (int i = 0; i < blocks.getLength(); i++) {
			Element block = (Element) blocks.item(i);
			GameObject buf_block = GameObject.getObject(block);
			map[Integer.valueOf(block.getAttribute("y"))][Integer.valueOf(block
					.getAttribute("x"))] = buf_block;
		}
		
		Vector<InvokeEvent> events = new Vector<InvokeEvent>();
		if (chunk.getElementsByTagName("Eventlist").item(0)!=null)
			events = EventsResolver.getEvents((Element) chunk
					.getElementsByTagName("Eventlist").item(0));

		Vector<Light> lights = new Vector<Light>();
		if (chunk.getElementsByTagName("Lightlist").item(0)!=null)
			lights = Light.getLights((Element) chunk
					.getElementsByTagName("Lightlist").item(0));
		
		Chunk buf = new Chunk(type, name, map, events, lights);
		chunks.add(buf);
	}
}