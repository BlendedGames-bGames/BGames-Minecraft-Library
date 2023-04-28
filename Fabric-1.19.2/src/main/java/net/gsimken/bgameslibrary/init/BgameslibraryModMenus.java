
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.gsimken.bgameslibrary.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

import net.gsimken.bgameslibrary.world.inventory.LoginMenu;
import net.gsimken.bgameslibrary.client.gui.LoginScreen;
import net.gsimken.bgameslibrary.BgameslibraryMod;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;

public class BgameslibraryModMenus {
	public static MenuType<LoginMenu> LOGIN;

	public static void load() {
		LOGIN = Registry.register(Registry.MENU, new ResourceLocation(BgameslibraryMod.MODID, "login"), new ExtendedScreenHandlerType<>(LoginMenu::new));
		LoginScreen.screenInit();
	}
}
