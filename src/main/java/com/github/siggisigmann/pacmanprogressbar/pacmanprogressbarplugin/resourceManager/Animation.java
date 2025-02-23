package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.resourceManager;

import org.bouncycastle.util.Exceptions;

import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Animation {
    ImageIcon[] frames;

    public Animation(String path, int framesCount){
        try{
            frames = new ImageIcon[framesCount];

            for(int f = 0; f < framesCount; f++){
                URL frameURL = cleanURL(path + File.separator + f + ".png");
                ImageIcon icon = new ImageIcon(frameURL);
                if(icon == null){throw new Error("no image found");}
                frames[f] = icon;
            }

        }catch(Exception e){
            System.out.println("Folder not found or empty.");
        }
    }

    public ImageIcon getImage(long tick){
        int frame = (int) (tick % frames.length);
        return frames[frame];
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
