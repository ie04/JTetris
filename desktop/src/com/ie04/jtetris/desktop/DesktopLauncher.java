package com.ie04.jtetris.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;
import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import com.ie04.jtetris.JTetris;

public class DesktopLauncher {
	
	public static void main (String[] arg) {
		DesktopMini2DxConfig config = new DesktopMini2DxConfig(JTetris.GAME_IDENTIFIER);
		config.width = 300;
		config.height = 400;
		config.vSyncEnabled = true;
		new DesktopMini2DxGame(new JTetris(), config);
		
		
	}
}
