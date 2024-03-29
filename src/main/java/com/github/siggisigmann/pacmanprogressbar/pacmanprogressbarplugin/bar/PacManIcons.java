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

    static final String PINK_PATH = "/Pink.gif";
    private final ImageIcon pinkGhost;

    static final String RED_PATH = "/Red.gif";
    private final ImageIcon redGhost;

    PacManIcons(){
        blueGhost = new ImageIcon(cleanURL(BLUE_PATH));
        cherry = new ImageIcon(cleanURL(CHERRY_PATH));
        dead1Ghost = new ImageIcon(cleanURL(DEAD1_PATH));
        dead2Ghost = new ImageIcon(cleanURL(DEAD2_PATH));
        dead3Ghost = new ImageIcon(cleanURL(DEAD3_PATH));
        dead4Ghost = new ImageIcon(cleanURL(DEAD4_PATH));
        orangeGhost = new ImageIcon(cleanURL(ORANGE_PATH));
        pacManLeft = new ImageIcon(cleanURL(PACMAN_LEFT_PATH));
        pacManRight = new ImageIcon(cleanURL(PACMAN_RIGHT_PATH));
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

    public ImageIcon getPacManLeft(){
        return pacManLeft;
    }
    public ImageIcon getPacManRight(){
        return pacManRight;
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