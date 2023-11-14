package net.gsimken.bgameslibrary.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.gsimken.bgameslibrary.bgames.ClientBGamesPlayerData;
import net.gsimken.bgameslibrary.client.menus.DisplayAttributesMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class DisplayAttributesScreen extends AbstractContainerScreen<DisplayAttributesMenu> {
	// modal display attributes
	// uses Display Attributes menu & Display Attributes Trigger
	private final static HashMap<String, Object> guistate = DisplayAttributesMenu.guistate;
	private final Player player;

	public DisplayAttributesScreen(DisplayAttributesMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.player = container.player;
		this.imageWidth = 420;
		this.imageHeight = 75;
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		ArrayList<ResourceLocation> dimensionIcons =new ArrayList<>();
		dimensionIcons.add(new ResourceLocation("bgameslibrary:textures/screens/dimension_icon/affective_button_20x18.png"));
		dimensionIcons.add(new ResourceLocation("bgameslibrary:textures/screens/dimension_icon/cognitive_button_20x18.png"));
		dimensionIcons.add(new ResourceLocation("bgameslibrary:textures/screens/dimension_icon/social_button_20x18.png"));
		dimensionIcons.add(new ResourceLocation("bgameslibrary:textures/screens/dimension_icon/linguistic_button_20x18.png"));
		dimensionIcons.add(new ResourceLocation("bgameslibrary:textures/screens/dimension_icon/physical_button_20x18.png"));
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.setShaderTexture(0, new ResourceLocation("bgameslibrary:textures/screens/generic_background.png"));
		this.blit(ms, this.leftPos , this.topPos , 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		int spacing = (this.imageWidth/(dimensionIcons.size()+1));
		int position;
		for (int i=0;i<dimensionIcons.size();i++){
			position = this.leftPos + spacing*(i+1);
			RenderSystem.setShaderTexture(0, dimensionIcons.get(i));
			this.blit(ms, position-15 , this.topPos+15 , 0, 0,30, 27, 30, 55);
		}
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
		ArrayList<Component> translationKeys = new ArrayList<>();
		ArrayList<Component> points = new ArrayList<>();
		translationKeys.add(Component.translatable("gui.bgameslibrary.display_attributes.label_affective"));
		translationKeys.add(Component.translatable("gui.bgameslibrary.display_attributes.label_cognitive"));
		translationKeys.add(Component.translatable("gui.bgameslibrary.display_attributes.label_social"));
		translationKeys.add(Component.translatable("gui.bgameslibrary.display_attributes.label_linguistic"));
		translationKeys.add(Component.translatable("gui.bgameslibrary.display_attributes.label_physical"));
		points.add(Component.literal(convertPoints(ClientBGamesPlayerData.getPlayerAffectivePoints())));
		points.add(Component.literal(convertPoints(ClientBGamesPlayerData.getPlayerSocialPoints())));
		points.add(Component.literal(convertPoints(ClientBGamesPlayerData.getPlayerPhysicalPoints())));
		points.add(Component.literal(convertPoints(ClientBGamesPlayerData.getPlayerLinguisticPoints())));
		points.add(Component.literal(convertPoints(ClientBGamesPlayerData.getPlayerCognitivePoints())));
		Component title =Component.translatable("gui.bgameslibrary.display_attributes.label_bgames_points");
		this.font.draw(poseStack, title , this.imageWidth/2-this.font.width(title)/2, 5, -12829636);
		int spacing = (this.imageWidth/(translationKeys.size()+1));
		int position;
		Component pointText;
		for (int i=0;i<translationKeys.size();i++){
			position = spacing*(i+1);
			pointText = Component.translatable("gui.bgameslibrary.display_attributes.points",points.get(i));
			this.font.draw(poseStack, translationKeys.get(i) , position -this.font.width(translationKeys.get(i))/2,45, -12829636);
			this.font.draw(poseStack,pointText,position -this.font.width(pointText)/2,55, -12829636);

		}

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
