/**
 *@author ie04
 * Jul 19, 201912:29:37 PM
 */
package com.ie04.jtetris.tetrominoes;

import com.ie04.jtetris.JTGrid;

public class TestTetromino extends Tetromino {

	/**
	 * @param img
	 * @param jtg
	 */
	TestTetromino(String img, JTGrid jtg) {
		super(img, jtg);
		construct();
	}

	@Override
	protected void construct() {
		blockArray[0].setPosition(4, 0);

	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub

	}

}
