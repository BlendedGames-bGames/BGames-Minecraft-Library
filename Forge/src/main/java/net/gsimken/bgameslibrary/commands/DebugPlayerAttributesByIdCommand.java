
package net.gsimken.bgameslibrary.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.gsimken.bgameslibrary.api.ApiResponse;
import net.gsimken.bgameslibrary.api.BGamesApi;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;


public class DebugPlayerAttributesByIdCommand {

	public DebugPlayerAttributesByIdCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("debug_attributes_by_id")

				.then(Commands.argument("id_player", IntegerArgumentType.integer()).executes(command -> {
					int id_player= IntegerArgumentType.getInteger(command, "id_player");
					return getAttributes(command.getSource(), id_player);
				})));
	}
	public static int getAttributes(CommandSourceStack source,int id_player) throws CommandSyntaxException {
		ServerPlayer player = source.getPlayerOrException();
		BGamesApi user_api = new BGamesApi();
		ApiResponse message = user_api.getPlayerAttributesById(id_player);
		if (message.isSuccess()) {
				player.sendSystemMessage(Component.translatable(  "debug_commands.bgameslibrary.attributes",Component.literal(message.getResponse().toString())).withStyle(ChatFormatting.GREEN));
				return -1;
		} else {
			player.sendSystemMessage(Component.translatable(message.getErrorDescription()).withStyle(ChatFormatting.RED));
			return 1;
		}
	}
}
