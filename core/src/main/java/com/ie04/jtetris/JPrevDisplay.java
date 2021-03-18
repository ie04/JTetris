package com.ie04.jtetris;

import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.ie04.jtetris.tetrominoes.*;

public class JPrevDisplay extends Sprite{ //Tetromino preview used for hold and next windows
	
	public void render(Graphics g, int xPos, int yPos) {
		if(this.getTexture() != null)
			g.drawTexture(this.getTexture(), xPos, yPos);		
	}
	public void setDisplay(Tetromino tet) { //Sets image based on tetromino fed
		if(tet instanceof ITetromino)
			this.setTexture(new Texture("ITetPrev.png"));
		else if(tet instanceof JTetromino)
			this.setTexture(new Texture("JTetPrev.png"));
		else if(tet instanceof LTetromino)
			this.setTexture(new Texture("LTetPrev.png"));
		else if(tet instanceof OTetromino)
			this.setTexture(new Texture("OTetPrev.png"));
		else if(tet instanceof STetromino)
			this.setTexture(new Texture("STetPrev.png"));
		else if(tet instanceof TTetromino)
			this.setTexture(new Texture("TTetPrev.png"));
		else if(tet instanceof ZTetromino)
			this.setTexture(new Texture("ZTetPrev.png"));
		else
			throw new NullPointerException();
	}
}
