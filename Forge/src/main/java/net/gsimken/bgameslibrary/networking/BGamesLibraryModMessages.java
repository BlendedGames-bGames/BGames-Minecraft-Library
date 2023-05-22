package net.gsimken.bgameslibrary.networking;

import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.networking.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class BGamesLibraryModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(BgamesLibrary.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(BGamesPlayerDataSyncC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BGamesPlayerDataSyncC2SPacket::new)
                .encoder(BGamesPlayerDataSyncC2SPacket::toBytes)
                .consumerMainThread(BGamesPlayerDataSyncC2SPacket::handle)
                .add();
        net.messageBuilder(BGamesPlayerDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(BGamesPlayerDataSyncS2CPacket::new)
                .encoder(BGamesPlayerDataSyncS2CPacket::toBytes)
                .consumerMainThread(BGamesPlayerDataSyncS2CPacket::handle)
                .add();

        net.messageBuilder(BGamesLoginC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BGamesLoginC2SPacket::new)
                .encoder(BGamesLoginC2SPacket::toBytes)
                .consumerMainThread(BGamesLoginC2SPacket::handle)
                .add();
        net.messageBuilder(ButtonOpenGuiC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ButtonOpenGuiC2SPacket::new)
                .encoder(ButtonOpenGuiC2SPacket::toBytes)
                .consumerMainThread(ButtonOpenGuiC2SPacket::handle)
                .add();
        net.messageBuilder(BGamesSpendPointsC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BGamesSpendPointsC2SPacket::new)
                .encoder(BGamesSpendPointsC2SPacket::toBytes)
                .consumerMainThread(BGamesSpendPointsC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}