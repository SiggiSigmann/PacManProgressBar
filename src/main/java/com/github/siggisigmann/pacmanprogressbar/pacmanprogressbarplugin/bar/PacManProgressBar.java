package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.bar;

import com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.settings.PacManProgressBarState;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;

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

    private static final int HEIGHT = 20;
    private static final int IMAGE_LENGTH = 95;

    private final PacManIcons icons = new PacManIcons();

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(super.getPreferredSize(c).width, JBUI.scale(HEIGHT));
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

    @Override
    protected void paintIndeterminate(Graphics g, JComponent c) {
        if (!(g instanceof Graphics2D)) return;
        Graphics2D g2 = (Graphics2D) g;

        //check if normal progressbar
        if (progressBar.getOrientation() != SwingConstants.HORIZONTAL || !c.getComponentOrientation().isLeftToRight()) {
            super.paintDeterminate(g, c);
            return;
        }
        //calc values
        int width = progressBar.getWidth();
        int height = progressBar.getPreferredSize().height;
        Insets insets = progressBar.getInsets();
        int barRectWidth = width - (insets.right + insets.left);
        int barRectHeight = height - (insets.top + insets.bottom);
        if (barRectWidth <= 0 || barRectHeight <= 0) return;
        final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);

        Shape clip = new RoundRectangle2D.Double(0, 0, width - 1, height- 1, 15, 15);
        g2.clip(clip);

        // background ##############################################################################################################
        drawDottedBackground(g2, width, height);

        // foreground ##############################################################################################################
        drawLoadingBar(g2, height, offset+IMAGE_LENGTH);

        switch(PacManProgressBarState.getInstance().getIndeterminateMode()){
            case PacManProgressBarState.OVERFLOW_MODE:
                drawOverflowingPacManAndGhosts(g2, width);
                break;
            case PacManProgressBarState.GAME_SIMULATION_MODE :
                drawGameSimulation(g2, width);
                break;
            default: drawOverflowingPacManAndGhosts(g2, width);
        }

        config.restore();
    }

    private int offset = 0;
    private void drawOverflowingPacManAndGhosts(Graphics2D g2, int width){
        if(System.currentTimeMillis() > lastPacManMove){
            offset++;

            int animationSpeed = PacManProgressBarState.getInstance().getPacManAnimationSpeed();
            lastPacManMove = System.currentTimeMillis() + animationSpeed;
        }
        if (offset >= width) {
            offset = 0;
        }
        drawPacManAndGhosts(g2, offset + IMAGE_LENGTH);
        if(offset >= (width - IMAGE_LENGTH)){
            drawPacManAndGhosts(g2, (offset-width+IMAGE_LENGTH));
        }
    }

    private boolean direction = true;
    private long lastPacManMove = System.currentTimeMillis();
    private void drawGameSimulation(Graphics2D g2, int width){
        if(System.currentTimeMillis() > lastPacManMove){
            offset += direction? 1 : -1;

            int animationSpeed = PacManProgressBarState.getInstance().getPacManAnimationSpeed();
            lastPacManMove = System.currentTimeMillis() + animationSpeed;
        }

        if(direction){
            direction = !(offset >= (width-IMAGE_LENGTH));
            ImageIcon cherry = icons.getCherry();
            cherry.paintIcon(progressBar, g2, width - cherry.getIconWidth(), 0);

            drawPacManAndGhosts(g2, offset + IMAGE_LENGTH);
        }else{
            direction = (offset <= 0);
            drawPacManAndDeadGhosts(g2, offset + IMAGE_LENGTH);
        }

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

        //calc values
        int width = progressBar.getWidth();
        int height = progressBar.getPreferredSize().height;
        Insets insets = progressBar.getInsets();
        int barRectWidth = width - (insets.right + insets.left);
        int barRectHeight = height - (insets.top + insets.bottom);
        int amountFull = getAmountFull(insets, barRectWidth, barRectHeight);
        if (barRectWidth <= 0 || barRectHeight <= 0) return;
        final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);

        Shape clip = new RoundRectangle2D.Double(0, 0, width - 1, height- 1, 15, 15);
        g2.clip(clip);

        // background ##############################################################################################################
        drawDottedBackground(g2, width, height);

        drawLoadingBar(g2, height, amountFull);

        drawPacManAndGhosts(g2, amountFull);

        config.restore();
    }

    private long lastDotMove = System.currentTimeMillis();
    private int movingDotOffset = 0;
    private void drawDottedBackground(Graphics2D g2, int width, int height){
        //draw background
        g2.setColor(JBColor.BLACK);
        g2.fillRoundRect(0, 0, width, height, 3,3);

        boolean animatedDots = PacManProgressBarState.getInstance().isAnimatedDots();

        g2.setColor(JBColor.WHITE);
        if(animatedDots){
            int animationSpeed = PacManProgressBarState.getInstance().getDotAnimationSpeed();

            if(System.currentTimeMillis() > lastDotMove){
                lastDotMove = System.currentTimeMillis() + animationSpeed;
                movingDotOffset++;
                if(movingDotOffset >= 7){
                    movingDotOffset = 0;
                }
            }

            for(int dotOffset = 0; (dotOffset-movingDotOffset)<width; dotOffset += 7){
                g2.fillOval(dotOffset-movingDotOffset, (height/2)-1, 2, 2);
            }
        }else{
            for(int dotOffset = 0; dotOffset<width; dotOffset += 7){
                g2.fillOval(dotOffset, (height/2)-1, 2, 2);
            }
        }
    }

    private void drawLoadingBar(Graphics2D g2, int height, int amountFull){
        g2.setColor(JBColor.BLACK);
        g2.fillRoundRect(0, 0, amountFull-10, height, 3,3);
    }

    private void drawPacManAndGhosts(Graphics2D g2, int amountFull){
        //draw pacman
        ImageIcon pacManIcon = icons.getPacManRight();
        pacManIcon.paintIcon(progressBar, g2, amountFull - pacManIcon.getIconWidth(), 0);

        //draw ghosts
        ImageIcon[] ghosts = new ImageIcon[4];
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

    private void drawPacManAndDeadGhosts(Graphics2D g2, int amountFull){
        //draw pacman
        ImageIcon pacManIcon = icons.getPacManLeft();
        pacManIcon.paintIcon(progressBar, g2, amountFull - pacManIcon.getIconWidth(), 0);

        //draw ghosts
        ImageIcon[] ghosts = new ImageIcon[4];
        ghosts[0] = icons.getDead1Ghost();
        ghosts[1] = icons.getDead2Ghost();
        ghosts[2] = icons.getDead3Ghost();
        ghosts[3] = icons.getDead4Ghost();

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