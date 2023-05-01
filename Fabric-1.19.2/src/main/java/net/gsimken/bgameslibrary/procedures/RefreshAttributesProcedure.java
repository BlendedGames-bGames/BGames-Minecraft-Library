package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.Entity;

import net.gsimken.bgameslibrary.network.BgameslibraryModVariables;
import net.gsimken.bgameslibrary.core.utils.Utils;
import net.gsimken.bgameslibrary.BgameslibraryMod;

import java.util.Map;
import java.util.ArrayList;

public class RefreshAttributesProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency entity for procedure RefreshAttributes!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		String afective_name = "";
		String social_name = "";
		String physical_name = "";
		String cognitive_name = "";
		String linguistic_name = "";
		ArrayList<Object> attributes_value = new ArrayList<>();
		attributes_value = GetAttributesProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("entity", entity).build());
		afective_name = BgameslibraryModVariables.bgames_afective_name;
		social_name = BgameslibraryModVariables.bgames_social_name;
		physical_name = BgameslibraryModVariables.bgames_physical_name;
		cognitive_name = BgameslibraryModVariables.bgames_cognitive_name;
		linguistic_name = BgameslibraryModVariables.bgames_linguistic_name;
		Utils compare = new Utils();
		entity.getExtraCustomData().getCompound("PlayerPersisted").putDouble("bgames_afective_points", compare.getDataPlayerAttributeFromJson(attributes_value, afective_name));
		entity.getExtraCustomData().getCompound("PlayerPersisted").putDouble("bgames_social_points ", compare.getDataPlayerAttributeFromJson(attributes_value, social_name));
		entity.getExtraCustomData().getCompound("PlayerPersisted").putDouble("bgames_physical_points", compare.getDataPlayerAttributeFromJson(attributes_value, physical_name));
		entity.getExtraCustomData().getCompound("PlayerPersisted").putDouble("bgames_cognitive_points", compare.getDataPlayerAttributeFromJson(attributes_value, cognitive_name));
		entity.getExtraCustomData().getCompound("PlayerPersisted").putDouble("bgames_linguistic_points", compare.getDataPlayerAttributeFromJson(attributes_value, linguistic_name));
	}
}
