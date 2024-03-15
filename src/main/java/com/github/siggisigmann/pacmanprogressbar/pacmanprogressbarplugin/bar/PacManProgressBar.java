package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.bar;

import com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.settings.PacManProgressBarState;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;


import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

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

        Shape clip = new RoundRectangle2D.Double(0, 0, width - 1, hight- 1, 15, 15);
        g2.clip(clip);

        drawDottedBackground(g2, width, hight);

        drawLodingBar(g2, hight, amountFull);

        drawPacManAndGhosts(g2, amountFull);

        config.restore();
    }

    private long lastDotMove = System.currentTimeMillis()+50;
    private int movingDotOffset = 0;

    private void drawDottedBackground(Graphics2D g2, int width, int hight){
        //draw background
        g2.setColor(Color.BLACK);
        g2.fillRoundRect(0, 0, width, hight, 3,3);

        boolean animatedDots = PacManProgressBarState.getInstance().isAnimatedDots();

        g2.setColor(Color.WHITE);
        if(animatedDots){
            int animationSpeed = PacManProgressBarState.getInstance().getAnimationSpeed();

            if(System.currentTimeMillis() > lastDotMove){
                lastDotMove = System.currentTimeMillis() + animationSpeed;
                movingDotOffset++;
                if(movingDotOffset >= 7){
                    movingDotOffset = 0;
                }
            }

            for(int dotOffset = 0; (dotOffset-movingDotOffset)<width; dotOffset += 7){
                g2.fillOval(dotOffset-movingDotOffset, (hight/2)-1, 2, 2);
            }
        }else{
            for(int dotOffset = 0; dotOffset<width; dotOffset += 7){
                g2.fillOval(dotOffset, (hight/2)-1, 2, 2);
            }
        }
    }

    private void drawLodingBar(Graphics2D g2, int hight, int amountFull){
        g2.setColor(Color.BLACK);
        g2.fillRoundRect(0, 0, amountFull-10, hight, 3,3);
    }

    private void drawPacManAndGhosts(Graphics2D g2, int amountFull){
        //draw pacman
        ImageIcon pacManIcon = icons.getPacMan();
        pacManIcon.paintIcon(progressBar, g2, amountFull - pacManIcon.getIconWidth(), 0);

        //draw ghosts
        ImageIcon ghosts[] = new ImageIcon[4];
        ghosts[0] = icons.getPinkGhost();
        ghosts[1] = icons.getBlueGhost();
        ghosts[2] = icons.getRedGhost();
        ghosts[3] = icons.getOrangeGhost();

        int offset = amountFull - pacManIcon.getIconWidth() - 5;
        for(ImageIcon ghost: ghosts){
            offset -= ghost.getIconWidth()-2;
            ghost.paintIcon(progressBar, g2, offset, 0);
        }
    }

    @Override
    protected int getBoxLength(int availableLength, int otherDimension) {
        return availableLength;
    }
}