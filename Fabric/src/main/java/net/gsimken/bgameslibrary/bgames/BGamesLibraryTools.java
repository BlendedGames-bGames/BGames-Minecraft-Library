package net.gsimken.bgameslibrary.bgames;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.api.ApiResponse;
import net.gsimken.bgameslibrary.api.BGamesApi;
import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.gsimken.bgameslibrary.utils.PlayerUtils;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class BGamesLibraryTools {
    /**
     * method to spent point in a name of player
     * @param player the player in the server side who spend the point
     * @param dimension the name of dimension, can be use by BgamesLibrary.bgames_afective_name or similar
     * @param ammountSpend the ammount of point to spent
     * @return bool if the points were spent
     * */
    public static boolean spendPoints(ServerPlayerEntity player, String dimension, int ammountSpend){

        if(!permittedDimensions(dimension) || ammountSpend<1 ){
            return false;
        }
        IBGamesDataSaver playerDataHandler = (IBGamesDataSaver) player;
        int player_id= new PlayerUtils().GetIdByEmail(BGamesPlayerData.getEmail(playerDataHandler),BGamesPlayerData.getPassword(playerDataHandler));
        if(player_id!=BGamesPlayerData.getId(playerDataHandler)){
            return false;
        }
        ApiResponse response = new BGamesApi().spendAttribute(player_id,dimension,ammountSpend);
        if(!response.isSuccess()){
            player.sendMessage(Text.translatable(response.getErrorDescription()).fillStyle(Style.EMPTY.withColor(Formatting.RED)));
            return false;
        }
        BGamesPlayerData.attributeRefresh((IBGamesDataSaver) player);
        BGamesPlayerData.syncData(player);
        return true;
    }
    /**
     * method to spent point in a name of player
     * @param player the player in the client side who spend the point
     * @param dimension the name of dimension, can be use by BgamesLibrary.bgames_afective_name or similar
     * @param ammountSpend the ammount of point to spent
     * @return bool if the points were spent
     * */
    public static boolean spendPoints(ClientPlayerEntity player, String dimension, int ammountSpend){
        if(!permittedDimensions(dimension) || ammountSpend<1 ){
            return false;
        }
        ClientPlayNetworking.send(BGamesLibraryModMessages.BGAMES_CLIENT_DATA_SYNC, PacketByteBufs.create());
        IBGamesDataSaver playerDataHandler = (IBGamesDataSaver) player;
        int player_id= new PlayerUtils().GetIdByEmail(BGamesPlayerData.getEmail(playerDataHandler),BGamesPlayerData.getPassword(playerDataHandler));
        if(player_id!=BGamesPlayerData.getId(playerDataHandler)){
            return false;
        }
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(ammountSpend);
        buffer.writeString(dimension);
        ClientPlayNetworking.send(BGamesLibraryModMessages.BGAMES_SPEND_POINT,buffer );
        return true;
    }
    /**
     * method to spent point in a name of player
     * @param player the player to consult
     * @return bool if the player is logged in bgmaes or not
     * */
    public static boolean isPlayerLogged(PlayerEntity player){
        IBGamesDataSaver playerDataHandler = (IBGamesDataSaver) player;
        return BGamesPlayerData.isLoggedIn(playerDataHandler);
    }

    /**
     * method to consult points of a player
     * @param dimension dimension name, can be use by BgamesLibrary.bgames_afective_name or similar
     * @param player the player to consult
     * @return points, -1 if it doesn't exist
     * */
    public static int getPoints(String dimension, PlayerEntity player){
        if(permittedDimensions(dimension)) {
            IBGamesDataSaver playerDataHandler = (IBGamesDataSaver) player;
            if(dimension == BgamesLibrary.bgames_afective_name) {
                return BGamesPlayerData.getAffectivePoints(playerDataHandler);
            } else if (dimension == BgamesLibrary.bgames_linguistic_name) {
                return BGamesPlayerData.getLinguisticPoints(playerDataHandler);
            } else if (dimension == BgamesLibrary.bgames_cognitive_name) {
                return BGamesPlayerData.getCognitivePoints(playerDataHandler);
            } else if (dimension == BgamesLibrary.bgames_social_name) {
                return BGamesPlayerData.getSocialPoints(playerDataHandler);
            } else if (dimension == BgamesLibrary.bgames_physical_name) {
                return BGamesPlayerData.getPhysicalPoints(playerDataHandler);
            }
        }
        return -1;
    }
    private static boolean permittedDimensions(String dimension){
        if(dimension!=BgamesLibrary.bgames_afective_name &&
                dimension!=BgamesLibrary.bgames_linguistic_name &&
                dimension!=BgamesLibrary.bgames_cognitive_name &&
                dimension!=BgamesLibrary.bgames_social_name &&
                dimension!=BgamesLibrary.bgames_physical_name){
            return false;
        }
        return true;
    }




}
