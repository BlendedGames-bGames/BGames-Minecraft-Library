package net.gsimken.bgameslibrary.bgames;

import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.api.ApiResponse;
import net.gsimken.bgameslibrary.api.BGamesApi;
import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;
import net.gsimken.bgameslibrary.networking.packet.BGamesPlayerDataSyncC2SPacket;
import net.gsimken.bgameslibrary.networking.packet.BGamesPlayerDataSyncS2CPacket;
import net.gsimken.bgameslibrary.networking.packet.BGamesSpendPointsC2SPacket;
import net.gsimken.bgameslibrary.utils.PlayerUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class BGamesLibraryTools {
    /**
     * method to spent point in a name of player
     * @param player the player in the server side who spend the point
     * @param dimension the name of dimension, can be use by BgamesLibrary.bgames_afective_name or similar
     * @param ammountSpend the ammount of point to spent
     * @return bool if the points were spent
     * */
    public static boolean spendPoints(ServerPlayer player, String dimension, int ammountSpend){
        if(!permittedDimensions(dimension) || ammountSpend<1 ){
            return false;
        }
        BGamesPlayerData data =  player.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).orElse(new BGamesPlayerData());
        int player_id= new PlayerUtils().GetIdByEmail(data.getEmail(),data.getPassword());
        if(player_id!=data.getId()){
            return false;
        }
        ApiResponse response = new BGamesApi().spendAttribute(player_id,dimension,ammountSpend);
        if(!response.isSuccess()){
            player.sendSystemMessage(Component.translatable(response.getErrorDescription()).withStyle(ChatFormatting.RED));
            return false;
        }
        data.attributeRefresh();
        BGamesLibraryModMessages.sendToPlayer(new BGamesPlayerDataSyncS2CPacket(data.getId(),
                data.getSocialPoints(),data.getPhysicalPoints(),data.getLinguisticPoints(),
                data.getAffectivePoints(),data.getCognitivePoints(),
                data.getEmail(),data.getPassword()), player);

        return true;
    }
    /**
     * method to spent point in a name of player
     * @param player the player in the client side who spend the point
     * @param dimension the name of dimension, can be use by BgamesLibrary.bgames_afective_name or similar
     * @param ammountSpend the ammount of point to spent
     * @return bool if the points were spent
     * */
    public static boolean spendPoints(LocalPlayer player, String dimension, int ammountSpend){
        if(!permittedDimensions(dimension) || ammountSpend<1 ){
            return false;
        }
        BGamesLibraryModMessages.sendToServer(new BGamesPlayerDataSyncC2SPacket());

        int player_id= new PlayerUtils().GetIdByEmail(ClientBGamesPlayerData.getPlayerEmail(),ClientBGamesPlayerData.getPlayerPassword());
        if(player_id!=ClientBGamesPlayerData.getPlayerId()){
            return false;
        }
        BGamesLibraryModMessages.sendToServer(new BGamesSpendPointsC2SPacket(ammountSpend,dimension));

        return true;
    }
    /**
     * method to spent point in a name of player
     * @param player the player to consult
     * @return bool if the player is logged in bgmaes or not
     * */
    public static boolean isPlayerLogged(Player player){
        if(player.isLocalPlayer()){
            return ClientBGamesPlayerData.isLoggedIn();
        }
        else{
            BGamesPlayerData data =  player.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).orElse(null);
            if(data==null){
                return false;
            }
            return data.isLoggedIn();
        }
    }
    /**
     * method to consult points of a player
     * @param dimension dimension name, can be use by BgamesLibrary.bgames_afective_name or similar
     * @param player the player to consult
     * @return points, -1 if it doesn't exist
     * */
    public static int getPoints(String dimension, Player player){
        if(permittedDimensions(dimension)) {
            if (player.isLocalPlayer()) {
                if (dimension == BgamesLibrary.bgames_afective_name) {
                    return ClientBGamesPlayerData.getPlayerAffectivePoints();
                } else if (dimension == BgamesLibrary.bgames_linguistic_name) {
                    return ClientBGamesPlayerData.getPlayerLinguisticPoints();
                } else if (dimension == BgamesLibrary.bgames_cognitive_name) {
                    return ClientBGamesPlayerData.getPlayerCognitivePoints();
                } else if (dimension == BgamesLibrary.bgames_social_name) {
                    return ClientBGamesPlayerData.getPlayerSocialPoints();
                } else if (dimension == BgamesLibrary.bgames_physical_name) {
                    return ClientBGamesPlayerData.getPlayerPhysicalPoints();
                }
            } else {
                BGamesPlayerData data = player.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).orElse(null);
                if (data != null) {
                    if (dimension == BgamesLibrary.bgames_afective_name) {
                        return data.getAffectivePoints();
                    } else if (dimension == BgamesLibrary.bgames_linguistic_name) {
                        return data.getLinguisticPoints();
                    } else if (dimension == BgamesLibrary.bgames_cognitive_name) {
                        return data.getCognitivePoints();
                    } else if (dimension == BgamesLibrary.bgames_social_name) {
                        return data.getSocialPoints();
                    } else if (dimension == BgamesLibrary.bgames_physical_name) {
                        return data.getPhysicalPoints();
                    }
                }
            }
        }
        return -1;
    }



    private static boolean permittedDimensions(String dimension){
        if(dimension!= BgamesLibrary.bgames_afective_name &&
                dimension!=BgamesLibrary.bgames_linguistic_name &&
                dimension!=BgamesLibrary.bgames_cognitive_name &&
                dimension!=BgamesLibrary.bgames_social_name &&
                dimension!=BgamesLibrary.bgames_physical_name){
            return false;
        }
        return true;
    }
}
