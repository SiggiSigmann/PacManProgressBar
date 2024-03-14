package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.components;

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

    PacManIcons(){}

    public ImageIcon getBlueGhost(){
        if(blueGhost == null) blueGhost = new ImageIcon(cleanURL(BLUE_PATH));
        return blueGhost;
    }

    public ImageIcon getCherry(){
        if(cherry == null) cherry = new ImageIcon(cleanURL(CHERRY_PATH));
        return cherry;
    }

    public ImageIcon getDeadGhost(){
        if(deadGhost == null) deadGhost = new ImageIcon(cleanURL(DEAD_PATH));
        return deadGhost;
    }

    public ImageIcon getOrangeGhost(){
        if(orangeGhost == null) orangeGhost = new ImageIcon(cleanURL(ORANGE_PATH));
        return orangeGhost;
    }

    public ImageIcon getPacMan(){
        if(pacMan == null) pacMan = new ImageIcon(cleanURL(PACMAN_PATH));
        return pacMan;
    }

    public ImageIcon getPinkGhost(){
        if(pinkGhost == null) pinkGhost = new ImageIcon(cleanURL(PINK_PATH));
        return pinkGhost;
    }

    public ImageIcon getRedGhost(){
        if(redGhost == null) redGhost = new ImageIcon(cleanURL(RED_PATH));
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