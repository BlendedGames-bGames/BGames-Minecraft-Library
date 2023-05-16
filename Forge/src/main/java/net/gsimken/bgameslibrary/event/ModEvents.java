package net.gsimken.bgameslibrary.event;
import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerDataProvider;
import net.gsimken.bgameslibrary.commands.DebugLoginCommand;
import net.gsimken.bgameslibrary.commands.DebugPlayerAttributesByIdCommand;
import net.gsimken.bgameslibrary.commands.DebugShowPlayerDataCommand;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
@Mod.EventBusSubscriber(modid = BgamesLibrary.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new DebugPlayerAttributesByIdCommand(event.getDispatcher());
        new DebugLoginCommand(event.getDispatcher());
        new DebugShowPlayerDataCommand(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(BGamesPlayerDataProvider.PLAYER_DATA).isPresent()) {
                event.addCapability(new ResourceLocation(BgamesLibrary.MOD_ID, "properties"), new BGamesPlayerDataProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        //if player dies its need to clone all the data for the new generated
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(BGamesPlayerDataProvider.PLAYER_DATA).ifPresent(oldStore -> {
                event.getOriginal().getCapability(BGamesPlayerDataProvider.PLAYER_DATA).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(BGamesPlayerData.class);
    }


}
