
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.gsimken.bgameslibrary.init;

import org.lwjgl.glfw.GLFW;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.KeyMapping;

import net.gsimken.bgameslibrary.network.OpenDisplayAttributeMessage;
import net.gsimken.bgameslibrary.BgameslibraryMod;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import com.mojang.blaze3d.platform.InputConstants;

public class BgameslibraryModKeyMappings {
	public static class BgameslibraryModKeyMapping extends KeyMapping {
		private boolean isDownOld;

		public BgameslibraryModKeyMapping(String string, int i, String string2) {
			super(string, InputConstants.Type.KEYSYM, i, string2);
		}

		public int action() {
			if (isDownOld != isDown() && isDown()) {
				isDownOld = isDown();
				return 1;
			} else if (isDownOld != isDown() && !isDown()) {
				isDownOld = isDown();
				return 2;
			}
			isDownOld = isDown();
			return 0;
		}
	}

	public static BgameslibraryModKeyMapping OPEN_DISPLAY_ATTRIBUTE = (BgameslibraryModKeyMapping) KeyBindingHelper
			.registerKeyBinding(new BgameslibraryModKeyMapping("key.bgameslibrary.open_display_attribute", GLFW.GLFW_KEY_L, "key.categories.bgames"));

	public static void serverLoad() {
		ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(BgameslibraryMod.MODID, "open_display_attribute"), OpenDisplayAttributeMessage::apply);
	}

	public static void load() {
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			int OPEN_DISPLAY_ATTRIBUTEaction = OPEN_DISPLAY_ATTRIBUTE.action();
			if (OPEN_DISPLAY_ATTRIBUTEaction == 1) {
				ClientPlayNetworking.send(new ResourceLocation(BgameslibraryMod.MODID, "open_display_attribute"), new OpenDisplayAttributeMessage(true, false));
			} else if (OPEN_DISPLAY_ATTRIBUTEaction == 2) {
				ClientPlayNetworking.send(new ResourceLocation(BgameslibraryMod.MODID, "open_display_attribute"), new OpenDisplayAttributeMessage(false, true));
			}
		});
	}
}
