
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.gsimken.bgameslibrary.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.gsimken.bgameslibrary.network.OpenDisplayAttributesMessage;
import net.gsimken.bgameslibrary.BgamesLibraryMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class BgamesLibraryModKeyMappings {
	public static final KeyMapping OPEN_DISPLAY_ATTRIBUTES = new KeyMapping("key.bgames_library.open_display_attributes", GLFW.GLFW_KEY_L, "key.categories.bgames") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				BgamesLibraryMod.PACKET_HANDLER.sendToServer(new OpenDisplayAttributesMessage(0, 0));
				OpenDisplayAttributesMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(OPEN_DISPLAY_ATTRIBUTES);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				OPEN_DISPLAY_ATTRIBUTES.consumeClick();
			}
		}
	}
}
