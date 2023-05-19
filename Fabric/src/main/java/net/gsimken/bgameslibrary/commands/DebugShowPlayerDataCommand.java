
package net.gsimken.bgameslibrary.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


public class DebugShowPlayerDataCommand {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {

		dispatcher.register(CommandManager.literal("debug_show_data")
				.executes(command -> {
					return showAttributes(command.getSource());
				}));
	}

	public static int showAttributes(ServerCommandSource source) throws CommandSyntaxException {
		ServerPlayerEntity player = source.getPlayerOrThrow();
		player.sendMessage(Text.literal(BGamesPlayerData.stringify((IBGamesDataSaver) player)).fillStyle(Style.EMPTY.withColor(Formatting.GREEN)));
		return 1;

	}
}
