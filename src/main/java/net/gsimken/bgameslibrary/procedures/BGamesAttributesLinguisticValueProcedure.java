package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.Entity;

import net.gsimken.bgameslibrary.network.BgamesLibraryModVariables;

import java.util.ArrayList;

public class BGamesAttributesLinguisticValueProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		ArrayList<Object> attributes_value = new ArrayList<>();
		String afective_name = "";
		double points = 0;
		points = (entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_linguistic_points;
		if (points == -1) {
			points = 0;
		}
		return (int) points + " points";
	}
}
