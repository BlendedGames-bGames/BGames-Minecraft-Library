package net.gsimken.bgameslibrary.core.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.gsimken.bgameslibrary.core.classes.BGamesPlayerAttribute;
import net.gsimken.bgameslibrary.core.classes.BGamesAttribute;
import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
public class Utils {
    /**
     * A method to remove quotable text
     * @param string text that can or not be in quotes
     * @return  same text without the quotes in extreme
     */
    public String removeQuotes(String string){
        if (string != null && string.length() >= 2
                && string.charAt(0) == '\"' && string.charAt(string.length() - 1) == '\"') {
            string = string.substring(1, string.length() - 1);
        }
        return string;
    }
    /**
     * A method to correct a json string
     * @param json text with some fails Ex: {a=2,b=2020-02-03T03:04:10.444Z}
     * @return  same text in json format Ex: {"a":"2","b":"2020-02-03T03:04:10.444Z"}
     */
    public String stringJsonFormating(String json){
        String[] splitted_json=json.toString().substring(1,json.toString().length()-1).split(",");
        String[] aux={};
        List<String> text_json_string= new ArrayList();
        List<String> json_string=  new ArrayList();

        for (String sub_string : splitted_json) {
            aux=sub_string.split("=");
            text_json_string= new ArrayList<>();
            for (String sub_string2 : aux) {
                sub_string2='\"'+removeQuotes(sub_string2.strip()).strip()+'\"';
                text_json_string.add(sub_string2);
            }
            String key_value=String.join( ":",text_json_string);
            json_string.add(key_value);
        }
        String final_string ="{"+String.join(",",json_string)+"}";
        return final_string;

    }
    /**
     * A method to search data from array of PlayerAttriobute
     * @param attributes json response of player_attributes
     * @param attribute_name name of attribute to search
     * @return  data of the attribute (-1 if doesnt exist)
     */
    public int getDataPlayerAttributeFromJson(ArrayList<Object> attributes, String attribute_name){
        attribute_name=removeQuotes(attribute_name).toLowerCase();

        String attribute_name_i="";
        for(int i=0; i<attributes.size();i++){
            Gson gson= new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").create();
            BGamesPlayerAttribute attribute_data= gson.fromJson( attributes.get(i).toString(), BGamesPlayerAttribute.class);
            /*nomalized, because in methods non ascii characther is missing*/
            attribute_name_i=attribute_data.name;
            attribute_name_i = Normalizer.normalize(attribute_name_i, Normalizer.Form.NFKD);
            attribute_name_i = attribute_name_i.replaceAll("\\p{M}", "");
            attribute_name_i=attribute_name_i.toLowerCase();

            if(attribute_name_i.equals(attribute_name)){
                return attribute_data.data;
            }
        }
        return -1;
    }
    /**
     * A method to search id from array of PlayerAttriobute
     * @param attributes json response of player_attributes
     * @param attribute_name name of attribute to search
     * @return  id of the attribute (-1 if doesnt exist)
     */
    public int getIdAttributeFromJson(ArrayList<Object> attributes, String attribute_name){
        attribute_name=removeQuotes(attribute_name).toLowerCase();

        String attribute_name_i="";

        for(int i=0; i<attributes.size();i++){
            String json= stringJsonFormating(attributes.get(i).toString());


            Gson gson= new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                        DateFormat iso8601DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") {{
                            setTimeZone(TimeZone.getTimeZone("UTC"));
                        }};

                        @Override
                        public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context)
                                throws JsonParseException {
                            try {
                                return iso8601DateFormat.parse(jsonElement.getAsString());
                            } catch (ParseException e) {
                                throw new JsonParseException("Invalid date format: " + jsonElement.getAsString());
                            }
                        }
                    })
                    .create();

            BGamesAttribute attribute_data= gson.fromJson(json , BGamesAttribute.class);
            /*nomalized, because in methods non ascii characther is missing*/
            attribute_name_i=attribute_data.name;
            attribute_name_i = Normalizer.normalize(attribute_name_i, Normalizer.Form.NFKD);
            attribute_name_i = attribute_name_i.replaceAll("\\p{M}", "");
            attribute_name_i=attribute_name_i.toLowerCase();

            if(attribute_name_i.equals(attribute_name)){
                return attribute_data.idAttribute;
            }
        }
        return -1;
    }

}
