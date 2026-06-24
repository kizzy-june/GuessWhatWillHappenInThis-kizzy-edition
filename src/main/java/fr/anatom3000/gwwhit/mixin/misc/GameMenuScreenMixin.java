package fr.anatom3000.gwwhit.mixin.misc;

import fr.anatom3000.gwwhit.kizzyjune.KizzyJuneIsCoolException;
import fr.anatom3000.gwwhit.kizzyjune.LoggerGuy;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("TAIL"), method = "initWidgets")
    private void addCrashButton(CallbackInfo info) {
        final short buttonWidth = 200;
        final short buttonHeight = 20;
        short x = (short) (this.width / 2 - (buttonWidth / 2));
        short y = (short) (this.height / 4 + 140);

        ButtonWidget crashButton = new ButtonWidget(
                x,
                y,
                buttonWidth,
                buttonHeight,
                Text.of("Crash"),
                (button) -> {
                    LoggerGuy.log("bruh-");
                    throw new KizzyJuneIsCoolException();
                }
        );

        this.addDrawableChild(crashButton);
    }
}
