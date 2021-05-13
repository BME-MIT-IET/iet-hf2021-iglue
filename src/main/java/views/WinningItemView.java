package views;

import Field.Field;
import Item.WinningItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * nyero item nezet
 */
public class WinningItemView implements IView{

    WinningItem winningItem;
    BufferedImage image;
    BufferedImage imageO;
    public WinningItemView(WinningItem w){
        winningItem = w;
        String path = null;
        String path2 = null;
        if(w.id == 0){
            path = "src/main/resources/flare.png";
            path2 = "src/main/resources/flare_fifty.png";
        }
        else if (w.id == 1) {
            path = "src/main/resources/gun.png";
            path2 = "src/main/resources/gun_fifty.png";
        }
        else if (w.id == 2) {
            path = "src/main/resources/cartridge.png";
            path2 = "src/main/resources/cartridge_fifty.png";
        }
        try{
            image = (BufferedImage) ImageIO.read(new File(path));
            imageO = (BufferedImage) ImageIO.read(new File(path2));
        }
        catch(IOException e){
            System.out.println("Valami baj van a winningitem i/o-val");
        }
    }

    /**
     * nyero item rajzolas
     * @param graphics
     */
    @Override
    public void Draw(Graphics graphics) {
        if (winningItem.getHolder() == null)   {
            Field field = winningItem.getField();
            if(field.getLayerOfSnow()==0){
                if(field.isOpen())graphics.drawImage(image, field.X*64, field.Y*64, null );
                else graphics.drawImage(imageO, field.X*64, field.Y*64, null );
            }
        }
    }

}
