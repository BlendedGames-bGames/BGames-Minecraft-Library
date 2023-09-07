package net.gsimken.bgameslibrary.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

public class BGamesLibraryModEvents {
    public static void registerServer(){
        ServerPlayerEvents.COPY_FROM.register(new ModCopyOnPlayerDeathEvent());

    }
    public static void registerClient(){
        ClientPlayConnectionEvents.JOIN.register(new ModClientPlayerLoginEvent());
    }
}
