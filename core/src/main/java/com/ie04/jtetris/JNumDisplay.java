package com.ie04.jtetris;

import org.mini2Dx.core.graphics.Graphics;

public class JNumDisplay {
	private int value = 0;
	private int x;
	private int y;
	
	public JNumDisplay(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void updateNum(int value) {
		this.value = value;
	}
	public void incrementNum() {
		updateNum(value++);
	}
	public void addToNum(int toAdd) {
		updateNum(value+toAdd);
	}
	public void render(Graphics g) {
		g.drawString(Integer.toString(value), x, y);
	}
}
