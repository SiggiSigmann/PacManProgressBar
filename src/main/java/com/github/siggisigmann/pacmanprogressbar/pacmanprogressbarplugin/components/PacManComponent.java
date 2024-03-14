package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.components;

import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import com.intellij.openapi.application.ApplicationActivationListener;
import com.intellij.openapi.wm.IdeFrame;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class PacManComponent implements LafManagerListener, ApplicationActivationListener {
    public PacManComponent() {}

    @Override
    public void lookAndFeelChanged(@NotNull LafManager lafManager) {
        updateProgressBarUi();
    }

    @Override
    public void applicationActivated(@NotNull IdeFrame ideFrame) {
        updateProgressBarUi();
    }

    private void updateProgressBarUi() {
        UIManager.put("ProgressBarUI", PacManProgressBar.class.getName());
        UIManager.getDefaults().put(PacManProgressBar.class.getName(), PacManProgressBar.class);
    }
}
