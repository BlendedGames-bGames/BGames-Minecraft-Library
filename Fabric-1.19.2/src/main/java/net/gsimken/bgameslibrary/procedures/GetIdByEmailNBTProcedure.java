package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.gsimken.bgameslibrary.core.api.BGamesApi;
import net.gsimken.bgameslibrary.core.api.ApiResponse;
import net.gsimken.bgameslibrary.BgameslibraryMod;

import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class GetIdByEmailNBTProcedure {

	public static double execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency entity for procedure GetIdByEmailNBT!");
			return 0;
		}
		Entity entity = (Entity) dependencies.get("entity");
		String email = "";
		String password = "";
		BGamesApi user_api = new BGamesApi();
		email = entity.getExtraCustomData().getCompound("PlayerPersisted").getString("bgames_email");
		password = entity.getExtraCustomData().getCompound("PlayerPersisted").getString("bgames_password");
		ApiResponse message = user_api.getPlayerByLogin(email, password);
		if (message.getCode() == 200) {
			Gson GSON = new Gson();
			JsonObject user = GSON.fromJson(message.getResponse().get(0).toString(), JsonObject.class);
			return (int) user.get("id_players").getAsDouble();
		} else if (message.getCode() != 200) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("§4Error: " + message.getErrorDescription()), (false));
		}
		return -1;
	}
}
