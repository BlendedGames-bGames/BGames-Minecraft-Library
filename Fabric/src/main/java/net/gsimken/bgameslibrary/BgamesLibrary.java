package net.gsimken.bgameslibrary;

import net.fabricmc.api.ModInitializer;
import net.gsimken.bgameslibrary.configs.BGamesApiConfigsModel;
import net.gsimken.bgameslibrary.client.BGamesLibraryModScreens;
import net.gsimken.bgameslibrary.commands.BGamesLibraryModCommands;
import net.gsimken.bgameslibrary.event.BGamesLibraryModEvents;
import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BgamesLibrary implements ModInitializer {
	public static String bgames_afective_name = "Afectivo";
	public static String bgames_social_name = "Social";
	public static String bgames_physical_name = "Fisica";
	public static String bgames_cognitive_name = "Cognitivo";
	public static String bgames_linguistic_name = "Linguistico";
	public static final String MOD_ID = "bgameslibrary";

	@Override
	public void onInitialize() {
		BGamesApiConfigsModel init = new BGamesApiConfigsModel(); // load the config
		init.loadConfigs();
		init.printConfigs();
		BGamesLibraryModMessages.registerC2SPackets();
		BGamesLibraryModScreens.registerMenus();
		BGamesLibraryModCommands.register();
		BGamesLibraryModEvents.registerServer();

	}


}
