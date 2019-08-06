/**
 *@author ie04
 * Jun 29, 201910:11:57 PM
 */
package com.ie04.jtetris.tetrominoes;


import com.ie04.jtetris.Direction;
import com.ie04.jtetris.JTGrid;
import com.ie04.jtetris.NullBlockException;
import com.ie04.jtetris.OutOfGridException;
import com.ie04.jtetris.Vector2i;

public class JTetromino extends Tetromino {

	/**
	 * @param img
	 * @param jtb
	 * @throws NullBlockException 
	 * @throws OutOfGridException 
	 */
	public JTetromino(JTGrid jtg) throws OutOfGridException, NullBlockException {
		super("JTetPiece.png", jtg);
	}

	@Override
	protected void construct() throws OutOfGridException, NullBlockException {
		jtg.updateBlock(blockArray.get(0), new Vector2i(5, 1));
		jtg.updateBlock(blockArray.get(1), new Vector2i(5, 0));
		jtg.updateBlock(blockArray.get(2), new Vector2i(4, 0));
		jtg.updateBlock(blockArray.get(3), new Vector2i(3, 0));

	}

	@Override
	public void rotate() throws NullBlockException, OutOfGridException {
		if(bottomHit)
			return;
		
		wallKick();
		if(currentState == 0) {
			jtg.setRelativeToBlock(blockArray.get(3), blockArray.get(2), Direction.UP);
			jtg.setRelativeToBlock(blockArray.get(1), blockArray.get(2), Direction.DOWN);
			jtg.setRelativeToBlock(blockArray.get(0), blockArray.get(1), Direction.LEFT);
			currentState = 1;
		}
		else if(currentState == 1) {
			jtg.setRelativeToBlock(blockArray.get(1), blockArray.get(2), Direction.LEFT);
			jtg.setRelativeToBlock(blockArray.get(0), blockArray.get(1), Direction.UP);
			jtg.setRelativeToBlock(blockArray.get(3), blockArray.get(2), Direction.RIGHT);
			currentState = 2;
		}
		
		else if(currentState == 2){
			jtg.setRelativeToBlock(blockArray.get(1), blockArray.get(2), Direction.UP);
			jtg.setRelativeToBlock(blockArray.get(3), blockArray.get(2), Direction.DOWN);
			jtg.setRelativeToBlock(blockArray.get(0), blockArray.get(1), Direction.RIGHT);
			currentState = 3;
		}
		else if(currentState == 3) {
			jtg.setRelativeToBlock(blockArray.get(1), blockArray.get(2), Direction.RIGHT);
			jtg.setRelativeToBlock(blockArray.get(3), blockArray.get(2), Direction.LEFT);
			jtg.setRelativeToBlock(blockArray.get(0), blockArray.get(2), Direction.DOWN);
			currentState = 0;
		}
		
	}

	@Override
	protected void wallKick() {
		// TODO Auto-generated method stub
		
	}

}
