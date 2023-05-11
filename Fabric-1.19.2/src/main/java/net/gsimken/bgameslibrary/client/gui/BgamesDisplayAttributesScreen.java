

package net.gsimken.bgameslibrary.client.gui;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gsimken.bgameslibrary.BgameslibraryMod;
import net.gsimken.bgameslibrary.network.OpenDisplayAttributeC2S;
import net.gsimken.bgameslibrary.network.LoginButtonMessage;
import net.gsimken.bgameslibrary.procedures.*;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.Minecraft;

import net.gsimken.bgameslibrary.world.inventory.BgamesDisplayAttributesMenu;

import java.util.HashMap;
import java.util.Map;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class BgamesDisplayAttributesScreen extends AbstractContainerScreen<BgamesDisplayAttributesMenu> {
	private final static HashMap<String, Object> guistate = BgamesDisplayAttributesMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public BgamesDisplayAttributesScreen(BgamesDisplayAttributesMenu container, Inventory inventory, Component text) {
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

		Map<String, Object> dependencies=  com.google.common.collect.ImmutableMap.<String, Object>builder().put("entity", entity).build();
		this.font.draw(poseStack,

				GetAfectiveAttributeProcedure.execute(dependencies), -132, 8, -12829636);
		this.font.draw(poseStack,

				GetSocialAttributeProcedure.execute(dependencies), -11, 8, -12829636);
		this.font.draw(poseStack,

				GetPhysicalAttributeProcedure.execute(dependencies), 97, 8, -12829636);
		this.font.draw(poseStack,

				GetLinguisticAttributeProcedure.execute(dependencies), 42, 8, -12829636);
		this.font.draw(poseStack,

				GetCognitiveAttributeProcedure.execute(dependencies), -71, 8, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.bgames_display_attributes.label_afective"), -128, -1, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.bgames_display_attributes.label_cognitive"), -73, -1, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.bgames_display_attributes.label_social"), -8, -1, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.bgames_display_attributes.label_linguistic"), 37, -1, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.bgames_display_attributes.label_physical"), 98, -2, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.bgames_display_attributes.label_bgames_points"), -27, -43, -12829636);

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
		ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation(BgameslibraryMod.MODID, "sync_data"), new LoginButtonMessage(0, x, y, z));


	}

	public static void screenInit() {
		ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(BgameslibraryMod.MODID, "open_display_attribute"), OpenDisplayAttributeC2S::apply);
	}
}
