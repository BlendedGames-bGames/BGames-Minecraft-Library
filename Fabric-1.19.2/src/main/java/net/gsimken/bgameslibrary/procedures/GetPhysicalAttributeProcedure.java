package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.Entity;

import net.gsimken.bgameslibrary.BgameslibraryMod;

import java.util.Map;

public class GetPhysicalAttributeProcedure {

	public static String execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency entity for procedure GetPhysicalAttribute!");
			return "";
		}
		Entity entity = (Entity) dependencies.get("entity");
		double points = 0;
		points = entity.getExtraCustomData().getCompound("PlayerPersisted").getDouble("bgames_physical_points");
		if (points == -1) {
			points = 0;
		}
		return (int) points + " points";
	}
}