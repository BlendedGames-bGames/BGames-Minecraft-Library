package net.gsimken.bgameslibrary.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.networking.packet.*;
import net.minecraft.util.Identifier;

public class BGamesLibraryModMessages {
    public static final Identifier BGAMES_SERVER_DATA_SYNC_ID =new Identifier(BgamesLibrary.MOD_ID,"server_data_sync");
    public static final Identifier BGAMES_CLIENT_DATA_SYNC =new Identifier(BgamesLibrary.MOD_ID,"client_data_sync");
    public static final Identifier BGAMES_SPEND_POINT =new Identifier(BgamesLibrary.MOD_ID,"spend_point");
    public static final Identifier OPEN_DISPLAY_ATTRIBUTE =new Identifier(BgamesLibrary.MOD_ID,"open_display_attribute");
    public static final Identifier BGAMES_LOGIN =new Identifier(BgamesLibrary.MOD_ID,"login");
    public static final Identifier PLAYER_JOIN =new Identifier(BgamesLibrary.MOD_ID,"player_join");
    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(OPEN_DISPLAY_ATTRIBUTE, ButtonOpenGuiC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(BGAMES_LOGIN, LoginC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(BGAMES_CLIENT_DATA_SYNC, BGamesPlayerDataSyncC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(BGAMES_SPEND_POINT, BGamesSpendPointsC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(PLAYER_JOIN, PlayerJoinC2SPacket::receive);

    }
    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(BGAMES_SERVER_DATA_SYNC_ID, BGamesPlayerDataSyncS2CPacket::receive);

    }

}
