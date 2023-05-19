
package net.gsimken.bgameslibrary.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.gsimken.bgameslibrary.utils.PlayerUtils;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


public class DebugLoginCommand {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {

		dispatcher.register(CommandManager.literal("debug_login")
						.then(CommandManager.argument("email", StringArgumentType.string()).
								then(CommandManager.argument("password", StringArgumentType.string())
										.executes(command -> {
											String email=StringArgumentType.getString(command,"email");
											String password=StringArgumentType.getString(command,"password");

												return loginPlayer(command.getSource(),email,password);
													}))));

	}

	private static int loginPlayer(ServerCommandSource source,String email, String password) throws CommandSyntaxException {
		ServerPlayerEntity player = source.getPlayerOrThrow();
		int playerId= new PlayerUtils().GetIdByEmail(email, password);
		IBGamesDataSaver playerDataHandler= (IBGamesDataSaver) player;
		BGamesPlayerData.attributeReset(playerDataHandler);
		if(playerId==-1){
			player.sendMessage(Text.translatable(  "api.bgameslibrary.player_not_found").fillStyle(Style.EMPTY.withColor(Formatting.RED)));
		}
		else {
			BGamesPlayerData.setIdEmailPassword(playerDataHandler,playerId,email,password );
			BGamesPlayerData.attributeRefresh(playerDataHandler);
			player.sendMessage(Text.translatable(  "login.bgameslibrary.logged").fillStyle(Style.EMPTY.withColor(Formatting.GREEN)));
		}
		BGamesPlayerData.syncData(player);

		return 1;

	}

	}

