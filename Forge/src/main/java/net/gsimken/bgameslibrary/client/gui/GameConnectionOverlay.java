
package net.gsimken.bgameslibrary.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.gsimken.bgameslibrary.bgames.BGamesLibraryTools;
import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;
import net.gsimken.bgameslibrary.networking.packet.ButtonOpenGuiC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class GameConnectionOverlay {
	private static final ResourceLocation  CONNECTED_LOGO = new ResourceLocation ("bgameslibrary:textures/guis/connection/connected.png");
	private static final ResourceLocation  DISCONNECTED_LOGO = new ResourceLocation ("bgameslibrary:textures/guis/connection/disconnected.png");

	private  static  final Component CONNECTED_TEXT = Component.translatable("api.bgameslibrary.status.connected");
	private  static  final Component DISCONNECTED_TEXT = Component.translatable("api.bgameslibrary.status.disconnected");
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void onRenderGameOverlay(RenderGuiOverlayEvent.Pre event) {
			int x = 0;
			int y = 1;
			Minecraft  mc = Minecraft.getInstance();
			if (mc.level != null && mc.player != null) {
				Font textRenderer = mc.font;
				int width = mc.getWindow().getGuiScaledWidth();
				int iconSize = 11;

				int maxTextWidht = textRenderer.width(CONNECTED_TEXT)>textRenderer.width(DISCONNECTED_TEXT) ? textRenderer.width(CONNECTED_TEXT) : textRenderer.width(DISCONNECTED_TEXT);


				x = width-iconSize-maxTextWidht-1;
				int posY = 10; // 10 pixels desde el borde superior

				// Dibujar el overlay
				PoseStack poseStack = new PoseStack();
				RenderSystem.setShaderColor(1, 1, 1, 1);
				RenderSystem.enableBlend();
				RenderSystem.defaultBlendFunc();
				if(BGamesLibraryTools.isPlayerLogged(mc.player)) {
					RenderSystem.setShaderTexture(0, CONNECTED_LOGO);
					GuiComponent.blit(poseStack, x, y, 0, 0, iconSize, iconSize, iconSize, iconSize);
					textRenderer.draw(poseStack, CONNECTED_TEXT, x + iconSize + 1, y + 2, 0xFFFFFF);
				}
				else{
					RenderSystem.setShaderTexture(0, DISCONNECTED_LOGO);
					GuiComponent.blit(poseStack, x, y, 0, 0, iconSize, iconSize, iconSize, iconSize);
					textRenderer.draw(poseStack, DISCONNECTED_TEXT, x + iconSize + 1, y + 2, 0xFFFFFF);
				}

		}
	}
}
