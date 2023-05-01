
package net.gsimken.bgameslibrary.world.inventory;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.Container;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.gsimken.bgameslibrary.procedures.RefreshAttributesProcedure;
import net.gsimken.bgameslibrary.init.BgameslibraryModMenus;

import java.util.HashMap;

public class BgamesDisplayAttributesMenu extends AbstractContainerMenu {
	public final static HashMap<String, Object> guistate = new HashMap<>();
	public final Level world;
	public final Player entity;
	public int x, y, z;
	private BlockPos pos;
	private final Container inventory;
	private boolean bound = false;

	public BgamesDisplayAttributesMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		this(id, inv, new SimpleContainer(0));
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
		}
	}

	public BgamesDisplayAttributesMenu(int id, Inventory inv, Container container) {
		super(BgameslibraryModMenus.BGAMES_DISPLAY_ATTRIBUTES, id);
		this.entity = inv.player;
		this.world = inv.player.level;
		this.inventory = container;

		RefreshAttributesProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("entity", entity).build());
	}

	@Override
	public boolean stillValid(Player player) {
		return this.inventory.stillValid(player);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slot) {
		return ItemStack.EMPTY;
	}
}
