package com.daysportal.amethystsjoke.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class DailyCommand {
    public static final Item LuckyItem = Items.DIRT;

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("daily")
                        .executes(context -> execute(context.getSource(), context.getSource().getPlayerOrThrow()))
        );
    }

    public static int execute(ServerCommandSource source, ServerPlayerEntity playerEntity) {
        int max_stack = LuckyItem.getMaxCount();
        int give_count;
        give_count = 5;


        int k = give_count;
        while (k > 0) {
            int stack_count = Math.min(k, max_stack);
            k -= stack_count;

            ItemStack stack = new ItemStack(LuckyItem, stack_count);
            boolean inserted = playerEntity.getInventory().insertStack(stack);
            if (inserted && stack.isEmpty()) {
                stack.setCount(1);
                ItemEntity ie = playerEntity.dropItem(stack, false);
                if (ie != null) ie.setDespawnImmediately();
                playerEntity.getWorld().playSound(
                        null,
                        playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(),
                        SoundEvents.ENTITY_ITEM_PICKUP,
                        SoundCategory.PLAYERS,
                        0.2F,
                        ((playerEntity.getRandom().nextFloat() - playerEntity.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F
                        );
                playerEntity.currentScreenHandler.sendContentUpdates();
            } else {
                ItemEntity itemEntity = playerEntity.dropItem(stack, false);
                if (itemEntity != null) {
                    itemEntity.resetPickupDelay();
                    itemEntity.setOwner(playerEntity.getUuid());
                }
            }
        }
        return 1;
    }
}
