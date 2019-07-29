package com.ie04.jtetris;


import org.mini2Dx.core.graphics.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.ie04.jtetris.Direction;

public class TetrisBlock extends Sprite implements Animate {
	
	/*prev and next create a doubly linked list of tetrisBlocks, allowing
	 * collision information to propagate across the entire tetromino */
	private TetrisBlock next; 
	private TetrisBlock prev; 
	private JTGrid jtg;
	private Vector2i position;
	private Vector2i prevPosition;
	private boolean topHit = false;
	private boolean groundHit = false;
	private boolean leftHit = false;
	private boolean rightHit = false;
	
	TetrisBlock(Vector2i position, String img, JTGrid jtg) {
		
		super(new Texture(img));
		setNext(null);
		setPrev(null);
		this.jtg = jtg;
		this.position = position;
	}
	
	public TetrisBlock(String img, JTGrid jtg) {

		
		super(new Texture(img));
		setNext(null);
		setPrev(null);
		this.jtg = jtg;
		
	}
	public void moveUp() { //Used for wallKick() and to satisfy Animate
		if(!topHit) {
			
			try {
				jtg.updateBlock(this, Direction.UP);
			} catch (NullBlockException e) {}
			
		}
	}
	@Override
	public void moveDown() {
			try {
				jtg.updateBlock(this, Direction.DOWN);
			} catch(NullBlockException e){} 	
	}
	@Override
	public void moveRight() {

		if(!rightHit && !groundHit) {
			try {
				jtg.updateBlock(this, Direction.RIGHT);
			} catch (NullBlockException e) {}
			if(leftHit)
				leftHit = false; //Removes residual collision
		}
	}
	@Override
	public void moveLeft() {

		
		if(!leftHit && !groundHit) {
			try {
				jtg.updateBlock(this, Direction.LEFT);
			} catch (NullBlockException e) {}
			if(rightHit)
				rightHit = false;
			}
		
	}
	public void topHit(boolean is) {
		if(is) {
			if(!topHit) {
				topHit = true;
				
				if(next != null)
					next.topHit(true);
				
				if(prev != null)
					prev.topHit(true);
			}
			
		}else {
			if(topHit) {
				topHit = false;
				
				if(next != null)
					next.topHit(false);
				
				if(prev != null)
					prev.topHit(false);
			}
		}
			
	}
	
	public void bottomHit(boolean is) {
		if(is) {
			if(!groundHit) {
				
				groundHit = true;
				
				if(next != null)
					next.bottomHit(true);
				
				if(prev != null)
					prev.bottomHit(true);	
			}
		}else {
			if(groundHit) {
				
				groundHit = false;
			
				if(next != null)
					next.bottomHit(false);
			
				if(prev != null)
					prev.bottomHit(false);
			
			}
		}
		
	}
	public void leftHit(boolean is){
		if(is) {
			if(!leftHit) {
						
				leftHit = true;
						
				if(next != null)
					next.leftHit(true);
						
				if(prev != null)
					prev.leftHit(true);
						
						
			}
		}else {
			if(leftHit) {
				leftHit = false;
				
				if(next != null)
					next.leftHit(true);
						
				if(prev != null)
					prev.leftHit(true);
			}
		}
	}
	public void rightHit(boolean is) {
		if(is) {
			if(!rightHit) {
					
				rightHit = true;
					
				if(next != null)
					next.rightHit(true);
					
				if(prev != null)
					prev.rightHit(true);
					
					
				}
		}else {
			if(rightHit) {	
				rightHit = false;
					
				if(next != null)
					next.rightHit(false);
					
				if(prev != null)
					prev.rightHit(false);
					
					
				}
		}
	}
	public TetrisBlock getNext() {
		return next;
	}
	public void setNext(TetrisBlock next) {
		this.next = next;
	}
	public TetrisBlock getPrev() {
		return prev;
	}
	public void setPrev(TetrisBlock prev) {
		this.prev = prev;
	}
	public Vector2i getPosition() {
		return position;
	}
	public void setPosition(Vector2i position) {
		this.position = position;
	}
	public void setPosition(int x, int y) {
		this.position = new Vector2i(x, y);
	}
	public boolean isGroundHit() {
		return groundHit;
	}
	public Vector2i getPrevPosition() {
		return prevPosition;
	}
	public void setPrevPosition(Vector2i prevPosition) {
		this.prevPosition = prevPosition;
	}
	
}
