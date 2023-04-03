package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.gsimken.bgameslibrary.network.BgamesLibraryModVariables;

public class ShowPersistentDataProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double id = 0;
		String email = "";
		String pass = "";
		id = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_player_id;
		email = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_email;
		pass = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_password;
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(Component.literal("§b========\n§6Id_player: §a" + String.valueOf((int) id) + "\n§6IEmail: §a" + email + "\n§6Password: §a" + pass + "\n§b========"), (false));
	}
}
