package net.gsimken.bgameslibrary.networking.packet;

import net.gsimken.bgameslibrary.bgames.BGamesPlayerDataProvider;
import net.gsimken.bgameslibrary.networking.ModMessages;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenGuiC2SPacket {
    public OpenGuiC2SPacket() {

    }

    public OpenGuiC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            player.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).ifPresent(data -> {

                player.sendSystemMessage(Component.literal(data.Stringify())
                        .withStyle(ChatFormatting.AQUA));
                /*
                   Al momento de abrir la gui se manda al jugador los datos del servidors para sincronizar
                 * */
                ModMessages.sendToPlayer(new BGamesPlayerDataSyncS2CPacket(data.getId(),
                        data.getSocialPoints(),data.getPhysicalPoints(),data.getLinguisticPoints(),
                        data.getAffectivePoints(),data.getCognitivePoints(),
                        data.getEmail(),data.getPassword()), player);
            });


        });
        return true;
    }

}