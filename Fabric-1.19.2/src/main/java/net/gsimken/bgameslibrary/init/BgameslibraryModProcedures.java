
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.gsimken.bgameslibrary.init;

import net.gsimken.bgameslibrary.procedures.VerifyLoggedInProcedure;
import net.gsimken.bgameslibrary.procedures.ShowPersistentDataActionProcedure;
import net.gsimken.bgameslibrary.procedures.ResetAttributesProcedure;
import net.gsimken.bgameslibrary.procedures.RefreshAttributesProcedure;
import net.gsimken.bgameslibrary.procedures.OpenLoginGuiProcedure;
import net.gsimken.bgameslibrary.procedures.LoginOnWorldSpawnProcedure;
import net.gsimken.bgameslibrary.procedures.LoginButtonActionProcedure;
import net.gsimken.bgameslibrary.procedures.GetIdByEmailProcedure;
import net.gsimken.bgameslibrary.procedures.GetIdByEmailNBTProcedure;
import net.gsimken.bgameslibrary.procedures.GetAttributesProcedure;

@SuppressWarnings("InstantiationOfUtilityClass")
public class BgameslibraryModProcedures {
	public static void load() {
		new GetAttributesProcedure();
		new RefreshAttributesProcedure();
		new ResetAttributesProcedure();
		new GetIdByEmailProcedure();
		new GetIdByEmailNBTProcedure();
		new VerifyLoggedInProcedure();
		new LoginOnWorldSpawnProcedure();
		new LoginButtonActionProcedure();
		new ShowPersistentDataActionProcedure();
		new OpenLoginGuiProcedure();
	}
}
