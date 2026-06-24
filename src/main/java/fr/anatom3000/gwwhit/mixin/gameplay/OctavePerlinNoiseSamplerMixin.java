package fr.anatom3000.gwwhit.mixin.gameplay;

import fr.anatom3000.gwwhit.config.ConfigManager;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OctavePerlinNoiseSampler.class)
public class OctavePerlinNoiseSamplerMixin {
    @Inject(method = "maintainPrecision", at = @At("TAIL"), cancellable = true)
    private static void whoNeedsPrecisionTheseDaysQuestionMarkSinceJavaSyntaxDoesNotLetMePutTheSymbolForQuestionMarks(double In, CallbackInfoReturnable<Double> ci) {
        if (ConfigManager.getActiveConfig().gameplay.farlands) {
            ci.setReturnValue(In);
        }
    }
}
