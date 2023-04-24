package net.gsimken.bgameslibrary.core.api_config;


import com.google.gson.Gson;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class BGamesApiConfigsModel {

    public static Integer GET_PORT=3001;
    public static Integer POST_PORT=3002;
    public static Integer USER_PORT=3010;
    public static String URL="http://localhost:";

    public BGamesApiConfigsModel(){
        Gson gson = new Gson();
        String route=FabricLoader.getInstance().getGameDir().toFile().getPath() + "/config/bGames-config.json";
        try (FileReader reader = new FileReader(route)) {
            BGamesApiConfigsModel serverConfig = gson.fromJson(reader, BGamesApiConfigsModel.class);
            System.out.println("Configs load successfully");
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating defaults");

            try {
                FileWriter writter = new FileWriter(route);
                writter.write("{\"URL\":\"http://localhost:\", \"GET_PORT\":\"3001\", \"POST_PORT\":\"3002\", \"USER_PORT\":\"3010\"}");
                writter.close();
                System.out.println("Configs created successfully");
            } catch (IOException ex) {
                System.out.println("Error creating the file, using defaults");
            }
        } catch (IOException e) {
            System.out.println("Wrong text in files");
        }

    }
//


}