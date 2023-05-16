package net.gsimken.bgameslibrary.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.api.ApiResponse;
import net.gsimken.bgameslibrary.api.BGamesApi;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerDataProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

public class PlayerUtils {
    private static final String NOT_LOGGED_MESSAGE = "login.bgameslibrary.not_logged";
    public ArrayList GetAttributesByPlayer(Player player) {
        if (player == null)
            return new ArrayList<>();
        int id_player = -1;
        ApiResponse message;
        ArrayList<Object> response = new ArrayList<>();
        BGamesApi user_api = new BGamesApi();
        id_player= player.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).orElse(new BGamesPlayerData()).id;
        if (id_player == -1) {

            player.sendSystemMessage(Component.translatable(NOT_LOGGED_MESSAGE).withStyle(ChatFormatting.RED));
            return response;
        }
        message = user_api.getPlayerAttributesById(id_player);
        if (!message.isSuccess()) {
                player.sendSystemMessage(Component.literal("§4Error: " + message.getErrorDescription()).withStyle(ChatFormatting.RED));
        }
        response = message.getResponse();
        return response;
    }
    public ArrayList GetAttributesById(int id_player) {
        BGamesApi user_api=new BGamesApi();
        ApiResponse message = user_api.getPlayerAttributesById(id_player);
        if (!message.isSuccess()) {
            return new ArrayList<>();
        }
        return message.getResponse();
    }
    public int  GetIdByEmail(String email,String password) {
        BGamesApi user_api = new BGamesApi();
        ApiResponse message = user_api.getPlayerByLogin(email, password);
        if (message.isSuccess()) {
            Gson GSON = new Gson();
            JsonObject user = GSON.fromJson(message.getResponse().get(0).toString(), JsonObject.class);
            return user.get("id_players").getAsInt();
        } else{
            return -1;
        }

    }
    public static boolean isLoggedIn(Player player) {
        if (player == null)
            return false;
        int id=(player.getCapability(BGamesPlayerDataProvider.PLAYER_DATA, null).orElse(new BGamesPlayerData())).id;
        if (id!=-1) {
            return true;
        }
        return false;
    }

}
