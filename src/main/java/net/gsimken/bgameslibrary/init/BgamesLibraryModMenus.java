
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.gsimken.bgameslibrary.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.gsimken.bgameslibrary.world.inventory.LoginMenu;
import net.gsimken.bgameslibrary.world.inventory.DisplayAttributesMenu;
import net.gsimken.bgameslibrary.world.inventory.BGamesDisplayAttributesMenu;
import net.gsimken.bgameslibrary.BgamesLibraryMod;

public class BgamesLibraryModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, BgamesLibraryMod.MODID);
	public static final RegistryObject<MenuType<DisplayAttributesMenu>> DISPLAY_ATTRIBUTES = REGISTRY.register("display_attributes", () -> IForgeMenuType.create(DisplayAttributesMenu::new));
	public static final RegistryObject<MenuType<LoginMenu>> LOGIN = REGISTRY.register("login", () -> IForgeMenuType.create(LoginMenu::new));
	public static final RegistryObject<MenuType<BGamesDisplayAttributesMenu>> B_GAMES_DISPLAY_ATTRIBUTES = REGISTRY.register("b_games_display_attributes", () -> IForgeMenuType.create(BGamesDisplayAttributesMenu::new));
}
