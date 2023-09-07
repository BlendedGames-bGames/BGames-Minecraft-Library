package net.gsimken.bgameslibrary.networking.packet;


import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.client.triggers.DisplayAttributesTrigger;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ButtonOpenGuiC2SPacket {
    public static void receive(MinecraftServer server,
                               ServerPlayerEntity player,
                               ServerPlayNetworkHandler handler,
                               PacketByteBuf buf,
                               PacketSender responseSender) {
        //serverside open gui
        IBGamesDataSaver playerDataHandler = (IBGamesDataSaver) player;
        if (!BGamesPlayerData.isLoggedIn(playerDataHandler)){
            BGamesPlayerData.syncData( player);
            player.sendMessage(Text.translatable(  "login.bgameslibrary.not_logged").fillStyle(Style.EMPTY.withColor(Formatting.RED)));
        }
        else {
            player.openHandledScreen(new DisplayAttributesTrigger());
        }

    }

}
