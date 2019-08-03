package com.ie04.jtetris.tetrominoes;

import org.mini2Dx.core.graphics.Graphics;

import com.ie04.jtetris.Animate;
import com.ie04.jtetris.Direction;
import com.ie04.jtetris.JTGrid;
import com.ie04.jtetris.NullBlockException;
import com.ie04.jtetris.OutOfGridException;
import com.ie04.jtetris.TetrisBlock;

public abstract class Tetromino implements Animate {
	protected static int numTetrominoes;
	public int tetID;
	protected JTGrid jtg;
	protected TetrisBlock[] blockArray; 
	protected int currentState = 0;
	protected boolean topHit; //Used to determine loss
	protected boolean bottomHit;
	protected boolean leftHit;
	protected boolean rightHit;
	
	Tetromino(String img, JTGrid jtg) {
		blockArray = new TetrisBlock[4]; //All tetrominoes have 4 blocks
		
		for(int i = 0; i < blockArray.length; i++) { //Initializing blocks...
			blockArray[i] = new TetrisBlock(img, jtg, this);
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
		tetID = numTetrominoes;
		numTetrominoes++;
		construct(); //Constructs tetromino shape
	}
	protected abstract void construct();	
	public abstract void rotate() throws NullBlockException, OutOfGridException;
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
			} catch (NullBlockException e) { e.printStackTrace();} 
			  catch (OutOfGridException e) { e.printStackTrace();} 
		}
	}

	@SuppressWarnings("unused")
	private void wallKick() { //Moves tetromino during rotation if it is near a boundary

			
			
	}
	public void moveUp() throws NullBlockException, OutOfGridException {
		for(int i = 0; i < blockArray.length; i++) {
			blockArray[i].moveUp();
	}
	}
	public void moveDown() throws NullBlockException, OutOfGridException {
		
		for(int i = 0; i < blockArray.length; i++) {
			if(jtg.queryCollision(blockArray[i], Direction.DOWN))
				blockArray[i].bottomHit(true);
		}
		
		for(int i = 0; i < blockArray.length; i++) {
				blockArray[i].moveDown();
		}
	}
	public void moveLeft() throws NullBlockException, OutOfGridException {
		
		for(int i = 0; i < blockArray.length; i++) {
			if(jtg.queryCollision(blockArray[i], Direction.LEFT)) {
				blockArray[i].leftHit(true);
				leftHit = true;
			}	
		}
		
		for(int i = 0; i < blockArray.length; i++) {
			blockArray[i].moveLeft();
			
		}
	}
	public void moveRight() throws NullBlockException, OutOfGridException {
		
		for(int i = 0; i < blockArray.length; i++) {
			if(jtg.queryCollision(blockArray[i], Direction.RIGHT)) {
				blockArray[i].rightHit(true);
				rightHit = true;
			}
		}
		
		for(int i = 0; i < blockArray.length; i++) {	
			blockArray[i].moveRight();
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
