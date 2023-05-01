
package net.gsimken.bgameslibrary.command;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandBuildContext;

import net.gsimken.bgameslibrary.procedures.OpenDisplayAttributeGuiProcedure;

import com.mojang.brigadier.CommandDispatcher;

public class ShowAttributeGuiCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext) {
		dispatcher.register(Commands.literal("attributes")

				.executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					Direction direction = entity.getDirection();

					OpenDisplayAttributeGuiProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("world", world).put("x", x).put("y", y).put("z", z).put("entity", entity).build());
					return 0;
				}));
	}
}
