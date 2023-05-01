package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.gsimken.bgameslibrary.core.api.BGamesApi;
import net.gsimken.bgameslibrary.core.api.ApiResponse;
import net.gsimken.bgameslibrary.BgameslibraryMod;

import java.util.Map;
import java.util.ArrayList;

public class GetAttributesProcedure {

	public static ArrayList execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency entity for procedure GetAttributes!");
			return new ArrayList<>();
		}
		Entity entity = (Entity) dependencies.get("entity");
		double id_player = 0;
		ArrayList<Object> response = new ArrayList<>();
		BGamesApi user_api = new BGamesApi();
		ApiResponse message = new ApiResponse();
		id_player = entity.getExtraCustomData().getCompound("PlayerPersisted").getDouble("bgames_player_id");
		if (entity.getExtraCustomData().getCompound("PlayerPersisted").getDouble("bgames_player_id") == -1) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("§4Not logged, please login before play"), (false));
			return response;
		}
		message = user_api.getPlayerAttributesById((int) id_player);
		if (message.getCode() != 200) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("§4Error: " + message.getErrorDescription()), (false));
		}
		response = message.getResponse();
		return response;
	}
}
