/**
 *@author ie04
 * Jun 30, 20191:21:38 PM
 */
package com.ie04.jtetris.tetrominoes;

import com.ie04.jtetris.JTGrid;
import com.ie04.jtetris.NullBlockException;
import com.ie04.jtetris.OutOfGridException;
import com.ie04.jtetris.Vector2i;

public class ZTetromino extends Tetromino {

	/**
	 * @param img
	 * @param jtb
	 */
	public ZTetromino(JTGrid jtg) {
		super("ZTetPiece.png", jtg);
	}

	@Override
	protected void construct() {
		blockArray[0].setPosition(3, 1);
		blockArray[1].setPosition(4, 1);
		blockArray[2].setPosition(2, 0);
		blockArray[3].setPosition(3, 0);
	}

	@Override
	public void rotate() throws NullBlockException, OutOfGridException {
		if(bottomHit)
			return;
		
		if(currentState == 0) {
			jtg.updateBlock(blockArray[2], new Vector2i(blockArray[3].getPosition().x, blockArray[3].getPosition().y - 1));
			jtg.updateBlock(blockArray[0], new Vector2i(blockArray[3].getPosition().x - 1, blockArray[3].getPosition().y));
			jtg.updateBlock(blockArray[1], new Vector2i(blockArray[0].getPosition().x, blockArray[0].getPosition().y + 1));
			currentState = 1;
		}else if(currentState == 1) {
			jtg.updateBlock(blockArray[2], new Vector2i(blockArray[3].getPosition().x + 1, blockArray[3].getPosition().y));
			jtg.updateBlock(blockArray[0], new Vector2i(blockArray[3].getPosition().x, blockArray[3].getPosition().y - 1));
			jtg.updateBlock(blockArray[1], new Vector2i(blockArray[0].getPosition().x - 1, blockArray[0].getPosition().y));
			currentState = 0;
		
		}
		
	}

}
