package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.gsimken.bgameslibrary.network.BgamesLibraryModVariables;
import net.gsimken.bgameslibrary.core.api.ApiResponse;
import net.gsimken.bgameslibrary.core.api.ApiGet;

import java.util.ArrayList;

public class GetAttributesProcedure {
	public static ArrayList execute(Entity entity) {
		if (entity == null)
			return new ArrayList<>();
		double id_player = 0;
		ArrayList<Object> response = new ArrayList<>();
		ApiGet user_api = new ApiGet();
		ApiResponse message = new ApiResponse();
		System.out.println("ANTES DEL LOGIN");
		id_player = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_player_id;
		System.out.println("id_player_local: " + id_player);
		if (id_player == -1) {
			System.out.println("NO LOGEADO ");
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("§4Not logged, please login before play"), (false));
			return response;
		}
		System.out.println("PASOLOGEADO ");
		message = user_api.getPlayerAttributesById((int) id_player);
		System.out.println(message.getResponse().toString() + "\n" + message.getErrorDescription());
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(Component.literal(("Message: " + message.getResponse().toString())), (false));
		if (message.getCode() != 200) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("§4Error: " + message.getErrorDescription()), (false));
		}
		response = (ArrayList) message.getResponse();
		return response;
	}
}
