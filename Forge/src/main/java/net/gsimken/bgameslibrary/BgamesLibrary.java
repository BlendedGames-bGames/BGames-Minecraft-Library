package net.gsimken.bgameslibrary;

import com.mojang.logging.LogUtils;
import net.gsimken.bgameslibrary.client.gui.DisplayAttributesScreen;
import net.gsimken.bgameslibrary.client.gui.LoginScreen;
import net.gsimken.bgameslibrary.configs.BGamesCommonConfigs;
import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;
import net.gsimken.bgameslibrary.client.BGamesLibraryModMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BgamesLibrary.MOD_ID)
public class BgamesLibrary
{
    public static String bgames_afective_name = "Afectivo";
    public static String bgames_social_name = "Social";
    public static String bgames_physical_name = "Fisica";
    public static String bgames_cognitive_name = "Cognitivo";
    public static String bgames_linguistic_name = "Linguistico";
    public static final String MOD_ID = "bgameslibrary";
    public static final Logger LOGGER = LogUtils.getLogger();
    public BgamesLibrary()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        eventBus.addListener(this::commonSetup);
        BGamesLibraryModMenus.register(eventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        //load configs

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BGamesCommonConfigs.SPEC,"bgames-common-application.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, BGamesCommonConfigs.SPEC,"bgames-client-application.toml");


    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        BGamesLibraryModMessages.register();
    }

     // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(BGamesLibraryModMenus.LOGIN.get(), LoginScreen::new);
            MenuScreens.register(BGamesLibraryModMenus.DISPLAY_ATTRIBUTES.get(), DisplayAttributesScreen::new);


        }
    }
}
