package fr.anatom3000.gwwhit.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;

public class SpicyEffect extends StatusEffect {
    public static int DURATION;

    public SpicyEffect() {
        super(StatusEffectCategory.HARMFUL, 0xf03622);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        DURATION = duration;
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient()) {
            entity.setFireTicks(DURATION);
        }
    }

    @Override
    public StatusEffect addAttributeModifier(EntityAttribute attribute, String uuid, double amount, EntityAttributeModifier.Operation operation) {
        return this.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9",
                3.0D,
                EntityAttributeModifier.Operation.ADDITION
        );
    }
}
