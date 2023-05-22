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
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.server.command.ConfigCommand;
@Mod.EventBusSubscriber(modid = BgamesLibrary.MOD_ID)
public class BGamesLibraryModEvents {
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
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        //command registrys,  debugs are off
        // new DebugPlayerAttributesByIdCommand(event.getDispatcher());
        // new DebugLoginCommand(event.getDispatcher());
        // new DebugShowPlayerDataCommand(event.getDispatcher());
        new LoginCommand(event.getDispatcher());
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
            Player deathPlayer= event.getOriginal();
            Player clonedPlayer= event.getEntity();
            deathPlayer.reviveCaps();
            deathPlayer.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).ifPresent(oldStore -> {
                    clonedPlayer.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                    });
            });
            deathPlayer.invalidateCaps();
        }
    }


    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(BGamesPlayerData.class);
    }
    @SubscribeEvent
    public static void onPlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        // when player join the world the attributes refresh or in case that the email o pass are not set or are invalids open the login screen
        if(event.getEntity() instanceof ServerPlayer player){ //server player
            player.getCapability(BGamesPlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
                String email = data.getEmail();
                String password = data.getPassword();
                data.attributeReset();
                if(email.equals("") || password.equals("")){  // empty pass or email
                    NetworkHooks.openScreen(player,new LoginTrigger());
                }
                else{
                    int playerId= new PlayerUtils().GetIdByEmail(email, password);
                    data.id = playerId;
                    if(!data.isLoggedIn()){ //player not found or invalid credentials
                        data.setEmail("");
                        data.setPassword("");
                        player.sendSystemMessage(Component.translatable(  "api.bgameslibrary.invalid_credentials").withStyle(ChatFormatting.RED));
                        NetworkHooks.openScreen(player,new LoginTrigger());
                    }
                    else { //OK
                        data.attributeRefresh();
                        player.sendSystemMessage(Component.translatable(  "login.bgameslibrary.logged").withStyle(ChatFormatting.GREEN));
                        //sync the server player with the client player
                        BGamesLibraryModMessages.sendToPlayer(new BGamesPlayerDataSyncS2CPacket(data.getId(),
                                data.getSocialPoints(), data.getPhysicalPoints(), data.getLinguisticPoints(),
                                data.getAffectivePoints(), data.getCognitivePoints(),
                                data.getEmail(), data.getPassword()), player);
                    }


                }

            });

        }
    }
}



