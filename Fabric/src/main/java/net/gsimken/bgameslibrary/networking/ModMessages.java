package net.gsimken.bgameslibrary.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.networking.packet.BGamesPlayerDataSyncS2CPacket;
import net.gsimken.bgameslibrary.networking.packet.ButtonOpenGuiC2SPacket;
import net.gsimken.bgameslibrary.networking.packet.LoginC2SPacket;
import net.gsimken.bgameslibrary.networking.packet.OpenGuiC2SPacket;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier BGAMES_DATA_SYNC_ID =new Identifier(BgamesLibrary.MOD_ID,"data_sync");
    public static final Identifier OPEN_DISPLAY_ATTRIBUTE =new Identifier(BgamesLibrary.MOD_ID,"open_display_attribute");
    public static final Identifier BGAMES_LOGIN =new Identifier(BgamesLibrary.MOD_ID,"login");
    public static final Identifier OPEN_GUI_SYNC =new Identifier(BgamesLibrary.MOD_ID,"sync_on_open_gui");
    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(OPEN_DISPLAY_ATTRIBUTE, ButtonOpenGuiC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(BGAMES_LOGIN, LoginC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(OPEN_GUI_SYNC, OpenGuiC2SPacket::receive);

    }
    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(BGAMES_DATA_SYNC_ID, BGamesPlayerDataSyncS2CPacket::receive);

    }

}
