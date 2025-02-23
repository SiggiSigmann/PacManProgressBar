package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.resourceManager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class PacManIcons {
    private final Animation blueGhost;
    private final Animation orangeGhost;
    private final Animation pinkGhost;
    private final Animation redGhost;

    private final Animation dead1Ghost;
    private final Animation dead2Ghost;
    private final Animation dead3Ghost;
    private final Animation dead4Ghost;

    //pacmans
    private final StyleManager pacManStyle;

    private final Animation pacManLeft;
    private final Animation pacManRight;
    private final Animation missPacManLeft;
    private final Animation missPacManRight;
    private final Animation ninjaPacManLeft;
    private final Animation ninjaPacManRight;
    private final Animation pizzaPacManLeft;
    private final Animation pizzaPacManRight;
    private final Animation cowBoyPacManLeft;
    private final Animation cowBoyPacManRight;

    //fruits
    private final StyleManager fruitStyle;

    private final Animation cherry;
    private final Animation cake;
    private final Animation strawberry;
    private final Animation banana;

    public PacManIcons(){
        //ghosts
        blueGhost = new Animation("/ghosts/Blue", 13);
        orangeGhost = new Animation("/ghosts/Orange", 13);
        pinkGhost = new Animation("/ghosts/Pink", 13);
        redGhost = new Animation("/ghosts/Red", 13);

        //ghosts dead
        dead1Ghost = new Animation("/ghosts/Dead1", 13);
        dead2Ghost = new Animation("/ghosts/Dead2", 13);
        dead3Ghost = new Animation("/ghosts/Dead3", 13);
        dead4Ghost = new Animation("/ghosts/Dead4", 13);

        //PagMan
        pacManLeft = new Animation("/pacMan/PacManLeft", 7);
        pacManRight = new Animation("/pacMan/PacManRight", 7);
        missPacManLeft = new Animation("/pacMan/MissPacManLeft", 7);
        missPacManRight = new Animation("/pacMan/MissPacManRight", 7);
        ninjaPacManLeft = new Animation("/pacMan/NinjaPacManLeft", 7);
        ninjaPacManRight = new Animation("/pacMan/NinjaPacManRight", 7);
        pizzaPacManLeft = new Animation("/pacMan/PizzaManLeft", 7);
        pizzaPacManRight = new Animation("/pacMan/PizzaManRight", 7);
        cowBoyPacManLeft = new Animation("/pacMan/CowBoyLeft", 7);
        cowBoyPacManRight = new Animation("/pacMan/CowBoyRight", 7);
        ArrayList<NamedImage> rightPacMans = new ArrayList<>(Arrays.asList(
                new NamedImage("Original PacMan",pacManRight),
                new NamedImage("Miss PacMan", missPacManRight),
                new NamedImage("Ninja PacMan", ninjaPacManRight),
                new NamedImage("Pizza PacMan", pizzaPacManRight),
                new NamedImage("CowBoy PacMan", cowBoyPacManRight)
        ));
        ArrayList<Animation> leftPacMans = new ArrayList<>(Arrays.asList(pacManLeft, missPacManLeft, ninjaPacManLeft, pizzaPacManLeft, cowBoyPacManLeft));
        pacManStyle = new StyleManager(rightPacMans, leftPacMans);

        //fruits
        cherry = new Animation("/fruits/Cherry", 1);
        strawberry = new Animation("/fruits/Strawberry", 1);
        cake = new Animation("/fruits/Cake", 1);
        banana = new Animation("/fruits/banana", 1);
        ArrayList<NamedImage> fritsList = new ArrayList<>(Arrays.asList(
                new NamedImage("Cherry", cherry),
                new NamedImage("Strawberry", strawberry),
                new NamedImage("Cake", cake),
                new NamedImage("Banana", banana)
        ));
        fruitStyle = new StyleManager(fritsList);
    }


    //ghosts
    public Animation getBlueGhost(){
        return blueGhost;
    }

    public Animation getOrangeGhost(){
        return orangeGhost;
    }

    public Animation getPinkGhost(){
        return pinkGhost;
    }

    public Animation getRedGhost(){
        return redGhost;
    }


    //ghosts dead
    public Animation getDead1Ghost(){
        return dead1Ghost;
    }

    public Animation getDead2Ghost(){
        return dead2Ghost;
    }

    public Animation getDead3Ghost(){
        return dead3Ghost;
    }

    public Animation getDead4Ghost(){
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