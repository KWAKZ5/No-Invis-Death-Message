package com.kwakz.noinvisdeathmessage.mixins;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import net.minecraft.entity.effect.StatusEffects;

@Mixin(PlayerEntity.class)
class RemoveOnDamage {
    @Inject(at = @At("RETURN"), method = "damage")
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Void> info) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (source.getAttacker() instanceof PlayerEntity && source.getAttacker() != player) {
            if (player.hasStatusEffect(StatusEffects.INVISIBILITY)) {
                player.removeStatusEffect(StatusEffects.INVISIBILITY);
                player.sendMessage(Text.literal("You have lost your invisibility because you got damaged by another player.").fillStyle(Style.EMPTY.withColor(Formatting.RED)));
            }
        }
    }
}
