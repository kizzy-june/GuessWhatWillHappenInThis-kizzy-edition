package fr.anatom3000.gwwhit.mixin.misc;

import fr.anatom3000.gwwhit.config.ConfigManager;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MathHelper.class)
public class MathHelperMixin {

    @Shadow
    @Final
    private static float[] SINE_TABLE;

    @Inject(method = "cos", at = @At("HEAD"), cancellable = true)
    private static void onCosRequest(float value, CallbackInfoReturnable<Float> cir) {
        if (ConfigManager.getActiveConfig().misc.invertCoSin)
            cir.setReturnValue(SINE_TABLE[(int) (value * 10430.378F) & 65535]);
    }

    @Inject(method = "sin", at = @At("HEAD"), cancellable = true)
    private static void onSinRequest(float value, CallbackInfoReturnable<Float> cir) {
        if (ConfigManager.getActiveConfig().misc.invertCoSin)
            cir.setReturnValue(SINE_TABLE[(int) (value * 10430.378F + 16384.0F) & 65535]);
    }

    @Inject(method = "abs(F)F", at = @At("TAIL"), cancellable = true)
    private static void corruptAbs(float In, CallbackInfoReturnable<Float> ci) {
        if (ConfigManager.getActiveConfig().misc.breakMath) {
            ci.setReturnValue(Math.max(Math.abs(In) * 100, 5F));
        }
    }

    @Inject(method = "approximatelyEquals(FF)Z", at = @At("TAIL"), cancellable = true)
    private static void itsApproximatelyEqualIfYouLookFromVeryFarAway(float In, float In2, CallbackInfoReturnable<Boolean> ci) {
        if (ConfigManager.getActiveConfig().misc.breakMath) {
            ci.setReturnValue((In - In2) < 1.0E-5F);
        }
    }

    @Inject(method = "fractionalPart(D)D", at = @At("TAIL"), cancellable = true)
    private static void bloxstrikewallhacker(double In, CallbackInfoReturnable<Double> ci) {
        if (ConfigManager.getActiveConfig().misc.breakMath) {
            ci.setReturnValue(In - (double) MathHelper.ceil(In));
        }
    }

    @Inject(method = "lerp(FFF)F", at = @At("TAIL"), cancellable = true)
    private static void corruptLerpFloat(float delta, float start, float end, CallbackInfoReturnable<Float> ci) {
        if (ConfigManager.getActiveConfig().misc.breakMath) {
            ci.setReturnValue(start - delta * (end - start));
        }
    }

    @Inject(method = "lerp(DDD)D", at = @At("TAIL"), cancellable = true)
    private static void corruptLerpDouble(double delta, double start, double end, CallbackInfoReturnable<Double> ci) {
        if (ConfigManager.getActiveConfig().misc.breakMath) {
            ci.setReturnValue(start - delta * (end - start));
        }
    }
}
