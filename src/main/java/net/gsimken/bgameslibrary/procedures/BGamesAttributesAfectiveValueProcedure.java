package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.Entity;

import net.gsimken.bgameslibrary.network.BgamesLibraryModVariables;

import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class BGamesAttributesAfectiveValueProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		ArrayList<Object> attributes_value = new ArrayList<>();
		String afective_name = "";
		double points = 0;
		attributes_value = (ArrayList) GetAttributesProcedure.execute(entity);
		afective_name = BgamesLibraryModVariables.bgames_afective_name;
		int i = 0;
		if (attributes_value.isEmpty()) {
			return "0 points";
		}
		for (i = 0; i < attributes_value.size(); i++) {
			JsonObject attribute = new Gson().fromJson(attributes_value.get(i).toString(), JsonObject.class);
			if (attribute.get("name").getAsString().equals(afective_name)) {
				points = attribute.get("data").getAsInt();
				i = attributes_value.size();
			}
		}
		return points + "points";
	}
}
