package fr.anatom3000.gwwhit.registry;

import fr.anatom3000.gwwhit.GWWHIT;
import fr.anatom3000.gwwhit.effect.SpicyEffect;
import net.minecraft.util.registry.Registry;

public class EffectRegistry {
    public static final SpicyEffect SPICY_EFFECT = new SpicyEffect();

    public static void register() {
        Registry.register(Registry.STATUS_EFFECT, GWWHIT.getId("spicy"), SPICY_EFFECT);
    }
}
