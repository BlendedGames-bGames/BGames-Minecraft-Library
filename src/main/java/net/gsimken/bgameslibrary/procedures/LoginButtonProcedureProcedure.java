package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;

import net.gsimken.bgameslibrary.network.BgamesLibraryModVariables;

import java.util.HashMap;

public class LoginButtonProcedureProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		double id = 0;
		id = GetIdByEmailProcedure.execute(entity, guistate);
		if (id != -1) {
			{
				double _setval = id;
				entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.bgames_player_id = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				String _setval = guistate.containsKey("text:Email") ? ((EditBox) guistate.get("text:Email")).getValue() : "";
				entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.bgames_email = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				String _setval = guistate.containsKey("text:Password") ? ((EditBox) guistate.get("text:Password")).getValue() : "";
				entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.bgames_password = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("\u00A72Login Succesfully"), (false));
		} else {
			{
				double _setval = -1;
				entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.bgames_player_id = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				String _setval = "";
				entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.bgames_email = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				String _setval = "";
				entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.bgames_password = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("\u00A74Wrong credentials"), (false));
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
	}
}
