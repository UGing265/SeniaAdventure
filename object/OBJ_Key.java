package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Key extends SuperObject {
    GamePanel gp;

    public OBJ_Key(GamePanel gp) {

        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/Object/key.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // solidArea.x = 5 DONT USE because if we have a lot object we will tied
    }
}