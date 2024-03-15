package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;

public class IndeterminatePBAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();

        new Task.Backgroundable(project, "Task with Progress Bar") {
            @Override
            public void run(ProgressIndicator indicator) {
                indicator.setIndeterminate(true);
                indicator.setText("Working...");

                // Simulate a delay with a loop
                for (int i = 0; i < 1000; i++) {
                    try {
                        // Simulate work
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                indicator.setText("Task completed.");
            }
        }.queue();
    }
}