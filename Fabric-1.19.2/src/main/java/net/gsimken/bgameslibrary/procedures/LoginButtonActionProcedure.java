package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;

import net.gsimken.bgameslibrary.BgameslibraryMod;

import java.util.Map;
import java.util.HashMap;

public class LoginButtonActionProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency entity for procedure LoginButtonAction!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency guistate for procedure LoginButtonAction!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		double id_player = 0;

		ResetAttributesProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("entity", entity).build());
		id_player = GetIdByEmailProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("entity", entity).put("guistate", guistate).build());
		if (id_player != -1) {
			entity.getExtraCustomData().getCompound("PlayerPersisted").putDouble("bgames_player_id", id_player);
			entity.getExtraCustomData().getCompound("PlayerPersisted").putString("bgames_email", (guistate.containsKey("text:Email") ? ((EditBox) guistate.get("text:Email")).getValue() : ""));
			entity.getExtraCustomData().getCompound("PlayerPersisted").putString("bgames_password", (guistate.containsKey("text:Password") ? ((EditBox) guistate.get("text:Password")).getValue() : ""));
			RefreshAttributesProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("entity", entity).build());
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("\u00A72Login Succesfully"), (false));
		} else {
			entity.getExtraCustomData().getCompound("PlayerPersisted").putDouble("bgames_player_id", (-1));
			entity.getExtraCustomData().getCompound("PlayerPersisted").putString("bgames_email", "");
			entity.getExtraCustomData().getCompound("PlayerPersisted").putString("bgames_password", "");
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("\u00A74Wrong credentials"), (false));
		}
		if (entity instanceof ServerPlayer _player)
			_player.closeContainer();
	}
}
