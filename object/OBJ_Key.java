package object;

import java.awt.image.ImagingOpException;
import java.io.IOException;

public class OBJ_Key extends SuperObject{
    public OBJ_Key(){

        name = "key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Object/key.png"))

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}