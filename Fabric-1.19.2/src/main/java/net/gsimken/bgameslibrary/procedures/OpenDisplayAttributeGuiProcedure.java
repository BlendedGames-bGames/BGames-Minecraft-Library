package net.gsimken.bgameslibrary.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.gsimken.bgameslibrary.world.inventory.BgamesDisplayAttributesMenu;
import net.gsimken.bgameslibrary.BgameslibraryMod;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;

import java.util.Map;

import io.netty.buffer.Unpooled;

public class OpenDisplayAttributeGuiProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency world for procedure OpenDisplayAttributeGui!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency x for procedure OpenDisplayAttributeGui!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency y for procedure OpenDisplayAttributeGui!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency z for procedure OpenDisplayAttributeGui!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency entity for procedure OpenDisplayAttributeGui!");
			return;
		}

		LevelAccessor world = (LevelAccessor) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		System.out.println("EN PROCEDURE: "+ entity+" data: "+entity.getExtraCustomData());
		{
			if (entity instanceof ServerPlayer _ent) {
				_ent.openMenu(new ExtendedScreenHandlerFactory() {
					final BlockPos _pos = new BlockPos(x, y, z);

					@Override
					public void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf) {
						buf.writeBlockPos(_pos);
					}

					@Override
					public Component getDisplayName() {
						return Component.literal("BgamesDisplayAttributes");
					}

					@Override
					public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
						return new BgamesDisplayAttributesMenu(syncId, inv, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_pos));
					}
				});
			}
		}
	}
}
