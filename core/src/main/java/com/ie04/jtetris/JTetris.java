package com.ie04.jtetris;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class JTetris extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.ie04.jtetris";

	private JTBoard jtb; //Board containing tetris grid, score, etc.
	public static float inputTime = 0; //Records game time, used for setting update speed
	public static double speed = 0.3; //Updates if inputTime equals this value
	public JNumDisplay font;
	
	@Override
    public void initialise() {
    	try {
			jtb = new JTBoard();
		} catch (OutOfGridException e) {
			e.printStackTrace();
		} catch (NullBlockException e) {
			e.printStackTrace();
		}  
    	
    }
    
    @Override
    public void update(float delta) {
    	boolean leftArrowPressed = Gdx.input.isKeyJustPressed(Input.Keys.LEFT);
    	boolean rightArrowPressed = Gdx.input.isKeyJustPressed(Input.Keys.RIGHT);
    	boolean downArrowPressed = Gdx.input.isKeyJustPressed(Input.Keys.DOWN);
    	boolean upArrowPressed = Gdx.input.isKeyJustPressed(Input.Keys.UP);
    	boolean heldPressed = Gdx.input.isKeyJustPressed(Input.Keys.H);
    	inputTime += delta;
	    try {
	    	
	    	if (inputTime > speed) { //speed represents how many seconds to wait before a frame update 
	    		jtb.currentTet.moveDown();
	    		
	    		if(jtb.currentTet.isBottomHit()) {
	    			jtb.queryPointsGained(jtb.jtg.cleaveComplete());
	    			speed = 0.3; //Resets speed if down was hit
	    			jtb.switchFocus(); //Switches focus to new random tetromino
	    			
	    		}
	    		
	    		if(jtb.jtg.isWallHit()) {
	    			System.exit(0);
	    		}
	    		
	    		inputTime = 0;
	    	}
	    	
	    	if(leftArrowPressed) 
	    		jtb.currentTet.moveLeft();
	    	
	    	if(rightArrowPressed)
	    		jtb.currentTet.moveRight();
	    	
	    	if(downArrowPressed)
	    		speed = 0; //speeds up tetrominos by not delaying frame updates
	    	
	    	if(upArrowPressed)
	    		jtb.currentTet.rotate(); //Rotates clockwise
	    	
	    	if(heldPressed) { //Switches hold register with current tetromino
	    		jtb.switchHeld();
	    	}
	    	}catch(NullBlockException e) { e.printStackTrace(); System.exit(0);}
	    	 catch(OutOfGridException e) { e.printStackTrace(); System.exit(0);}
    }
    
    @Override
    public void interpolate(float alpha) {}
    
    @Override
    public void render(Graphics g) { 
    	jtb.render(g);	//Renders all JTBoard objects
    }
}
