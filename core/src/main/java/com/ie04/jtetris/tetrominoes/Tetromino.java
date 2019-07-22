package com.ie04.jtetris.tetrominoes;

import org.mini2Dx.core.graphics.Graphics;

import com.ie04.jtetris.Animate;
import com.ie04.jtetris.Direction;
import com.ie04.jtetris.JTGrid;
import com.ie04.jtetris.NullBlockException;
import com.ie04.jtetris.OutOfGridException;
import com.ie04.jtetris.TetrisBlock;

public abstract class Tetromino implements Animate {
	protected JTGrid jtg;
	protected TetrisBlock[] blockArray; 
	protected int currentState = 0;
	protected boolean topHit; //Used to determine loss
	protected boolean groundHit;
	protected boolean leftHit;
	protected boolean rightHit;
	
	Tetromino(String img, JTGrid jtg) {
		blockArray = new TetrisBlock[4]; //All tetrominoes have 4 blocks
		
		for(int i = 0; i < blockArray.length; i++) { //Initializing blocks...
			blockArray[i] = new TetrisBlock(img, jtg);
		}
		for(int i = 0; i < blockArray.length; i++) { //Creating linked list of TetrisBlocks
			
			if(i == 0) //is head
				blockArray[i].setNext(blockArray[i+1]);
			
			else if(i == 3) //is tail
				blockArray[i].setPrev(blockArray[i-1]);
			
			else { //is middle
				blockArray[i].setNext(blockArray[i+1]);
				blockArray[i].setPrev(blockArray[i-1]);
			}
			
			
		}
		this.jtg = jtg;
		construct(); //Constructs tetromino shape
	}
	protected abstract void construct();	
	public abstract void rotate();
	
	public void render(Graphics g) {
		
			for(int i = 0; i < blockArray.length; i++) {
				try {
					jtg.addBlockToGrid(blockArray[i], g);
				} catch (OutOfGridException e) {
					System.out.println("Error: Block doesn't exist");
					e.printStackTrace();
				}
			}
		
	}
	

	public void update(Direction direction) {

		
		for(int i = 0; i < blockArray.length; i++) {
			try {
				jtg.updateBlock(blockArray[i], direction);
			} catch (NullBlockException e) {
				System.out.println("Error: Block Doesn't exist");
				e.printStackTrace();
			}
		}
	}

	private void wallKick() { //Moves tetromino during rotation if it is near a boundary

			
			
	}
	public void moveUp() {
		for(int i = 0; i < blockArray.length; i++) {
			blockArray[i].moveUp();
	}
	}
	public void moveDown() {
		for(int i = 0; i < blockArray.length; i++) {
				blockArray[i].moveDown();
		}
	}
	public void moveLeft() {
		for(int i = 0; i < blockArray.length; i++) {
			blockArray[i].moveLeft();
			
		}
		if(rightHit) //Removes residual collision if movement is in opposite direction
			rightHit = false;
	}
	public void moveRight() {
		for(int i = 0; i < blockArray.length; i++) {	
			blockArray[i].moveRight();
		}
		if(leftHit) 
			leftHit = false;
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
		return groundHit;
	}
	/**
	 * @param groundHit the groundHit to set
	 */
	public void setGroundHit(boolean groundHit) {
		this.groundHit = groundHit;
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
