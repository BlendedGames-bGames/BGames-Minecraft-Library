package net.gsimken.bgameslibrary.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;

public class BGamesPlayerDataSyncS2CPacket {
    public static void receive(MinecraftClient client,
                               ClientPlayNetworkHandler handler,
                               PacketByteBuf buf,
                               PacketSender responseSender) {
        //clientside
        IBGamesDataSaver player = (IBGamesDataSaver)client.player;
        int id = buf.readInt();
        int socialPoints = buf.readInt();
        int physicalPoints = buf.readInt();
        int linguisticPoints = buf.readInt();
        int affectivePoints = buf.readInt();
        int cognitivePoints = buf.readInt();
        String email= buf.readString();
        String password= buf.readString();
        NbtCompound nbt = player.getPersistentData();
        nbt.putInt("id",id) ;
        nbt.putInt("social_points",socialPoints) ;
        nbt.putInt("physical_points",physicalPoints);
        nbt.putInt("linguistic_points",linguisticPoints) ;
        nbt.putInt("affective_points",affectivePoints) ;
        nbt.putInt("cognitive_points",cognitivePoints) ;
        nbt.putString("email",email);
        nbt.putString("password",password);


    }

}
