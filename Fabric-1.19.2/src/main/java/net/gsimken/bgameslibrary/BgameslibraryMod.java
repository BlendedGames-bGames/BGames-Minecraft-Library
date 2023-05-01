/*
 *	MCreator note:
 *
 *	If you lock base mod element files, you can edit this file and the proxy files
 *	and they won't get overwritten. If you change your mod package or modid, you
 *	need to apply these changes to this file MANUALLY.
 *
 *
 *	If you do not lock base mod element files in Workspace settings, this file
 *	will be REGENERATED on each build.
 *
 */
package net.gsimken.bgameslibrary;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraft.nbt.CompoundTag;

import net.gsimken.bgameslibrary.init.BgameslibraryModProcedures;
import net.gsimken.bgameslibrary.init.BgameslibraryModMenus;
import net.gsimken.bgameslibrary.init.BgameslibraryModKeyMappings;
import net.gsimken.bgameslibrary.init.BgameslibraryModCommands;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.api.ModInitializer;
import net.gsimken.bgameslibrary.core.api_config.BGamesApiConfigsModel;

public class BgameslibraryMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "bgameslibrary";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing BgameslibraryMod");
		BGamesApiConfigsModel init = new BGamesApiConfigsModel(); // load the config
		init.loadConfigs();
		init.printConfigs();
		BgameslibraryModProcedures.load();
		BgameslibraryModCommands.load();

		BgameslibraryModMenus.load();
		BgameslibraryModKeyMappings.serverLoad();

		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			if (handler.getPlayer().getExtraCustomData().getCompound("PlayerPersisted").isEmpty()) {
				handler.getPlayer().getExtraCustomData().put("PlayerPersisted", new CompoundTag());
			}
			BgameslibraryMod.LOGGER.info(handler.getPlayer().getExtraCustomData());
		});
	}
}
