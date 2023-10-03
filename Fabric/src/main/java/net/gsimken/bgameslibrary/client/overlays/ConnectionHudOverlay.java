package net.gsimken.bgameslibrary.client.overlays;


import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.gsimken.bgameslibrary.bgames.BGamesLibraryTools;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ConnectionHudOverlay implements HudRenderCallback {
    private static final Identifier CONNECTED_LOGO = new Identifier("bgameslibrary:textures/guis/connection/connected.png");
    private static final Identifier DISCONNECTED_LOGO = new Identifier("bgameslibrary:textures/guis/connection/disconnected.png");

    private  static  final Text CONNECTED_TEXT = Text.translatable("api.bgameslibrary.status.connected");
    private  static  final Text DISCONNECTED_TEXT = Text.translatable("api.bgameslibrary.status.disconnected");
    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        int x = 0;
        int y = 1;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            TextRenderer textRenderer = client.textRenderer;
            int width = client.getWindow().getScaledWidth();
            int iconSize = 11;

            int maxTextWidht = textRenderer.getWidth(CONNECTED_TEXT)>textRenderer.getWidth(DISCONNECTED_TEXT) ? textRenderer.getWidth(CONNECTED_TEXT) : textRenderer.getWidth(DISCONNECTED_TEXT);
            x = width-iconSize-maxTextWidht-1;


            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            if(BGamesLibraryTools.isPlayerLogged(client.player)){
                RenderSystem.setShaderTexture(0,CONNECTED_LOGO);
                DrawableHelper.drawTexture(matrixStack,x,y,0,0,iconSize,iconSize,iconSize,iconSize);
                textRenderer.draw(matrixStack,CONNECTED_TEXT,x+iconSize+1,y+2,0xFFFFFF);
            }else{
                RenderSystem.setShaderTexture(0,DISCONNECTED_LOGO);
                DrawableHelper.drawTexture(matrixStack,x,y,0,0,iconSize,iconSize,iconSize,iconSize);
                textRenderer.draw(matrixStack,DISCONNECTED_TEXT,x+iconSize+1,y+2,0xFFFFFF);

            }
        }

    }
}