package com.daysportal.amethystsjoke.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class DirtCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("dirt")
                        .executes(commandContext -> execute(commandContext.getSource(), commandContext.getSource().getPlayerOrThrow()))
        );
    }

    public static int execute(ServerCommandSource source, ServerPlayerEntity player) {
        ItemStack stack = Items.DIRT.getDefaultStack();
        stack.setCount(256);
        int slot = player.getInventory().getEmptySlot();
        if (slot < 0) {
            slot = player.getInventory().getOccupiedSlotWithRoomForStack(Items.DIRT.getDefaultStack());
        }
        if (slot < 0) {
            source.sendError(Text.literal("Not Enough Inventory Space!"));
            return 0;
        }
        player.getInventory().setStack(slot, stack);
        return 1;
    }
}
