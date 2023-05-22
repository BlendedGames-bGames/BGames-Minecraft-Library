 package net.gsimken.bgameslibrary.client.menus;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.gsimken.bgameslibrary.client.BGamesLibraryModScreens;
import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;

import java.util.HashMap;

 public class DisplayAttributesMenu extends ScreenHandler {
     public final static HashMap<String, Object> guistate = new HashMap<>();
     private final Inventory inventory;
     public final PlayerEntity player;
    public DisplayAttributesMenu(int id, PlayerInventory inv, PacketByteBuf extraData) {
        this(id, inv, new SimpleInventory(0));

    }

    public DisplayAttributesMenu(int id, PlayerInventory inv, SimpleInventory container) {
        super(BGamesLibraryModScreens.BGAMES_DISPLAY_ATTRIBUTES, id);
        this.inventory = container;
        this.player = inv.player;
        ClientPlayNetworking.send(BGamesLibraryModMessages.BGAMES_CLIENT_DATA_SYNC, PacketByteBufs.create());

    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

}
