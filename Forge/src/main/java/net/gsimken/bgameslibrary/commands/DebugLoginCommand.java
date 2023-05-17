
package net.gsimken.bgameslibrary.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerDataProvider;
import net.gsimken.bgameslibrary.networking.ModMessages;
import net.gsimken.bgameslibrary.networking.packet.BGamesPlayerDataSyncS2CPacket;
import net.gsimken.bgameslibrary.utils.PlayerUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;


public class DebugLoginCommand {

	public DebugLoginCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("debug_login")
						.then(Commands.argument("email", StringArgumentType.string()).
								then(Commands.argument("password", StringArgumentType.string())
										.executes(command -> {
											String email=StringArgumentType.getString(command,"email");
											String password=StringArgumentType.getString(command,"password");

												return loginPlayer(command.getSource(),email,password);
													}))));

	}

	public static int loginPlayer(CommandSourceStack source,String email, String password) throws CommandSyntaxException {
		ServerPlayer player = source.getPlayerOrException();
		int playerId= new PlayerUtils().GetIdByEmail(email, password);
		if(playerId==-1){
			player.sendSystemMessage(Component.translatable(  "api.bgameslibrary.player_not_found").withStyle(ChatFormatting.RED));
			return -1;
		}
		player.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
			data.id=playerId;
			data.setEmail(email);
			data.setPassword(password);
			data.attributeRefresh();
			player.sendSystemMessage(Component.translatable(  "login.bgameslibrary.logged").withStyle(ChatFormatting.GREEN));
			ModMessages.sendToPlayer(new BGamesPlayerDataSyncS2CPacket(data.getId(),
					data.getSocialPoints(), data.getPhysicalPoints(), data.getLinguisticPoints(),
					data.getAffectivePoints(), data.getCognitivePoints(),
					data.getEmail(), data.getPassword()), player);
		});
		return 1;

	}
}
