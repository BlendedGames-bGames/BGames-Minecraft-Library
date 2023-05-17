package net.gsimken.bgameslibrary.client.triggers;

import io.netty.buffer.Unpooled;
import net.gsimken.bgameslibrary.client.menus.DisplayAttributesMenu;
import net.gsimken.bgameslibrary.client.menus.LoginMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class DisplayAttributesTrigger implements MenuProvider {
    @Override
    public Component getDisplayName() {
        return Component.literal("Player Attributes");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new DisplayAttributesMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()));
    }
}

