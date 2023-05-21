package net.gsimken.bgameslibrary.bgames;

import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.api.ApiResponse;
import net.gsimken.bgameslibrary.api.BGamesApi;
import net.gsimken.bgameslibrary.client.menus.LoginMenu;
import net.gsimken.bgameslibrary.networking.ModMessages;
import net.gsimken.bgameslibrary.networking.packet.BGamesPlayerDataSyncC2SPacket;
import net.gsimken.bgameslibrary.networking.packet.BGamesSpendPointsC2SPacket;
import net.gsimken.bgameslibrary.networking.packet.LoginC2SPacket;
import net.gsimken.bgameslibrary.utils.PlayerUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class BGamesTools {
    public static boolean spendPoints(ServerPlayer player, String attribute, int ammountSpend){
        if(!permittedAttributtes(attribute) || ammountSpend<1 ){
            return false;
        }
        BGamesPlayerData data =  player.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).orElse(new BGamesPlayerData());
        int player_id= new PlayerUtils().GetIdByEmail(data.getEmail(),data.getPassword());
        if(player_id!=data.getId()){
            return false;
        }
        ApiResponse response = new BGamesApi().spendAttribute(player_id,attribute,ammountSpend);
        if(!response.isSuccess()){
            player.sendSystemMessage(Component.translatable(response.getErrorDescription()).withStyle(ChatFormatting.RED));
            return false;
        }
        return true;
    }
    public static boolean spendPoints(LocalPlayer player, String attribute, int ammountSpend){
        if(!permittedAttributtes(attribute) || ammountSpend<1 ){
            return false;
        }
        ModMessages.sendToServer(new BGamesPlayerDataSyncC2SPacket());

        int player_id= new PlayerUtils().GetIdByEmail(ClientBGamesPlayerData.getPlayerEmail(),ClientBGamesPlayerData.getPlayerPassword());
        if(player_id!=ClientBGamesPlayerData.getPlayerId()){
            return false;
        }
        ModMessages.sendToServer(new BGamesSpendPointsC2SPacket(ammountSpend,attribute));

        return true;
    }
    private static boolean permittedAttributtes(String attribute){
        if(attribute!= BgamesLibrary.bgames_afective_name &&
                attribute!=BgamesLibrary.bgames_linguistic_name &&
                attribute!=BgamesLibrary.bgames_cognitive_name &&
                attribute!=BgamesLibrary.bgames_social_name &&
                attribute!=BgamesLibrary.bgames_physical_name){
            return false;
        }
        return true;
    }
}
