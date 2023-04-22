package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import net.gsimken.bgameslibrary.network.BgamesLibraryModVariables;
import net.gsimken.bgameslibrary.core.api.BGamesApi;
import net.gsimken.bgameslibrary.core.api.ApiResponse;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class SpentAttributeProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		String attribute_name = "";
		double id_player = 0;
		double ammount = 0;
		attribute_name = StringArgumentType.getString(arguments, "attribute_name");
		ammount = DoubleArgumentType.getDouble(arguments, "point_spent");
		id_player = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_player_id;
		if (VerifyLoggedInProcedure.execute(entity)) {
			ApiResponse data = new BGamesApi().spendAttribute((int) id_player, attribute_name, (int) ammount);
			if (data.getCode() != 200) {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(Component.literal("§4Error: " + data.getErrorDescription()), (false));
			} else {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(Component.literal("§aSuccess"), (false));
			}
		} else {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("§4Error: not logged"), (false));
		}
	}
}
