package net.gsimken.bgameslibrary.core.api;

import net.gsimken.bgameslibrary.core.api_config.BgamesCommonConfigs;
import org.apache.http.HttpEntity;

import java.io.IOException;
import java.util.ArrayList;

public class ApiUserManagement extends Api{
    private static final String API_BASE_URL = BgamesCommonConfigs.URL.get()+BgamesCommonConfigs.USER_PORT.get()+"/";


    public ApiResponse getPlayerId(String email,String password) throws IOException {
        ApiResponse requestResponse = new ApiResponse();//manage the mod response

        String url = API_BASE_URL + "players/" + email;
        requestResponse= this.makeRequest(url);
        if(requestResponse.getCode()!=null){ //an error has occured in request
            return requestResponse;
        }
        if(requestResponse.getResponse().size()==0) {
            requestResponse.setCodeError(404, "Player not registered in bGames");
        }
        //falta revisar que la contra este correcta

        requestResponse.setCode(200);
        return requestResponse;


    }
}

