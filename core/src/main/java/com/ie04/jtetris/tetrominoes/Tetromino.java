package com.ie04.jtetris.tetrominoes;

import java.util.ArrayList;

import javax.swing.text.Position;

import org.mini2Dx.core.graphics.Graphics;
import com.ie04.jtetris.Animate;
import com.ie04.jtetris.Direction;
import com.ie04.jtetris.JTGrid;
import com.ie04.jtetris.NullBlockException;
import com.ie04.jtetris.OutOfGridException;
import com.ie04.jtetris.TetrisBlock;

public abstract class Tetromino implements Animate{
	
	private static int numTetrominoes; //Amount of tetrominoes spawned on grid
	protected static int NUM_BLOCKS = 4; //All tetrominoes have 4 blocks
	public int tetID; //Each tetromino recieves an ID to differentiate its own blocks from others during collision checking
	protected JTGrid jtg;
	public ArrayList<TetrisBlock> blockArray; 
	protected int currentState = 0; //State of figure used for rotation
	protected boolean topHit;
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
	protected abstract void wallKick() throws NullBlockException, OutOfGridException;
	
	public void deleteBlock(TetrisBlock block) throws OutOfGridException {
		if(blockArray.contains(block)) {
			blockArray.remove(block);
			jtg.deleteAtVector(block.getPosition());
		}
	}
	public void render(Graphics g) {
		
			for(int i = 0; i < blockArray.size(); i++) {
				try {
					jtg.addBlockToGrid(blockArray.get(i), g);
				} catch (OutOfGridException e) { e.printStackTrace(); }
			}
		
	}
	

	public void update(Direction direction) throws NullBlockException, OutOfGridException {

		
		for(int i = 0; i < blockArray.size(); i++) {
			jtg.updateBlock(blockArray.get(i), direction); 
		}
	}




	public void moveUp() throws NullBlockException, OutOfGridException {
		for(int i = 0; i < blockArray.size(); i++) {
			blockArray.get(i).moveUp();
	}
	}
	public void moveDown() throws NullBlockException, OutOfGridException {
		
		for(int i = 0; i < blockArray.size(); i++) { //Tests individual blocks for collision
			if(jtg.queryCollision(blockArray.get(i), Direction.DOWN)) {
				blockArray.get(i).bottomHit(true);
				bottomHit = true;
			}
		}
		
		for(int i = 0; i < blockArray.size(); i++) {
			blockArray.get(i).moveDown();
		}
	}
	public void moveLeft() throws NullBlockException, OutOfGridException {
		boolean areAnyHit = false;
		for(int i = 0; i < blockArray.size(); i++) { 
			if(jtg.queryCollision(blockArray.get(i), Direction.LEFT)) {
				blockArray.get(i).leftHit(true);
				leftHit = true;
			}	
		}
		for(int i = 0; i < blockArray.size(); i++) {
			areAnyHit = blockArray.get(i).isLeftHit() ? true : false;
			if(areAnyHit)
				break;
		}
		if(!areAnyHit) {
			for(int i = 0; i < blockArray.size(); i++) {
				blockArray.get(i).leftHit(false);
			}
				
		}
			
		for(int i = 0; i < blockArray.size(); i++) {
			blockArray.get(i).moveLeft();
			
		}
		if(rightHit)
			rightHit = false;
		
	}
	public void moveRight() throws NullBlockException, OutOfGridException {
		
		for(int i = 0; i < blockArray.size(); i++) {
			if(jtg.queryCollision(blockArray.get(i), Direction.RIGHT)) {
				blockArray.get(i).rightHit(true);
				rightHit = true;
			}
		}
		
		for(int i = 0; i < blockArray.size(); i++) {	
			blockArray.get(i).moveRight();
		}
	}
	public void selfDestruct() throws OutOfGridException {
		for(int i = 0; i < blockArray.size(); i++)
			deleteBlock(blockArray.get(i));
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
	public boolean isBottomHit() {
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
