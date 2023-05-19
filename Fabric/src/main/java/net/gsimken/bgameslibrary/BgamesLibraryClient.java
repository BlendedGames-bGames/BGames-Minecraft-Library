package net.gsimken.bgameslibrary;

import net.fabricmc.api.ClientModInitializer;
import net.gsimken.bgameslibrary.client.ModScreens;
import net.gsimken.bgameslibrary.networking.ModMessages;

public class BgamesLibraryClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        ModScreens.registerScreens();
    }
}
