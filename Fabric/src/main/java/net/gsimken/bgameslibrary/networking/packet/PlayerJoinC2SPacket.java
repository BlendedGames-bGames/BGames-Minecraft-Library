package net.gsimken.bgameslibrary.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.client.triggers.LoginTrigger;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.gsimken.bgameslibrary.utils.PlayerUtils;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class PlayerJoinC2SPacket {
    public static void receive(MinecraftServer server,
                               ServerPlayerEntity player,
                               ServerPlayNetworkHandler handler,
                               PacketByteBuf buf,
                               PacketSender responseSender) {
        //serverside
        IBGamesDataSaver playerDataHandler = (IBGamesDataSaver) player;
        String email = BGamesPlayerData.getEmail(playerDataHandler);
        String password = BGamesPlayerData.getPassword(playerDataHandler);
        BGamesPlayerData.setId(playerDataHandler,-1);
        BGamesPlayerData.attributeReset(playerDataHandler);
        if (email.equals("") || password.equals("")) {  // empty pass or email
            player.openHandledScreen(new LoginTrigger());
        } else {
            int playerId = new PlayerUtils().GetIdByEmail(email, password);
            BGamesPlayerData.setId(playerDataHandler,playerId);
            if (!BGamesPlayerData.isLoggedIn(playerDataHandler)) { //player not found or invalid credentials
                BGamesPlayerData.setEmail(playerDataHandler, "");
                BGamesPlayerData.setPassword(playerDataHandler, "");

                player.sendMessage(Text.translatable("api.bgameslibrary.player_not_found").fillStyle(Style.EMPTY.withColor(Formatting.RED)));
                player.openHandledScreen(new LoginTrigger());
            } else { //OK
                BGamesPlayerData.attributeRefresh(playerDataHandler);
                player.sendMessage(Text.translatable("login.bgameslibrary.logged").fillStyle(Style.EMPTY.withColor(Formatting.GREEN)));
                //sync the server player with the client player

            }
        }
        BGamesPlayerData.syncData(player);
    }
}
