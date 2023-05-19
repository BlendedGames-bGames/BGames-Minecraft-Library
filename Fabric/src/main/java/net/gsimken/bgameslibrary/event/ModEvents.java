package net.gsimken.bgameslibrary.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class ModEvents{
    public static void register(){
        ServerPlayerEvents.COPY_FROM.register(new ModCopyOnPlayerDeathEvent());
        ServerPlayConnectionEvents.JOIN.register(new ModPlayerLoginEvent());
    }
}
