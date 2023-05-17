 package net.gsimken.bgameslibrary.client.menus;

import net.gsimken.bgameslibrary.client.ModMenus;
import net.gsimken.bgameslibrary.networking.ModMessages;
import net.gsimken.bgameslibrary.networking.packet.OpenGuiC2SPacket;
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
        super(ModMenus.DISPLAY_ATTRIBUTES.get(), id);
        this.player = inv.player;
        //sync serverdata
        ModMessages.sendToServer(new OpenGuiC2SPacket());
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
