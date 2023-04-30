package net.gsimken.bgameslibrary.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.Minecraft;

import net.gsimken.bgameslibrary.world.inventory.BGamesDisplayAttributesMenu;
import net.gsimken.bgameslibrary.procedures.BGamesAttributesSocialValueProcedure;
import net.gsimken.bgameslibrary.procedures.BGamesAttributesPhysicalValueProcedure;
import net.gsimken.bgameslibrary.procedures.BGamesAttributesLinguisticValueProcedure;
import net.gsimken.bgameslibrary.procedures.BGamesAttributesCognitiveValueProcedure;
import net.gsimken.bgameslibrary.procedures.BGamesAttributesAfectiveValueProcedure;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class BGamesDisplayAttributesScreen extends AbstractContainerScreen<BGamesDisplayAttributesMenu> {
	private final static HashMap<String, Object> guistate = BGamesDisplayAttributesMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public BGamesDisplayAttributesScreen(BGamesDisplayAttributesMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
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

		RenderSystem.setShaderTexture(0, new ResourceLocation("bgames_library:textures/screens/container_button_v4.png"));
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

				BGamesAttributesAfectiveValueProcedure.execute(entity), -132, 8, -12829636);
		this.font.draw(poseStack,

				BGamesAttributesSocialValueProcedure.execute(entity), -11, 8, -12829636);
		this.font.draw(poseStack,

				BGamesAttributesPhysicalValueProcedure.execute(entity), 97, 8, -12829636);
		this.font.draw(poseStack,

				BGamesAttributesLinguisticValueProcedure.execute(entity), 42, 8, -12829636);
		this.font.draw(poseStack,

				BGamesAttributesCognitiveValueProcedure.execute(entity), -71, 8, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgames_library.b_games_display_attributes.label_afective"), -128, -1, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgames_library.b_games_display_attributes.label_cognitive"), -73, -1, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgames_library.b_games_display_attributes.label_social"), -8, -1, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgames_library.b_games_display_attributes.label_linguistic"), 37, -1, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgames_library.b_games_display_attributes.label_physical"), 98, -2, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgames_library.b_games_display_attributes.label_bgames_points"), -27, -43, -12829636);
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
}