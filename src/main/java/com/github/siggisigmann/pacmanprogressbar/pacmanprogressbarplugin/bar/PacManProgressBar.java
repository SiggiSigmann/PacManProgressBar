package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.bar;

import com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.resourceManager.PacManIcons;
import com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.settings.PacManProgressBarState;
import com.intellij.openapi.ui.GraphicsConfig;
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
import java.util.Random;

public class PacManProgressBar extends BasicProgressBarUI {

    public static ComponentUI createUI(JComponent c) {
        c.setBorder(JBUI.Borders.empty().asUIResource());
        return new PacManProgressBar();
    }

    private static final int HEIGHT = 20;
    private static final int IMAGE_LENGTH = 95;

    private final PacManIcons icons;
    private final int pacManWidth;

    private final int randomPacMan;
    private final int randomFruit;

    public PacManProgressBar(){
        icons = new PacManIcons();
        pacManWidth = icons.getPacMan().getImage(0).getIconWidth();

        final Random rg = new Random();
        randomPacMan = rg.nextInt(icons.getPacMan().getSize());
        randomFruit = rg.nextInt(icons.getFruits().getSize());
    }

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
        switch(PacManProgressBarState.getInstance().getIndeterminateMode()){
            case PacManProgressBarState.OVERFLOW_MODE:
                drawOverflowingPacManAndGhosts(g2, width);
                break;
            case PacManProgressBarState.GAME_SIMULATION_MODE :
                drawLoadingBar(g2, height, offset+IMAGE_LENGTH);
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
            ImageIcon cherry;
            if(PacManProgressBarState.getInstance().isRandomPacman()){
                cherry = icons.getFruits().getImage(randomFruit);
            }else{
                cherry = icons.getFruits().getImage(PacManProgressBarState.getInstance().getFruitsStyle());
            }
            cherry.paintIcon(progressBar, g2, width - cherry.getIconWidth(), 0);


        }else{
            direction = (offset <= 0);
        }

        drawPacManAndGhosts(g2, offset + IMAGE_LENGTH, !direction);
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
        final int width = progressBar.getWidth();
        final int height = progressBar.getPreferredSize().height;
        final Insets insets = progressBar.getInsets();
        final int barRectWidth = width - (insets.right + insets.left);
        final int barRectHeight = height - (insets.top + insets.bottom);
        final int amountFull = getAmountFull(insets, barRectWidth - pacManWidth, barRectHeight);
        if (barRectWidth <= 0 || barRectHeight <= 0) return;
        final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);

        Shape clip = new RoundRectangle2D.Double(0, 0, width - 1, height- 1, 15, 15);
        g2.clip(clip);

        // background ##############################################################################################################
        drawDottedBackground(g2, width, height);

        drawLoadingBar(g2, height, amountFull+ pacManWidth);

        drawPacManAndGhosts(g2, amountFull+ pacManWidth);

        config.restore();
    }

    private long lastDotMove = System.currentTimeMillis();
    private int movingDotOffset = 0;
    private void drawDottedBackground(Graphics2D g2, int width, int height){
        //draw background
        g2.setColor(Color.BLACK);
        g2.fillRoundRect(0, 0, width, height, 3,3);

        boolean animatedDots = PacManProgressBarState.getInstance().isAnimatedDots();

        g2.setColor(Color.WHITE);
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
        g2.setColor(Color.BLACK);
        g2.fillRoundRect(0, 0, amountFull-10, height, 3,3);
    }

    private void drawPacManAndGhosts(Graphics2D g2, int amountFull, boolean direction){
        //draw pacman
        ImageIcon pacManIcon;
        if(PacManProgressBarState.getInstance().isRandomPacman()){
            pacManIcon = icons.getPacMan().getImage(randomPacMan, direction);
        }else{
            pacManIcon = icons.getPacMan().getImage(PacManProgressBarState.getInstance().getPacManStyle(), direction);
        }

        pacManIcon.paintIcon(progressBar, g2, amountFull - pacManWidth, 0);

        //draw ghosts
        ImageIcon[] ghosts = new ImageIcon[4];
        ghosts[0] = direction ? icons.getDead1Ghost() : icons.getPinkGhost();
        ghosts[1] = direction ? icons.getDead2Ghost() : icons.getBlueGhost();
        ghosts[2] = direction ? icons.getDead3Ghost() : icons.getRedGhost();
        ghosts[3] = direction ? icons.getDead4Ghost() : icons.getOrangeGhost();

        int offset = amountFull - pacManIcon.getIconWidth() - 5;
        for(ImageIcon ghost: ghosts){
            offset -= ghost.getIconWidth()-2;
            ghost.paintIcon(progressBar, g2, offset, 0);
        }
    }

    private void drawPacManAndGhosts(Graphics2D g2, int amountFull){
        drawPacManAndGhosts(g2, amountFull, false);
    }

    @Override
    protected int getBoxLength(int availableLength, int otherDimension) {
        return availableLength;
    }
}