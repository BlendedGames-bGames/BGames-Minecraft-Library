
package net.gsimken.bgameslibrary.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.api.ApiResponse;
import net.gsimken.bgameslibrary.api.BGamesApi;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


public class StatsRequestsCommand {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {

		dispatcher.register(CommandManager.literal("compute_stats")

				.then(CommandManager.argument("number_of_request", IntegerArgumentType.integer()).executes(command -> {
					int number_of_request= IntegerArgumentType.getInteger(command, "number_of_request");
					return computeStats(command.getSource(), number_of_request);
				})));
	}
	public static int computeStats(ServerCommandSource source,int number_of_request) throws CommandSyntaxException {
		ServerPlayerEntity player = source.getPlayerOrThrow();
		BGamesApi get_api = new BGamesApi();
		int i=0;
		for ( i=0;i<number_of_request;i++){
			try {
				ApiResponse message = get_api.getPlayerAttributesById(2);


				Thread.sleep(500);  // Hace una pausa de 500 milisegundos antes de la próxima iteración
				player.sendMessage(Text.literal(  "ESPERA DE GET 500MS EXITOSA: "+(i+1)+"/"+number_of_request+" requests").fillStyle(Style.EMPTY.withColor(Formatting.GREEN)));
			} catch (InterruptedException e) {
				player.sendMessage(Text.literal("FALLO LA ESPERA DE 500MS: "+(i+1)+"/"+number_of_request+" requests").fillStyle(Style.EMPTY.withColor(Formatting.RED)));

			}

		}
		BGamesApi post_api = new BGamesApi();
		int j=0;
		for (j=0;j<number_of_request;j++){
			try {
				ApiResponse message = post_api.spendAttribute(2, BgamesLibrary.bgames_afective_name,1);
				Thread.sleep(500);  // Hace una pausa de 500 milisegundos antes de la próxima iteración
				player.sendMessage(Text.literal(  "ESPERA DE POST 500MS EXITOSA: " +(j+1)+"/"+number_of_request+" requests").fillStyle(Style.EMPTY.withColor(Formatting.GREEN)));
			} catch (InterruptedException e) {
				player.sendMessage(Text.literal("FALLO LA ESPERA DE 500MS: "+(j+1)+"/"+number_of_request+" requests").fillStyle(Style.EMPTY.withColor(Formatting.RED)));

			}

		}

		return 1;
	}
}
