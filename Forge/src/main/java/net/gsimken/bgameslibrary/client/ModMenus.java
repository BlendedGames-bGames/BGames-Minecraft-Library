package net.gsimken.bgameslibrary.client;

import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.client.menus.DisplayAttributesMenu;
import net.gsimken.bgameslibrary.client.menus.LoginMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, BgamesLibrary.MOD_ID);
//    public static final RegistryObject<MenuType<BGamesDisplayAttributesMenu>> B_GAMES_DISPLAY_ATTRIBUTES = REGISTRY.register("b_games_display_attributes", () -> IForgeMenuType.create(BGamesDisplayAttributesMenu::new));
//public static final RegistryObject<MenuType<DisplayAttributesMenu>> DISPLAY_ATTRIBUTES = REGISTRY.register("display_attributes", () -> IForgeMenuType.create(DisplayAttributesMenu::new));
public static final RegistryObject<MenuType<LoginMenu>> LOGIN = registerMenuType(LoginMenu::new,"login_menu");
public static final RegistryObject<MenuType<DisplayAttributesMenu>> DISPLAY_ATTRIBUTES = registerMenuType(DisplayAttributesMenu::new,"display_attributes_menu");


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
