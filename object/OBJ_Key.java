package object;


import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp) {
        super(gp);

        name = "Key";
        down1 = setup("/Object/key");
   

        // solidArea.x = 5 DONT USE because if we have a lot object we will tied
    }
}