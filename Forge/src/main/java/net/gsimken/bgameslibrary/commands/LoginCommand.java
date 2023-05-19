package net.gsimken.bgameslibrary.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.gsimken.bgameslibrary.client.triggers.LoginTrigger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkHooks;

public class LoginCommand {
    public LoginCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("login")
                .executes(command -> {
                    return openLoginScreen(command.getSource());
                }));
    }

    public static int openLoginScreen(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        NetworkHooks.openScreen((ServerPlayer) player,new LoginTrigger());
        return 1;

    }
}
