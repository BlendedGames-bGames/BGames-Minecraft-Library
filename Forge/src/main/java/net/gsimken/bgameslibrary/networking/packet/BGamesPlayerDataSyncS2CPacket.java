package net.gsimken.bgameslibrary.networking.packet;

import net.gsimken.bgameslibrary.bgames.ClientBGamesPlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BGamesPlayerDataSyncS2CPacket {
    /*
    Clase que se encarga de sincronizar la data en el servidor y en el cliente
    */
    private final int id;
    private final int socialPoints;
    private final int physicalPoints;
    private final int linguisticPoints;
    private final int affectivePoints;
    private final int cognitivePoints;
    private final String email;
    private final String password;

    public BGamesPlayerDataSyncS2CPacket(int id, int socialPoints,
                                         int physicalPoints,int linguisticPoints,
                                         int affectivePoints,int cognitivePoints,
                                         String email,String password) {
        this.id = id;
        this.socialPoints = socialPoints;
        this.physicalPoints = physicalPoints;
        this.linguisticPoints = linguisticPoints;
        this.affectivePoints = affectivePoints;
        this.cognitivePoints = cognitivePoints;
        this.email=email;
        this.password=password;
    }

    public BGamesPlayerDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.id = buf.readInt();
        this.socialPoints = buf.readInt();
        this.physicalPoints = buf.readInt();
        this.linguisticPoints = buf.readInt();
        this.affectivePoints = buf.readInt();
        this.cognitivePoints = buf.readInt();
        this.email= buf.readUtf();
        this.password= buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(id);
        buf.writeInt(socialPoints);
        buf.writeInt(physicalPoints);
        buf.writeInt(linguisticPoints);
        buf.writeInt(affectivePoints);
        buf.writeInt(cognitivePoints);
        buf.writeUtf(email);
        buf.writeUtf(password);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //client only area
            ClientBGamesPlayerData.setPlayerId(id);
            ClientBGamesPlayerData.setPlayerSocialPoints(socialPoints);
            ClientBGamesPlayerData.setPlayerPhysicalPoints(physicalPoints);
            ClientBGamesPlayerData.setPlayerLinguisticPoints(linguisticPoints);
            ClientBGamesPlayerData.setPlayerAffectivePoints(affectivePoints);
            ClientBGamesPlayerData.setPlayerCognitivePoints(cognitivePoints);
            ClientBGamesPlayerData.setPlayerEmail(email);
            ClientBGamesPlayerData.setPlayerPassword(password);
        });
        return true;
    }
}