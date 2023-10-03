 package net.gsimken.bgameslibrary.client.menus;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.client.BGamesLibraryModScreens;
import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;
import net.gsimken.bgameslibrary.networking.packet.BGamesPlayerDataSyncS2CPacket;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;

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
        if(!this.player.getWorld().isClient()){
            BGamesPlayerData.attributeRefresh((IBGamesDataSaver) player);
            BGamesPlayerData.syncData((ServerPlayerEntity) player);
        }
    }

     @Override
     public ItemStack quickMove(PlayerEntity player, int slot) {
         return null;
     }

     @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }



}
