package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.components;

import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;

public class PacManProgressBar extends BasicProgressBarUI {
    private static int HIGHT = 20;

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(super.getPreferredSize(c).width, JBUI.scale(HIGHT));
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        progressBar.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
            }
        });
    }

    private volatile int offset = 0;

    @Override
    protected void paintIndeterminate(Graphics g2d, JComponent c) {
    }

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
    }

    @Override
    protected int getBoxLength(int availableLength, int otherDimension) {
        return availableLength;
    }

}