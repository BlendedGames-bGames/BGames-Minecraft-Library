package net.gsimken.bgameslibrary.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.gsimken.bgameslibrary.configs.BGamesCommonConfigs;

import net.gsimken.bgameslibrary.utils.CSVwriter;
import net.gsimken.bgameslibrary.utils.JsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BGamesApi {

    protected static final Gson GSON = new Gson();
    protected static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(10000)
            .setConnectionRequestTimeout(10000)
            .setSocketTimeout(10000)
            .build();
    protected HttpClient CLIENT = HttpClientBuilder.create().build();

    private static final String API_GET_BASE_URL = BGamesCommonConfigs.URL.get()+ BGamesCommonConfigs.GET_PORT.get()+"/";
    private static final String API_USER_BASE_URL = BGamesCommonConfigs.URL.get()+ BGamesCommonConfigs.USER_PORT.get()+"/";
    private static final String API_POST_BASE_URL = BGamesCommonConfigs.URL.get()+ BGamesCommonConfigs.POST_PORT.get()+"/";
    private static  final int MINECRAFT_BGAMES_ID= 2;

    //GET API
    /**
     * OBTAIN THE ATTRIBUTES OF A PLAYER BY AN ID
     * @param id id of player
     * @return attributes of the player in bGames (The response has code, error_description, response)
     */
    public ApiResponse getPlayerAttributesById(Integer id) {
        ApiResponse requestResponse = new ApiResponse();//manage the mod response

        String url = API_GET_BASE_URL + "player_all_attributes/" + id;
        if(id==-1){
            requestResponse.setCodeError(401,"api.bgameslibrary.invalid_credentials");
            return requestResponse;
        }
        requestResponse= this.makeGetRequest(url);

        if(!requestResponse.isSuccess()){ //an error has occured in request
            return requestResponse;
        }
        if(requestResponse.getResponse().size()==0){
            requestResponse.setCodeError(404, "api.bgameslibrary.player_not_found");
            return requestResponse;
        }

        requestResponse.setCode(200);
        return requestResponse;
    }

    /*USER MANAGEMENT API*/
    /**
     * Obtain the user of a player
     * @param email email registered in bGames
     * @param password the password of bgames account
     * @return  @return user json (The response has code, error_description, response)
     */
    public ApiResponse getPlayerByLogin(String email,String password) {
        ApiResponse requestResponse= new ApiResponse();
        if(email.equals("") || password.equals("")){
            requestResponse.setCodeError(401, "api.bgameslibrary.invalid_credentials");
            return requestResponse;
        }
        String url = API_USER_BASE_URL + "player_by_email/" + email;
        requestResponse = this.makeGetRequest(url);
        if (!requestResponse.isSuccess()) { //an error has occured in request
            return requestResponse;
        }
        if (requestResponse.getResponse().size() == 0) {
            requestResponse.setCodeError(404, "api.bgameslibrary.player_not_found");
            return requestResponse;
        }

        //falta revisar que la contra este correcta
        JsonObject user = GSON.fromJson(requestResponse.getResponse().get(0).toString(), JsonObject.class);

        if (user.get("password").getAsString().equals(password)) {
            requestResponse.setCode(200);
        } else {
            requestResponse.setCodeError(401, "api.bgameslibrary.invalid_credentials");
        }
        return requestResponse;
    }


    public int getAttributeIdByName(String attribute_name) {
        String url = API_GET_BASE_URL + "attributes_all";
        ApiResponse requestResponse = this.makeGetRequest(url);
        if (!requestResponse.isSuccess()) { //an error has occured in request
            return -1;
        }
        int id = new JsonUtils().getIdAttributeFromJson(requestResponse.getResponse(),attribute_name);

        return id;
    }

    /*POST API*/
    /**
     * Obtain the user of a player
     * @param player_id Id of player to spent points
     * @param attribute_name name of the attribute to spent
     * @return  @return user json (The response has code, error_description, response)
     */
    public ApiResponse spendAttribute(int player_id,String attribute_name,int amount_spent ) {

        ApiResponse requestResponse=new ApiResponse();
        String url_spend = API_POST_BASE_URL + "spend_attribute/";
        ApiResponse data= getPlayerAttributesById(player_id);

        int id_attribute= getAttributeIdByName(attribute_name);
        int available_points= new JsonUtils().getDataPlayerAttributeFromJson(data.getResponse(),attribute_name);
        if(available_points-amount_spent<0){
            requestResponse.setCodeError(400,"api.bgameslibrary.not_enough_points");
            return requestResponse;
        }
        List<NameValuePair> payload= new ArrayList<>();
        payload.add(new BasicNameValuePair("id_player", String.valueOf(player_id) ));
        payload.add(new BasicNameValuePair("id_videogame", String.valueOf(MINECRAFT_BGAMES_ID)) );
        payload.add(new BasicNameValuePair("id_attributes", String.valueOf(id_attribute) ));
        payload.add(new BasicNameValuePair("new_data", String.valueOf(amount_spent) ));

        requestResponse = this.makepostRequest(url_spend, payload);
        if (!requestResponse.isSuccess()) { //an error has occured in request
            return requestResponse;
        }
        return requestResponse;
    }

    /*UTILS FOR APIS*/
    /**
     * make a get request to an url
     * @param url String with full url to make a request
     * @return  the response of the request in case its possible to make it (The response has code, error_description, response)
     */
    public ApiResponse makeGetRequest(String url){
        ApiResponse requestResponse = new ApiResponse();//manage the mod response
        ArrayList<Object> jsonResponseArray = new ArrayList<>(); //manage the http response
        HttpEntity entity=null;

        try{ //executes the request with 10s to get the responses, else gives an excepción for timeout
            HttpGet request = new HttpGet(url);
            request.setConfig(this.requestConfig);
            long startTime = System.currentTimeMillis();
            HttpResponse response = CLIENT.execute(request);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            CSVwriter.updateCSV("data-get-request-time.csv","get", Long.toString(elapsedTime));
            requestResponse.setCode(response.getStatusLine().getStatusCode());
            entity = response.getEntity();

        }
        catch(Exception e){
            CSVwriter.updateCSV("data-get-request-time.csv","get", "ERROR");
            System.out.println(e);

            requestResponse.setCodeError(503,"api.bgameslibrary.api_unavailable"); //server error
            return requestResponse;
        }

        if (entity == null) {
            requestResponse.setCodeError(500, "api.bgameslibrary.content_error"); //player id not found
            return requestResponse;
        }
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
            JsonElement element = GSON.fromJson(reader, JsonElement.class);
            if(element!=null) {
                if (!element.isJsonArray()) {
                    Object object = new Gson().fromJson(element, Object.class);
                    jsonResponseArray.add(object);
                } else {
                    JsonArray jsonArray = element.getAsJsonArray();
                    for (JsonElement jsonElement : jsonArray) {
                        Object object = new Gson().fromJson(jsonElement, Object.class);
                        jsonResponseArray.add(object);
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            requestResponse.setCodeError(500,"api.bgameslibrary.content_error"); //server error
            return requestResponse;
        }
        requestResponse.setResponse(jsonResponseArray);
        return  requestResponse;

    }
    /**
     * make a post request to an url
     * @param url String with full url to make a request
     * @param payload the "json" to de request
     * @return  the response of the request in case its possible to make it (The response has code, error_description, response)
     */
    public ApiResponse makepostRequest(String url, List<NameValuePair> payload){
        ArrayList<Object> jsonResponseArray=new ArrayList<>();
        ApiResponse requestResponse = new ApiResponse();//manage the mod response

        HttpEntity entity=null;

        try{ //executes the request with 10s to get the responses, else gives an excepción for timeout
            HttpPost request = new HttpPost(url);
            request.setEntity(new UrlEncodedFormEntity(payload));
            request.setConfig(this.requestConfig);
            long startTime = System.currentTimeMillis();
            HttpResponse response = CLIENT.execute(request);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            CSVwriter.updateCSV("data-post-request-time.csv","post", Long.toString(elapsedTime));
            requestResponse.setCode(response.getStatusLine().getStatusCode());
            entity = response.getEntity();
        }
        catch(Exception e){
            CSVwriter.updateCSV("data-get-request-time.csv","get", "ERROR");
            requestResponse.setCodeError(503,"api.bgameslibrary.api_unavailable"); //server error
            return requestResponse;
        }

        if (entity == null) {
            requestResponse.setCodeError(500, "api.bgameslibrary.request_error"); //player id not found
            return requestResponse;
        }
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
            JsonElement element = GSON.fromJson(reader, JsonElement.class);
            if(element!=null) {
                if (!element.isJsonArray()) {
                    Object object = new Gson().fromJson(element, Object.class);
                    jsonResponseArray.add(object);
                } else {
                    JsonArray jsonArray = element.getAsJsonArray();
                    for (JsonElement jsonElement : jsonArray) {
                        Object object = new Gson().fromJson(jsonElement, Object.class);
                        jsonResponseArray.add(object);
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            requestResponse.setCodeError(500,"api.bgameslibrary.content_error"); //server error
            return requestResponse;
        }
        requestResponse.setResponse(jsonResponseArray);
        return  requestResponse;

    }
    public static void printConfigs(){
        System.out.println("==========BGAMES LIBRARY==========");
        System.out.println("=============CONFIGS=============");
        System.out.println("URL: " + API_GET_BASE_URL);
        System.out.println("USER PORT:"+API_USER_BASE_URL);
        System.out.println("GET PORT:"+API_GET_BASE_URL);
        System.out.println("POST PORT:"+API_POST_BASE_URL);
        System.out.println("==========BGAMES LIBRARY==========");
    }

}
