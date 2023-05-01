
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.gsimken.bgameslibrary.init;

import net.minecraft.client.gui.screens.MenuScreens;

import net.gsimken.bgameslibrary.client.gui.LoginScreen;
import net.gsimken.bgameslibrary.client.gui.BgamesDisplayAttributesScreen;

public class BgameslibraryModScreens {
	public static void load() {
		MenuScreens.register(BgameslibraryModMenus.BGAMES_DISPLAY_ATTRIBUTES, BgamesDisplayAttributesScreen::new);
		MenuScreens.register(BgameslibraryModMenus.LOGIN, LoginScreen::new);
	}
}
