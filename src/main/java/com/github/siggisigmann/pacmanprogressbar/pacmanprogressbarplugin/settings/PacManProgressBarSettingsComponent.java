package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.settings;

import com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.resourceManager.PacManIcons;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class PacManProgressBarSettingsComponent {

    private final PacManIcons icons = new PacManIcons();

    private JPanel contentPanel, settingsPanel, dotAnimationPanel, indeterminateModePanel, pacManAnimationPanel, pacManStylePanel, fruitPanel;
    private JCheckBox animateBoxCB;
    private JSlider dotAnimationSpeedSlider, pacManAnimationSpeedSlider;
    private JRadioButton overFlowModeOption, gameSimulationModeOption;
    private final ArrayList<JRadioButton> pacManStyles = new ArrayList<>();
    private final ArrayList<JRadioButton> fruitsStyles = new ArrayList<>();
    private JCheckBox randomPacMan, randomFruit;
    private ButtonGroup fruitStyleGroup, pacManStyleGroup;

    PacManProgressBarSettingsComponent(){
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        // dot settings +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        animateBoxCB = new JCheckBox("Animate dots");

        dotAnimationSpeedSlider = new JSlider(JSlider.HORIZONTAL, 1, 81, 40);
        dotAnimationSpeedSlider.setMajorTickSpacing(40);
        dotAnimationSpeedSlider.setMinorTickSpacing(10);
        dotAnimationSpeedSlider.setPaintTicks(true);
        dotAnimationSpeedSlider.setPaintLabels(true);
        dotAnimationSpeedSlider.setLabelTable(getLabelTable(dotAnimationSpeedSlider.getMinimum(), dotAnimationSpeedSlider.getMaximum()));

        JLabel dotAnimationLabel = new JLabel("Dot speed:");
        dotAnimationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dotAnimationPanel = new JPanel();
        dotAnimationPanel.setLayout(new BoxLayout(dotAnimationPanel, BoxLayout.Y_AXIS));
        dotAnimationPanel.add(dotAnimationLabel);
        dotAnimationPanel.add(dotAnimationSpeedSlider);

        // pacMan Speed settings +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        pacManAnimationSpeedSlider = new JSlider(JSlider.HORIZONTAL, 1, 81, 40);
        pacManAnimationSpeedSlider.setMajorTickSpacing(40);
        pacManAnimationSpeedSlider.setMinorTickSpacing(10);
        pacManAnimationSpeedSlider.setPaintTicks(true);
        pacManAnimationSpeedSlider.setPaintLabels(true);
        pacManAnimationSpeedSlider.setLabelTable(getLabelTable(pacManAnimationSpeedSlider.getMinimum(), pacManAnimationSpeedSlider.getMaximum()));

        JLabel pacManAnimationLabel = new JLabel("PacMan speed:");
        pacManAnimationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        pacManAnimationPanel = new JPanel();
        pacManAnimationPanel.setLayout(new BoxLayout(pacManAnimationPanel, BoxLayout.Y_AXIS));
        pacManAnimationPanel.add(pacManAnimationLabel);
        pacManAnimationPanel.add(pacManAnimationSpeedSlider);

        // pacman style +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        JLabel pacManStyleLabel = new JLabel("PacMan Style:");
        pacManStyleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        pacManStylePanel = new JPanel();
        pacManStylePanel.setLayout(new BoxLayout(pacManStylePanel, BoxLayout.Y_AXIS));
        pacManStylePanel.add(pacManStyleLabel);

        pacManStyleGroup = new ButtonGroup();
        for(int i = 0; i < icons.getFruits().getSize(); i++){
            JRadioButton pacManStyle = new JRadioButton(icons.getFruits().getName(i));

            pacManStyles.add(pacManStyle);
            pacManStyleGroup.add(pacManStyle);
            pacManStylePanel.add(pacManStyle);
        }

        randomPacMan = new JCheckBox();
        randomPacMan.setText("random PacMan");
        randomPacMan.addActionListener(e -> {
            for(AbstractButton rb: Collections.list(pacManStyleGroup.getElements())){
                rb.setEnabled(!randomPacMan.isSelected());
            }
        });
        pacManStylePanel.add(randomPacMan);

        // fruit style +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        JLabel FruitStyleLabel = new JLabel("Fruit Style:");
        FruitStyleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        fruitPanel = new JPanel();
        fruitPanel.setLayout(new BoxLayout(fruitPanel, BoxLayout.Y_AXIS));
        fruitPanel.add(FruitStyleLabel);

        fruitStyleGroup = new ButtonGroup();
        for(int i = 0; i < icons.getFruits().getSize(); i++){
            JRadioButton pacManStyle = new JRadioButton(icons.getFruits().getName(i));

            fruitsStyles.add(pacManStyle);
            fruitStyleGroup.add(pacManStyle);
            fruitPanel.add(pacManStyle);
        }

        randomFruit = new JCheckBox();
        randomFruit.setText("random Fruit");
        randomFruit.addActionListener(e -> {
            for(AbstractButton rb: Collections.list(fruitStyleGroup.getElements())){
                rb.setEnabled(!randomFruit.isSelected());
            }
        });
        fruitPanel.add(randomFruit);

        // mode settings +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
        JSeparator separator1 = new JSeparator(JSeparator.VERTICAL);
        separator1.setForeground(settingsPanel.getForeground());
        settingsPanel.add(separator1, gbc);

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

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = JBUI.insetsTop(10);
        JSeparator separator2 = new JSeparator(JSeparator.VERTICAL);
        separator2.setForeground(settingsPanel.getForeground());
        settingsPanel.add(separator2, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.insets = JBUI.insets(10, 20, 10, 10);

        JLabel styleHeader = new JLabel("Style");
        styleHeader.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.anchor = GridBagConstraints.NORTH;
        settingsPanel.add(styleHeader, gbc);

        gbc.gridy++;
        settingsPanel.add(pacManStylePanel, gbc);

        gbc.gridy++;
        settingsPanel.add(fruitPanel, gbc);


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
        double value = ((double)dotAnimationSpeedSlider.getValue())/10.0;
        return (int) Math.pow(2,value)+10;
    }

    public void setDotAnimationSpeed(int value) {
        double scaled = (double)value - 10.0;
        double log2 = Math.log(scaled) / Math.log(2);
        double res = log2 * 10;
        if(res < 1) res = 1;
        if(res > 80) res = 80;
        dotAnimationSpeedSlider.setValue((int) res);
    }

    public int getPacManAnimationSpeed() {
        double value = ((double)pacManAnimationSpeedSlider.getValue())/10.0;
        return (int) Math.pow(2,value);
    }

    public void setPacManAnimationSpeed(int value) {
        double log2 = Math.log(value) / Math.log(2);
        double res = log2 * 10;
        if(res < 1) res = 1;
        if(res > 80) res = 80;
        pacManAnimationSpeedSlider.setValue((int) res);
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

    public void setPacManStyle(int style){
        int fixedStyle = 0 <= style && style < pacManStyles.size() ? style : 0;
        pacManStyles.get(fixedStyle).setSelected(true);
    }

    public int getPacManStyle(){
        for(int i = 0; i < pacManStyles.size(); i++){
            if(pacManStyles.get(i).isSelected()) return i;
        }
        return 0;
    }

    public boolean getRandomPacMan(){
        return randomPacMan.isSelected();
    }

    public void setRandomPacMan(boolean selected){
        randomPacMan.setSelected(selected);

        for(AbstractButton rb: Collections.list(pacManStyleGroup.getElements())){
            rb.setEnabled(!randomPacMan.isSelected());
        }
    }

    public boolean getRandomFruit(){
        return randomFruit.isSelected();
    }

    public void setRandomFruit(boolean selected){
        randomFruit.setSelected(selected);

        for(AbstractButton rb: Collections.list(fruitStyleGroup.getElements())){
            rb.setEnabled(!randomFruit.isSelected());
        }
    }

    public void setFruitStyle(int style){
        int fixedStyle = 0 <= style && style < fruitsStyles.size() ? style : 0;
        fruitsStyles.get(fixedStyle).setSelected(true);
    }

    public int getFruitStyle(){
        for(int i = 0; i < fruitsStyles.size(); i++){
            if(fruitsStyles.get(i).isSelected()) return i;
        }
        return 0;
    }

    private static Hashtable<Integer, JLabel> getLabelTable(int min, int max) {
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(min, new JLabel("fast"));
        labelTable.put(max, new JLabel("slow"));
        return labelTable;
    }

}
