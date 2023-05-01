package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.Entity;

import net.gsimken.bgameslibrary.BgameslibraryMod;

import java.util.Map;

public class VerifyLoggedInProcedure {

	public static boolean execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency entity for procedure VerifyLoggedIn!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity.getExtraCustomData().getCompound("PlayerPersisted").getDouble("id_player") != -1) {
			return true;
		}
		return false;
	}
}
