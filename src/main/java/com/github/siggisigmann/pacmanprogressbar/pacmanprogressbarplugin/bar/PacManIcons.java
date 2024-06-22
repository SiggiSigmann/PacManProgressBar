package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.bar;

import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class PacManIcons {
    static final String BLUE_PATH = "/Blue.gif";
    private final ImageIcon blueGhost;

    static final String CHERRY_PATH = "/Cherry.gif";
    private final ImageIcon cherry;

    static final String DEAD1_PATH = "/Dead1.gif";
    private final ImageIcon dead1Ghost;

    static final String DEAD2_PATH = "/Dead2.gif";
    private final ImageIcon dead2Ghost;

    static final String DEAD3_PATH = "/Dead3.gif";
    private final ImageIcon dead3Ghost;

    static final String DEAD4_PATH = "/Dead4.gif";
    private final ImageIcon dead4Ghost;

    static final String ORANGE_PATH = "/Orange.gif";
    private final ImageIcon orangeGhost;

    static final String PACMAN_LEFT_PATH = "/PacManLeft.gif";
    private final ImageIcon pacManLeft;

    static final String PACMAN_RIGHT_PATH = "/PacManRight.gif";
    private final ImageIcon pacManRight;

    static final String MISS_PACMAN_LEFT_PATH = "/MissPacManLeft.gif";
    private final ImageIcon missPacManLeft;

    static final String MISS_PACMAN_RIGHT_PATH = "/MissPacManRight.gif";
    private final ImageIcon missPacManRight;

    static final String NINJA_PACMAN_LEFT_PATH = "/NinjaPacManLeft.gif";
    private final ImageIcon ninjaPacManLeft;

    static final String NINJA_PACMAN_RIGHT_PATH = "/NinjaPacManRight.gif";
    private final ImageIcon ninjaPacManRight;

    static final String PINK_PATH = "/Pink.gif";
    private final ImageIcon pinkGhost;

    static final String RED_PATH = "/Red.gif";
    private final ImageIcon redGhost;

    public PacManIcons(){
        blueGhost = new ImageIcon(cleanURL(BLUE_PATH));
        cherry = new ImageIcon(cleanURL(CHERRY_PATH));
        dead1Ghost = new ImageIcon(cleanURL(DEAD1_PATH));
        dead2Ghost = new ImageIcon(cleanURL(DEAD2_PATH));
        dead3Ghost = new ImageIcon(cleanURL(DEAD3_PATH));
        dead4Ghost = new ImageIcon(cleanURL(DEAD4_PATH));
        orangeGhost = new ImageIcon(cleanURL(ORANGE_PATH));
        pacManLeft = new ImageIcon(cleanURL(PACMAN_LEFT_PATH));
        pacManRight = new ImageIcon(cleanURL(PACMAN_RIGHT_PATH));
        missPacManLeft = new ImageIcon(cleanURL(MISS_PACMAN_LEFT_PATH));
        missPacManRight = new ImageIcon(cleanURL(MISS_PACMAN_RIGHT_PATH));
        ninjaPacManLeft = new ImageIcon(cleanURL(NINJA_PACMAN_LEFT_PATH));
        ninjaPacManRight = new ImageIcon(cleanURL(NINJA_PACMAN_RIGHT_PATH));
        pinkGhost = new ImageIcon(cleanURL(PINK_PATH));
        redGhost = new ImageIcon(cleanURL(RED_PATH));
    }

    public ImageIcon getBlueGhost(){
        return blueGhost;
    }

    public ImageIcon getCherry(){
        return cherry;
    }

    public ImageIcon getDead1Ghost(){
        return dead1Ghost;
    }

    public ImageIcon getDead2Ghost(){
        return dead2Ghost;
    }

    public ImageIcon getDead3Ghost(){
        return dead3Ghost;
    }

    public ImageIcon getDead4Ghost(){
        return dead4Ghost;
    }

    public ImageIcon getOrangeGhost(){
        return orangeGhost;
    }

    public ImageIcon getPacManLeft(int style){
        switch (style){
            case 0: return pacManLeft;
            case 1: return missPacManLeft;
            case 2: return ninjaPacManLeft;
            default: return pacManLeft;
        }
    }

    public ImageIcon getPacManRight(int style){
        switch (style){
            case 0: return pacManRight;
            case 1: return missPacManRight;
            case 2: return ninjaPacManRight;
            default: return pacManRight;
        }
    }

    public ImageIcon getPinkGhost(){
        return pinkGhost;
    }

    public ImageIcon getRedGhost(){
        return redGhost;
    }


    private URL cleanURL(String filePath){
        var url = this.getClass().getResource(filePath);
        if (url == null){
            try {
                url = new File(filePath).toURI().toURL();
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            }
        }
        return url;
    }
}