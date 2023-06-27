package net.gsimken.bgameslibrary;

import net.fabricmc.api.ClientModInitializer;
import net.gsimken.bgameslibrary.client.BGamesLibraryModScreens;
import net.gsimken.bgameslibrary.event.BGamesLibraryModEvents;
import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;

public class BgamesLibraryClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BGamesLibraryModMessages.registerS2CPackets();
        BGamesLibraryModScreens.registerScreens();
        BGamesLibraryModEvents.registerClient();

    }
}
