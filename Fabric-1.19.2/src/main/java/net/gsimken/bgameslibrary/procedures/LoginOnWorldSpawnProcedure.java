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

import net.gsimken.bgameslibrary.world.inventory.LoginMenu;
import net.gsimken.bgameslibrary.BgameslibraryMod;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

import java.util.Map;
import java.util.HashMap;

import io.netty.buffer.Unpooled;

public class LoginOnWorldSpawnProcedure {
	public LoginOnWorldSpawnProcedure() {
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("entity", handler.getPlayer());
			dependencies.put("x", handler.getPlayer().getX());
			dependencies.put("y", handler.getPlayer().getY());
			dependencies.put("z", handler.getPlayer().getZ());
			dependencies.put("world", handler.getPlayer().getLevel());
			execute(dependencies);
		});
	}

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency world for procedure LoginOnWorldSpawn!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency x for procedure LoginOnWorldSpawn!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency y for procedure LoginOnWorldSpawn!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency z for procedure LoginOnWorldSpawn!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BgameslibraryMod.LOGGER.warn("Failed to load dependency entity for procedure LoginOnWorldSpawn!");
			return;
		}
		LevelAccessor world = (LevelAccessor) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		double id_player = 0;
		entity.getExtraCustomData().getCompound("PlayerPersisted").putDouble("bgames_player_id", (-1));
		ResetAttributesProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("entity", entity).build());
		if (!(entity.getExtraCustomData().getCompound("PlayerPersisted").getString("bgames_email")).equals("") && !(entity.getExtraCustomData().getCompound("PlayerPersisted").getString("bgames_password")).equals("")) {
			id_player = GetIdByEmailNBTProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("entity", entity).build());
			if (id_player != -1) {
				entity.getExtraCustomData().getCompound("PlayerPersisted").putDouble("bgames_player_id", id_player);
				RefreshAttributesProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("entity", entity).build());
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(Component.literal("\u00A72Login Succesfully"), (false));
			} else {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(Component.literal("\u00A74Wrong credentials"), (false));
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
								return Component.literal("Login");
							}

							@Override
							public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
								return new LoginMenu(syncId, inv, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_pos));
							}
						});
					}
				}
			}
		} else {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("\u00A76No credentials set"), (false));
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
							return Component.literal("Login");
						}

						@Override
						public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
							return new LoginMenu(syncId, inv, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_pos));
						}
					});
				}
			}
		}
	}
}
