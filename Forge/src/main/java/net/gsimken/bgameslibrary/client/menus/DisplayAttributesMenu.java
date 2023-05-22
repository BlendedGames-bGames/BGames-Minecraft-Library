 package net.gsimken.bgameslibrary.client.menus;

import net.gsimken.bgameslibrary.client.BGamesLibraryModMenus;
import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;
import net.gsimken.bgameslibrary.networking.packet.BGamesPlayerDataSyncC2SPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;

public class DisplayAttributesMenu extends AbstractContainerMenu {
    public final static HashMap<String, Object> guistate = new HashMap<>();
    public final Player player;
    public DisplayAttributesMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(BGamesLibraryModMenus.DISPLAY_ATTRIBUTES.get(), id);
        this.player = inv.player;
        //sync serverdata
        BGamesLibraryModMessages.sendToServer(new BGamesPlayerDataSyncC2SPacket());
    }


    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }


}
