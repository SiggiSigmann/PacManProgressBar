package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "com.github.siggisigmann.PacManProgressBar.PacManProgressBarPlugin.settings.PacManProgressBarState",
        storages = @Storage("PacManProgressBarState.xml")
)
public class PacManProgressBarState implements PersistentStateComponent<PacManProgressBarState> {

    private boolean animatedDots = true;
    public boolean isAnimatedDots() {
        return animatedDots;
    }
    public void setAnimatedDots(boolean animateDots) {
        this.animatedDots = animateDots;
    }

    private int animationSpeed = 50;
    public int getAnimationSpeed() {
        return animationSpeed;
    }
    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }






    public static PacManProgressBarState getInstance() {
        return ApplicationManager.getApplication().getService(PacManProgressBarState.class);
    }

    public static PacManProgressBarState getDefault(){
        PacManProgressBarState defaultSettings = new PacManProgressBarState();
        defaultSettings.setAnimatedDots(true);

        return defaultSettings;
    }

    @Override
    public @Nullable PacManProgressBarState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull PacManProgressBarState pacManProgressBarState) {
        XmlSerializerUtil.copyBean(pacManProgressBarState, this);
    }
}
