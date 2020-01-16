/**
 *@author ie04
 * Jun 27, 20197:04:58 PM
 */
package com.ie04.jtetris;

public interface Animate { //Interface of animate figures
	
	public void moveUp()    throws NullBlockException, OutOfGridException;
	public void moveDown()  throws NullBlockException, OutOfGridException;
	public void moveRight() throws NullBlockException, OutOfGridException;
	public void moveLeft()  throws NullBlockException, OutOfGridException;
}
