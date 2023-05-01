package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.gsimken.bgameslibrary.BgameslibraryMod;

import java.util.Map;

public class ShowPersistentDataActionProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency entity for procedure ShowPersistentDataAction!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		String email = "";
		String pass = "";
		double id = 0;
		double social = 0;
		double linguistic = 0;
		double cognitive = 0;
		double physical = 0;
		double afective = 0;
		email = entity.getExtraCustomData().getCompound("PlayerPersisted").getString("bgames_email");
		pass = entity.getExtraCustomData().getCompound("PlayerPersisted").getString("bgames_password");
		id = entity.getExtraCustomData().getCompound("PlayerPersisted").getDouble("bgames_player_id");
		social = entity.getExtraCustomData().getCompound("PlayerPersisted").getDouble("bgames_social_points ");
		linguistic = entity.getExtraCustomData().getCompound("PlayerPersisted").getDouble("bgames_linguistic_points");
		cognitive = entity.getExtraCustomData().getCompound("PlayerPersisted").getDouble("bgames_cognitive_points");
		physical = entity.getExtraCustomData().getCompound("PlayerPersisted").getDouble("bgames_physical_points");
		afective = entity.getExtraCustomData().getCompound("PlayerPersisted").getDouble("bgames_afective_points");
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(
					Component.literal("§b========\n§6Id_player: §a" + String.valueOf((int) id) + "\n§6IEmail: §a" + email + "\n§6Password: §a" + pass + "\n§6social: §a" + String.valueOf((int) social) + "\n§6linguistic: §a"
							+ String.valueOf((int) linguistic) + "\n§6cognitive: §a" + String.valueOf((int) cognitive) + "\n§6physical: §a" + String.valueOf((int) physical) + "\n§6afective: §a" + String.valueOf((int) afective) + "\n§b========"),
					(false));
	}
}
