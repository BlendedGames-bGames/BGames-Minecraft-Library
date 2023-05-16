
package net.gsimken.bgameslibrary.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.gsimken.bgameslibrary.api.ApiResponse;
import net.gsimken.bgameslibrary.api.BGamesApi;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerDataProvider;
import net.gsimken.bgameslibrary.networking.ModMessages;
import net.gsimken.bgameslibrary.networking.packet.BGamesPlayerDataSyncS2CPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;


public class DebugShowPlayerDataCommand {

	public DebugShowPlayerDataCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("debug_show_data")
				.executes(command -> {
					return showAttributes(command.getSource());
				}));
	}

	public static int showAttributes(CommandSourceStack source) throws CommandSyntaxException {
		ServerPlayer player = source.getPlayerOrException();
		player.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
			data.attributeRefresh();
			player.sendSystemMessage(Component.literal(data.Stringify())  );
			ModMessages.sendToPlayer(new BGamesPlayerDataSyncS2CPacket(data.getId(),
					data.getSocialPoints(), data.getPhysicalPoints(), data.getLinguisticPoints(),
					data.getAffectivePoints(), data.getCognitivePoints(),
					data.getEmail(), data.getPassword()), player);
		});
		return 1;

	}
}
