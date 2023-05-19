package net.gsimken.bgameslibrary.client.menus;

import net.gsimken.bgameslibrary.client.ModMenus;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;

public class LoginMenu extends AbstractContainerMenu {
    public final static HashMap<String, Object> guistate = new HashMap<>();
    public final Player player;
    public LoginMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(ModMenus.LOGIN.get(), id);
        this.player = inv.player;

    }
    public static String getEmailFromBox(){
        String email = guistate.containsKey("text:Email") ? ((EditBox) guistate.get("text:Email")).getValue() : "";
        return email;
    }
    public static String getPasswordFromBox(){
        String password = guistate.containsKey("text:Password") ? ((EditBox) guistate.get("text:Password")).getValue() : "";
        return password;
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
