package net.gsimken.bgameslibrary.core.classes;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BGamesAttribute {
    @SerializedName("id_attributes")
    public Integer idAttribute;
    public String name;
    public String description;
    @SerializedName("data_type")
    public String dataType;
    @SerializedName("initiated_date")
    public Date initiatedDate;
    @SerializedName("last_modified")
    public Date lastModified;
}
