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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.youtoolife.labyrinth.utils.Assets;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public class GUI {
	
	Sprite bg;
	public Array<SpriteGUI> sprites = new Array<SpriteGUI>();
	public Array<DividerGUI> divs = new Array<DividerGUI>();
	public Array<ContainerGUI> conts = new Array<ContainerGUI>();
	
	public int width = 800, height = 600;
	
	public GUI(int width, int height) {
		this.width = width;
		this.height = height;
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
				bg.setSize(800, 600);
			}
		}
		
		NodeList divList = doc.getElementsByTagName("div");
		for (int temp = 0; temp < divList.getLength(); temp++) {
			Node nDiv = divList.item(temp);
			if (nDiv.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nDiv;
				DividerGUI div = new DividerGUI();
				div.top = Float.parseFloat(eElement.getAttribute("top"));
				div.x = Float.parseFloat(eElement.getAttribute("x"));
				div.y = height-div.top-Float.parseFloat(eElement.getAttribute("y"))-(divs.size>0?divs.get(divs.size-1).size:0);
				
				System.out.println(div.top+" "+div.x+" "+div.y);
				
				NodeList contList = eElement.getElementsByTagName("container");
				for (int temp2 = 0; temp2 < contList.getLength(); temp2++) {
					Node nCont = contList.item(temp2);
					if (nCont.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement2 = (Element) nCont;
						
						ContainerGUI cont = new ContainerGUI();
						cont.top = Float.parseFloat(eElement2.getAttribute("top"));
						cont.sizeY+=cont.top;
						cont.x = div.x + Float.parseFloat(eElement2.getAttribute("x"));
						cont.y = div.y-cont.top-Float.parseFloat(eElement2.getAttribute("y"))-(conts.size>0?(conts.get(conts.size-1).sizeY):0);
						cont.type = eElement2.getAttribute("type");
						cont.indent_left = Float.parseFloat(eElement2.getAttribute("indent_left"));
						cont.indent_top = Float.parseFloat(eElement2.getAttribute("indent_top"));
						
						System.out.println(cont.top+" "+cont.x+" "+cont.y+" "+cont.type+" "+cont.indent_left+" "+cont.indent_top);
						
						NodeList spriteList = eElement2.getElementsByTagName("sprite");
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
			
								float x = 0;
								//if (eElement3.getAttribute("align").equalsIgnoreCase("center"))
								x = cont.x + ((cont.type.equalsIgnoreCase("left")&&temp3>0)?(cont.size+cont.indent_left*temp3):0)
										+Float.parseFloat(eElement3.getAttribute("x"));
								float y = cont.y - heightS - Float.parseFloat(eElement3.getAttribute("y"))
										-((cont.type.equalsIgnoreCase("top")&&temp3>0)?(cont.sizeY+cont.indent_top*temp3-cont.top):0);
								System.out.println(x+" "+y);
								//System.out.println(eElement3.getAttribute("img"));
								System.out.println("2");
								System.out.println(eElement3.getAttribute("action"));
								SpriteGUI sprite = new SpriteGUI(texture,
										x,
										y,
										widthS,
										heightS);
								sprite.id = eElement3.getAttribute("id");
								sprite.action = eElement3.getAttribute("action");
								cont.addSprite(sprite);
								sprites.add(sprite);
							}
						}
						div.addContainer(cont);
						conts.add(cont);
					}
				}
				divs.add(div);
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
		
	}
	
}
