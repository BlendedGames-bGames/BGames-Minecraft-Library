
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.gsimken.bgameslibrary.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.gsimken.bgameslibrary.client.gui.LoginScreen;
import net.gsimken.bgameslibrary.client.gui.DisplayAttributesScreen;
import net.gsimken.bgameslibrary.client.gui.BGamesDisplayAttributesScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BgamesLibraryModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(BgamesLibraryModMenus.DISPLAY_ATTRIBUTES.get(), DisplayAttributesScreen::new);
			MenuScreens.register(BgamesLibraryModMenus.LOGIN.get(), LoginScreen::new);
			MenuScreens.register(BgamesLibraryModMenus.B_GAMES_DISPLAY_ATTRIBUTES.get(), BGamesDisplayAttributesScreen::new);
		});
	}
}
