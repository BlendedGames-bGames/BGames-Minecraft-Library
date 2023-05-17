
package net.gsimken.bgameslibrary.client.gui;

import net.gsimken.bgameslibrary.networking.ModMessages;
import net.gsimken.bgameslibrary.networking.packet.ButtonOpenGuiC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class DisplayAttributeButtonInventoryOverlay {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void inventoryEventHandler(ScreenEvent.Init.Post event) {
		if (event.getScreen() instanceof InventoryScreen) {
			ResourceLocation BGAMES_BUTTON_LOCATION=new ResourceLocation("bgameslibrary:textures/screens/bgames8bitlogo20x18.png");
			int w = event.getScreen().width;
			int h = event.getScreen().height;
			int posX = w / 2;
			int posY = h / 2;
			Level world = null;

			Player entity = Minecraft.getInstance().player;
			if (entity != null) {
				ImageButton bgames_logo= new ImageButton(posX + 64, posY + -101, 20, 18, 0, 0, 19,  BGAMES_BUTTON_LOCATION,
						e -> {
							ModMessages.sendToServer(new ButtonOpenGuiC2SPacket());
						});
				event.addListener(bgames_logo);
			}



		}
	}
}
