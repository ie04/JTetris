package com.ie04.jtetris;


import org.mini2Dx.core.graphics.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.ie04.jtetris.Direction;

public class TetrisBlock extends Sprite implements Animate {
	
	/*prev and next create a doubly linked list of tetrisBlocks, allowing
	 * collision information to propagate across the entire tetromino */
	private TetrisBlock next; 
	private TetrisBlock prev; 
	private TetrisBlock upBlock; 
	private TetrisBlock downBlock; 
	private TetrisBlock leftBlock; 
	private TetrisBlock rightBlock; 
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
		try {
		resetAdjacents();
		}catch(OutOfGridException e) {}
	}
	
	public TetrisBlock(String img, JTGrid jtg) {

		
		super(new Texture(img));
		setNext(null);
		setPrev(null);
		this.jtg = jtg;
		
	}
	private void resetAdjacents() throws OutOfGridException {
			upBlock = jtg.getRelativeToBlock(this, Direction.UP);
			downBlock = jtg.getRelativeToBlock(this, Direction.DOWN);
			leftBlock = jtg.getRelativeToBlock(this, Direction.LEFT);
			rightBlock = jtg.getRelativeToBlock(this, Direction.RIGHT);
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
		
		if(downBlock != null) {
			bottomHit();
			return;
		}
			
			try {
				jtg.updateBlock(this, Direction.DOWN);
				resetAdjacents();
			} catch(NullBlockException e){} 
		      catch (OutOfGridException e) {}
			
		
		
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
	public void topHit() {
		if(!topHit) {
			
			topHit = true;
			
			if(next != null)
				next.topHit();
			
			if(prev != null)
				prev.topHit();
			
		}
	}
	
	public void bottomHit() {
		
		if(!groundHit) {
			
			groundHit = true;
			
			if(next != null)
				next.bottomHit();
			
			if(prev != null)
				prev.bottomHit();
			
			
		}
		
		
	}
	public void leftHit(){
		if(!leftHit) {
					
					leftHit = true;
					
					if(next != null)
						next.leftHit();
					
					if(prev != null)
						prev.leftHit();
					
					
				}
	}
	public void rightHit() {
		if(!rightHit) {
					
					rightHit = true;
					
					if(next != null)
						next.rightHit();
					
					if(prev != null)
						prev.rightHit();
					
					
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
