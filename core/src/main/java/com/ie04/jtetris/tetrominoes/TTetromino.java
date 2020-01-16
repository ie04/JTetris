/**
 *@author ie04
 * Jun 30, 20191:15:43 PM
 */
package com.ie04.jtetris.tetrominoes;

import com.ie04.jtetris.Direction;
import com.ie04.jtetris.JTGrid;
import com.ie04.jtetris.NullBlockException;
import com.ie04.jtetris.OutOfGridException;

public class TTetromino extends Tetromino {

	/**
	 * @param img
	 * @param jtb
	 * @throws NullBlockException 
	 * @throws OutOfGridException 
	 */
	public TTetromino(JTGrid jtg) throws OutOfGridException, NullBlockException {
		super("TTetPiece.png", jtg);
	}

	@Override
	protected void construct() throws OutOfGridException, NullBlockException {
		blockArray.get(0).setPosition(4, 1);
		blockArray.get(1).setPosition(3, 0);
		blockArray.get(2).setPosition(4, 0);
		blockArray.get(3).setPosition(5, 0);
	}

	@Override
	public void rotate() throws NullBlockException, OutOfGridException {
		if(bottomHit)
			return;
		
		wallKick();
		if(currentState == 0) {
			jtg.setRelativeToBlock(blockArray.get(1), blockArray.get(2), Direction.UP);
			jtg.setRelativeToBlock(blockArray.get(0), blockArray.get(2), Direction.LEFT);
			jtg.setRelativeToBlock(blockArray.get(3), blockArray.get(2), Direction.DOWN);
			currentState = 1;
		}else if(currentState == 1) {
			jtg.setRelativeToBlock(blockArray.get(1), blockArray.get(2), Direction.RIGHT);
			jtg.setRelativeToBlock(blockArray.get(0), blockArray.get(2), Direction.UP);
			jtg.setRelativeToBlock(blockArray.get(3), blockArray.get(2), Direction.LEFT);
			currentState = 2;
		}else if(currentState == 2) {
			jtg.setRelativeToBlock(blockArray.get(1), blockArray.get(2), Direction.UP);
			jtg.setRelativeToBlock(blockArray.get(0), blockArray.get(2), Direction.RIGHT);
			jtg.setRelativeToBlock(blockArray.get(3), blockArray.get(2), Direction.DOWN);
			currentState = 3;
		}else if(currentState == 3) {
			jtg.setRelativeToBlock(blockArray.get(1), blockArray.get(2), Direction.RIGHT);
			jtg.setRelativeToBlock(blockArray.get(0), blockArray.get(2), Direction.DOWN);
			jtg.setRelativeToBlock(blockArray.get(3), blockArray.get(2), Direction.LEFT);
			currentState = 0;
		}
	}

	@Override
	protected void wallKick() {
		// TODO Auto-generated method stub
		
	}

}
