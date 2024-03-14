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

    public static ComponentUI createUI(JComponent c) {
        c.setBorder(JBUI.Borders.empty().asUIResource());
        return new PacManProgressBar();
    }

    private static int HIGHT = 20;

    private PacManIcons icons = new PacManIcons();

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
    protected void paintIndeterminate(Graphics g, JComponent c) {
        if (!(g instanceof Graphics2D)) return;
        Graphics2D g2 = (Graphics2D) g;

    }

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        if (!(g instanceof Graphics2D)) return;
        Graphics2D g2 = (Graphics2D) g;

        //check if normal progressbar
        if (progressBar.getOrientation() != SwingConstants.HORIZONTAL || !c.getComponentOrientation().isLeftToRight()) {
            super.paintDeterminate(g, c);
            return;
        }

        //get default background color
        Container parent = c.getParent();
        Color defaultBackGroundColor = parent != null ? parent.getBackground() : UIUtil.getPanelBackground();

        //calc values
        int width = progressBar.getWidth();
        int hight = progressBar.getPreferredSize().height;
        Insets insets = progressBar.getInsets();
        int barRectWidth = width - (insets.right + insets.left);
        int barRectHeight = hight - (insets.top + insets.bottom);
        int amountFull = getAmountFull(insets, barRectWidth, barRectHeight);
        if (barRectWidth <= 0 || barRectHeight <= 0) return;
        final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);

        //draw background
        g2.setColor(Color.BLACK);
        g2.fillRoundRect(0, 0, width, hight, 3,3);

        g2.setColor(Color.WHITE);
        for(int dotOffset = 0; dotOffset<width; dotOffset += 10){
            g2.fillOval(dotOffset, (hight/2)-1, 2, 2);
        }

        //draw bar
        g2.setColor(Color.BLACK);
        g2.fillRoundRect(0, 0, amountFull, hight, 3,3);

        //draw pacman
        ImageIcon pacManIcon = icons.getPacMan();
        pacManIcon.paintIcon(progressBar, g2, amountFull - pacManIcon.getIconWidth(), 0);

        //draw ghosts
        ImageIcon pinkGhostIcon = icons.getPinkGhost();
        int offset = amountFull - pacManIcon.getIconWidth() - 10 - pinkGhostIcon.getIconWidth();;
        pinkGhostIcon.paintIcon(progressBar, g2, offset, 0);

        ImageIcon blueGhostIcon = icons.getBlueGhost();
        offset = offset - blueGhostIcon.getIconWidth();
        blueGhostIcon.paintIcon(progressBar, g2, offset, 0);

        ImageIcon redGhostIcon = icons.getRedGhost();
        offset = offset - redGhostIcon.getIconWidth();
        redGhostIcon.paintIcon(progressBar, g2, offset, 0);

        ImageIcon orangeGhostIcon = icons.getOrangeGhost();
        offset = offset - orangeGhostIcon.getIconWidth();
        orangeGhostIcon.paintIcon(progressBar, g2, offset, 0);

        config.restore();
    }

    @Override
    protected int getBoxLength(int availableLength, int otherDimension) {
        return availableLength;
    }

}