 package net.gsimken.bgameslibrary.client.menus;

import net.gsimken.bgameslibrary.bgames.BGamesPlayerDataProvider;
import net.gsimken.bgameslibrary.client.BGamesLibraryModMenus;
import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;
import net.gsimken.bgameslibrary.networking.packet.BGamesPlayerDataSyncC2SPacket;
import net.gsimken.bgameslibrary.networking.packet.BGamesPlayerDataSyncS2CPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
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
        if(!this.player.getLevel().isClientSide()){
           this.player.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
                data.attributeReset();
                data.attributeRefresh();

                /*
                   Al momento de abrir la gui se manda al jugador los datos del servidors para sincronizar
                 * */
                BGamesLibraryModMessages.sendToPlayer(new BGamesPlayerDataSyncS2CPacket(data.getId(),
                        data.getSocialPoints(),data.getPhysicalPoints(),data.getLinguisticPoints(),
                        data.getAffectivePoints(),data.getCognitivePoints(),
                        data.getEmail(),data.getPassword()), (ServerPlayer) player);
            });
        }
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
