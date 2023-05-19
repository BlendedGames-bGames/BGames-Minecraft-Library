package net.gsimken.bgameslibrary.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.minecraft.server.network.ServerPlayerEntity;

public class ModCopyOnPlayerDeathEvent implements  ServerPlayerEvents.CopyFrom{

        @Override
        public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
            IBGamesDataSaver original = ((IBGamesDataSaver) oldPlayer);
            IBGamesDataSaver player = ((IBGamesDataSaver) newPlayer);
            BGamesPlayerData.copyFrom(player,original);
        }



}
