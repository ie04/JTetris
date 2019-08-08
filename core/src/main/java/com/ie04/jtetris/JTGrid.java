/**
 *@author ie04
 * Jun 30, 2019 2:43:36 PM
 */
package com.ie04.jtetris;

import org.mini2Dx.core.graphics.Graphics;

public class JTGrid {
	

	public static final float LEFT_EXTREME = 21; //White border contains extra pixel
	public static final float RIGHT_EXTREME = 121;
	public static final float TOP_EXTREME = 21;
	public static final float BLOCK_WIDTH = 20; //Block width in pixels
	public static final float MAX_Y = 17; //Excludes 0
	public static final float MAX_X = 7;
	public static final float MIN_XY = 0; //Both 0
	private TetrisBlock[][] tetGrid;
	
	JTGrid(){
		tetGrid = new TetrisBlock[18][8]; //0 inclusive
	}

	public boolean queryCollision(TetrisBlock block, Direction direction) throws OutOfGridException {
		switch(direction) {
		case DOWN:
			if(block.getPosition().y == MAX_Y  || isForeignBlockAdjacent(block, Direction.DOWN)) return true; else return false;
		case LEFT:
			if(block.getPosition().x == MIN_XY || isForeignBlockAdjacent(block, Direction.LEFT)) return true; else return false;
		case RIGHT:
			if(block.getPosition().x == MAX_X  || isForeignBlockAdjacent(block, Direction.RIGHT)) return true; else return false;
		case UP:
			if(block.getPosition().y == MIN_XY || isForeignBlockAdjacent(block, Direction.UP)) return true; else return false;
		default:
			return false;
		
		}
		
	}


	private boolean doesVectorExceedBounds(Vector2i vec) {
		if(vec.x > MAX_X || vec.x < MIN_XY || vec.y > MAX_Y || vec.y < MIN_XY)
			return true;
		else
			return false;
	}
	private void setAtVector(Vector2i vec, TetrisBlock block) throws OutOfGridException {
		if(doesVectorExceedBounds(vec))
			throw new OutOfGridException();
		
		tetGrid[vec.y][vec.x] = block;
	}
	private void setAtVector(TetrisBlock block) throws OutOfGridException {
		setAtVector(block.getPosition(), block);
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
		if( block.getPosition().x + xOffset < 0 || block.getPosition().y + yOffset < 0)
			return block; //If offset is greater than bounds, return itself
		
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
	public void setRelativeToBlock(TetrisBlock setblock, TetrisBlock relBlock, int xOffset, int yOffset) throws OutOfGridException, NullBlockException {
		updateBlock(setblock, new Vector2i(relBlock.getPosition().x + xOffset, relBlock.getPosition().y + yOffset));	
	}
	public void setRelativeToBlock(TetrisBlock setBlock, TetrisBlock relBlock, Direction direction) throws OutOfGridException, NullBlockException {
		switch(direction) {
		case DOWN:
			setRelativeToBlock(setBlock, relBlock, 0, 1);
			break;
		case LEFT:
			setRelativeToBlock(setBlock, relBlock, -1, 0);
			break;
		case RIGHT:
			setRelativeToBlock(setBlock, relBlock, 1, 0);
			break;
		case UP:
			setRelativeToBlock(setBlock, relBlock, 0, -1);
			break;
		default:
			break;
		
		}
	}
	public boolean isForeignBlockAdjacent(TetrisBlock block, Direction direction) throws OutOfGridException {
		switch(direction) {
		case DOWN:
			if(block.isBlockForeign(getRelativeToBlock(block, Direction.DOWN))) return true; else return false;
		case LEFT:
			if(block.isBlockForeign(getRelativeToBlock(block, Direction.LEFT))) return true; else return false;
		case RIGHT:
			if(block.isBlockForeign(getRelativeToBlock(block, Direction.RIGHT))) return true; else return false;
		case UP:
			if(block.isBlockForeign(getRelativeToBlock(block, Direction.UP))) return true; else return false;
		default:
			break;
		
		}
		return false;
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
		g.drawSprite(block, JTGrid.TOP_EXTREME + (gridPos.x * BLOCK_WIDTH), 
							JTGrid.TOP_EXTREME + (gridPos.y * BLOCK_WIDTH));
		
		
	}
	//Adds block to vector based on block position
	public void addBlockToGrid(TetrisBlock block, Graphics g) throws OutOfGridException {	
		addBlockToGrid(block, block.getPosition(), g);
	}
	public void updateBlock(TetrisBlock block, Direction direction) throws NullBlockException, OutOfGridException{ 
		
		if(block == null)
			throw new NullBlockException();	
		

		Vector2i prevPosition = block.getPosition();

		Vector2i newPosition;
		
		switch(direction) {
		case UP:
			if(prevPosition.y > MIN_XY) {
				newPosition = new Vector2i(prevPosition.x, block.getPosition().y - 1);
				updateBlock(block, newPosition);
			}
			break;
		case DOWN:
			if(prevPosition.y < MAX_Y) {
				newPosition = new Vector2i(prevPosition.x, prevPosition.y + 1);
				updateBlock(block, newPosition);
				
			}
				
			break;
		case LEFT:	
			if(prevPosition.x > MIN_XY) {
				newPosition = new Vector2i(prevPosition.x - 1, prevPosition.y);
				updateBlock(block, newPosition);			
			}
				
			break;
		case RIGHT:
			if(prevPosition.x < MAX_X) {
				newPosition = new Vector2i(prevPosition.x + 1, prevPosition.y);
				updateBlock(block, newPosition);	
			}
			break;
		}
	}
	

	public void updateBlock(TetrisBlock block, Vector2i newPosition) throws OutOfGridException, NullBlockException { //Safely moves TetrisBlock while deleting previous position

		
		if(block == null)
				throw new NullBlockException();
			
		deleteAtVector(block.getPosition());
		block.setPosition(newPosition);
		setAtVector(block);

		
			
				
			
	}	
	public int testLineComplete() throws OutOfGridException {
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
	public void cleaveLine(int line) throws OutOfGridException { //Deletes selected line
		if(line > MAX_Y || line < MIN_XY)
			throw new OutOfGridException();
		
		for(int i = 0; i < 8; i++) {
			if(isBlockAtVector(i, line))
				getAtVector(i, line).destruct();
		}
		
	}
}
