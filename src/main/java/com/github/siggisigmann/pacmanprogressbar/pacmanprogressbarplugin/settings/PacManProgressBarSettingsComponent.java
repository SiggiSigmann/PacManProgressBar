package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.settings;

import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;

public class PacManProgressBarSettingsComponent {

    private JPanel contentPanel, settingsPanel, dotAnimationPanel, indeterminateModePanel, pacManAnimationPanel;
    private JCheckBox animateBoxCB;
    private JSlider dotAnimationSpeedSlider, pacManAnimationSpeedSlider;
    private JRadioButton overFlowModeOption, gameSimulationModeOption;

    PacManProgressBarSettingsComponent(){
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        animateBoxCB = new JCheckBox("Animate dots");

        dotAnimationSpeedSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, 1);
        dotAnimationSpeedSlider.setMajorTickSpacing(100); // Set major ticks every 100 units
        dotAnimationSpeedSlider.setMinorTickSpacing(10); // Set minor ticks every 10 units
        dotAnimationSpeedSlider.setPaintTicks(true);
        dotAnimationSpeedSlider.setPaintLabels(true);

        JLabel dotAnimationLabel = new JLabel("Dot speed:");
        dotAnimationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dotAnimationPanel = new JPanel();
        dotAnimationPanel.setLayout(new BoxLayout(dotAnimationPanel, BoxLayout.Y_AXIS));
        dotAnimationPanel.add(dotAnimationLabel);
        dotAnimationPanel.add(dotAnimationSpeedSlider);

        pacManAnimationSpeedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 1);
        pacManAnimationSpeedSlider.setMajorTickSpacing(50); // Set major ticks every 100 units
        pacManAnimationSpeedSlider.setMinorTickSpacing(10); // Set minor ticks every 10 units
        pacManAnimationSpeedSlider.setPaintTicks(true);
        pacManAnimationSpeedSlider.setPaintLabels(true);

        JLabel pacManAnimationLabel = new JLabel("PacMan speed:");
        pacManAnimationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        pacManAnimationPanel = new JPanel();
        pacManAnimationPanel.setLayout(new BoxLayout(pacManAnimationPanel, BoxLayout.Y_AXIS));
        pacManAnimationPanel.add(pacManAnimationLabel);
        pacManAnimationPanel.add(pacManAnimationSpeedSlider);

        overFlowModeOption = new JRadioButton("Overflow Mode");
        gameSimulationModeOption = new JRadioButton("GameSimulation Mode");

        ButtonGroup indeterminateModeGroup = new ButtonGroup();
        indeterminateModeGroup.add(overFlowModeOption);
        indeterminateModeGroup.add(gameSimulationModeOption);

        JLabel indeterminateModeLabel = new JLabel("Indeterminate Mode :");

        indeterminateModePanel = new JPanel();
        indeterminateModePanel.setLayout(new BoxLayout(indeterminateModePanel, BoxLayout.Y_AXIS));
        indeterminateModePanel.add(indeterminateModeLabel);
        indeterminateModePanel.add(overFlowModeOption);
        indeterminateModePanel.add(gameSimulationModeOption);

        settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
    }

    private void layoutComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = JBUI.insets(10);

        JLabel dotSettingsHeader = new JLabel("Dot Settings");
        dotSettingsHeader.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.anchor = GridBagConstraints.NORTH;
        settingsPanel.add(dotSettingsHeader, gbc);
        gbc.gridy++;
        gbc.insets = JBUI.insets(5, 10); // Adding spacing between components
        settingsPanel.add(animateBoxCB, gbc);
        gbc.gridy++;
        settingsPanel.add(dotAnimationPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = JBUI.insetsTop(10);
        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setForeground(settingsPanel.getForeground());
        settingsPanel.add(separator, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.insets = JBUI.insets(10, 20, 10, 10);

        JLabel indeterminateModeHeader = new JLabel("Indeterminate Mode");
        indeterminateModeHeader.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.anchor = GridBagConstraints.NORTH;
        settingsPanel.add(indeterminateModeHeader, gbc);

        gbc.gridy++;
        gbc.insets = JBUI.insets(5, 20, 5, 10); // Adding spacing between components
        settingsPanel.add(indeterminateModePanel, gbc);

        gbc.gridy++;
        settingsPanel.add(pacManAnimationPanel, gbc);

        contentPanel = new JPanel();
        contentPanel.add(settingsPanel);
    }


    public JPanel getPanel() {
        return contentPanel;
    }

    public boolean isAnimatedDots() {
        return animateBoxCB.isSelected();
    }

    public void setAnimatedDots(Boolean isAnimatedDots) {
        animateBoxCB.setSelected(isAnimatedDots);
    }

    public int getDotAnimationSpeed() {
        int value = dotAnimationSpeedSlider.getValue();
        return value == 0 ? 1: value;
    }

    public void setDotAnimationSpeed(int value) {
        dotAnimationSpeedSlider.setValue(value);
    }

    public int getPacManAnimationSpeed() {
        int value = pacManAnimationSpeedSlider.getValue();
        return value == 0 ? 1: value;
    }

    public void setPacManAnimationSpeed(int value) {
        pacManAnimationSpeedSlider.setValue(value);
    }

    public int getIndeterminateMode() {
        if(overFlowModeOption.isSelected()) return PacManProgressBarState.OVERFLOW_MODE;
        if(gameSimulationModeOption.isSelected()) return PacManProgressBarState.GAME_SIMULATION_MODE;
        return PacManProgressBarState.OVERFLOW_MODE;
    }

    public void setIndeterminateMode(int value) {
        overFlowModeOption.setSelected(false);
        gameSimulationModeOption.setSelected(false);

        switch (value){
            case PacManProgressBarState.OVERFLOW_MODE:
                overFlowModeOption.setSelected(true);
                break;
            case PacManProgressBarState.GAME_SIMULATION_MODE:
                gameSimulationModeOption.setSelected(true);
                break;
            default:
                overFlowModeOption.setSelected(true);
        }
    }

}
