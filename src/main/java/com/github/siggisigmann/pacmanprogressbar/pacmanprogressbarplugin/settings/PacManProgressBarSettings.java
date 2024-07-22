package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PacManProgressBarSettings implements Configurable {

    private PacManProgressBarSettingsComponent settingsComponent;

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "PacManProgressBar Settings";
    }

    @Override
    public @Nullable JComponent createComponent() {
        settingsComponent = new PacManProgressBarSettingsComponent();
        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        PacManProgressBarState oldSettings = PacManProgressBarState.getInstance();

        boolean isAnimatedDotsChanged = oldSettings.isAnimatedDots() != settingsComponent.isAnimatedDots();
        boolean dotAnimationSpeedChange = oldSettings.getDotAnimationSpeed() != settingsComponent.getDotAnimationSpeed();
        boolean pacManAnimationSpeedChange = oldSettings.getPacManAnimationSpeed() != settingsComponent.getPacManAnimationSpeed();
        boolean indeterminateModeChange = oldSettings.getIndeterminateMode() != settingsComponent.getIndeterminateMode();
        boolean pacManVersionChanged = oldSettings.getPacManStyle() != settingsComponent.getPacManStyle();
        boolean randomPacManChanged = oldSettings.isRandomPacman() != settingsComponent.getRandomPacMan();
        boolean fruitVersionChanged= oldSettings.getFruitsStyle() != settingsComponent.getFruitStyle();
        boolean randomFruitChanged = oldSettings.isRandomFruits() != settingsComponent.getRandomFruit();

        return isAnimatedDotsChanged || dotAnimationSpeedChange || pacManAnimationSpeedChange || indeterminateModeChange ||
                pacManVersionChanged || randomPacManChanged || fruitVersionChanged || randomFruitChanged;
    }

    @Override
    public void apply() {
        PacManProgressBarState settings = PacManProgressBarState.getInstance();

        settings.setAnimatedDots(settingsComponent.isAnimatedDots());
        settings.setDotAnimationSpeed(settingsComponent.getDotAnimationSpeed());
        settings.setPacManAnimationSpeed(settingsComponent.getPacManAnimationSpeed());
        settings.setIndeterminateMode(settingsComponent.getIndeterminateMode());
        settings.setPacManStyle(settingsComponent.getPacManStyle());
        settings.setRandomPacman(settingsComponent.getRandomPacMan());
        settings.setFruitsStyle(settingsComponent.getFruitStyle());
        settings.setRandomFruits(settingsComponent.getRandomFruit());
    }

    @Override
    public void reset() {
        PacManProgressBarState settings = PacManProgressBarState.getInstance();

        settingsComponent.setAnimatedDots(settings.isAnimatedDots());
        settingsComponent.setDotAnimationSpeed(settings.getDotAnimationSpeed());
        settingsComponent.setPacManAnimationSpeed(settings.getPacManAnimationSpeed());
        settingsComponent.setIndeterminateMode(settings.getIndeterminateMode());
        settingsComponent.setPacManStyle(settings.getPacManStyle());
        settingsComponent.setRandomPacMan(settings.isRandomPacman());
        settingsComponent.setFruitStyle(settings.getFruitsStyle());
        settingsComponent.setRandomFruit(settings.isRandomFruits());
    }
}
