package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.gsimken.bgameslibrary.network.BgamesLibraryModVariables;
import net.gsimken.bgameslibrary.core.api.ApiResponse;
import net.gsimken.bgameslibrary.core.api.BGamesApi;

import java.util.ArrayList;

public class GetAttributesProcedure {
	public static ArrayList execute(Entity entity) {
		if (entity == null)
			return new ArrayList<>();
		double id_player = 0;
		ArrayList<Object> response = new ArrayList<>();
		BGamesApi user_api = new BGamesApi();
		ApiResponse message = new ApiResponse();
		id_player = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_player_id;
		if (id_player == -1) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("§4Not logged, please login before play"), (false));
			return response;
		}
		message = user_api.getPlayerAttributesById((int) id_player);
		if (message.getCode() != 200) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("§4Error: " + message.getErrorDescription()), (false));
		}
		response = (ArrayList) message.getResponse();
		return response;
	}
}