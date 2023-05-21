package net.gsimken.bgameslibrary.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class ModEvents{
    public static void register(){
        ServerPlayerEvents.COPY_FROM.register(new ModCopyOnPlayerDeathEvent());
        ClientPlayConnectionEvents.JOIN.register(new ModClientPlayerLoginEvent());
        ScreenEvents.AFTER_INIT.register(new ModSpamOnInventoryOpenEvent());
    }
}
