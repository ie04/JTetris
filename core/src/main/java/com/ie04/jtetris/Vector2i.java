/**
 *@author ie04
 * Jun 27, 2019 7:16:38 PM
 */
package com.ie04.jtetris;

public class Vector2i {
	    public int x;
	    public int  y;
	    
	    public Vector2i(int x, int y){
	    	this.x = x;
	    	this.y = y;
	    }
	    public String printPosition() {
	    	return this.x + "," + this.y;
	    }
}
