
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.gsimken.bgameslibrary.init;

import net.minecraft.client.gui.screens.MenuScreens;

import net.gsimken.bgameslibrary.client.gui.LoginScreen;

public class BgameslibraryModScreens {
	public static void load() {
		MenuScreens.register(BgameslibraryModMenus.LOGIN, LoginScreen::new);
	}
}
