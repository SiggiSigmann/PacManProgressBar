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

    private int dotAnimationSpeed = 50;
    public int getDotAnimationSpeed() {
        return dotAnimationSpeed;
    }
    public void setDotAnimationSpeed(int animationSpeed) {
        this.dotAnimationSpeed = animationSpeed;
    }

    private int indeterminateMode = 0;
    public static final int OVERFLOW_MODE = 0;
    public static final int GAME_SIMULATION_MODE = 1;
    public int getIndeterminateMode() {
        return indeterminateMode;
    }
    public void setIndeterminateMode(int indeterminateMode) {
        this.indeterminateMode = indeterminateMode;
    }

    private int pacManAnimationSpeed = 20;
    public int getPacManAnimationSpeed() {
        return pacManAnimationSpeed;
    }
    public void setPacManAnimationSpeed(int animationSpeed) {
        this.pacManAnimationSpeed = animationSpeed;
    }

    private int pacManStyle= 0;
    public int getPacManStyle() {return pacManStyle;}
    public void setPacManStyle(int pacManStyle) {this.pacManStyle = pacManStyle;}


    public static PacManProgressBarState getInstance() {
        return ApplicationManager.getApplication().getService(PacManProgressBarState.class);
    }

    public static PacManProgressBarState getDefault(){
        PacManProgressBarState defaultSettings = new PacManProgressBarState();
        defaultSettings.setAnimatedDots(true);
        defaultSettings.setDotAnimationSpeed(50);
        defaultSettings.setIndeterminateMode(0);
        defaultSettings.setPacManAnimationSpeed(20);
        defaultSettings.setPacManStyle(0);
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
