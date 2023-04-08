package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import net.gsimken.bgameslibrary.core.api.ApiResponse;
import net.gsimken.bgameslibrary.core.api.ApiGet;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class GetAttributeIdProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		double id_player = 0;
		ApiGet user_api = new ApiGet();
		id_player = DoubleArgumentType.getDouble(arguments, "id_player");
		ApiResponse message = user_api.getPlayerAttributesById((int) id_player);
		if (message.getCode() == 200) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("id player logged: " + message.getResponse().toString()), (false));
		} else {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("§4Error: " + message.getErrorDescription()), (false));
		}
	}
}
