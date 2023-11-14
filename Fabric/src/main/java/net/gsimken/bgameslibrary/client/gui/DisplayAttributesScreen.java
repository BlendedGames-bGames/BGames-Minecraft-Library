package net.gsimken.bgameslibrary.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.client.menus.DisplayAttributesMenu;

import java.util.ArrayList;
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
		this.backgroundWidth = 420;
		this.backgroundHeight = 75;


	}

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.drawMouseoverTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawBackground(MatrixStack ms, float partialTicks, int gx, int gy) {
		ArrayList<Identifier> dimensionIcons =new ArrayList<>();
		dimensionIcons.add(new Identifier("bgameslibrary:textures/screens/dimension_icon/affective_button_20x18.png"));
		dimensionIcons.add(new Identifier("bgameslibrary:textures/screens/dimension_icon/cognitive_button_20x18.png"));
		dimensionIcons.add(new Identifier("bgameslibrary:textures/screens/dimension_icon/social_button_20x18.png"));
		dimensionIcons.add(new Identifier("bgameslibrary:textures/screens/dimension_icon/linguistic_button_20x18.png"));
		dimensionIcons.add(new Identifier("bgameslibrary:textures/screens/dimension_icon/physical_button_20x18.png"));
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, new Identifier("bgameslibrary:textures/screens/generic_background.png"));
		this.drawTexture(ms, this.x , this.y , 0, 0, this.backgroundWidth, this.backgroundHeight, this.backgroundWidth, this.backgroundHeight);
		//RenderSystem.setShaderTexture(0, new Identifier("bgameslibrary:textures/screens/bgameslogo.png"));

		int spacing = (this.backgroundWidth/(dimensionIcons.size()+1));
		int position;
		for (int i=0;i<dimensionIcons.size();i++){
			position = this.x + spacing*(i+1);
			RenderSystem.setShaderTexture(0, dimensionIcons.get(i));
			this.drawTexture(ms, position-15 , this.y+15 , 0, 0,30, 27, 30, 55);
		}
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
		ArrayList<Text> translationKeys = new ArrayList<>();
		ArrayList<Text> points = new ArrayList<>();
		translationKeys.add(Text.translatable("gui.bgameslibrary.display_attributes.label_affective"));
		translationKeys.add(Text.translatable("gui.bgameslibrary.display_attributes.label_cognitive"));
		translationKeys.add(Text.translatable("gui.bgameslibrary.display_attributes.label_social"));
		translationKeys.add(Text.translatable("gui.bgameslibrary.display_attributes.label_linguistic"));
		translationKeys.add(Text.translatable("gui.bgameslibrary.display_attributes.label_physical"));
		points.add(Text.literal(convertPoints(BGamesPlayerData.getAffectivePoints(playerData))));
		points.add(Text.literal(convertPoints(BGamesPlayerData.getSocialPoints(playerData))));
		points.add(Text.literal(convertPoints(BGamesPlayerData.getPhysicalPoints(playerData))));
		points.add(Text.literal(convertPoints(BGamesPlayerData.getLinguisticPoints(playerData))));
		points.add(Text.literal(convertPoints(BGamesPlayerData.getCognitivePoints(playerData))));

		Text title =Text.translatable("gui.bgameslibrary.display_attributes.label_bgames_points");
		this.textRenderer.draw(poseStack, title , this.backgroundWidth/2-this.textRenderer.getWidth(title)/2, 5, -12829636);
		int spacing = (this.backgroundWidth/(translationKeys.size()+1));
		int position;
		Text pointText;
		for (int i=0;i<translationKeys.size();i++){
			position = spacing*(i+1);
			pointText = Text.translatable("gui.bgameslibrary.display_attributes.points",points.get(i));
			this.textRenderer.draw(poseStack, translationKeys.get(i) , position -this.textRenderer.getWidth(translationKeys.get(i))/2,45, -12829636);
			this.textRenderer.draw(poseStack,pointText,position -this.textRenderer.getWidth(pointText)/2,55, -12829636);

		}
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