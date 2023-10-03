package net.gsimken.bgameslibrary.client;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.client.gui.DisplayAttributesScreen;
import net.gsimken.bgameslibrary.client.gui.LoginScreen;
import net.gsimken.bgameslibrary.client.menus.DisplayAttributesMenu;
import net.gsimken.bgameslibrary.client.menus.LoginMenu;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class BGamesLibraryModScreens {
    public static ScreenHandlerType<DisplayAttributesMenu> BGAMES_DISPLAY_ATTRIBUTES;
    public static ScreenHandlerType<LoginMenu> LOGIN;

    public static void registerMenus() {
        BGAMES_DISPLAY_ATTRIBUTES = Registry.register(Registries.SCREEN_HANDLER, new Identifier(BgamesLibrary.MOD_ID,
                "bgames_display_attributes"), new ExtendedScreenHandlerType<>(DisplayAttributesMenu::new));

        LOGIN = Registry.register(Registries.SCREEN_HANDLER, new Identifier(BgamesLibrary.MOD_ID, "bgames_login"), new ExtendedScreenHandlerType<>(LoginMenu::new));
    }

    public static void registerScreens() {
        HandledScreens.register(BGAMES_DISPLAY_ATTRIBUTES, DisplayAttributesScreen::new);
        HandledScreens.register(LOGIN, LoginScreen::new);
    }
}
