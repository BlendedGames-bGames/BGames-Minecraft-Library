package net.gsimken.bgameslibrary.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class BgamesCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<Integer> GET_PORT;

    public static final ForgeConfigSpec.ConfigValue<Integer> POST_PORT;
    public static final ForgeConfigSpec.ConfigValue<String> URL;


    static {
        BUILDER.push("Config Bgames API");
        URL= BUILDER.comment("This is the url to make the request. Default: http://localhost:").define("API URL","http://localhost:");
        GET_PORT= BUILDER.comment("This is the port to make get requets. Default: 3001").define("API GET PORT",3001);
        POST_PORT= BUILDER.comment("This is the port to make post requets. Default: 3002").define("API POST PORT",3002);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
