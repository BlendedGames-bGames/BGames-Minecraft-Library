package net.gsimken.bgameslibrary.core.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class Api {

    protected static final Gson GSON = new Gson();
    protected static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(10000)
            .setConnectionRequestTimeout(10000)
            .setSocketTimeout(10000)
            .build();
    protected HttpClient CLIENT = HttpClientBuilder.create().build();

    public ApiResponse makeRequest(String url) throws IOException, SocketTimeoutException {
        ApiResponse requestResponse = new ApiResponse();//manage the mod response
        ArrayList<Object> jsonResponseArray = new ArrayList<>(); //manage the http response
        if(!this.checkConnection(url)){ //the API is not available
            requestResponse.setCodeError(500,"API Connection Error"); //server error
            return requestResponse;
        }
        HttpEntity entity=null;
        try{ //executes the request with 10s to get the responses, else gives an excepción for timeout
            HttpGet request = new HttpGet(url);
            request.setConfig(this.requestConfig);
            HttpResponse response = CLIENT.execute(request);
            entity = response.getEntity();
        }
        catch(Exception e){
            System.out.println(e);
            requestResponse.setCodeError(500,"API TimeOut Error"); //server error
            return requestResponse;
        }

        if (entity == null) {
            requestResponse.setCodeError(500, "Error in request"); //player id not found
            return requestResponse;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
        JsonElement element = GSON.fromJson(reader, JsonElement.class);
        JsonArray jsonArray = element.getAsJsonArray();
        for (JsonElement jsonElement : jsonArray) {
            Object object = new Gson().fromJson(jsonElement, Object.class);
            jsonResponseArray.add(object);
        }
        requestResponse.setResponse(jsonResponseArray);
        return  requestResponse;

    }
    public boolean checkConnection(String url){
        try{
            this.makeRequest(url);
            return true;

        }catch (Exception e){

            return false;
        }
    }
}
