package net.gsimken.bgameslibrary.client.triggers;

import io.netty.buffer.Unpooled;
import net.gsimken.bgameslibrary.client.menus.LoginMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class LoginTrigger implements MenuProvider {
    @Override
    public Component getDisplayName() {
        return Component.literal("Login");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new LoginMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()));
    }
}

