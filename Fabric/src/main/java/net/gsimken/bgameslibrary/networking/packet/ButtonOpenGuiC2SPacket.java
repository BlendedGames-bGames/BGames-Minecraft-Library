package net.gsimken.bgameslibrary.networking.packet;


import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.gsimken.bgameslibrary.client.triggers.DisplayAttributesTrigger;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class ButtonOpenGuiC2SPacket {
    public static void receive(MinecraftServer server,
                               ServerPlayerEntity player,
                               ServerPlayNetworkHandler handler,
                               PacketByteBuf buf,
                               PacketSender responseSender) {
        //serverside open gui
        player.openHandledScreen(new DisplayAttributesTrigger());

    }

}
