/**
 *@author ie04
 * Jun 30, 2019 1:21:38 PM
 */
package com.ie04.jtetris.tetrominoes;

import com.ie04.jtetris.Direction;
import com.ie04.jtetris.JTGrid;
import com.ie04.jtetris.NullBlockException;
import com.ie04.jtetris.OutOfGridException;

public class ZTetromino extends Tetromino {

	/**
	 * @param img
	 * @param jtb
	 * @throws NullBlockException 
	 * @throws OutOfGridException 
	 */
	public ZTetromino(JTGrid jtg) throws OutOfGridException, NullBlockException {
		super("ZTetPiece.png", jtg);
	}

	@Override
	protected void construct() throws OutOfGridException, NullBlockException {
		blockArray.get(0).setPosition(3, 1);
		blockArray.get(1).setPosition(4, 1);
		blockArray.get(2).setPosition(2, 0);
		blockArray.get(3).setPosition(3, 0);
	}

	@Override
	public void rotate() throws NullBlockException, OutOfGridException {
		if(bottomHit)
			return;
		
		wallKick();
		if(currentState == 0) {
			jtg.setRelativeToBlock(blockArray.get(2), blockArray.get(3), Direction.UP);
			jtg.setRelativeToBlock(blockArray.get(0), blockArray.get(3), Direction.LEFT);
			jtg.setRelativeToBlock(blockArray.get(1), blockArray.get(0), Direction.DOWN);
			currentState = 1;
		}else if(currentState == 1) {
			jtg.setRelativeToBlock(blockArray.get(2), blockArray.get(3), Direction.RIGHT);
			jtg.setRelativeToBlock(blockArray.get(0), blockArray.get(3), Direction.UP);
			jtg.setRelativeToBlock(blockArray.get(1), blockArray.get(0), Direction.LEFT);
			currentState = 0;
		
		}
		
	}

	@Override
	protected void wallKick() throws NullBlockException, OutOfGridException {
		if(currentState == 0 && blockArray.get(2).getPosition().y == 0) {
			moveDown();
		}
		if(currentState == 1 && blockArray.get(2).getPosition().x == 7) {
			moveLeft();
		}
	}

}
