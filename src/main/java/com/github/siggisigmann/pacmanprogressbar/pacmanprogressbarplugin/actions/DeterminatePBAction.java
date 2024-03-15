package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;

import java.text.DecimalFormat;

public class DeterminatePBAction extends AnAction {
    private DecimalFormat df = new DecimalFormat("###.###");

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();

        new Task.Backgroundable(project, "Task with Progress Bar") {
            @Override
            public void run(ProgressIndicator indicator) {
                indicator.setIndeterminate(false);


                // Simulate a delay with a loop
                for (double i = 0; i <= 1.0; i += 0.001) {
                    try {
                        indicator.setText("Working(" + df.format(i*100) + "%)");
                        // Simulate work
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    // Update progress
                    indicator.setFraction(i);
                }

                indicator.setFraction(1.0);
                indicator.setText("Task completed.");
            }
        }.queue();
    }
}