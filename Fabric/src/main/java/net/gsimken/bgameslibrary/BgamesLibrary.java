package net.gsimken.bgameslibrary;

import net.fabricmc.api.ModInitializer;
import net.gsimken.bgameslibrary.api.configs.BGamesApiConfigsModel;
import net.gsimken.bgameslibrary.client.ModScreens;
import net.gsimken.bgameslibrary.commands.ModCommands;
import net.gsimken.bgameslibrary.event.ModEvents;
import net.gsimken.bgameslibrary.networking.ModMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BgamesLibrary implements ModInitializer {
	public static String bgames_afective_name = "Afectivo";
	public static String bgames_social_name = "Social";
	public static String bgames_physical_name = "Fisica";
	public static String bgames_cognitive_name = "Cognitivo";
	public static String bgames_linguistic_name = "Linguistico";
	public static final String MOD_ID = "bgameslibrary";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		BGamesApiConfigsModel init = new BGamesApiConfigsModel(); // load the config
		init.loadConfigs();
		init.printConfigs();

		ModMessages.registerC2SPackets();
		ModScreens.registerMenus();
		ModCommands.register();
		ModEvents.register();

	}


}
