package com.daysportal.amethystsjoke.mixin;

import net.minecraft.block.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CommandBlock.class)
public abstract class CommandBlockMixin extends BlockWithEntity implements OperatorBlock  {
    protected CommandBlockMixin(Settings settings) {
        super(AbstractBlock.Settings.create().mapColor(MapColor.BROWN).strength(1.0F, 3600000.0F));
    }

}
