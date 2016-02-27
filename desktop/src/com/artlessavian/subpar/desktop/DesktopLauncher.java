package com.artlessavian.subpar.desktop;

import com.artlessavian.subpar.SubparMain;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 720;
		config.width = 1280;
		config.addIcon("icon.png", Files.FileType.Internal);
		new LwjglApplication(new SubparMain(), config);
	}
}
