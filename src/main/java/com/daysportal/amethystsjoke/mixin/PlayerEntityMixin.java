package com.daysportal.amethystsjoke.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CommandBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "isCreativeLevelTwoOp", at = @At("HEAD"), cancellable = true)
    private void isCreativeLevelTwoOp(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

    @Inject(method = "canHarvest", at = @At("HEAD"), cancellable = true)
    private void canHarvest(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.getBlock() instanceof CommandBlock) cir.setReturnValue(true);
    }
}
