package net.gsimken.bgameslibrary.event;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.client.triggers.LoginTrigger;
import net.gsimken.bgameslibrary.networking.ModMessages;
import net.gsimken.bgameslibrary.networking.packet.BGamesPlayerDataSyncS2CPacket;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.gsimken.bgameslibrary.utils.PlayerUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ModPlayerLoginEvent implements ServerPlayConnectionEvents.Join {
    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        /*
        ServerPlayerEntity player = handler.getPlayer();
        IBGamesDataSaver playerDataHandler = (IBGamesDataSaver) player;
        String email = BGamesPlayerData.getEmail(playerDataHandler);
        String password = BGamesPlayerData.getPassword(playerDataHandler);
        BGamesPlayerData.attributeReset(playerDataHandler);
        if(email.equals("") || password.equals("")){  // empty pass or email
            player.openHandledScreen(new LoginTrigger());
        }
        else{
            int playerId= new PlayerUtils().GetIdByEmail(email, password);
            int id = playerId;
            if(id==-1){ //player not found or invalid credentials
                BGamesPlayerData.setEmail(playerDataHandler,"");
                BGamesPlayerData.setPassword(playerDataHandler,"");

                player.sendMessage(Text.translatable(  "api.bgameslibrary.player_not_found").fillStyle(Style.EMPTY.withColor(Formatting.RED)));
                player.openHandledScreen(new LoginTrigger());
            }
            else { //OK
                BGamesPlayerData.attributeRefresh(playerDataHandler);
                player.sendMessage(Text.translatable(  "login.bgameslibrary.logged").fillStyle(Style.EMPTY.withColor(Formatting.GREEN)));
                //sync the server player with the client player
                BGamesPlayerData.syncData(player);
            }


        }

     */

    }
}