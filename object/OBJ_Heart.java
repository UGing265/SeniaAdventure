package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {
  

    public OBJ_Heart(GamePanel gp) {
        super(gp);

        name = "Heart";
        image = setup("/Object/heart_full");
        image2 = setup("/Object/heart_half");
        image3 = setup("/Object/heart_blank");
       
    }
}
