package net.gsimken.bgameslibrary.networking.packet;

import net.gsimken.bgameslibrary.bgames.BGamesLibraryTools;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerDataProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BGamesSpendPointsC2SPacket {
        private final int ammountSpent;
        private final String attribute;
    public BGamesSpendPointsC2SPacket(int ammountSpent,String attribute) {
        this.ammountSpent = ammountSpent;
        this.attribute=attribute;
    }

    public BGamesSpendPointsC2SPacket(FriendlyByteBuf buf) {
        this.ammountSpent = buf.readInt();
        this.attribute=buf.readUtf();

    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(ammountSpent);
        buf.writeUtf(attribute);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            player.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
                data.attributeReset();
                data.attributeRefresh();
            });
            BGamesLibraryTools.spendPoints(player,attribute,ammountSpent);

        });
        return true;
    }/*
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
    */

}
