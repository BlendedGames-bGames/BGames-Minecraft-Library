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
     * @param attribute the name of attribute, can be use by BgamesLibrary.bgames_afective_name or similar
     * @param ammountSpend the ammount of point to spent
     * @return bool if the points were spent
     * */
    public static boolean spendPoints(ServerPlayerEntity player, String attribute, int ammountSpend){

        if(!permittedAttributtes(attribute) || ammountSpend<1 ){
            return false;
        }
        IBGamesDataSaver playerDataHandler = (IBGamesDataSaver) player;
        int player_id= new PlayerUtils().GetIdByEmail(BGamesPlayerData.getEmail(playerDataHandler),BGamesPlayerData.getPassword(playerDataHandler));
        if(player_id!=BGamesPlayerData.getId(playerDataHandler)){
            return false;
        }
        ApiResponse response = new BGamesApi().spendAttribute(player_id,attribute,ammountSpend);
        if(!response.isSuccess()){
            player.sendMessage(Text.translatable(response.getErrorDescription()).fillStyle(Style.EMPTY.withColor(Formatting.RED)));
            return false;
        }
        BGamesPlayerData.syncData(player);
        return true;
    }
    /**
     * method to spent point in a name of player
     * @param player the player in the client side who spend the point
     * @param attribute the name of attribute, can be use by BgamesLibrary.bgames_afective_name or similar
     * @param ammountSpend the ammount of point to spent
     * @return bool if the points were spent
     * */
    public static boolean spendPoints(ClientPlayerEntity player, String attribute, int ammountSpend){
        if(!permittedAttributtes(attribute) || ammountSpend<1 ){
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
        buffer.writeString(attribute);
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

    private static boolean permittedAttributtes(String attribute){
        if(attribute!=BgamesLibrary.bgames_afective_name &&
                attribute!=BgamesLibrary.bgames_linguistic_name &&
                attribute!=BgamesLibrary.bgames_cognitive_name &&
                attribute!=BgamesLibrary.bgames_social_name &&
                attribute!=BgamesLibrary.bgames_physical_name){
            return false;
        }
        return true;
    }

}
