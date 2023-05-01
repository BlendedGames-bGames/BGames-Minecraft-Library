package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.Entity;

import net.gsimken.bgameslibrary.BgameslibraryMod;

import java.util.Map;
import java.util.ArrayList;

public class ResetAttributesProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency entity for procedure ResetAttributes!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		String afective_name = "";
		String social_name = "";
		String physical_name = "";
		String cognitive_name = "";
		String linguistic_name = "";
		ArrayList<Object> attributes_value = new ArrayList<>();
		entity.getExtraCustomData().getCompound("PlayerPersisted").putDouble("bgames_afective_points", (-1));
		entity.getExtraCustomData().getCompound("PlayerPersisted").putDouble("bgames_social_points ", (-1));
		entity.getExtraCustomData().getCompound("PlayerPersisted").putDouble("bgames_physical_points", (-1));
		entity.getExtraCustomData().getCompound("PlayerPersisted").putDouble("bgames_cognitive_points", (-1));
		entity.getExtraCustomData().getCompound("PlayerPersisted").putDouble("bgames_linguistic_points", (-1));
	}
}
