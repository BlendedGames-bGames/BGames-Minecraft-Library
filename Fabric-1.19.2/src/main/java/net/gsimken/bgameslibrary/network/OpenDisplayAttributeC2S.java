
package net.gsimken.bgameslibrary.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.gsimken.bgameslibrary.procedures.OpenDisplayAttributeGuiProcedure;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.level.Level;

public class OpenDisplayAttributeC2S extends FriendlyByteBuf {
	public OpenDisplayAttributeC2S(int button_id) {
		super(Unpooled.buffer());
		writeInt(button_id);

	}

	public static void apply(MinecraftServer server, ServerPlayer entity, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
		int button_id= buf.readInt();
		server.execute(() -> {
			Level world = entity.level;
			double x = entity.getX();
			double y = entity.getY();
			double z = entity.getZ();
			// security measure to prevent arbitrary chunk generation
			if (!world.hasChunkAt(entity.blockPosition())) {
				return;
			}
				System.out.println("EN KEY: "+ entity+"button_id:"+button_id);
				OpenDisplayAttributeGuiProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("world", world).put("x", x).put("y", y).put("z", z).put("entity", entity).build());

		});
	}
}
