package net.gsimken.bgameslibrary.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.gsimken.bgameslibrary.client.menus.LoginMenu;
import net.gsimken.bgameslibrary.networking.ModMessages;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class LoginScreen extends HandledScreen<LoginMenu> {
    private final static HashMap<String, Object> guistate = LoginMenu.guistate;

    private final PlayerEntity player;

    TextFieldWidget Password;
    TextFieldWidget Email;
    ButtonWidget button_login;

    public LoginScreen(LoginMenu container, PlayerInventory inventory, Text text) {
        super(container, inventory, text);
        this.player = container.player;
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
    }

    private static final Identifier texture = new Identifier("bgameslibrary:textures/screens/login.png");

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        this.drawMouseoverTooltip(ms, mouseX, mouseY);
        Password.render(ms, mouseX, mouseY, partialTicks);
        Email.render(ms, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawBackground(MatrixStack ms, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, texture);
        this.drawTexture(ms, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight, this.backgroundWidth, this.backgroundHeight);

        RenderSystem.setShaderTexture(0, new Identifier("bgameslibrary:textures/screens/bgameslogo.png"));
        this.drawTexture(ms, this.x + 129, this.y + 3, 0, 0, 50, 35, 50, 35);

        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            //cierra con escape el menu
            this.client.player.closeHandledScreen();

            return true;
        }
        if (Password.isFocused()) {
            return Password.keyPressed(key, b, c);
        }

        if (Email.isFocused())
            return Email.keyPressed(key, b, c);
        return super.keyPressed(key, b, c);
    }

    @Override
    public void handledScreenTick() {
        super.handledScreenTick();
        Password.tick();
        Email.tick();

    }

    @Override
    protected void drawForeground(MatrixStack poseStack, int mouseX, int mouseY) {
        this.textRenderer.draw(poseStack, Text.translatable("gui.bgameslibrary.login.label_password"), 26, 78, -12829636);
        this.textRenderer.draw(poseStack, Text.translatable("gui.bgameslibrary.login.label_email"), 27, 35, -12829636);
        this.textRenderer.draw(poseStack, Text.translatable("gui.bgameslibrary.login.label_blended_games"), 52, 16, -12829636);
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
        Password = new TextFieldWidget(this.textRenderer, this.x + 26, this.y + 91, 120, 20, Text.translatable("gui.bgameslibrary.login.Password"));
        Password.setMaxLength(20);
        guistate.put("text:Password", Password);
        this.addSelectableChild(this.Password);



        Email = new TextFieldWidget(this.textRenderer, this.x + 26, this.y + 51, 120, 20, Text.translatable("gui.bgameslibrary.login.Email"));
        Email.setMaxLength(100);
        guistate.put("text:Email", Email);
        this.addSelectableChild(this.Email);
        button_login = new ButtonWidget(this.x + 58, this.y + 130, 51, 20, Text.translatable("gui.bgameslibrary.login.button_login"), e -> {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeString(LoginMenu.getEmailFromBox());
            buf.writeString(LoginMenu.getPasswordFromBox());
            ClientPlayNetworking.send(ModMessages.BGAMES_LOGIN, buf);

        });
        guistate.put("button:button_login", button_login);
        this.addDrawableChild(button_login);
    }

}
