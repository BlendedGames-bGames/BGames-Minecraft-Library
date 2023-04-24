package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import net.gsimken.bgameslibrary.network.BgamesLibraryModVariables;

import java.util.HashMap;

public class OpenGuiLoginProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		if (!((entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_email).equals("")) {
			if (guistate.get("text:Email") instanceof EditBox _tf)
				_tf.setValue(((entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_email));
		}
		if (!((entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_password).equals("")) {
			if (guistate.get("text:Password") instanceof EditBox _tf)
				_tf.setValue(((entity.getCapability(BgamesLibraryModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BgamesLibraryModVariables.PlayerVariables())).bgames_password));
		}
	}
}
