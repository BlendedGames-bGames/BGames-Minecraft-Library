
package net.gsimken.bgameslibrary.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.gsimken.bgameslibrary.procedures.OpenDisplayAttributeGuiProcedure;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.level.Level;

public class SyncPersistentDataS2C extends FriendlyByteBuf {
	public SyncPersistentDataS2C(int button_id) {
		super(Unpooled.buffer());
		writeInt(button_id);

	}

	public static void apply(MinecraftServer server, ServerPlayer entity, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
		int button_id= buf.readInt();

	}
}
