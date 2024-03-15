package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.settings;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBRadioButton;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class PacManProgressBarSettingsComponent {

    private JPanel settingsPanel;

    private final JCheckBox animateBoxCB;
    private final JSlider aniamtionSpeedSilder;

    PacManProgressBarSettingsComponent(){
        animateBoxCB = new JCheckBox("animate dots");

        aniamtionSpeedSilder = new JSlider(JSlider.HORIZONTAL, 0, 200, 5);
        aniamtionSpeedSilder.setMajorTickSpacing(100); // Set major ticks every 100 units
        aniamtionSpeedSilder.setMinorTickSpacing(10); // Set minor ticks every 10 units
        aniamtionSpeedSilder.setPaintTicks(true);
        aniamtionSpeedSilder.setPaintLabels(true);
        JLabel valueLabel = new JLabel("Animation speed:");
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
        sliderPanel.add(valueLabel);
        sliderPanel.add(aniamtionSpeedSilder);


        settingsPanel = new JPanel();
        settingsPanel.add(animateBoxCB);
        settingsPanel.add(sliderPanel);
    }


    public JPanel getPanel() {
        return settingsPanel;
    }

    @NotNull
    public boolean isAnimatedDots() {
        return animateBoxCB.isSelected();
    }

    public void setAnimatedDots(@NotNull Boolean isAnimatedDots) {
        animateBoxCB.setSelected(isAnimatedDots);
    }

    @NotNull
    public int getAnimationSpeed() {
        int value = aniamtionSpeedSilder.getValue();
        return value == 0 ? 1: value;
    }

    public void setAnimationSpeed(@NotNull int value) {
        aniamtionSpeedSilder.setValue(value);
    }

}
