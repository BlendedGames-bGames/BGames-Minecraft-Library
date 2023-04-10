package net.gsimken.bgameslibrary.core.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Utils {
    public String removeQuotes(String string){
        if (string != null && string.length() >= 2
                && string.charAt(0) == '\"' && string.charAt(string.length() - 1) == '\"') {
            string = string.substring(1, string.length() - 1);
        }
        return string;
    }
    public int getDataAttributeFromJson(ArrayList<Object> attributes, String attribute_name){
        attribute_name=removeQuotes(attribute_name).toLowerCase();

        String attribute_name_i="";
        for(int i=0; i<attributes.size();i++){
            JsonObject attribute_data= new Gson().fromJson( attributes.get(i).toString(), JsonObject.class);
            /*nomalized, because in methods non ascii characther is missing*/
            attribute_name_i=attribute_data.get("name").getAsString();
            attribute_name_i = Normalizer.normalize(attribute_name_i, Normalizer.Form.NFKD);
            attribute_name_i = attribute_name_i.replaceAll("\\p{M}", "");
            attribute_name_i=attribute_name_i.toLowerCase();

            System.out.println(attribute_data.get("name").getAsString()+":"+attribute_name_i+":"+attribute_name+":"+attribute_name_i.equals(attribute_name));
            if(attribute_name_i.equals(attribute_name)){
                return attribute_data.get("data").getAsInt();
            }
        }
        return -1;
    }
}
