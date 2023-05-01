
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.gsimken.bgameslibrary.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

import net.gsimken.bgameslibrary.world.inventory.LoginMenu;
import net.gsimken.bgameslibrary.world.inventory.BgamesDisplayAttributesMenu;
import net.gsimken.bgameslibrary.client.gui.LoginScreen;
import net.gsimken.bgameslibrary.client.gui.BgamesDisplayAttributesScreen;
import net.gsimken.bgameslibrary.BgameslibraryMod;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;

public class BgameslibraryModMenus {
	public static MenuType<BgamesDisplayAttributesMenu> BGAMES_DISPLAY_ATTRIBUTES;
	public static MenuType<LoginMenu> LOGIN;

	public static void load() {
		BGAMES_DISPLAY_ATTRIBUTES = Registry.register(Registry.MENU, new ResourceLocation(BgameslibraryMod.MODID, "bgames_display_attributes"), new ExtendedScreenHandlerType<>(BgamesDisplayAttributesMenu::new));
		BgamesDisplayAttributesScreen.screenInit();
		LOGIN = Registry.register(Registry.MENU, new ResourceLocation(BgameslibraryMod.MODID, "login"), new ExtendedScreenHandlerType<>(LoginMenu::new));
		LoginScreen.screenInit();
	}
}
