package com.ie04.jtetris;



import java.util.ArrayList;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.ie04.jtetris.tetrominoes.*;

public class JTBoard extends Texture { //Manages game and point system
	public JTGrid jtg;
	public Tetromino currentTet;
	public Tetromino nextTet;
	public ArrayList<Tetromino> prevTet; //Dynamically increasing array of all previous terominoes
	public Tetromino heldTet; //Tetromino in hold
	public JPrevDisplay nextDisplay;
	public JPrevDisplay heldDisplay;
	
 	public JTBoard() throws OutOfGridException, NullBlockException {
		super("JTetrisBG.png");
		jtg = new JTGrid();
		currentTet = nextTet();
		nextTet = nextTet();
		prevTet = new ArrayList<Tetromino>(); 
		nextDisplay= new JPrevDisplay();
		nextDisplay.setDisplay(nextTet);
		heldDisplay = new JPrevDisplay();
	}
 	public void render(Graphics g) {
 		
 		g.drawTexture(this, 0f, 0f);
 		nextDisplay.render(g, 210, 99);
 		heldDisplay.render(g, 200, 200);
 		currentTet.render(g);
 		for(Tetromino tet : prevTet) //Renders all previous tetrominoes
 			tet.render(g);
 	}
	public Tetromino nextTet() throws OutOfGridException, NullBlockException { //Picks random tetromino from pool of 7
		int selection = 1; //(int)(Math.random() * 7 + 1);
		switch(selection) {
		case 1: return new ITetromino(jtg);
		case 2: return new JTetromino(jtg);
		case 3: return new LTetromino(jtg);
		case 4: return new OTetromino(jtg);
		case 5: return new STetromino(jtg);
		case 6: return new TTetromino(jtg);
		case 7: return new ZTetromino(jtg);
		default: return null;
		}
		
	}
	public void switchFocus() throws OutOfGridException, NullBlockException {
		
		prevTet.add(currentTet); //Adds old tetromino to array	
		currentTet = nextTet;
		nextTet = nextTet(); //nextTet gets random tetromino
		nextDisplay.setDisplay(nextTet);
	}
	public Tetromino queryTetType(Tetromino tet) throws OutOfGridException, NullBlockException {
		if(tet instanceof ITetromino)
			return new ITetromino(jtg);
		else if(tet instanceof JTetromino)
			return new JTetromino(jtg);
		else if(tet instanceof LTetromino)
			return new LTetromino(jtg);
		else if(tet instanceof OTetromino)
			return new OTetromino(jtg);
		else if(tet instanceof STetromino)
			return new STetromino(jtg);
		else if(tet instanceof TTetromino)
			return new TTetromino(jtg);
		else if(tet instanceof ZTetromino)
			return new ZTetromino(jtg);
		else
			return null;
		
	}
	public void switchHeld() throws OutOfGridException, NullBlockException {
		Tetromino tempTet; 
		 //Destroys currentTet blocks
		if(heldTet == null) {
			heldTet = queryTetType(currentTet); //Returns new instance of currentTet class
			currentTet.selfDestruct();
			currentTet = nextTet;
			heldDisplay.setDisplay(heldTet);
		} else {
			tempTet = queryTetType(currentTet);
			currentTet.selfDestruct();
			currentTet = heldTet;
			heldTet = tempTet;
			heldDisplay.setDisplay(heldTet);
		}
	}
}
