
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.gsimken.bgameslibrary.init;

import net.gsimken.bgameslibrary.command.ShowPersistentDataCommand;
import net.gsimken.bgameslibrary.command.ShowAttributeGuiCommand;
import net.gsimken.bgameslibrary.command.OpenLoginCommand;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class BgameslibraryModCommands {
	public static void load() {
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, dedicated) -> {
			OpenLoginCommand.register(dispatcher, commandBuildContext);
			ShowPersistentDataCommand.register(dispatcher, commandBuildContext);
			ShowAttributeGuiCommand.register(dispatcher, commandBuildContext);
		});
	}
}
