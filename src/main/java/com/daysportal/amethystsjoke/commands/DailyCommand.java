package com.daysportal.amethystsjoke.commands;

import com.daysportal.amethystsjoke.luckyblocks.LuckyBlockItems;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
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
                        .executes(context -> execute(context.getSource().getPlayerOrThrow()))
        );
    }
    
    public static int execute(ServerPlayerEntity player) {

        int give_count;
        float ran = player.getRandom().nextFloat();
        if (ran < 0.75) give_count = 2;
        else if (ran < 0.90) give_count = 3;
        else give_count = 5;

        for (int i = 0; i < give_count; i++) {
            int rand = player.getRandom().nextBetween(0, LuckyBlockItems.ALL.size()-1);
            ItemStack stack = LuckyBlockItems.ALL.get(rand).copy();
            boolean inserted = player.getInventory().insertStack(stack);
            if (inserted && stack.isEmpty()) {
                stack.setCount(1);
                ItemEntity ie = player.dropItem(stack, false);
                if (ie != null) ie.setDespawnImmediately();
                player.getWorld().playSound(
                        null,
                        player.getX(), player.getY(), player.getZ(),
                        SoundEvents.ENTITY_ITEM_PICKUP,
                        SoundCategory.PLAYERS,
                        0.2F,
                        ((player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F
                );
                player.currentScreenHandler.sendContentUpdates();

            } else {
                ItemEntity itemEntity = player.dropItem(stack, false);
                if (itemEntity != null) {
                    itemEntity.resetPickupDelay();
                    itemEntity.setOwner(player.getUuid());
                }
            }
        }
        return give_count;
    }
}
