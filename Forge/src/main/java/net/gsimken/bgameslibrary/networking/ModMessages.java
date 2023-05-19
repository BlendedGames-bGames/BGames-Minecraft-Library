package net.gsimken.bgameslibrary.networking;

import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.networking.packet.BGamesPlayerDataSyncS2CPacket;
import net.gsimken.bgameslibrary.networking.packet.ButtonOpenGuiC2SPacket;
import net.gsimken.bgameslibrary.networking.packet.LoginC2SPacket;
import net.gsimken.bgameslibrary.networking.packet.OpenGuiC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
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

        net.messageBuilder(OpenGuiC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(OpenGuiC2SPacket::new)
                .encoder(OpenGuiC2SPacket::toBytes)
                .consumerMainThread(OpenGuiC2SPacket::handle)
                .add();
        net.messageBuilder(BGamesPlayerDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(BGamesPlayerDataSyncS2CPacket::new)
                .encoder(BGamesPlayerDataSyncS2CPacket::toBytes)
                .consumerMainThread(BGamesPlayerDataSyncS2CPacket::handle)
                .add();

        net.messageBuilder(LoginC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(LoginC2SPacket::new)
                .encoder(LoginC2SPacket::toBytes)
                .consumerMainThread(LoginC2SPacket::handle)
                .add();
        net.messageBuilder(ButtonOpenGuiC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ButtonOpenGuiC2SPacket::new)
                .encoder(ButtonOpenGuiC2SPacket::toBytes)
                .consumerMainThread(ButtonOpenGuiC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}