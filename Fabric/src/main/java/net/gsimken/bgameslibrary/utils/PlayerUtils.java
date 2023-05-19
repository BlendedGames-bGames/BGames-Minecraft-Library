package net.gsimken.bgameslibrary.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.gsimken.bgameslibrary.api.ApiResponse;
import net.gsimken.bgameslibrary.api.BGamesApi;

import java.util.ArrayList;

public class PlayerUtils {
    private static final String NOT_LOGGED_MESSAGE = "login.bgameslibrary.not_logged";

    /**
     * Obtain Attributes of a player
     * @param id_player bgames player id to consult in api
     * @return arraylist with all attributes
     * */
    public ArrayList GetAttributesById(int id_player) {
        BGamesApi user_api=new BGamesApi();
        ApiResponse message = user_api.getPlayerAttributesById(id_player);
        if (!message.isSuccess()) {
            return new ArrayList<>();
        }
        return message.getResponse();
    }
    /**
     * Basically a login
     * @param email bgames email
     * @param password bgames password
     * @return int id, in case player doesnt exist or invalid credential, -1
     * */
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


}
