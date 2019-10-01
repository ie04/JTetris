package com.ie04.jtetris;


import org.mini2Dx.core.graphics.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.ie04.jtetris.Direction;
import com.ie04.jtetris.tetrominoes.Tetromino;

public class TetrisBlock extends Sprite implements Animate {
	
	/* prev and next create a doubly linked list of tetrisBlocks, allowing
	 * collision information to propagate across the entire tetromino */
	private Tetromino tet;
	private int tetID;
	private TetrisBlock next; 
	private TetrisBlock prev; 
	private JTGrid jtg;
	private Vector2i position;
	private boolean topHit = false;
	private boolean bottomHit = false;
	private boolean leftHit = false;
	private boolean rightHit = false;
	
	TetrisBlock(Vector2i position, String img, JTGrid jtg, Tetromino tet) {
		
		super(new Texture(img));
		setNext(null);
		setPrev(null);
		this.jtg = jtg;
		this.position = position;
		this.tet = tet;
		tetID = tet.tetID;
	}
	
	public TetrisBlock(String img, JTGrid jtg, Tetromino tet) {	
		this(new Vector2i(0,0), img, jtg, tet);
	}
	public void moveUp() throws NullBlockException, OutOfGridException { //Used for wallKick() and to satisfy Animate
		if(!topHit) {		
			jtg.updateBlock(this, Direction.UP);
			
			
		}
	}
	@Override
	public void moveDown() throws NullBlockException, OutOfGridException {
		if(!bottomHit) {
			jtg.updateBlock(this, Direction.DOWN);
		}
	}
	@Override
	public void moveRight() throws NullBlockException, OutOfGridException {
		
		if(!rightHit && !bottomHit) {
			jtg.updateBlock(this, Direction.RIGHT);
		}
		if(leftHit)
			leftHit = false;
	}
	@Override
	public void moveLeft() throws NullBlockException, OutOfGridException {
		
		if(!leftHit && !bottomHit) {
			jtg.updateBlock(this, Direction.LEFT);
			
		}
		
		if(rightHit)
			rightHit = false;
		
	}
	public void topHit(boolean is) {
		if(is) {
			if(!topHit) {
				topHit = true;
				
				if(next != null)
					next.topHit(true);
				
				if(prev != null)
					prev.topHit(true);
				
				tet.setTopHit(true);
			}
			
		}else {
			if(topHit) {
				topHit = false;
				
				if(next != null)
					next.topHit(false);
				
				if(prev != null)
					prev.topHit(false);
				
				tet.setTopHit(false);
			}
		
		}
			
	}
	
	public void bottomHit(boolean is) {
		if(is) {
			if(!bottomHit) {
				
				bottomHit = true;
				
				if(next != null)
					next.bottomHit(true);
				
				if(prev != null)
					prev.bottomHit(true);	
				
				tet.setBottomHit(true);
			}
		}else {
			if(bottomHit) {
			
				bottomHit = false;
			
				if(next != null)
					next.bottomHit(false);
			
				if(prev != null)
					prev.bottomHit(false);
				
				tet.setBottomHit(false);
			
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
						
				tet.setLeftHit(true);	
			}
		}else {
			if(leftHit) {
				leftHit = false;
				
				if(next != null)
					next.leftHit(true);
						
				if(prev != null)
					prev.leftHit(true);
				
				tet.setLeftHit(false);
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
					
				tet.setRightHit(true);	
				}
		}else {
			if(rightHit) {	
				rightHit = false;
					
				if(next != null)
					next.rightHit(false);
					
				if(prev != null)
					prev.rightHit(false);
					
				tet.setRightHit(false);	
				}
		}
	}
	public boolean isBlockForeign(TetrisBlock block) {
		
		if(block == null || block.getTetID() == this.getTetID())
			return false;
		else
			return true;
	}
	public void destruct() throws OutOfGridException {
		this.tet.deleteBlock(this);
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
		return bottomHit;
	}
	public boolean isLeftHit() {
		return leftHit;
	}
	public boolean isRightHit() {
		return rightHit;
	}
	public Tetromino getTet() {
		return tet;
	}
	public int getTetID() {
		return tetID;
	}
}
