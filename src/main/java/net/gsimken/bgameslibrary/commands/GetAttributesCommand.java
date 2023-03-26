package net.gsimken.bgameslibrary.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializer;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.gsimken.bgameslibrary.core.ApiGet;
import net.gsimken.bgameslibrary.core.ApiResponse;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.commands.Commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetAttributesCommand {
    public GetAttributesCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("get_attributes").
                then(Commands.argument("id", IntegerArgumentType.integer())
                .executes((command) ->{
                    try {
                        return getAttributes(command.getSource(), command.getArgument("id", Integer.class));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })));
    }
    private int getAttributes(CommandSourceStack source,Integer id) throws CommandSyntaxException, IOException {
        ApiGet bgames_api= new ApiGet();
        ApiResponse Message= bgames_api.getPlayerAttributes(id);
        if(Message.getCode()==200) {
            source.sendSuccess(Component.literal(Message.getResponse().toString()), true);
        }
        else{
              source.sendSuccess(Component.literal("Error: "+Message.getErrorDescription()).withStyle(ChatFormatting.RED), true);
        }
        return 1;
    }


}
