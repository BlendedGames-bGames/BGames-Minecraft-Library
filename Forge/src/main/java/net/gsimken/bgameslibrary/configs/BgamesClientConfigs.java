package net.gsimken.bgameslibrary.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class BgamesClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;


    static {
        BUILDER.push("Config Bgames");
        BUILDER.comment("This file needs to be empty for now");
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
