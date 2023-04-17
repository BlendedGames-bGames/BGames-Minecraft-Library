package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.Entity;

import net.gsimken.bgameslibrary.network.BgamesLibraryModVariables;
import net.gsimken.bgameslibrary.core.utils.Utils;

import java.util.ArrayList;

public class AttributeRefreshProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double points = 0;
		ArrayList<Object> attributes_value = new ArrayList<>();
		String afective_name = "";
		String social_name = "";
		String physical_name = "";
		String cognitive_name = "";
		String linguistic_name = "";
		attributes_value = (ArrayList) GetAttributesProcedure.execute(entity);
		afective_name = BgamesLibraryModVariables.bgames_afective_name;
		social_name = BgamesLibraryModVariables.bgames_social_name;
		physical_name = BgamesLibraryModVariables.bgames_physical_name;
		cognitive_name = BgamesLibraryModVariables.bgames_cognitive_name;
		linguistic_name = BgamesLibraryModVariables.bgames_linguistic_name;
		Utils compare = new Utils();
		{
			double _setval = compare.getDataPlayerAttributeFromJson(attributes_value, afective_name);
			entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.bgames_afective_points = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = compare.getDataPlayerAttributeFromJson(attributes_value, social_name);
			entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.bgames_social_points = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = compare.getDataPlayerAttributeFromJson(attributes_value, physical_name);
			entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.bgames_physical_points = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = compare.getDataPlayerAttributeFromJson(attributes_value, cognitive_name);
			entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.bgames_cognitive_points = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = compare.getDataPlayerAttributeFromJson(attributes_value, linguistic_name);
			entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.bgames_linguistic_points = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
