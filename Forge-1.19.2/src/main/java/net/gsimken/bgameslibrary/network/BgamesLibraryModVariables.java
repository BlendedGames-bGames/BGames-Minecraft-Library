package net.gsimken.bgameslibrary.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.gsimken.bgameslibrary.BgamesLibraryMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BgamesLibraryModVariables {
	public static String bgames_afective_name = "Afectivo";
	public static String bgames_social_name = "Social";
	public static String bgames_physical_name = "Fisica";
	public static String bgames_cognitive_name = "Cognitivo";
	public static String bgames_linguistic_name = "Linguistico";

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		BgamesLibraryMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level.isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level.isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level.isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			clone.bgames_player_id = original.bgames_player_id;
			clone.bgames_email = original.bgames_email;
			clone.bgames_password = original.bgames_password;
			clone.bgames_afective_points = original.bgames_afective_points;
			clone.bgames_social_points = original.bgames_social_points;
			clone.bgames_physical_points = original.bgames_physical_points;
			clone.bgames_cognitive_points = original.bgames_cognitive_points;
			clone.bgames_linguistic_points = original.bgames_linguistic_points;
			if (!event.isWasDeath()) {
			}
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("bgames_library", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public double bgames_player_id = -1.0;
		public String bgames_email = "\"\"";
		public String bgames_password = "\"\"";
		public double bgames_afective_points = -1.0;
		public double bgames_social_points = -1.0;
		public double bgames_physical_points = -1.0;
		public double bgames_cognitive_points = -1.0;
		public double bgames_linguistic_points = -1.0;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				BgamesLibraryMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("bgames_player_id", bgames_player_id);
			nbt.putString("bgames_email", bgames_email);
			nbt.putString("bgames_password", bgames_password);
			nbt.putDouble("bgames_afective_points", bgames_afective_points);
			nbt.putDouble("bgames_social_points", bgames_social_points);
			nbt.putDouble("bgames_physical_points", bgames_physical_points);
			nbt.putDouble("bgames_cognitive_points", bgames_cognitive_points);
			nbt.putDouble("bgames_linguistic_points", bgames_linguistic_points);
			return nbt;
		}

		public void readNBT(Tag Tag) {
			CompoundTag nbt = (CompoundTag) Tag;
			bgames_player_id = nbt.getDouble("bgames_player_id");
			bgames_email = nbt.getString("bgames_email");
			bgames_password = nbt.getString("bgames_password");
			bgames_afective_points = nbt.getDouble("bgames_afective_points");
			bgames_social_points = nbt.getDouble("bgames_social_points");
			bgames_physical_points = nbt.getDouble("bgames_physical_points");
			bgames_cognitive_points = nbt.getDouble("bgames_cognitive_points");
			bgames_linguistic_points = nbt.getDouble("bgames_linguistic_points");
		}
	}

	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.bgames_player_id = message.data.bgames_player_id;
					variables.bgames_email = message.data.bgames_email;
					variables.bgames_password = message.data.bgames_password;
					variables.bgames_afective_points = message.data.bgames_afective_points;
					variables.bgames_social_points = message.data.bgames_social_points;
					variables.bgames_physical_points = message.data.bgames_physical_points;
					variables.bgames_cognitive_points = message.data.bgames_cognitive_points;
					variables.bgames_linguistic_points = message.data.bgames_linguistic_points;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
