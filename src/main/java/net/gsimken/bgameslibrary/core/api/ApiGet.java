package net.gsimken.bgameslibrary.core.api;

import com.google.gson.Gson;
import net.gsimken.bgameslibrary.core.api_config.BgamesCommonConfigs;
import org.apache.http.HttpEntity;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ApiGet extends Api {
    private static final String API_BASE_URL = BgamesCommonConfigs.URL.get()+BgamesCommonConfigs.GET_PORT.get()+"/";

    public ApiResponse getPlayerAttributes(Integer id) throws IOException {

        ApiResponse requestResponse = new ApiResponse();//manage the mod response

        String url = API_BASE_URL + "player_all_attributes/" + id;
        requestResponse= this.makeRequest(url);
        if(requestResponse.getCode()!=null){ //an error has occured in request
            return requestResponse;
        }
        if(requestResponse.getResponse().size()==0){
            requestResponse.setCodeError(404,"Player id not found");
        }

        requestResponse.setCode(200);
        return requestResponse;
    }

}

