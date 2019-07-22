/**
 *@author ie04
 * Jun 30, 20191:15:43 PM
 */
package com.ie04.jtetris.tetrominoes;

import com.ie04.jtetris.JTGrid;
import com.ie04.jtetris.NullBlockException;
import com.ie04.jtetris.Vector2i;

public class TTetromino extends Tetromino {

	/**
	 * @param img
	 * @param jtb
	 */
	public TTetromino(JTGrid jtg) {
		super("TTetPiece.png", jtg);
	}

	@Override
	protected void construct() {
		blockArray[0].setPosition(4, 1);
		blockArray[1].setPosition(3, 0);
		blockArray[2].setPosition(4, 0);
		blockArray[3].setPosition(5, 0);
	}

	@Override
	public void rotate() {
		if(groundHit)
			return;
		if(currentState == 0) {
			try {
			jtg.updateBlock(blockArray[1], new Vector2i(blockArray[2].getPosition().x, blockArray[2].getPosition().y - 1));
			jtg.updateBlock(blockArray[0], new Vector2i(blockArray[2].getPosition().x - 1, blockArray[2].getPosition().y));
			jtg.updateBlock(blockArray[3], new Vector2i(blockArray[2].getPosition().x, blockArray[2].getPosition().y + 1));
			} catch(NullBlockException e) { e.printStackTrace(); }
			currentState = 1;
		}else if(currentState == 1) {
		
			try {
			jtg.updateBlock(blockArray[1], new Vector2i(blockArray[2].getPosition().x + 1, blockArray[2].getPosition().y));
			jtg.updateBlock(blockArray[0], new Vector2i(blockArray[2].getPosition().x, blockArray[2].getPosition().y - 1));
			jtg.updateBlock(blockArray[3], new Vector2i(blockArray[2].getPosition().x - 1, blockArray[2].getPosition().y));
			} catch(NullBlockException e) { e.printStackTrace(); }
			currentState = 2;
		}else if(currentState == 2) {
			try {
			jtg.updateBlock(blockArray[1], new Vector2i(blockArray[2].getPosition().x, blockArray[2].getPosition().y - 1));
			jtg.updateBlock(blockArray[0], new Vector2i(blockArray[2].getPosition().x + 1, blockArray[2].getPosition().y));
			jtg.updateBlock(blockArray[3], new Vector2i(blockArray[2].getPosition().x, blockArray[2].getPosition().y + 1));
			} catch(NullBlockException e) { e.printStackTrace(); }
			currentState = 3;
		}else if(currentState == 3) {
			try {
			jtg.updateBlock(blockArray[1], new Vector2i(blockArray[2].getPosition().x + 1, blockArray[2].getPosition().y));
			jtg.updateBlock(blockArray[0], new Vector2i(blockArray[2].getPosition().x, blockArray[2].getPosition().y + 1));
			jtg.updateBlock(blockArray[3], new Vector2i(blockArray[2].getPosition().x - 1, blockArray[2].getPosition().y));
			} catch(NullBlockException e) { e.printStackTrace(); }
			currentState = 0;
		}
	}

}
