package net.gsimken.bgameslibrary.networking.packet;

import net.gsimken.bgameslibrary.bgames.BGamesPlayerDataProvider;
import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;
import net.gsimken.bgameslibrary.utils.PlayerUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BGamesLoginC2SPacket {
    private final String password,email;
    public BGamesLoginC2SPacket(String email, String password) {
        this.email = email;
        this.password = password;

    }
    public BGamesLoginC2SPacket(FriendlyByteBuf buf) {
        this.email = buf.readUtf();
        this.password = buf.readUtf();

    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.email);
        buf.writeUtf(this.password);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {

        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            player.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
                //Reset attributos nbt
                data.attributeReset();
                //get
                String email = this.email;
                String password = this.password;


                int playerId= new PlayerUtils().GetIdByEmail(email, password);

                data.id = playerId;
                if(playerId==-1){
                    player.sendSystemMessage(Component.translatable(  "api.bgameslibrary.player_not_found").withStyle(ChatFormatting.RED));
                }
                else {
                    data.setEmail(email);
                    data.setPassword(password);
                    data.attributeRefresh();
                    player.sendSystemMessage(Component.translatable(  "login.bgameslibrary.logged").withStyle(ChatFormatting.GREEN));
                }
                BGamesLibraryModMessages.sendToPlayer(new BGamesPlayerDataSyncS2CPacket(data.getId(),
                        data.getSocialPoints(), data.getPhysicalPoints(), data.getLinguisticPoints(),
                        data.getAffectivePoints(), data.getCognitivePoints(),
                        data.getEmail(), data.getPassword()), player);
            });


        });
        return true;
    }

}