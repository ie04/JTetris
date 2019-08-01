/**
 *@author ie04
 * Jun 30, 20191:07:00 PM
 */
package com.ie04.jtetris.tetrominoes;

import com.ie04.jtetris.JTGrid;
import com.ie04.jtetris.NullBlockException;
import com.ie04.jtetris.OutOfGridException;
import com.ie04.jtetris.Vector2i;

public class LTetromino extends Tetromino {

	public LTetromino(JTGrid jtg) {
		super("LTetPiece.png", jtg);
	}
	
	protected void construct() {
		blockArray[0].setPosition(3, 1);
		blockArray[1].setPosition(3, 0);
		blockArray[2].setPosition(4, 0);
		blockArray[3].setPosition(5, 0);
	}

	@Override
	public void rotate() throws NullBlockException, OutOfGridException {
		if(bottomHit)
			return;
		if(currentState == 0) {
			jtg.updateBlock(blockArray[3], new Vector2i(blockArray[2].getPosition().x, blockArray[2].getPosition().y + 1));
			jtg.updateBlock(blockArray[1], new Vector2i(blockArray[2].getPosition().x, blockArray[2].getPosition().y - 1));
			jtg.updateBlock(blockArray[0], new Vector2i(blockArray[1].getPosition().x - 1, blockArray[1].getPosition().y));
			currentState = 1;
		}
		else if(currentState == 1) {
			jtg.updateBlock(blockArray[1], new Vector2i(blockArray[2].getPosition().x + 1, blockArray[2].getPosition().y));
			jtg.updateBlock(blockArray[0], new Vector2i(blockArray[1].getPosition().x, blockArray[2].getPosition().y - 1));
			jtg.updateBlock(blockArray[3], new Vector2i(blockArray[2].getPosition().x - 1, blockArray[2].getPosition().y));
			currentState = 2;
		}
		
		else if(currentState == 2){
			jtg.updateBlock(blockArray[1], new Vector2i(blockArray[2].getPosition().x, blockArray[2].getPosition().y + 1));
			jtg.updateBlock(blockArray[3], new Vector2i(blockArray[2].getPosition().x, blockArray[2].getPosition().y - 1));
			jtg.updateBlock(blockArray[0], new Vector2i(blockArray[1].getPosition().x + 1, blockArray[1].getPosition().y));
			currentState = 3;
		}
		else if(currentState == 3) {
			jtg.updateBlock(blockArray[1], new Vector2i(blockArray[2].getPosition().x - 1, blockArray[2].getPosition().y));
			jtg.updateBlock(blockArray[3], new Vector2i(blockArray[2].getPosition().x + 1, blockArray[2].getPosition().y));
			jtg.updateBlock(blockArray[0], new Vector2i(blockArray[1].getPosition().x, blockArray[2].getPosition().y + 1));
			currentState = 0;
		}
		
	}

}
