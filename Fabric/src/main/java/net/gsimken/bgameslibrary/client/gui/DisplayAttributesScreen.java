package net.gsimken.bgameslibrary.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.client.menus.DisplayAttributesMenu;
import java.util.HashMap;

import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Map;

public class DisplayAttributesScreen extends HandledScreen<DisplayAttributesMenu> {
	private final static HashMap<String, Object> guistate = DisplayAttributesMenu.guistate;
	private final PlayerEntity player;

	public DisplayAttributesScreen(DisplayAttributesMenu container, PlayerInventory inventory, Text text) {
		super(container, inventory, text);
		this.player = container.player;
		this.backgroundWidth = 0;
		this.backgroundHeight = 0;


	}

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.drawMouseoverTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawBackground(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.setShaderTexture(0, new Identifier("bgameslibrary:textures/screens/container_button_v4.png"));
		this.drawTexture(ms, this.x + -135, this.y + -46, 0, 0, 283, 68, 283, 68);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.client.player.closeHandledScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void handledScreenTick() {
		super.handledScreenTick();
	}

	@Override
	protected void drawForeground(MatrixStack poseStack, int mouseX, int mouseY) {


		IBGamesDataSaver playerData =(IBGamesDataSaver)player;
		this.textRenderer.draw(poseStack,
				Text.translatable("gui.bgameslibrary.display_attributes.points",
						Text.literal(convertPoints(BGamesPlayerData.getAffectivePoints(playerData)))),
				-132, 8, -12829636);

		this.textRenderer.draw(poseStack,
				Text.translatable("gui.bgameslibrary.display_attributes.points",
						Text.literal(convertPoints(BGamesPlayerData.getSocialPoints(playerData)))),
				-11, 8, -12829636);
		this.textRenderer.draw(poseStack,

				Text.translatable("gui.bgameslibrary.display_attributes.points",
						Text.literal(convertPoints(BGamesPlayerData.getPhysicalPoints(playerData)))),
				97, 8, -12829636);
		this.textRenderer.draw(poseStack,

				Text.translatable("gui.bgameslibrary.display_attributes.points",
						Text.literal(convertPoints(BGamesPlayerData.getLinguisticPoints(playerData)))),
				42, 8, -12829636);
		this.textRenderer.draw(poseStack,

				Text.translatable("gui.bgameslibrary.display_attributes.points",
						Text.literal(convertPoints(BGamesPlayerData.getCognitivePoints(playerData)))),
				-71, 8, -12829636);
		this.textRenderer.draw(poseStack, Text.translatable("gui.bgameslibrary.display_attributes.label_affective"), -128, -1, -12829636);
		this.textRenderer.draw(poseStack, Text.translatable("gui.bgameslibrary.display_attributes.label_cognitive"), -73, -1, -12829636);
		this.textRenderer.draw(poseStack, Text.translatable("gui.bgameslibrary.display_attributes.label_social"), -8, -1, -12829636);
		this.textRenderer.draw(poseStack, Text.translatable("gui.bgameslibrary.display_attributes.label_linguistic"), 37, -1, -12829636);
		this.textRenderer.draw(poseStack, Text.translatable("gui.bgameslibrary.display_attributes.label_physical"), 98, -2, -12829636);
		this.textRenderer.draw(poseStack, Text.translatable("gui.bgameslibrary.display_attributes.label_bgames_points"), -27, -43, -12829636);

	}

	@Override
	public void close() {
		super.close();
		MinecraftClient.getInstance().keyboard.setRepeatEvents(false);
	}

	@Override
	public void init() {
		super.init();
		this.client.keyboard.setRepeatEvents(true);


	}

	public String convertPoints(int points){
		if (points == -1) {
			points = 0;
		}
		return String.valueOf(points);
	}
}