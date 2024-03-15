package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.bar;

import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;


import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
        for(int dotOffset = 0; dotOffset<width; dotOffset += 5){
            g2.fillOval(dotOffset, (hight/2)-1, 2, 2);
        }

        //draw bar
        g2.setColor(Color.BLACK);
        g2.fillRoundRect(0, 0, amountFull, hight, 3,3);

        //draw pacman
        ImageIcon pacManIcon = icons.getPacMan();
        pacManIcon.paintIcon(progressBar, g2, amountFull - pacManIcon.getIconWidth(), 0);

        //draw ghosts
        ImageIcon ghosts[] = new ImageIcon[4];
        ghosts[0] = icons.getPinkGhost();
        ghosts[1] = icons.getBlueGhost();
        ghosts[2] = icons.getRedGhost();
        ghosts[3] = icons.getOrangeGhost();

        int offset = amountFull - pacManIcon.getIconWidth() - 10;
        for(ImageIcon ghost: ghosts){
            offset -= ghost.getIconWidth();
            ghost.paintIcon(progressBar, g2, offset, 0);
        }

        config.restore();
    }

    @Override
    protected int getBoxLength(int availableLength, int otherDimension) {
        return availableLength;
    }
}