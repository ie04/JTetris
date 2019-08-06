package com.ie04.jtetris.tetrominoes;

import java.util.ArrayList;
import java.util.Arrays;

import org.mini2Dx.core.graphics.Graphics;

import com.ie04.jtetris.Animate;
import com.ie04.jtetris.Direction;
import com.ie04.jtetris.JTGrid;
import com.ie04.jtetris.NullBlockException;
import com.ie04.jtetris.OutOfGridException;
import com.ie04.jtetris.TetrisBlock;

public abstract class Tetromino implements Animate {
	
	static int numTetrominoes; //Amount of tetrominoes on grid
	private static int NUM_BLOCKS = 4; //All tetrominoes have 4 blocks
	public int tetID; //Each tetromino recieves an ID to differentiate from other blocks
	protected JTGrid jtg;
	protected ArrayList<TetrisBlock> blockArray; 
	protected int currentState = 0; //State of figure used for rotation
	protected boolean topHit; //Used to determine loss
	protected boolean bottomHit;
	protected boolean leftHit;
	protected boolean rightHit;
	
	Tetromino(String img, JTGrid jtg) throws OutOfGridException, NullBlockException {
		
		tetID = numTetrominoes;
		numTetrominoes++;
		
		
		blockArray = new ArrayList<TetrisBlock>();
		
		for(int i = 0; i < NUM_BLOCKS; i++) { //Initializing blocks...
			blockArray.add(new TetrisBlock(img, jtg, this));
		}
		for(int i = 0; i < NUM_BLOCKS; i++) { //Creating linked list of TetrisBlocks
			
			if(i == 0) //is head
				blockArray.get(i).setNext(blockArray.get(i + 1));
			
			else if(i == 3) //is tail
				blockArray.get(i).setPrev(blockArray.get(i - 1));
			
			else { //is middle
				blockArray.get(i).setNext(blockArray.get(i + 1));
				blockArray.get(i).setPrev(blockArray.get(i - 1));
			}
			
			
		}
		this.jtg = jtg;
		
		
		construct(); //Constructs tetromino shape
	}
	protected abstract void construct() throws OutOfGridException, NullBlockException;	
	public abstract void rotate() throws NullBlockException, OutOfGridException;
	protected abstract void wallKick();
	
	private void deleteBlock(TetrisBlock block) {
		if(blockArray.contains(block))
			blockArray.remove(block);
	}
	public void render(Graphics g) throws OutOfGridException {
		
			for(int i = 0; i < NUM_BLOCKS; i++) {
				jtg.addBlockToGrid(blockArray.get(i), g);
			}
		
	}
	

	public void update(Direction direction) throws NullBlockException, OutOfGridException {

		
		for(int i = 0; i < NUM_BLOCKS; i++) {
			jtg.updateBlock(blockArray.get(i), direction); 
		}
	}




	public void moveUp() throws NullBlockException, OutOfGridException {
		for(int i = 0; i < NUM_BLOCKS; i++) {
			blockArray.get(i).moveUp();
	}
	}
	public void moveDown() throws NullBlockException, OutOfGridException {
		
		for(int i = 0; i < NUM_BLOCKS; i++) {
			if(jtg.queryCollision(blockArray.get(i), Direction.DOWN)) {
				blockArray.get(i).bottomHit(true);
				bottomHit = true;
			}
		}
		
		for(int i = 0; i < NUM_BLOCKS; i++) {
			blockArray.get(i).moveDown();
		}
	}
	public void moveLeft() throws NullBlockException, OutOfGridException {
		
		for(int i = 0; i < NUM_BLOCKS; i++) {
			if(jtg.queryCollision(blockArray.get(i), Direction.LEFT)) {
				blockArray.get(i).leftHit(true);
				leftHit = true;
			}	
		}
		
		for(int i = 0; i < NUM_BLOCKS; i++) {
			blockArray.get(i).moveLeft();
			
		}
		if(rightHit)
			rightHit = false;
		
	}
	public void moveRight() throws NullBlockException, OutOfGridException {
		
		for(int i = 0; i < NUM_BLOCKS; i++) {
			if(jtg.queryCollision(blockArray.get(i), Direction.RIGHT)) {
				blockArray.get(i).rightHit(true);
				rightHit = true;
			}
		}
		
		for(int i = 0; i < NUM_BLOCKS; i++) {	
			blockArray.get(i).moveRight();
		}
	}
	/**
	 * @return the topHit
	 */
	public boolean isTopHit() {
		return topHit;
	}
	/**
	 * @param topHit the topHit to set
	 */
	public void setTopHit(boolean topHit) {
		this.topHit = topHit;
	}
	/**
	 * @return the groundHit
	 */
	public boolean isGroundHit() {
		return bottomHit;
	}
	/**
	 * @param groundHit the groundHit to set
	 */
	public void setBottomHit(boolean bottomHit) {
		this.bottomHit = bottomHit;
	}
	/**
	 * @return the leftHit
	 */
	public boolean isLeftHit() {
		return leftHit;
	}
	/**
	 * @param leftHit the leftHit to set
	 */
	public void setLeftHit(boolean leftHit) {
		this.leftHit = leftHit;
	}
	/**
	 * @return the rightHit
	 */
	public boolean isRightHit() {
		return rightHit;
	}
	/**
	 * @param rightHit the rightHit to set
	 */
	public void setRightHit(boolean rightHit) {
		this.rightHit = rightHit;
	}
	

	
}
