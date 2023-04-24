package net.gsimken.bgameslibrary.core.classes;

import com.google.gson.annotations.SerializedName;

public class BGamesPlayer {
    @SerializedName("id_players")
    public Integer idPlayer;
    public String name;
    private String password;
    public String email;
    public Integer age;

}
