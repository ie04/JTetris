package com.ie04.jtetris;



import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.ie04.jtetris.tetrominoes.*;

public class JTBoard extends Texture { //Manages game and point system
	public JTGrid jtg;
	public Tetromino currentTet;
	public Tetromino nextTet;
	public ArrayList<Tetromino> prevTet; //Dynamically increasing array of all previous terominoes
	
 	public JTBoard() throws OutOfGridException, NullBlockException {
		super("JTetrisBG.png");
		jtg = new JTGrid();
		currentTet = nextTet();
		nextTet = nextTet();
		prevTet = new ArrayList<Tetromino>(); 
	}
	public Tetromino nextTet() throws OutOfGridException, NullBlockException { //Picks random tetromino from pool of 7
		int selection = (int)(Math.random() * 7 + 1);
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
	}
	
}
