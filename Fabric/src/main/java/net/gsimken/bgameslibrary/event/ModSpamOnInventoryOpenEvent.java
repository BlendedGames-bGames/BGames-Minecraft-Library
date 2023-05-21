package net.gsimken.bgameslibrary.event;

import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ModSpamOnInventoryOpenEvent implements ScreenEvents.AfterInit{
    @Override
    public void afterInit(MinecraftClient client, Screen screen, int scaledWidth, int scaledHeight) {
        if(screen instanceof InventoryScreen){
            IBGamesDataSaver playerDataHandler = (IBGamesDataSaver) client.player;
            if (!BGamesPlayerData.isLoggedIn(playerDataHandler)){
                client.player.sendMessage(Text.translatable(  "login.bgameslibrary.not_logged").fillStyle(Style.EMPTY.withColor(Formatting.RED)));
            }
        }
    }
}
