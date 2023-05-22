package net.gsimken.bgameslibrary.mixin;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(InventoryScreen.class)
public class BGamesLibraryMixinInventoryScreen extends Screen {
    protected BGamesLibraryMixinInventoryScreen(Text text_1) {
        super(text_1);
    }

    private TexturedButtonWidget bGamesButton;
    private static final Identifier BGAMES_BUTTON_LOCATION = new Identifier("bgameslibrary:textures/screens/bgames8bitlogo20x18.png");

    @Inject(method = "init", at = @At("TAIL"))
    private void bGamesButtonInit(CallbackInfo callbackinfo) {

        if (!this.client.interactionManager.hasCreativeInventory()) {

            int posX = this.width / 2;
            int posY = this.height / 2;
            bGamesButton = new TexturedButtonWidget(posX + 64, posY + -101, 20, 18, 0, 0, 19, BGAMES_BUTTON_LOCATION,
                    e -> {
                        ClientPlayNetworking.send(BGamesLibraryModMessages.OPEN_DISPLAY_ATTRIBUTE, PacketByteBufs.create());
                    });

            this.addDrawableChild(bGamesButton);
        }
    }
}

