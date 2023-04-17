
package net.gsimken.bgameslibrary.client.screens;

import net.gsimken.bgameslibrary.BgamesLibraryMod;
import net.gsimken.bgameslibrary.network.DisplayAttributesButtonMessage;
import net.minecraft.client.gui.components.ImageButton;
import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class DisplayAttributeButtonInventoryOverlay {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(ScreenEvent.Init.Post event) {
		if (event.getScreen() instanceof InventoryScreen) {
			int w = event.getScreen().width;
			int h = event.getScreen().height;
			int posX = w / 2;
			int posY = h / 2;
			Level world = null;

			Player entity = Minecraft.getInstance().player;
			if (entity != null) {
				world = entity.level;

				ImageButton bgames_logo= new ImageButton(posX + 64, posY + -74, 16, 16, 0, 0, 16, new ResourceLocation("bgames_library:textures/screens/b_games_logo_16x16_v2.png"), 32, 64,
						e -> {
							int x = (int)entity.getX();
							int y = (int)entity.getY();
							int z = (int)entity.getZ();
							BgamesLibraryMod.PACKET_HANDLER.sendToServer(new DisplayAttributesButtonMessage(0, x, y, z));
							DisplayAttributesButtonMessage.handleButtonAction(entity, 0, x, y, z);
						});
				event.addListener(bgames_logo);
			}



		}
	}
}
