
package net.gsimken.bgameslibrary.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import net.gsimken.bgameslibrary.world.inventory.LoginMenu;
import net.gsimken.bgameslibrary.network.LoginButtonMessage;
import net.gsimken.bgameslibrary.BgameslibraryMod;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class LoginScreen extends AbstractContainerScreen<LoginMenu> {
	private final static HashMap<String, Object> guistate = LoginMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox Password;
	EditBox Email;
	Button button_login;

	public LoginScreen(LoginMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("bgameslibrary:textures/screens/login.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		Password.render(ms, mouseX, mouseY, partialTicks);
		Email.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		RenderSystem.setShaderTexture(0, new ResourceLocation("bgameslibrary:textures/screens/logo_bgames_gui.png"));
		this.blit(ms, this.leftPos + 128, this.topPos + 2, 0, 0, 50, 35, 50, 35);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (Password.isFocused())
			return Password.keyPressed(key, b, c);
		if (Email.isFocused())
			return Email.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		Password.tick();
		Email.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.login.label_password"), 30, 78, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.login.label_email"), 28, 35, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.bgameslibrary.login.label_blended_games"), 48, 16, -12829636);
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
		Password = new EditBox(this.font, this.leftPos + 28, this.topPos + 92, 120, 20, Component.translatable("gui.bgameslibrary.login.Password"));
		Password.setMaxLength(32767);
		guistate.put("text:Password", Password);
		this.addWidget(this.Password);
		Email = new EditBox(this.font, this.leftPos + 27, this.topPos + 48, 120, 20, Component.translatable("gui.bgameslibrary.login.Email"));
		Email.setMaxLength(32767);
		guistate.put("text:Email", Email);
		this.addWidget(this.Email);
		button_login = new Button(this.leftPos + 56, this.topPos + 130, 51, 20, Component.translatable("gui.bgameslibrary.login.button_login"), e -> {
			if (true) {
				ClientPlayNetworking.send(new ResourceLocation("bgameslibrary:login_button_" + 0), new LoginButtonMessage(0, x, y, z));
			}
		});
		guistate.put("button:button_login", button_login);
		this.addRenderableWidget(button_login);
	}

	public static void screenInit() {
		ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(BgameslibraryMod.MODID, "login_button_0"), LoginButtonMessage::apply);
	}
}
