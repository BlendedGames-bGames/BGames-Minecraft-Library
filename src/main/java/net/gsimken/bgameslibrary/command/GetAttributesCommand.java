
package net.gsimken.bgameslibrary.command;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.gsimken.bgameslibrary.core.ApiGet;
import net.gsimken.bgameslibrary.core.ApiResponse;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;

import net.minecraft.commands.Commands;

import com.mojang.brigadier.arguments.DoubleArgumentType;

import java.io.IOException;

@Mod.EventBusSubscriber
public class GetAttributesCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("getattributes")

                .then(Commands.argument("id_player", IntegerArgumentType.integer())
                        .executes((command) -> {
                            try {
                                return getAttributes(command.getSource(), command.getArgument("id_player", Integer.class));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })));
    }

    private static int getAttributes(CommandSourceStack source, Integer id) throws CommandSyntaxException, IOException {
        ApiGet bgames_api = new ApiGet();
        ApiResponse Message = bgames_api.getPlayerAttributes(id);
        if (Message.getCode() == 200) {
            source.sendSuccess(Component.literal(Message.getResponse().toString()), true);
        } else {
            source.sendSuccess(Component.literal("Error: " + Message.getErrorDescription()).withStyle(ChatFormatting.RED), true);
        }
        return 1;
    }
}