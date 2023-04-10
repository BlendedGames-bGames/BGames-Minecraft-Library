package net.gsimken.bgameslibrary.core.api;

import com.google.gson.JsonObject;
import net.gsimken.bgameslibrary.core.api_config.BgamesCommonConfigs;

public class ApiUserManagement extends Api{
    private static final String API_BASE_URL = BgamesCommonConfigs.URL.get()+BgamesCommonConfigs.USER_PORT.get()+"/";


    public ApiResponse getPlayerByLogin(String email,String password){
        ApiResponse requestResponse = new ApiResponse();//manage the mod response
        String url = API_BASE_URL + "player_by_email/" + email;
        requestResponse= this.makeGetRequest(url);
        if(requestResponse.getCode()!=200){ //an error has occured in request
            return requestResponse;
        }
        if(requestResponse.getResponse().size()==0) {
            requestResponse.setCodeError(404, "Player not registered in bGames");
            return requestResponse;
        }

        //falta revisar que la contra este correcta
        JsonObject user= GSON.fromJson( requestResponse.getResponse().get(0).toString(), JsonObject.class);

        if(user.get("password").getAsString().equals(password)){
            requestResponse.setCode(200);
        }
        else{
            requestResponse.setCodeError(401, "Invalid credentials");
        }
        return requestResponse;

    }




}

