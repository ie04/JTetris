package com.ie04.jtetris.tetrominoes;

import com.ie04.jtetris.JTGrid;
import com.ie04.jtetris.NullBlockException;
import com.ie04.jtetris.OutOfGridException;
import com.ie04.jtetris.Vector2i;

public class ITetromino extends Tetromino {
	
	public ITetromino(JTGrid jtg){
		super("ITetPiece.png", jtg);
	}
	
	@Override
	public void construct() {
		blockArray[0].setPosition(4, 3);		
		blockArray[1].setPosition(4, 2);
		blockArray[2].setPosition(4, 1);
		blockArray[3].setPosition(4, 0);
		
	}

	@Override
	public void rotate() throws NullBlockException, OutOfGridException {
		if(bottomHit)
			return;
		if(currentState == 0) {
			jtg.updateBlock(blockArray[1], new Vector2i(blockArray[2].getPosition().x - 1, blockArray[2].getPosition().y) );
			jtg.updateBlock(blockArray[0], new Vector2i(blockArray[1].getPosition().x - 1, blockArray[1].getPosition().y) );
			jtg.updateBlock(blockArray[3], new Vector2i(blockArray[2].getPosition().x + 1, blockArray[2].getPosition().y) );
			currentState = 1;
		} else {
			jtg.updateBlock(blockArray[1], new Vector2i(blockArray[2].getPosition().x, blockArray[2].getPosition().y + 1));
			jtg.updateBlock(blockArray[0], new Vector2i(blockArray[1].getPosition().x, blockArray[1].getPosition().y + 1));
			jtg.updateBlock(blockArray[3], new Vector2i(blockArray[2].getPosition().x, blockArray[2].getPosition().y - 1));
			currentState = 0;
		}
	}

}
