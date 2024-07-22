package com.github.siggisigmann.pacmanprogressbar.pacmanprogressbarplugin.resourceManager;

import javax.swing.*;
import java.util.ArrayList;

public class StyleManager {

    private final ArrayList<NamedImage> imagesRight;
    private final ArrayList<ImageIcon> imagesLeft;

    public StyleManager(ArrayList<NamedImage> imagesRight, ArrayList<ImageIcon> imagesLeft){
        this.imagesLeft = new ArrayList<>(imagesLeft);
        this.imagesRight = new ArrayList<>(imagesRight);
    }

    public StyleManager(ArrayList<NamedImage> imagesRight){
        this.imagesRight = new ArrayList<>(imagesRight);
        this.imagesLeft = new ArrayList<>();
    }

    private int fixIndex(int style){
        final boolean isStyleOk = (style < imagesRight.size()) && (style >= 0);
        return isStyleOk ? style : 0;
    }

    public ImageIcon getImage(int style, boolean direction){
        final int correctedIndex = fixIndex(style);

        if(!direction || imagesLeft.isEmpty()){
            return imagesRight.get(correctedIndex).icon();
        }else{
            return imagesLeft.get(correctedIndex);
        }
    }

    public ImageIcon getImage(int style){
        return this.getImage(style, false);
    }

    public String getName(int style){
        final int correctedIndex = fixIndex(style);
        return imagesRight.get(correctedIndex).name();
    }

    public int getSize(){
        return imagesRight.size();
    }
}
