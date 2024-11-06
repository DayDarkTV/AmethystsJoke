package com.daysportal.amethystsjoke.commands;

import com.daysportal.amethystsjoke.luckyblocks.LuckyBlockItems;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

import java.util.Date;

public class DailyCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("daily")
                        .executes(context -> execute(context.getSource(), context.getSource().getPlayerOrThrow()))
        );
    }
    
    public static int execute(ServerCommandSource source, ServerPlayerEntity player) {
        Scoreboard board = player.getScoreboard();
        if (!board.containsObjective("dailycooldown"))
            board.addObjective("dailycooldown", ScoreboardCriterion.DUMMY, Text.of("Daily Cooldown"), ScoreboardCriterion.RenderType.INTEGER);
        ScoreboardObjective objective = board.getObjective("dailycooldown");
        ScoreboardPlayerScore score = board.getPlayerScore(player.getGameProfile().getName(), objective);
        if (score.getScore() == 0) score.setScore(-1152000);
//        score.setScore(player.age);
        Date now = new Date();
        int currentTick = (int) (now.getTime()/50);
        if (currentTick - score.getScore() < 1152000) {
            int ticks = 1152000- (currentTick - score.getScore());
            int sec = ticks/20;
            int mins = sec/60;
            sec -= mins*60;
            int hours = mins/60;
            mins -= hours*60;
            source.sendError(Text.literal(hours + " hours, " + mins + " minutes, and " + sec + " seconds left until you can use this command!"));
            return 0;
        }


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
        score.setScore(currentTick);
        return give_count;
    }
}
