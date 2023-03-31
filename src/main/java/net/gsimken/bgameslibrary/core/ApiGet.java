package net.gsimken.bgameslibrary.core;

import com.google.gson.Gson;
import net.gsimken.bgameslibrary.core.config.BgamesCommonConfigs;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class ApiGet {
    private static final String API_BASE_URL = BgamesCommonConfigs.URL.get()+BgamesCommonConfigs.GET_PORT.get()+"/";
    private static final Gson GSON = new Gson();
    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(10000)
            .setConnectionRequestTimeout(10000)
            .setSocketTimeout(10000)
            .build();
    private HttpClient CLIENT = HttpClientBuilder.create().build();

    public HttpEntity makeRequest(String url) throws IOException, SocketTimeoutException {
        HttpGet request = new HttpGet(url);
        request.setConfig(this.requestConfig);
        HttpResponse response = CLIENT.execute(request);
        HttpEntity entity = response.getEntity();
        return entity;
    }
    public boolean checkConnection(){
        try{
            this.makeRequest(API_BASE_URL);
            return true;

        }catch (Exception e){

            return false;
        }

    }
    public ApiResponse getPlayerAttributes(Integer id) throws IOException {

        ApiResponse requestResponse = new ApiResponse();//manage the mod response
        ArrayList<Object> jsonResponseArray = new ArrayList<>(); //manage the http response
        if(!this.checkConnection()){ //the API is not available
            requestResponse.setCodeError(500,"API Connection Error"); //server error
            return requestResponse;
        }
        String url = API_BASE_URL + "player_all_attributes/" + id;
        HttpEntity entity=null;
        try{ //executes the request with 10s to get the responses, else gives an excepción for timeout
            entity = this.makeRequest(url);
        }
        catch(Exception e){
            System.out.println(e);
            requestResponse.setCodeError(500,"API TimeOut Error"); //server error
            return requestResponse;
        }

        if (entity == null) {
            requestResponse.setCodeError(404, "Player Id not found in API"); //player id not found
            return requestResponse;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
        JsonElement element = GSON.fromJson(reader, JsonElement.class);
        JsonArray jsonArray = element.getAsJsonArray();
        for (JsonElement jsonElement : jsonArray) {
            Object object = new Gson().fromJson(jsonElement, Object.class);
            jsonResponseArray.add(object);
        }
        requestResponse.setCode(200);
        requestResponse.setResponse(jsonResponseArray);
        return requestResponse;


    }

}

