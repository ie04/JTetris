/**
 *@author ie04
 * Jun 30, 20191:48:37 PM
 */
package com.ie04.jtetris.tetrominoes;

import com.ie04.jtetris.JTGrid;
import com.ie04.jtetris.NullBlockException;
import com.ie04.jtetris.OutOfGridException;
import com.ie04.jtetris.Vector2i;

public class OTetromino extends Tetromino {

	/**
	 * @param img
	 * @param jtb
	 * @throws NullBlockException 
	 * @throws OutOfGridException 
	 */
	public OTetromino(JTGrid jtg) throws OutOfGridException, NullBlockException {
		super("OTetPiece.png", jtg);
	}

	@Override
	protected void construct() throws OutOfGridException, NullBlockException {
		blockArray.get(0).setPosition(3, 1);
		blockArray.get(1).setPosition(4, 1);
		blockArray.get(2).setPosition(3, 0);
		blockArray.get(3).setPosition(4, 0);
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void wallKick() {
		// TODO Auto-generated method stub
		
	}

}
