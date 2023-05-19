package net.gsimken.bgameslibrary.bgames;

import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.utils.JsonUtils;
import net.gsimken.bgameslibrary.utils.PlayerUtils;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;

public class BGamesPlayerData {
    /*
    Server class data
    */
    public int id = -1;
    public int socialPoints = -1;
    public int physicalPoints = 1;
    public int linguisticPoints = -1;
    public int affectivePoints = -1;
    public int cognitivePoints = -1;
    private String email = "";
    private String password = "";


    public void copyFrom(BGamesPlayerData source){
        this.id = source.id;
        this.socialPoints = source.socialPoints;
        this.physicalPoints = source.physicalPoints;
        this.linguisticPoints = source.linguisticPoints;
        this.affectivePoints = source.affectivePoints;
        this.cognitivePoints = source.cognitivePoints;
        this.setEmail(source.getEmail());
        this.setPassword(source.getPassword());
    }
    public void savePersistentData(CompoundTag nbt){
        nbt.putInt("bgames_id",id) ;
        nbt.putInt("bgames_social_points",socialPoints) ;
        nbt.putInt("bgames_physical_points",physicalPoints);
        nbt.putInt("bgames_linguistic_points",linguisticPoints) ;
        nbt.putInt("bgames_affective_points",affectivePoints) ;
        nbt.putInt("bgames_cognitive_points",cognitivePoints) ;
        nbt.putString("bgames_email", getEmail());
        nbt.putString("bgames_password", getPassword());
    }
    public void loadPersistentData(CompoundTag nbt){
        id = nbt.getInt("bgames_id") ;
        socialPoints  = nbt.getInt("bgames_social_points") ;
        physicalPoints= nbt.getInt("bgames_physical_points");
        linguisticPoints  = nbt.getInt("bgames_linguistic_points") ;
        affectivePoints = nbt.getInt("bgames_affective_points") ;
        cognitivePoints = nbt.getInt("bgames_cognitive_points") ;
         setEmail(nbt.getString("bgames_email"));
         setPassword(nbt.getString("bgames_password"));
    }
    public void attributeReset(){
        socialPoints = -1;
        physicalPoints = 1;
        linguisticPoints = -1;
        affectivePoints = -1;
        cognitivePoints = -1;

    }
    public void attributeRefresh(){
        ArrayList<Object> attributes= new PlayerUtils().GetAttributesById(id);
        JsonUtils comparator= new JsonUtils();
        affectivePoints = comparator.getDataPlayerAttributeFromJson(attributes, BgamesLibrary.bgames_afective_name);
        socialPoints = comparator.getDataPlayerAttributeFromJson(attributes, BgamesLibrary.bgames_social_name);
        physicalPoints = comparator.getDataPlayerAttributeFromJson(attributes, BgamesLibrary.bgames_physical_name);
        linguisticPoints = comparator.getDataPlayerAttributeFromJson(attributes, BgamesLibrary.bgames_linguistic_name);
        cognitivePoints = comparator.getDataPlayerAttributeFromJson(attributes, BgamesLibrary.bgames_cognitive_name);
    }


    public String stringify(){
        String data= "" +
                "===============\n" +
                "id: " + id+"\n"+
                "email: "+email +"\n"+
                "password" + password + "\n" +
                "social: "+ socialPoints+"\n"+
                "físico: "+physicalPoints+"\n"+
                "linguistico: "+linguisticPoints+"\n"+
                "afectivo: "+affectivePoints+"\n"+
                "coginitivo: "+cognitivePoints+"\n"+
                "===============\n";
        return data;
    }

    public boolean isLoggedIn() {;
        if (this.getId()!=-1) {
            return true;
        }
        return false;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public int getSocialPoints() {
        return socialPoints;
    }

    public int getPhysicalPoints() {
        return physicalPoints;
    }

    public int getLinguisticPoints() {
        return linguisticPoints;
    }

    public int getAffectivePoints() {
        return affectivePoints;
    }

    public int getCognitivePoints() {
        return cognitivePoints;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


}
