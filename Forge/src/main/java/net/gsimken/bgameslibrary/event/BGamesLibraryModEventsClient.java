package net.gsimken.bgameslibrary.event;
import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerData;
import net.gsimken.bgameslibrary.bgames.BGamesPlayerDataProvider;
import net.gsimken.bgameslibrary.bgames.ClientBGamesPlayerData;
import net.gsimken.bgameslibrary.client.triggers.LoginTrigger;
import net.gsimken.bgameslibrary.commands.LoginCommand;
import net.gsimken.bgameslibrary.networking.BGamesLibraryModMessages;
import net.gsimken.bgameslibrary.networking.packet.BGamesPlayerDataSyncS2CPacket;
import net.gsimken.bgameslibrary.utils.PlayerUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class BGamesLibraryModEventsClient {
    @SubscribeEvent
    public static void inventoryOpen(ScreenEvent.Init.Post event) {
        //if player its not login, every time he open the inventory will be a message for login
        if (event.getScreen() instanceof InventoryScreen) {
            Player player = Minecraft.getInstance().player;
            if(player instanceof LocalPlayer && !ClientBGamesPlayerData.isLoggedIn()) {
                player.sendSystemMessage(Component.translatable("login.bgameslibrary.not_logged").withStyle(ChatFormatting.RED));
            }
        }
    }

}



