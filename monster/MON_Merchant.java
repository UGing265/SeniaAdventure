package monster;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Merchant extends Entity{

    public MON_Merchant(GamePanel gp){
        super(gp);
        
        type = 2;
        name = "Merchant";
        speed = 2;
        maxLife = 12;
        life = maxLife;

        solidArea = new Rectangle(0,0,48,48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){
        up1 = setup("/npc/another/merchant_down_1");
        up2 = setup("/npc/another/merchant_down_2");
        down1 = setup("/npc/another/merchant_down_1");
        down2 = setup("/npc/another/merchant_down_2");
        left1 = setup("/npc/another/merchant_down_1");
        left2 = setup("/npc/another/merchant_down_2");
        right1 = setup("/npc/another/merchant_down_1");
        right2 = setup("/npc/another/merchant_down_2");
        
    }
    public void setAction(){
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up a number from 1 to 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

}