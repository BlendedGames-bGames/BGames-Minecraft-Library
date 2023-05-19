package net.gsimken.bgameslibrary.api.schemes;

import com.google.gson.annotations.SerializedName;

public class BGamesSchemePlayer {
    @SerializedName("id_players")
    public Integer idPlayer;
    public String name;
    private String password;
    public String email;

}
