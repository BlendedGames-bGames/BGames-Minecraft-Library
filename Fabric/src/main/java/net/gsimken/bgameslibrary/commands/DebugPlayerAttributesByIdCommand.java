
package net.gsimken.bgameslibrary.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.gsimken.bgameslibrary.api.ApiResponse;
import net.gsimken.bgameslibrary.api.BGamesApi;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


public class DebugPlayerAttributesByIdCommand {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {

		dispatcher.register(CommandManager.literal("debug_attributes_by_id")

				.then(CommandManager.argument("id_player", IntegerArgumentType.integer()).executes(command -> {
					int id_player= IntegerArgumentType.getInteger(command, "id_player");
					return getAttributes(command.getSource(), id_player);
				})));
	}
	public static int getAttributes(ServerCommandSource source,int id_player) throws CommandSyntaxException {
		ServerPlayerEntity player = source.getPlayerOrThrow();
		BGamesApi user_api = new BGamesApi();
		ApiResponse message = user_api.getPlayerAttributesById(id_player);
		if (message.isSuccess()) {
			player.sendMessage(Text.translatable(  "debug_commands.bgameslibrary.attributes").fillStyle(Style.EMPTY.withColor(Formatting.GREEN)));
				return 1;
		} else {
			player.sendMessage(Text.translatable( message.getErrorDescription()).fillStyle(Style.EMPTY.withColor(Formatting.GREEN)));
			return -1;
		}
	}
}
