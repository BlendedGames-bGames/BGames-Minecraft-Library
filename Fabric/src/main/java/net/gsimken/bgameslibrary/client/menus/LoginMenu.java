package net.gsimken.bgameslibrary.client.menus;

import net.gsimken.bgameslibrary.client.ModScreens;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;

import java.util.HashMap;

public class LoginMenu extends ScreenHandler {

    public final static HashMap<String, Object> guistate = new HashMap<>();
    private final Inventory inventory;
    public final PlayerEntity player;
    public LoginMenu(int id, PlayerInventory inv, PacketByteBuf extraData) {
        this(id, inv, new SimpleInventory(0));

    }

    public LoginMenu(int id, PlayerInventory inv, SimpleInventory container) {
        super(ModScreens.LOGIN, id);
        this.inventory = container;
        this.player = inv.player;

    }

    public static String getEmailFromBox(){
        String email = guistate.containsKey("text:Email") ? ((TextFieldWidget) guistate.get("text:Email")).getText() : "";
        return email;
    }
    public static String getPasswordFromBox(){
        String password = guistate.containsKey("text:Password") ? ((TextFieldWidget) guistate.get("text:Password")).getText() : "";
        return password;
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
