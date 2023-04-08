package net.gsimken.bgameslibrary.core.api;

import net.gsimken.bgameslibrary.core.api_config.BgamesCommonConfigs;

public class ApiGet extends Api {
    private static final String API_BASE_URL = BgamesCommonConfigs.URL.get()+BgamesCommonConfigs.GET_PORT.get()+"/";

    public ApiResponse getPlayerAttributesById(Integer id) {

        ApiResponse requestResponse = new ApiResponse();//manage the mod response

        String url = API_BASE_URL + "player_all_attributes/" + id;
        if(id==-1){
            requestResponse.setCodeError(401,"Invalid credentials");
            return requestResponse;
        }
        requestResponse= this.makeGetRequest(url);
        if(requestResponse.getCode()!=200){ //an error has occured in request
            return requestResponse;
        }
        if(requestResponse.getResponse().size()==0){
            requestResponse.setCodeError(404,"Player id not found");
            return requestResponse;
        }

        requestResponse.setCode(200);
        return requestResponse;
    }

}

