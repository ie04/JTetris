/**
 *@author ie04
 * Jun 30, 20191:24:50 PM
 */
package com.ie04.jtetris.tetrominoes;

import com.ie04.jtetris.Direction;
import com.ie04.jtetris.JTGrid;
import com.ie04.jtetris.NullBlockException;
import com.ie04.jtetris.OutOfGridException;

public class STetromino extends Tetromino {

	/**
	 * @param img
	 * @param jtb
	 * @throws NullBlockException 
	 * @throws OutOfGridException 
	 */
	public STetromino(JTGrid jtg) throws OutOfGridException, NullBlockException {
		super("STetPiece.png", jtg);
	}

	@Override
	protected void construct() throws OutOfGridException, NullBlockException {
		blockArray.get(0).setPosition(2, 1);
		blockArray.get(1).setPosition(3, 1);
		blockArray.get(2).setPosition(3, 0);
		blockArray.get(3).setPosition(4, 0);

	}

	@Override
	public void rotate() throws NullBlockException, OutOfGridException {
		if(bottomHit)
			return;
		
		wallKick();
		if(currentState == 0) {
			jtg.setRelativeToBlock(blockArray.get(1), blockArray.get(2), Direction.LEFT);
			jtg.setRelativeToBlock(blockArray.get(0), blockArray.get(1), Direction.UP);
			jtg.setRelativeToBlock(blockArray.get(3), blockArray.get(2), Direction.DOWN);
			currentState = 1;
		}else if(currentState == 1) {
			jtg.setRelativeToBlock(blockArray.get(1), blockArray.get(2), Direction.UP);
			jtg.setRelativeToBlock(blockArray.get(0), blockArray.get(1), Direction.RIGHT);
			jtg.setRelativeToBlock(blockArray.get(3), blockArray.get(2), Direction.LEFT);
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
