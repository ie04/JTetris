/**
 *@author ie04
 * Jun 30, 2019 2:43:36 PM
 */
package com.ie04.jtetris;

import org.mini2Dx.core.graphics.Graphics;

public class JTGrid {
	
	public static final float LEFT_EXTREME = 21; //In pixels
	public static final float RIGHT_EXTREME = 121;
	public static final float TOP_EXTREME = 21;
	public static final float MAX_Y = 17; //Excludes 0
	public static final float MAX_X = 7;
	public static final float MIN_XY = 0; //Both 0
	private TetrisBlock[][] tetGrid;
	
	JTGrid(){
		tetGrid = new TetrisBlock[18][8]; //0 inclusive
	}
	private void queryCollision(TetrisBlock block) {
		
	}
	private boolean doesVectorExceedBounds(Vector2i vec) {
		if(vec.x > MAX_X || vec.x < MIN_XY || vec.y > MAX_Y || vec.x < MIN_XY)
			return true;
		else
			return false;
	}
	private void setAtVector(Vector2i vec, TetrisBlock block) throws OutOfGridException {
		if(doesVectorExceedBounds(vec))
			throw new OutOfGridException();
		
		tetGrid[vec.y][vec.x] = block;
	}
	public TetrisBlock getAtVector(Vector2i vec) throws OutOfGridException {
		if(doesVectorExceedBounds(vec))
			throw new OutOfGridException();
		return tetGrid[vec.y][vec.x];
	}
	public TetrisBlock getAtVector(int x, int y) throws OutOfGridException {
		return getAtVector(new Vector2i(x, y));
	}
	public TetrisBlock getRelativeToBlock(TetrisBlock block, int xOffset, int yOffset) throws OutOfGridException {
		return getAtVector(block.getPosition().x + xOffset, block.getPosition().y + yOffset);
	}
	public TetrisBlock getRelativeToBlock(TetrisBlock block, Direction direction) throws OutOfGridException {
		switch(direction) {
		case UP: return getRelativeToBlock(block, 0, -1);
		case DOWN: return getRelativeToBlock(block, 0, 1);
		case LEFT: return getRelativeToBlock(block, -1, 0);
		case RIGHT: return getRelativeToBlock(block, 1, 0);
		default: return block;
		}
	}
	private void deleteAtVector(Vector2i vec) throws OutOfGridException {
		
		if(doesVectorExceedBounds(vec))
			throw new OutOfGridException();
		
		tetGrid[vec.y][vec.x] = null;
	}
	public void deleteAtVector(int x, int y) throws OutOfGridException {
		deleteAtVector(new Vector2i(x,y));
	}
	
	public boolean isBlockAtVector(Vector2i vec) throws OutOfGridException {
		if(doesVectorExceedBounds(vec))
			return false;
		
		if(getAtVector(vec) != null)
			return true;
		else
			return false;
	}
	public boolean isBlockAtVector(int x, int y) throws OutOfGridException {
		return isBlockAtVector(new Vector2i(x, y));
	}
	//Adds block to grid using explicit position
	public void addBlockToGrid(TetrisBlock block, Vector2i gridPos, Graphics g) throws OutOfGridException {
		if(doesVectorExceedBounds(gridPos)) 
			throw new OutOfGridException();
		
		block.setPosition(gridPos);
		setAtVector(gridPos, block);
		g.drawSprite(block, JTGrid.TOP_EXTREME + (gridPos.x * 20), 
							JTGrid.TOP_EXTREME + (gridPos.y * 20) );
		
		
	}
	//Adds block to vector based on block position
	public void addBlockToGrid(TetrisBlock block, Graphics g) throws OutOfGridException {	
		addBlockToGrid(block, block.getPosition(), g);
	}
	public void updateBlock(TetrisBlock block, Direction direction) throws NullBlockException { 
		
		if(block.getPosition() == null)
			throw new NullBlockException();	
		
		
		
		block.setPrevPosition(block.getPosition());
		Vector2i newPosition;
		
		switch(direction) {
		case UP:
			if(block.getPrevPosition().y > MIN_XY) {
				newPosition = new Vector2i(block.getPrevPosition().x, block.getPosition().y - 1);
				updateBlock(block, newPosition);
			}
			break;
		case DOWN:
			if(block.getPrevPosition().y < MAX_Y) {
				newPosition = new Vector2i(block.getPrevPosition().x, block.getPrevPosition().y + 1);
				updateBlock(block, newPosition);
				
			}
			break;
		case LEFT:	
			if(block.getPrevPosition().x > MIN_XY) {
				newPosition = new Vector2i(block.getPrevPosition().x - 1, block.getPrevPosition().y);
				updateBlock(block, newPosition);			
			}
			break;
		case RIGHT:
			if(block.getPrevPosition().x < MAX_X) {
				newPosition = new Vector2i(block.getPrevPosition().x + 1, block.getPrevPosition().y);
				updateBlock(block, newPosition);	
			}
			break;
		
		}
	}
	
	public void updateBlock(TetrisBlock block, Vector2i newPosition) throws NullBlockException { //Safely moves TetrisBlock while deleting previous position
		
		if(block == null)
			throw new NullBlockException();
		
		try {
			if(isBlockAtVector(newPosition)) {
				block.setPrevPosition(block.getPosition());
				block.setPosition(newPosition);
				deleteAtVector(block.getPrevPosition());
			}	
		}catch(OutOfGridException e) { e.printStackTrace(); }
	}	
	public int testLineComplete() {
		int pointsAwarded = 0;
		int blocksInLine = 0;
		for(int i = 0; i < MAX_Y; i++) {
			
			for(int j = 0; j < MAX_X; j++) {
				
				if(tetGrid[i][j] == null)
					break;	
				 else 
					blocksInLine++;
			}
			if(blocksInLine == MAX_Y) { //If line is filled
				cleaveLine(i);
				pointsAwarded++;
				blocksInLine = 0; 
			}
				
		}
		return pointsAwarded;
	}
	public void cleaveLine(int line) { //Deletes selected line
		if(line > MAX_Y)
			return;
		
		for(int i = 0; i < MAX_X; i++) {
			try {
				deleteAtVector(line, i);
			} catch (OutOfGridException e) {
				e.printStackTrace();
			}
		}
		
	}
}
