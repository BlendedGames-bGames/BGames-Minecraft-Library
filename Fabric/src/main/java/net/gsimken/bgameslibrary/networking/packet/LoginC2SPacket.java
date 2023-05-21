package net.gsimken.bgameslibrary.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.client.triggers.DisplayAttributesTrigger;
import net.gsimken.bgameslibrary.networking.ModMessages;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.gsimken.bgameslibrary.utils.PlayerUtils;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class LoginC2SPacket {
    public static void receive(MinecraftServer server,
                               ServerPlayerEntity player,
                               ServerPlayNetworkHandler handler,
                               PacketByteBuf buf,
                               PacketSender responseSender) {
        //serverside
        IBGamesDataSaver playerDataHandler= (IBGamesDataSaver) player;
        String email = buf.readString();
        String password= buf.readString();
        BGamesPlayerData.attributeReset(playerDataHandler);
        int playerId= new PlayerUtils().GetIdByEmail(email, password);
        BGamesPlayerData.setId(playerDataHandler,playerId);
        if (!BGamesPlayerData.isLoggedIn(playerDataHandler)) { //player not found or invalid credentials
            BGamesPlayerData.setEmail(playerDataHandler, "");
            BGamesPlayerData.setPassword(playerDataHandler, "");
            player.sendMessage(Text.translatable(  "api.bgameslibrary.player_not_found").fillStyle(Style.EMPTY.withColor(Formatting.RED)));
        }
        else {
            BGamesPlayerData.setIdEmailPassword(playerDataHandler,playerId,email,password );
            BGamesPlayerData.attributeRefresh(playerDataHandler);
            player.sendMessage(Text.translatable(  "login.bgameslibrary.logged").fillStyle(Style.EMPTY.withColor(Formatting.GREEN)));
            BGamesPlayerData.syncData(player);
        }

        player.closeHandledScreen();


    }
}
