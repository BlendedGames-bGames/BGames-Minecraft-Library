package net.gsimken.bgameslibrary.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerDataProvider;
import net.gsimken.bgameslibrary.client.ClientBGamesPlayerData;
import net.gsimken.bgameslibrary.client.menus.DisplayAttributesMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.HashMap;

public class DisplayAttributesScreen extends AbstractContainerScreen<DisplayAttributesMenu> {
	private final static HashMap<String, Object> guistate = DisplayAttributesMenu.guistate;
	private final Player player;

	public DisplayAttributesScreen(DisplayAttributesMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.player = container.player;
		this.imageWidth = 0;
		this.imageHeight = 0;
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.setShaderTexture(0, new ResourceLocation("bgameslibrary:textures/screens/container_button_v4.png"));
		this.blit(ms, this.leftPos + -135, this.topPos + -46, 0, 0, 283, 68, 283, 68);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {


		this.font.draw(poseStack,

				Component.translatable("gui.bgameslibrary.display_attributes.points",Component.literal(convertPoints(ClientBGamesPlayerData.getPlayerAffectivePoints()))), -132, 8, -12829636);
		this.font.draw(poseStack,

				Component.translatable("gui.bgameslibrary.display_attributes.points",Component.literal(convertPoints(ClientBGamesPlayerData.getPlayerSocialPoints()))), -11, 8, -12829636);
		this.font.draw(poseStack,

				Component.translatable("gui.bgameslibrary.display_attributes.points",Component.literal(convertPoints(ClientBGamesPlayerData.getPlayerPhysicalPoints()))), 97, 8, -12829636);
		this.font.draw(poseStack,

				Component.translatable("gui.bgameslibrary.display_attributes.points",Component.literal(convertPoints(ClientBGamesPlayerData.getPlayerLinguisticPoints()))), 42, 8, -12829636);
		this.font.draw(poseStack,

				Component.translatable("gui.bgameslibrary.display_attributes.points",Component.literal(convertPoints(ClientBGamesPlayerData.getPlayerCognitivePoints()))), -71, 8, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.display_attributes.label_affective"), -128, -1, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.display_attributes.label_cognitive"), -73, -1, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.display_attributes.label_social"), -8, -1, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.display_attributes.label_linguistic"), 37, -1, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.display_attributes.label_physical"), 98, -2, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.display_attributes.label_bgames_points"), -27, -43, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
	}
	public String convertPoints(int points){
		if (points == -1) {
			points = 0;
		}
		return String.valueOf(points);
	}
}
