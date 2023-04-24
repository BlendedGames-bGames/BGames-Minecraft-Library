package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import net.gsimken.bgameslibrary.core.api.BGamesApi;
import net.gsimken.bgameslibrary.core.api.ApiResponse;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

public class GetIdLoginProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		String password = "";
		String email = "";
		BGamesApi user_api = new BGamesApi();
		email = StringArgumentType.getString(arguments, "email");
		password = StringArgumentType.getString(arguments, "password");
		ApiResponse message = user_api.getPlayerByLogin(email, password);
		if (message.getCode() == 200) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("id player logged: " + message.getResponse().toString()), (false));
		} else {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("§4Error: " + message.getErrorDescription()), (false));
		}
	}
}
