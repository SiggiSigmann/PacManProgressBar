package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.resourceManager;

import com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.settings.PacManProgressBarState;
import javax.swing.ImageIcon;
import java.util.Random;

public class AnimationManager {
    private final PacManIcons icons;

    private long tick = 0;
    private final int randomPacMan;
    private final int randomFruit;

    public AnimationManager(){
        this.tick();
        icons = new PacManIcons();

        Random rg = new Random();

        randomPacMan = rg.nextInt(icons.getPacMan().getSize());
        randomFruit = rg.nextInt(icons.getFruits().getSize());
    }

    public void tick(){
        tick = System.currentTimeMillis() / 100L;
    }

    public ImageIcon getPacMan(boolean direction){
        Animation pacMan;
        if(PacManProgressBarState.getInstance().isRandomPacman()){
            pacMan = icons.getPacMan().getImage(randomPacMan, direction);
        }else{
            pacMan = icons.getPacMan().getImage(PacManProgressBarState.getInstance().getPacManStyle(), direction);
        }

        return pacMan.getImage(tick);
    }

    public ImageIcon getGhost1(boolean direction){
        if(direction){
            return icons.getDead1Ghost().getImage(tick);
        }else{
            return icons.getPinkGhost().getImage(tick);
        }
    }

    public ImageIcon getGhost2(boolean direction){
        if(direction){
            return icons.getDead2Ghost().getImage(tick);
        }else{
            return icons.getBlueGhost().getImage(tick);
        }
    }

    public ImageIcon getGhost3(boolean direction){
        if(direction){
            return icons.getDead3Ghost().getImage(tick);
        }else{
            return icons.getRedGhost().getImage(tick);
        }
    }

    public ImageIcon getGhost4(boolean direction){
        if(direction){
            return icons.getDead4Ghost().getImage(tick);
        }else{
            return icons.getOrangeGhost().getImage(tick);
        }
    }

    public ImageIcon getFruit(){
        Animation fruit;
        if(PacManProgressBarState.getInstance().isRandomPacman()){
            fruit =  icons.getFruits().getImage(randomFruit, false);
        }else{
            fruit =  icons.getFruits().getImage(PacManProgressBarState.getInstance().getFruitsStyle(), false);
        }

        return fruit.getImage(tick);
    }
}
