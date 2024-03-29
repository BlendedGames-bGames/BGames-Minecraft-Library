
package net.gsimken.bgameslibrary.client.gui;

import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;
import net.gsimken.bgameslibrary.networking.packet.ButtonOpenGuiC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class DisplayAttributeButtonInventoryOverlay {
	//Button on Inventory
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void inventoryEventHandler(ScreenEvent.Init.Post event) {
		if (event.getScreen() instanceof InventoryScreen) {
			ResourceLocation BGAMES_BUTTON_LOCATION=new ResourceLocation("bgameslibrary:textures/screens/bgames8bitlogo20x18.png");
			InventoryScreen inv = (InventoryScreen) event.getScreen();
			int w = inv.width;
			int h = inv.height;
			int posX = w / 2;
			int posY = h / 2;
			RecipeBookComponent recipeBook = inv.getRecipeBookComponent();


			Player entity = inv.getMinecraft().player;
			int buttonPosX=  posX + 64; //recipeBook.updateScreenPosition(posX + 64,20);
			int buttonPosY =posY + -101;
			if (entity != null) {
				ImageButton bGamesLogo= new ImageButton(buttonPosX,buttonPosY , 20, 18, 0, 0, 19,  BGAMES_BUTTON_LOCATION,
						e -> {
							BGamesLibraryModMessages.sendToServer(new ButtonOpenGuiC2SPacket());
						});
				event.addListener(bGamesLogo);
			}



		}
	}
}
