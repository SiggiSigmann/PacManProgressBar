package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.bar;

import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class PacManIcons {
    static final String BLUE_PATH = "/Blue.gif";
    private ImageIcon blueGhost;

    static final String CHERRY_PATH = "/Cherry.gif";
    private ImageIcon cherry;

    static final String DEAD_PATH = "/Dead.gif";
    private ImageIcon deadGhost;

    static final String ORANGE_PATH = "/Orange.gif";
    private ImageIcon orangeGhost;

    static final String PACMAN_PATH = "/PacMan.gif";
    private ImageIcon pacMan;

    static final String PINK_PATH = "/Pink.gif";
    private ImageIcon pinkGhost;

    static final String RED_PATH = "/Red.gif";
    private ImageIcon redGhost;

    PacManIcons(){
        blueGhost = new ImageIcon(cleanURL(BLUE_PATH));
        cherry = new ImageIcon(cleanURL(CHERRY_PATH));
        deadGhost = new ImageIcon(cleanURL(DEAD_PATH));
        orangeGhost = new ImageIcon(cleanURL(ORANGE_PATH));
        pacMan = new ImageIcon(cleanURL(PACMAN_PATH));
        pinkGhost = new ImageIcon(cleanURL(PINK_PATH));
        redGhost = new ImageIcon(cleanURL(RED_PATH));
    }

    public ImageIcon getBlueGhost(){
        return blueGhost;
    }

    public ImageIcon getCherry(){
        return cherry;
    }

    public ImageIcon getDeadGhost(){
        return deadGhost;
    }

    public ImageIcon getOrangeGhost(){
        return orangeGhost;
    }

    public ImageIcon getPacMan(){
        return pacMan;
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
                e.printStackTrace();
            }
        }
        return url;
    }
}