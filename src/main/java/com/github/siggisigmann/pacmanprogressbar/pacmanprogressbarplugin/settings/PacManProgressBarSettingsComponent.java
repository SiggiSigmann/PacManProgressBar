package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.settings;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class PacManProgressBarSettingsComponent {

    private JPanel settingsPanel, dotAniamtionPanel, indeterministicModePanel, pacManAniamtionPanel;

    private JCheckBox animateBoxCB;
    private JSlider dotAniamtionSpeedSilder, pacManAniamtionSpeedSilder;

    private JRadioButton overFlowModeOption, gameSimulationModeOption;

    PacManProgressBarSettingsComponent(){
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        animateBoxCB = new JCheckBox("Animate dots");

        dotAniamtionSpeedSilder = new JSlider(JSlider.HORIZONTAL, 0, 200, 1);
        dotAniamtionSpeedSilder.setMajorTickSpacing(100); // Set major ticks every 100 units
        dotAniamtionSpeedSilder.setMinorTickSpacing(10); // Set minor ticks every 10 units
        dotAniamtionSpeedSilder.setPaintTicks(true);
        dotAniamtionSpeedSilder.setPaintLabels(true);

        JLabel dotAniamtionLabel = new JLabel("Dot speed:");
        dotAniamtionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dotAniamtionPanel = new JPanel();
        dotAniamtionPanel.setLayout(new BoxLayout(dotAniamtionPanel, BoxLayout.Y_AXIS));
        dotAniamtionPanel.add(dotAniamtionLabel);
        dotAniamtionPanel.add(dotAniamtionSpeedSilder);

        pacManAniamtionSpeedSilder = new JSlider(JSlider.HORIZONTAL, 0, 100, 1);
        pacManAniamtionSpeedSilder.setMajorTickSpacing(50); // Set major ticks every 100 units
        pacManAniamtionSpeedSilder.setMinorTickSpacing(10); // Set minor ticks every 10 units
        pacManAniamtionSpeedSilder.setPaintTicks(true);
        pacManAniamtionSpeedSilder.setPaintLabels(true);

        JLabel pacManAniamtionLabel = new JLabel("PacMan speed:");
        pacManAniamtionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        pacManAniamtionPanel = new JPanel();
        pacManAniamtionPanel.setLayout(new BoxLayout(pacManAniamtionPanel, BoxLayout.Y_AXIS));
        pacManAniamtionPanel.add(pacManAniamtionLabel);
        pacManAniamtionPanel.add(pacManAniamtionSpeedSilder);

        overFlowModeOption = new JRadioButton("Overflow Mode");
        gameSimulationModeOption = new JRadioButton("GameSimulation Mode");

        ButtonGroup indeterministicModeGroup = new ButtonGroup();
        indeterministicModeGroup.add(overFlowModeOption);
        indeterministicModeGroup.add(gameSimulationModeOption);

        JLabel indeterministicModeLabel = new JLabel("Indeterministic Mode :");

        indeterministicModePanel = new JPanel();
        indeterministicModePanel.setLayout(new BoxLayout(indeterministicModePanel, BoxLayout.Y_AXIS));
        indeterministicModePanel.add(indeterministicModeLabel);
        indeterministicModePanel.add(overFlowModeOption);
        indeterministicModePanel.add(gameSimulationModeOption);

        settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
        settingsPanel.setBackground(Color.BLUE);
    }

    private void layoutComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel dotSettingsHeader = new JLabel("Dot Settings");
        dotSettingsHeader.setFont(new Font("Arial", Font.BOLD, 18));
        settingsPanel.add(dotSettingsHeader, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(5, 10, 5, 10);
        settingsPanel.add(animateBoxCB, gbc);
        gbc.gridy++;
        settingsPanel.add(dotAniamtionPanel, gbc);



        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 20, 10, 10); // Adjusting insets for the second column

        JLabel indeterministicModeHeader = new JLabel("Indeterministic Mode");
        indeterministicModeHeader.setFont(new Font("Arial", Font.BOLD, 18));
        settingsPanel.add(indeterministicModeHeader, gbc);


        gbc.gridy++;
        gbc.insets = new Insets(5, 20, 5, 10); // Adding spacing between components
        settingsPanel.add(indeterministicModePanel, gbc);

        gbc.gridy++;
        settingsPanel.add(pacManAniamtionPanel, gbc);



        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3; // Make the separator span multiple rows
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 0, 0, 0); // Adjusting insets for the separator
        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setForeground(settingsPanel.getForeground()); // Set separator color to black
        settingsPanel.add(separator, gbc);
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
    public int getDotAnimationSpeed() {
        int value = dotAniamtionSpeedSilder.getValue();
        return value == 0 ? 1: value;
    }

    public void setDotAnimationSpeed(@NotNull int value) {
        dotAniamtionSpeedSilder.setValue(value);
    }

    @NotNull
    public int getPacManAnimationSpeed() {
        int value = pacManAniamtionSpeedSilder.getValue();
        return value == 0 ? 1: value;
    }

    public void setPacManAnimationSpeed(@NotNull int value) {
        pacManAniamtionSpeedSilder.setValue(value);
    }

    @NotNull
    public int getIndeterminateMode() {
        if(overFlowModeOption.isSelected()) return PacManProgressBarState.OVERFLOW_MODE;
        if(gameSimulationModeOption.isSelected()) return PacManProgressBarState.GAME_SIMULATION_MODE;
        return PacManProgressBarState.OVERFLOW_MODE;
    }

    public void setIndeterminateMode(@NotNull int value) {
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
