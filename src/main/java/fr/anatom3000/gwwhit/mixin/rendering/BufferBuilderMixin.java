package fr.anatom3000.gwwhit.mixin.rendering;

import fr.anatom3000.gwwhit.config.ConfigManager;
import net.minecraft.client.render.BufferBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BufferBuilder.class)
public class BufferBuilderMixin {
    @ModifyArg(
            method = "vertex",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/BufferBuilder;putFloat(IF)V", ordinal = 0),
            index = 1
    )
    private float modifyX(float ogData) {
        if (ConfigManager.getActiveConfig().rendering.world.stretchVertexBufferz) {
            return ogData * 6;
        } else {
            return ogData;
        }
    }

    @ModifyArg(
            method = "vertex",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/BufferBuilder;putFloat(IF)V", ordinal = 1),
            index = 1
    )
    private float modifyY(float ogData) {
        if (ConfigManager.getActiveConfig().rendering.world.stretchVertexBufferz) {
            return ogData * 1.5F;
        } else {
            return ogData;
        }
    }
}
