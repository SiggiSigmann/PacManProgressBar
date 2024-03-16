package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class IndeterminatePBAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();

        new Task.Backgroundable(project, "Task with progress bar") {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(true);
                indicator.setText("Working...");

                // Simulate a delay with a loop
                for (int i = 0; i < 1000; i++) {
                    try {
                        // Simulate work
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

                indicator.setText("Task completed.");
            }
        }.queue();
    }
}