package com.kwakz.noinvisdeathmessage.mixins;

import com.kwakz.noinvisdeathmessage.logger.Logs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.damage.DamageSource;

@Mixin(DamageSource.class)
abstract
class DeathMessage {

    @Shadow @Final @Nullable private Entity attacker;

    @ModifyVariable(method = "getDeathMessage", at = @At("STORE"))
    private Text noinvisdeathmessage$modifyDamageSource(Text original, LivingEntity killed) {
        if (this.attacker instanceof LivingEntity && ((LivingEntity) this.attacker).hasStatusEffect(StatusEffects.INVISIBILITY)) {
            Logs.addlogg(attacker.getDisplayName() + " killed " + killed.getDisplayName());
            return Text.of("An Invisible Player");
        }
        return original;
    }
}
