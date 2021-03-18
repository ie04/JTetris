package com.ie04.jtetris;



import java.util.ArrayList;

import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.ie04.jtetris.tetrominoes.*;

public class JTBoard extends Texture { //Manages game and point system
	public JTGrid jtg; //grid manager
	public Tetromino currentTet; //current user-controlled tetromino
	public Tetromino nextTet; //randomly generated next tetromino
	public ArrayList<Tetromino> prevTet; //Dynamically increasing array of all previous terominoes
	public Tetromino heldTet; //Tetromino in hold buffer
	public JPrevDisplay nextDisplay; //displays nextTet
	public JPrevDisplay heldDisplay; 
	public JNumDisplay scoreDisplay;
	private int currentLevel = 1;
	
 	public JTBoard() throws OutOfGridException, NullBlockException {
		super("JTetrisBG.png");
		jtg = new JTGrid();
		currentTet = nextTet();
		nextTet = nextTet();
		prevTet = new ArrayList<Tetromino>(); 
		nextDisplay= new JPrevDisplay();
		nextDisplay.setDisplay(nextTet); //displays tetromino at nextTet
		heldDisplay = new JPrevDisplay();
		scoreDisplay = new JNumDisplay(236, 115);
	}
 	public void render(Graphics g) {
 		g.drawTexture(this, 0f, 0f);
 		nextDisplay.render(g, 213, 230);
 		heldDisplay.render(g, 213, 307);
 		currentTet.render(g);
 		scoreDisplay.render(g);
 		for(Tetromino tet : prevTet) //Renders all previous tetrominoes
 			tet.render(g);
 	}
	public Tetromino nextTet() throws OutOfGridException, NullBlockException { 
		int selection = (int)(Math.random() * 6 + 1); //Picks random tetromino from pool of 7
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
	public Tetromino queryTetType(Tetromino tet) throws OutOfGridException, NullBlockException { //Returns new tet object based on given tet
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
		/* Switches current tetromino with one in held buffer*/
		Tetromino tempTet; 
		 //Destroys currentTet blocks
		if(heldTet == null) { //If the held buffer is empty tetromino is received from nexTet
			
			heldTet = queryTetType(currentTet); //Returns new instance of currentTet's class
			currentTet.selfDestruct();
			currentTet = nextTet;
			nextTet = nextTet();
			heldDisplay.setDisplay(heldTet);
			nextDisplay.setDisplay(nextTet);
			
		} else { //CurrentTet and heldTet swap
			tempTet = queryTetType(currentTet);
			currentTet.selfDestruct();
			currentTet = heldTet;
			heldTet = tempTet;
			heldDisplay.setDisplay(heldTet);
		}
	}
	public void queryPointsGained(int linesComplete) {
		switch(linesComplete) {
		case 1: scoreDisplay.addToNum(40   *  currentLevel);
				break;
		case 2: scoreDisplay.addToNum(100  *  currentLevel);
				break;
		case 3: scoreDisplay.addToNum(300  *  currentLevel);
				break;
		case 4: scoreDisplay.addToNum(1200 *  currentLevel);
				break;
		default: return;
		}
	}
}
