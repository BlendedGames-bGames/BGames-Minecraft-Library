package net.gsimken.bgameslibrary.mixins;

import net.gsimken.bgameslibrary.procedures.OpenDisplayAttributeGuiProcedure;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(InventoryScreen.class)
public class MixinInventoryScreen extends Screen {
    protected MixinInventoryScreen(Component text_1) {
        super(text_1);
    }

    private ImageButton bGamesButton;
    private static final ResourceLocation BGAMES_BUTTON_LOCATION = new ResourceLocation("bgameslibrary:textures/screens/bgames8bitlogo20x18.png");

    @Inject(method = "init", at = @At("TAIL"))
    private void bGamesButtonInit(CallbackInfo callbackinfo) {

        if (!this.minecraft.gameMode.hasInfiniteItems()) {

            int posX = this.width / 2;
            int posY = this.height / 2;
            bGamesButton = new ImageButton(posX + 64, posY + -101, 20, 18, 0, 0, 19, BGAMES_BUTTON_LOCATION,
                    e -> {

                        Level world = this.minecraft.level;
                        Player entity = this.minecraft.player;
                        double x = entity.getX();
                        double y = entity.getY();
                        double z = entity.getZ();

                        OpenDisplayAttributeGuiProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("world", world).put("x", x).put("y", y).put("z", z).put("entity", entity).build());
                    });

            this.addRenderableWidget(bGamesButton);
        }
    }
}

