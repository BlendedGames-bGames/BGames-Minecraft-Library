package net.gsimken.bgameslibrary.commands;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModCommands {
    public static void register(){
        CommandRegistrationCallback.EVENT.register(DebugLoginCommand::register);
        CommandRegistrationCallback.EVENT.register(DebugPlayerAttributesByIdCommand::register);
        CommandRegistrationCallback.EVENT.register(DebugShowPlayerDataCommand::register);
        CommandRegistrationCallback.EVENT.register(LoginCommand::register);
    }
}
