package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.gsimken.bgameslibrary.network.BgamesLibraryModVariables;
import net.gsimken.bgameslibrary.core.api.BGamesApi;
import net.gsimken.bgameslibrary.core.api.ApiResponse;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class GetIdByEmailGlobalProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		String email = "";
		String password = "";
		BGamesApi user_api = new BGamesApi();
		email = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_email;
		password = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_password;
		ApiResponse message = user_api.getPlayerByLogin(email, password);
		if (message.getCode() == 200) {
			Gson GSON = new Gson();
			JsonObject user = GSON.fromJson(message.getResponse().get(0).toString(), JsonObject.class);
			return (int) user.get("id_players").getAsDouble();
		} else if (message.getCode() != 200) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("§4ERROR: " + message.getResponse().toString()), (false));
		}
		return -1;
	}
}
