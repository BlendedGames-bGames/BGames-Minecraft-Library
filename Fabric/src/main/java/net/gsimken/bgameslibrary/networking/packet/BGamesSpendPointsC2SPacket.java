package net.gsimken.bgameslibrary.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.gsimken.bgameslibrary.bgames.BGamesLibraryTools;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class BGamesSpendPointsC2SPacket {
    public static void receive(MinecraftServer server,
                               ServerPlayerEntity player,
                               ServerPlayNetworkHandler handler,
                               PacketByteBuf buf,
                               PacketSender responseSender) {
        //serverside
        int points = buf.readInt();
        String attribute= buf.readString();
        BGamesLibraryTools.spendPoints(player,attribute,points);
    }
}
