package com.ie04.jtetris.tetrominoes;

import com.ie04.jtetris.Direction;
import com.ie04.jtetris.JTGrid;
import com.ie04.jtetris.NullBlockException;
import com.ie04.jtetris.OutOfGridException;
import com.ie04.jtetris.TetrisBlock;

public class ITetromino extends Tetromino {
	
	public ITetromino(JTGrid jtg) throws OutOfGridException, NullBlockException{
		super("ITetPiece.png", jtg);
	}
	
	@Override
	public void construct() throws OutOfGridException, NullBlockException {
		blockArray.get(0).setPosition(4, 3);		
		blockArray.get(1).setPosition(4, 2);
		blockArray.get(2).setPosition(4, 1);
		blockArray.get(3).setPosition(4, 0);
		
	}
	
	@Override
	public void rotate() throws NullBlockException, OutOfGridException {
		if(bottomHit || this.blockArray.get(0).getPosition().y == 0)
			return;
		wallKick();
		if(currentState == 0) {
			jtg.setRelativeToBlock(blockArray.get(1), blockArray.get(2), Direction.LEFT);
			jtg.setRelativeToBlock(blockArray.get(0), blockArray.get(1), Direction.LEFT);
			jtg.setRelativeToBlock(blockArray.get(3), blockArray.get(2), Direction.RIGHT);
			currentState = 1;
		} else {
			jtg.setRelativeToBlock(blockArray.get(1), blockArray.get(2), Direction.DOWN);
			jtg.setRelativeToBlock(blockArray.get(0), blockArray.get(1), Direction.DOWN);
			jtg.setRelativeToBlock(blockArray.get(3), blockArray.get(2), Direction.UP);
			currentState = 0;
		}
	}

	@Override
	protected void wallKick() throws NullBlockException, OutOfGridException {
		if(currentState == 0) {
			if(blockArray.get(0).getPosition().x == 0) {
				moveRight();
				moveRight();
			} else if(blockArray.get(0).getPosition().x == 1) {
				moveRight();
			} else if(blockArray.get(0).getPosition().x == 7) {
				moveLeft();
			}
				
		}
				
				
		
	}
		

}
