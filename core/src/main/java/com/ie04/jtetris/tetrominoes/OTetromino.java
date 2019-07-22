/**
 *@author ie04
 * Jun 30, 20191:48:37 PM
 */
package com.ie04.jtetris.tetrominoes;

import com.ie04.jtetris.JTGrid;

public class OTetromino extends Tetromino {

	/**
	 * @param img
	 * @param jtb
	 */
	public OTetromino(JTGrid jtg) {
		super("OTetPiece.png", jtg);
	}

	@Override
	protected void construct() {
		blockArray[0].setPosition(3, 1);
		blockArray[1].setPosition(4, 1);
		blockArray[2].setPosition(3, 0);
		blockArray[3].setPosition(4, 0);
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub
		
	}

}
