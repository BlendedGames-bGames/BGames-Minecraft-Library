package net.gsimken.bgameslibrary.api.configs;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class BGamesApiConfigsModel {

    public static Integer GET_PORT=3001;
    public static Integer POST_PORT=3002;
    public static Integer USER_PORT=3010;
    public static String URL="http://localhost:";
    public static Boolean created=false;

    public void loadConfigs(){

        String route=FabricLoader.getInstance().getGameDir().toFile().getPath() + "/config/bGames-config.json";
        try (FileReader reader = new FileReader(route)) {
            GsonBuilder gsonBuilder  = new GsonBuilder();

            BufferedReader bufferedReader = new BufferedReader(reader);
            String todoEnUnaLinea = "";
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                todoEnUnaLinea += linea;
            }
            bufferedReader.close();
            gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
            Gson gson = gsonBuilder.create();
            BGamesApiConfigsModel serverConfig = gson.fromJson(todoEnUnaLinea, BGamesApiConfigsModel.class);
            System.out.println("Configs load successfully");
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating defaults");

            try {
                FileWriter writter = new FileWriter(route);
                writter.write("{\n\"URL\":\"http://localhost:\",\n\"GET_PORT\":3001,\n\"POST_PORT\":3002,\n\"USER_PORT\":3010\n}");
                writter.close();
                System.out.println("Configs created successfully");
            } catch (IOException ex) {
                System.out.println("Error creating the file, using defaults");
            }
        } catch (IOException e) {
            System.out.println("Wrong text in files");
        }
    }
    public void printConfigs(){
        System.out.println("==========BGAMES LIBRARY==========");
        System.out.println("=============CONFIGS=============");
        System.out.println("URL: " + URL);
        System.out.println("USER PORT:"+USER_PORT);
        System.out.println("GET PORT:"+GET_PORT);
        System.out.println("POST PORT:"+POST_PORT);
        System.out.println("==========BGAMES LIBRARY==========");
    }
//
    public String getFullGetPath(){
        return this.URL+this.GET_PORT+"/";
    }
    public String getFullPostPath(){
        return this.URL+this.POST_PORT+"/";
    }
    public String getFullUserPath(){
        return this.URL+this.USER_PORT+"/";
    }
    public static Integer getGetPort() {
        return GET_PORT;
    }

    public static void setGetPort(Integer getPort) {
        GET_PORT = getPort;
    }

    public static Integer getPostPort() {
        return POST_PORT;
    }

    public static void setPostPort(Integer postPort) {
        POST_PORT = postPort;
    }

    public static Integer getUserPort() {
        return USER_PORT;
    }

    public static void setUserPort(Integer userPort) {
        USER_PORT = userPort;
    }

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        BGamesApiConfigsModel.URL = URL;
    }

    public static Boolean getCreated() {
        return created;
    }

    public static void setCreated(Boolean created) {
        BGamesApiConfigsModel.created = created;
    }
}