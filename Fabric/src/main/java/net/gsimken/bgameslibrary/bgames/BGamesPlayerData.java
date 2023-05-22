package net.gsimken.bgameslibrary.bgames;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.gsimken.bgameslibrary.utils.JsonUtils;
import net.gsimken.bgameslibrary.utils.PlayerUtils;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;

public class BGamesPlayerData {

    public static void syncData(ServerPlayerEntity player){
        //ONLY USE IN SERVER CONTEXT
        NbtCompound nbt = ((IBGamesDataSaver) player).getPersistentData();
        PacketByteBuf buf = PacketByteBufs.create();
        int id = nbt.getInt("id");
        int socialPoints = nbt.getInt("social_points");
        int physicalPoints = nbt.getInt("physical_points");
        int linguisticPoints = nbt.getInt("linguistic_points");
        int affectivePoints = nbt.getInt("affective_points");
        int cognitivePoints = nbt.getInt("cognitive_points");
        String email = nbt.getString("email");
        String password = nbt.getString("password");
        buf.writeInt(id) ;
        buf.writeInt(socialPoints) ;
        buf.writeInt(physicalPoints);
        buf.writeInt(linguisticPoints) ;
        buf.writeInt(affectivePoints) ;
        buf.writeInt(cognitivePoints) ;
        buf.writeString(email);
        buf.writeString(password);
        ServerPlayNetworking.send(player, BGamesLibraryModMessages.BGAMES_SERVER_DATA_SYNC_ID,buf);
    }
    public static void setDefaultValues(IBGamesDataSaver player){
        NbtCompound nbt = player.getPersistentData();
        nbt.putInt("id",-1) ;
        nbt.putInt("social_points",-1) ;
        nbt.putInt("physical_points",-1);
        nbt.putInt("linguistic_points",-1) ;
        nbt.putInt("affective_points",-1) ;
        nbt.putInt("cognitive_points",-1) ;
        nbt.putString("email",""); ;
        nbt.putString("password","") ;
    }

    public static void copyFrom(IBGamesDataSaver newPlayer,IBGamesDataSaver oldPlayer){
        NbtCompound oldNbt = oldPlayer.getPersistentData();
        NbtCompound newNbt = newPlayer.getPersistentData();
        int id = oldNbt.getInt("id");
        int socialPoints = oldNbt.getInt("social_points");
        int physicalPoints = oldNbt.getInt("physical_points");
        int linguisticPoints = oldNbt.getInt("linguistic_points");
        int affectivePoints = oldNbt.getInt("affective_points");
        int cognitivePoints = oldNbt.getInt("cognitive_points");
        String email = oldNbt.getString("email");
        String password = oldNbt.getString("password");

        newNbt.putInt("id",id) ;
        newNbt.putInt("social_points",socialPoints) ;
        newNbt.putInt("physical_points",physicalPoints);
        newNbt.putInt("linguistic_points",linguisticPoints) ;
        newNbt.putInt("affective_points",affectivePoints) ;
        newNbt.putInt("cognitive_points",cognitivePoints) ;
        newNbt.putString("email",email);
        newNbt.putString("password",password);
    }
    public static void attributeReset(IBGamesDataSaver player){
        NbtCompound nbt = player.getPersistentData();

        nbt.putInt("social_points",-1) ;
        nbt.putInt("physical_points",-1);
        nbt.putInt("linguistic_points",-1) ;
        nbt.putInt("affective_points",-1) ;
        nbt.putInt("cognitive_points",-1) ;
    }

    public static void attributeRefresh(IBGamesDataSaver player){
        NbtCompound nbt = player.getPersistentData();
        int id = getId(player);

        ArrayList<Object> attributes= new PlayerUtils().GetAttributesById(id);
        JsonUtils comparator= new JsonUtils();
        int affectivePoints = comparator.getDataPlayerAttributeFromJson(attributes, BgamesLibrary.bgames_afective_name);
        int socialPoints = comparator.getDataPlayerAttributeFromJson(attributes, BgamesLibrary.bgames_social_name);
        int physicalPoints = comparator.getDataPlayerAttributeFromJson(attributes, BgamesLibrary.bgames_physical_name);
        int linguisticPoints = comparator.getDataPlayerAttributeFromJson(attributes, BgamesLibrary.bgames_linguistic_name);
        int cognitivePoints = comparator.getDataPlayerAttributeFromJson(attributes, BgamesLibrary.bgames_cognitive_name);
        nbt.putInt("social_points",socialPoints) ;
        nbt.putInt("physical_points",physicalPoints);
        nbt.putInt("linguistic_points",linguisticPoints) ;
        nbt.putInt("affective_points",affectivePoints) ;
        nbt.putInt("cognitive_points",cognitivePoints) ;
    }


    public static String stringify(IBGamesDataSaver player){
        String data= "" +
                "===============\n" +
                "id: " + getId(player)+"\n"+
                "email: "+getEmail(player) +"\n"+
                "password" + getPassword(player) + "\n" +
                "social: "+ getSocialPoints(player)+"\n"+
                "físico: "+getPhysicalPoints(player)+"\n"+
                "linguistico: "+getLinguisticPoints(player)+"\n"+
                "afectivo: "+getAffectivePoints(player)+"\n"+
                "coginitivo: "+getCognitivePoints(player)+"\n"+
                "===============\n";
        return data;
    }

    public static boolean isLoggedIn(IBGamesDataSaver player) {;

        if (getId(player)!=-1) {
            return true;
        }
        return false;
    }
    public static void setIdEmailPassword(IBGamesDataSaver player,int id, String email,String password) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putString("email", email);
        nbt.putString("password", password);
        nbt.putInt("id", id);
    }
    public static void setEmail(IBGamesDataSaver player,String email) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putString("email", email);
    }
    public static void setId(IBGamesDataSaver player,int id) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putInt("id", id);
    }
    public static void setPassword(IBGamesDataSaver player,String password) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putString("password", password);
    }

    public static int getId(IBGamesDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        int id = nbt.getInt("id");
        return id;
    }

    public static  int getSocialPoints(IBGamesDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        int socialPoints = nbt.getInt("social_points");
        return socialPoints;
    }

    public static int getPhysicalPoints(IBGamesDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        int physicalPoints = nbt.getInt("physical_points");
        return physicalPoints;
    }

    public static int getLinguisticPoints(IBGamesDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        int linguisticPoints = nbt.getInt("linguistic_points");
        return linguisticPoints;
    }

    public static int getAffectivePoints(IBGamesDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        int affectivePoints = nbt.getInt("affective_points");
        return affectivePoints;
    }

    public static int getCognitivePoints(IBGamesDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        int cognitivePoints = nbt.getInt("cognitive_points");
        return cognitivePoints;
    }

    public static String getEmail(IBGamesDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        String email = nbt.getString("email");
        return email;
    }

    public static String getPassword(IBGamesDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        String password = nbt.getString("password");
        return password;
    }


}
