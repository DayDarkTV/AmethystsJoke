package com.daysportal.amethystsjoke.mixin;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.CommandBlock;
import net.minecraft.block.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Blocks.class)
public abstract class BlocksMixin {

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/block/AbstractBlock$Settings;Z)Lnet/minecraft/block/CommandBlock;"
            )
    )
    private static CommandBlock overwriteCommandBlock(AbstractBlock.Settings settings, boolean auto) {
        return new CommandBlock(
                FabricBlockSettings.create()
                        .mapColor(MapColor.BROWN)
                        .strength(10.0F, 3600000.0F),
                true);
    }
}
