package net.gsimken.bgameslibrary.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.gsimken.bgameslibrary.utils.PlayerUtils;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class OpenGuiC2SPacket {
    public static void receive(MinecraftServer server,
                               ServerPlayerEntity player,
                               ServerPlayNetworkHandler handler,
                               PacketByteBuf buf,
                               PacketSender responseSender) {
        //serverside
        IBGamesDataSaver playerDataHandler= (IBGamesDataSaver) player;
        BGamesPlayerData.attributeReset(playerDataHandler);
        BGamesPlayerData.attributeRefresh(playerDataHandler);
        BGamesPlayerData.syncData(player);

    }
}
