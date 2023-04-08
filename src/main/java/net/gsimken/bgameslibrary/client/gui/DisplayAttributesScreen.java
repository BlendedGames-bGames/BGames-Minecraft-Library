package net.gsimken.bgameslibrary.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.Minecraft;

import net.gsimken.bgameslibrary.world.inventory.DisplayAttributesMenu;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class DisplayAttributesScreen extends AbstractContainerScreen<DisplayAttributesMenu> {
	private final static HashMap<String, Object> guistate = DisplayAttributesMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_logo_social_v2;
	ImageButton imagebutton_logo_fisico;
	ImageButton imagebutton_logo_afectivo;
	ImageButton imagebutton_logo_cognitivo;
	ImageButton imagebutton_logo_linguitico;

	public DisplayAttributesScreen(DisplayAttributesMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 260;
		this.imageHeight = 135;
	}

	private static final ResourceLocation texture = new ResourceLocation("bgames_library:textures/screens/display_attributes.png");

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
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
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
		this.font.draw(poseStack, Component.translatable("gui.bgames_library.display_attributes.label_social"), 35, 67, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgames_library.display_attributes.label_fisico"), 110, 68, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgames_library.display_attributes.label_consume_points"), 90, 11, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgames_library.display_attributes.label_afective"), 189, 67, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgames_library.display_attributes.label_cognitive"), 66, 115, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgames_library.display_attributes.label_linguistic"), 145, 115, -12829636);
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
		imagebutton_logo_social_v2 = new ImageButton(this.leftPos + 34, this.topPos + 35, 32, 32, 0, 0, 32, new ResourceLocation("bgames_library:textures/screens/atlas/imagebutton_logo_social_v2.png"), 32, 64, e -> {
		});
		guistate.put("button:imagebutton_logo_social_v2", imagebutton_logo_social_v2);
		this.addRenderableWidget(imagebutton_logo_social_v2);
		imagebutton_logo_fisico = new ImageButton(this.leftPos + 114, this.topPos + 35, 32, 32, 0, 0, 32, new ResourceLocation("bgames_library:textures/screens/atlas/imagebutton_logo_fisico.png"), 32, 64, e -> {
		});
		guistate.put("button:imagebutton_logo_fisico", imagebutton_logo_fisico);
		this.addRenderableWidget(imagebutton_logo_fisico);
		imagebutton_logo_afectivo = new ImageButton(this.leftPos + 194, this.topPos + 35, 32, 32, 0, 0, 32, new ResourceLocation("bgames_library:textures/screens/atlas/imagebutton_logo_afectivo.png"), 32, 64, e -> {
		});
		guistate.put("button:imagebutton_logo_afectivo", imagebutton_logo_afectivo);
		this.addRenderableWidget(imagebutton_logo_afectivo);
		imagebutton_logo_cognitivo = new ImageButton(this.leftPos + 74, this.topPos + 83, 32, 32, 0, 0, 32, new ResourceLocation("bgames_library:textures/screens/atlas/imagebutton_logo_cognitivo.png"), 32, 64, e -> {
		});
		guistate.put("button:imagebutton_logo_cognitivo", imagebutton_logo_cognitivo);
		this.addRenderableWidget(imagebutton_logo_cognitivo);
		imagebutton_logo_linguitico = new ImageButton(this.leftPos + 154, this.topPos + 83, 32, 32, 0, 0, 32, new ResourceLocation("bgames_library:textures/screens/atlas/imagebutton_logo_linguitico.png"), 32, 64, e -> {
		});
		guistate.put("button:imagebutton_logo_linguitico", imagebutton_logo_linguitico);
		this.addRenderableWidget(imagebutton_logo_linguitico);
	}
}
