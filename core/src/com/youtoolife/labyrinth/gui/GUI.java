package com.youtoolife.labyrinth.gui;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.youtoolife.labyrinth.states.inputHandler.InputHandler;
import com.youtoolife.labyrinth.utils.Assets;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public class GUI {
	
	Sprite bg;
	public Array<SpriteGUI> sprites = new Array<SpriteGUI>();
	public Array<ContainerGUI> conts = new Array<ContainerGUI>();
	
	public int width = 800, height = 600;
	
	private InputHandler inputHandler;
	
	public GUI(int width, int height, InputHandler inputHandler) {
		this.width = width;
		this.height = height;
		this.inputHandler = inputHandler;
	}
	
	public void loadGui(String fileXML) throws ParserConfigurationException, SAXException, IOException {
		File fXmlFile = new File(fileXML);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		doc.getDocumentElement().normalize();
		
		NodeList bgList = doc.getElementsByTagName("background");
		for (int temp = 0; temp < bgList.getLength(); temp++) {
			Node nDiv = bgList.item(temp);
			if (nDiv.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nDiv;
				System.out.println("1");
				bg = new Sprite(Assets.getTexture(eElement.getAttribute("img")));
				bg.setPosition(0, 0);
				bg.setSize(this.width, this.height);
			}
		}
				NodeList contList = doc.getElementsByTagName("container");
				for (int temp2 = 0; temp2 < contList.getLength(); temp2++) {
					Node nCont = contList.item(temp2);
					if (nCont.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nCont;
						
						ContainerGUI cont = new ContainerGUI();
						cont.x = (eElement.getAttribute("type").equalsIgnoreCase("relativeX")?
								(conts.size>0?conts.get(conts.size-1).x+conts.get(conts.size-1).getWidth():0):0)
								-Float.parseFloat(eElement.getAttribute("x"));
						
						//System.out.println(temp2+" X:"+cont.x);
						cont.y =(eElement.getAttribute("type").equalsIgnoreCase("absolute")||conts.size<=0?this.height:0);
						//System.out.println(temp2+" Y:"+cont.y);
						if (eElement.getAttribute("type").equalsIgnoreCase("relativeY"))
						cont.y=(conts.size>0?conts.get(conts.size-1).y-conts.get(conts.size-1).getHeigth():0);
						//System.out.println(temp2+" Y:"+cont.y);
						cont.y-=Float.parseFloat(eElement.getAttribute("y"));
						System.out.println(temp2+" Y:"+cont.y);
						cont.type = eElement.getAttribute("contType");
						cont.indent = Float.parseFloat(eElement.getAttribute("indent"));
						
						NodeList spriteList = eElement.getElementsByTagName("sprite");
						for (int temp3 = 0; temp3 < spriteList.getLength(); temp3++) {
							Node nSprite = spriteList.item(temp3);
							if (nSprite.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement3 = (Element) nSprite;
								
								Texture texture = Assets.getTexture(eElement3.getAttribute("img"));
								float widthS = (eElement3.getAttribute("autoSize").equalsIgnoreCase("YES")?
										texture.getWidth()
								:Float.parseFloat(eElement3.getAttribute("width")));
								float heightS = (eElement3.getAttribute("autoSize").equalsIgnoreCase("YES")?
										texture.getHeight()
								:Float.parseFloat(eElement3.getAttribute("height")));
			
								//if (eElement3.getAttribute("align").equalsIgnoreCase("center"))
								float x = cont.x + ((cont.type.equalsIgnoreCase("left")&&temp3>0)?(cont.getWidth()+cont.indent):0)
										+Float.parseFloat(eElement3.getAttribute("x"));
								float y = cont.y - Float.parseFloat(eElement3.getAttribute("y")) - heightS
										-((cont.type.equalsIgnoreCase("top")&&temp3>0)?(cont.getHeigth()+cont.indent):0);
				
								//System.out.println(eElement3.getAttribute("action"));
								SpriteGUI sprite = new SpriteGUI(texture,
										x,
										y,
										widthS,
										heightS);
								sprite.id = eElement3.getAttribute("id");
								sprite.action = eElement3.getAttribute("action");
								//System.out.println((temp3==0?true:false));
								cont.addSprite(sprite);
								sprites.add(sprite);
							}
						}
						conts.add(cont);
						//System.out.println(temp2+" W:"+cont.getWidth()+" H:"+cont.getHeigth());
					}
				}
	}
	
	public SpriteGUI getSpriteAtName(String id) {
		for (SpriteGUI sprite:sprites)
			if (sprite.id.equalsIgnoreCase(id))
				return sprite;
		return sprites.get(0);
	}
	
	public void draw(SpriteBatch batch) {
		bg.draw(batch);
		for (SpriteGUI sprite:sprites)
			sprite.draw(batch);
	}
	
	public void update(StateBasedGame game) {
		if (Gdx.input.justTouched()) {
		float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight(), 
				dw = w/800, dh = h/600, 
				cX = Gdx.input.getX(), cY = Gdx.input.getY(),
				x = cX/dw, y = 600 - cY/dh;
				System.out.println(x+" - "+y);
		inputHandler.update(game, this, x, y);
		}
	}
	
}
