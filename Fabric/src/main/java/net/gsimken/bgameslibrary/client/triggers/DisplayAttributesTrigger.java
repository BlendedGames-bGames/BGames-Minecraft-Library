package net.gsimken.bgameslibrary.client.triggers;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.gsimken.bgameslibrary.client.menus.DisplayAttributesMenu;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class DisplayAttributesTrigger implements ExtendedScreenHandlerFactory {
    @Override
    public Text getDisplayName() {
        return Text.literal("Player Attributes");
    }
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
    }
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new DisplayAttributesMenu(syncId, inv, new PacketByteBuf(Unpooled.buffer()));
    }
}

