package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.gsimken.bgameslibrary.network.BgamesLibraryModVariables;

public class ShowPersistentDataProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String email = "";
		String pass = "";
		double id = 0;
		double social = 0;
		double linguistic = 0;
		double cognitive = 0;
		double physical = 0;
		double afective = 0;
		id = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_player_id;
		email = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_email;
		pass = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_password;
		social = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_social_points;
		linguistic = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_linguistic_points;
		cognitive = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_cognitive_points;
		physical = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_physical_points;
		afective = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_afective_points;
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(
					Component.literal("§b========\n§6Id_player: §a" + String.valueOf((int) id) + "\n§6IEmail: §a" + email + "\n§6Password: §a" + pass + "\n§6social: §a" + String.valueOf((int) social) + "\n§6linguistic: §a"
							+ String.valueOf((int) linguistic) + "\n§6cognitive: §a" + String.valueOf((int) cognitive) + "\n§6physical: §a" + String.valueOf((int) physical) + "\n§6afective: §a" + String.valueOf((int) afective) + "\n§b========"),
					(false));
	}
}
