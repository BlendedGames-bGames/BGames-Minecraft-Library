package net.gsimken.bgameslibrary.events;

import com.google.common.eventbus.Subscribe;
import net.gsimken.bgameslibrary.BGamesApi;
import net.gsimken.bgameslibrary.commands.GetAttributesCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = BGamesApi.MOD_ID)
public class ModEvents {
@SubscribeEvent
    public static void  onCommandRegister(RegisterCommandsEvent event){
        new GetAttributesCommand(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }
}
