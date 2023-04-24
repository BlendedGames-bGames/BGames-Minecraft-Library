package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;

import net.gsimken.bgameslibrary.core.api.BGamesApi;
import net.gsimken.bgameslibrary.core.api.ApiResponse;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class GetIdByEmailProcedure {
	public static double execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return 0;
		String email = "";
		String password = "";
		BGamesApi user_api = new BGamesApi();
		email = guistate.containsKey("text:Email") ? ((EditBox) guistate.get("text:Email")).getValue() : "";
		password = guistate.containsKey("text:Password") ? ((EditBox) guistate.get("text:Password")).getValue() : "";
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
