package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.resourceManager;

import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class PacManIcons {
    private final ImageIcon blueGhost;
    private final ImageIcon orangeGhost;
    private final ImageIcon pinkGhost;
    private final ImageIcon redGhost;

    private final ImageIcon dead1Ghost;
    private final ImageIcon dead2Ghost;
    private final ImageIcon dead3Ghost;
    private final ImageIcon dead4Ghost;


    //pag mans
    private final StyleManager pacManStyle;

    private final ImageIcon pacManLeft;
    private final ImageIcon pacManRight;
    private final ImageIcon missPacManLeft;
    private final ImageIcon missPacManRight;
    private final ImageIcon ninjaPacManLeft;
    private final ImageIcon ninjaPacManRight;
    private final ImageIcon pizzaPacManLeft;
    private final ImageIcon pizzaPacManRight;
    private final ImageIcon cowBoyPacManLeft;
    private final ImageIcon cowBoyPacManRight;

    //fruits
    private final StyleManager fruitStyle;

    private final ImageIcon cherry;
    private final ImageIcon cake;
    private final ImageIcon strawberry;
    private final ImageIcon banana;


    public PacManIcons(){
        //ghosts
        blueGhost = new ImageIcon(cleanURL("/ghosts/Blue.gif"));
        orangeGhost = new ImageIcon(cleanURL("/ghosts/Orange.gif"));
        pinkGhost = new ImageIcon(cleanURL("/ghosts/Pink.gif"));
        redGhost = new ImageIcon(cleanURL("/ghosts/Red.gif"));

        //ghosts dead
        dead1Ghost = new ImageIcon(cleanURL("/ghosts/Dead1.gif"));
        dead2Ghost = new ImageIcon(cleanURL("/ghosts/Dead2.gif"));
        dead3Ghost = new ImageIcon(cleanURL("/ghosts/Dead3.gif"));
        dead4Ghost = new ImageIcon(cleanURL("/ghosts/Dead4.gif"));

        //PagMan
        pacManLeft = new ImageIcon(cleanURL("/pacMan/PacManLeft.gif"));
        pacManRight = new ImageIcon(cleanURL("/pacMan/PacManRight.gif"));
        missPacManLeft = new ImageIcon(cleanURL("/pacMan/MissPacManLeft.gif"));
        missPacManRight = new ImageIcon(cleanURL("/pacMan/MissPacManRight.gif"));
        ninjaPacManLeft = new ImageIcon(cleanURL("/pacMan/NinjaPacManLeft.gif"));
        ninjaPacManRight = new ImageIcon(cleanURL("/pacMan/NinjaPacManRight.gif"));
        pizzaPacManLeft = new ImageIcon(cleanURL("/pacMan/PizzaManLeft.gif"));
        pizzaPacManRight = new ImageIcon(cleanURL("/pacMan/PizzaManRight.gif"));
        cowBoyPacManLeft = new ImageIcon(cleanURL("/pacMan/CowBoyLeft.gif"));
        cowBoyPacManRight = new ImageIcon(cleanURL("/pacMan/CowBoyRight.gif"));
        ArrayList<NamedImage> rightPacMans = new ArrayList<>(Arrays.asList(
                new NamedImage("Original PacMan",pacManRight),
                new NamedImage("Miss PacMan", missPacManRight),
                new NamedImage("Ninja PacMan", ninjaPacManRight),
                new NamedImage("Pizza PacMan", pizzaPacManRight),
                new NamedImage("CowBoy PacMan", cowBoyPacManRight)
        ));
        ArrayList<ImageIcon> leftPacMans = new ArrayList<>(Arrays.asList(pacManLeft, missPacManLeft, ninjaPacManLeft, pizzaPacManLeft, cowBoyPacManLeft));
        pacManStyle = new StyleManager(rightPacMans, leftPacMans);

        //fruits
        cherry = new ImageIcon(cleanURL("/fruits/Cherry.gif"));
        strawberry = new ImageIcon(cleanURL("/fruits/Strawberry.gif"));
        cake = new ImageIcon(cleanURL("/fruits/Cake.gif"));
        banana = new ImageIcon(cleanURL("/fruits/banana.gif"));
        ArrayList<NamedImage> fritsList = new ArrayList<>(Arrays.asList(
                new NamedImage("Cherry", cherry),
                new NamedImage("Strawberry", strawberry),
                new NamedImage("Cake", cake),
                new NamedImage("Banana", banana)
        ));
        fruitStyle = new StyleManager(fritsList);
    }


    //ghosts
    public ImageIcon getBlueGhost(){
        return blueGhost;
    }

    public ImageIcon getOrangeGhost(){
        return orangeGhost;
    }

    public ImageIcon getPinkGhost(){
        return pinkGhost;
    }

    public ImageIcon getRedGhost(){
        return redGhost;
    }


    //ghosts dead
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


    //PacMan
    public StyleManager getPacMan(){
        return pacManStyle;
    }


    //Fruits
    public StyleManager getFruits(){
        return fruitStyle;
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