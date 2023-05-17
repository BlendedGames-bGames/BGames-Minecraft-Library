package net.gsimken.bgameslibrary.networking.packet;

import net.gsimken.bgameslibrary.bgames.BGamesPlayerDataProvider;
import net.gsimken.bgameslibrary.client.triggers.DisplayAttributesTrigger;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.Supplier;

public class ButtonOpenGuiC2SPacket {

    public ButtonOpenGuiC2SPacket() {

    }

    public ButtonOpenGuiC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            player.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
                if(data.isLoggedIn()){
                    NetworkHooks.openScreen((ServerPlayer) player,new DisplayAttributesTrigger());
                }
                else{
                    player.sendSystemMessage(Component.translatable(  "login.bgameslibrary.not_logged").withStyle(ChatFormatting.RED));

                }
            });



        });
        return true;
    }

}