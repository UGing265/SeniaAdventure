package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.GamePanel;

public class redGuys extends Entity {

    public redGuys(GamePanel gp){
        super(gp);
        solidArea = new Rectangle(22, 38, 39, 45);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    
        direction = "down";
        speed = 2;

        getImage();
    }

    public void getImage() {
      

        up1 = setup("/npc/redGuys/redGuy_36",gp.tileSize + 22, gp.tileSize +22);
        up2 = setup("/npc/redGuys/redGuy_37",gp.tileSize + 22, gp.tileSize +22);
        up3 = setup("/npc/redGuys/redGuy_38",gp.tileSize + 22, gp.tileSize +22);
        down1 = setup("/npc/redGuys/redGuy_00",gp.tileSize + 22, gp.tileSize +22);
        down2 = setup("/npc/redGuys/redGuy_01",gp.tileSize + 22, gp.tileSize +22);
        down3 = setup("/npc/redGuys/redGuy_02",gp.tileSize + 22, gp.tileSize +22);
        left1 = setup("/npc/redGuys/redGuy_12",gp.tileSize + 22, gp.tileSize +22);
        left2 = setup("/npc/redGuys/redGuy_13",gp.tileSize + 22, gp.tileSize +22);
        left3 = setup("/npc/redGuys/redGuy_14",gp.tileSize + 22, gp.tileSize +22);
        right1 = setup("/npc/redGuys/redGuy_24",gp.tileSize + 22, gp.tileSize +22);
        right2 = setup("/npc/redGuys/redGuy_25",gp.tileSize + 22, gp.tileSize +22);
        right3 = setup("/npc/redGuys/redGuy_26",gp.tileSize + 22, gp.tileSize +22);
   
    }
    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1; // pick up a number from 1 to 100
    
            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }

        if (spriteCounter > 8) { // speed Animation
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
     }

     public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenX &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
        

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
                break;

        }
        g2.drawImage(image, screenX, screenY, null);
        g2.setColor((Color.red));
        g2.drawRect(screenX +solidArea.x, screenY + solidArea.y , solidArea.width,solidArea.height);
        System.out.println("X: "+solidArea.x+"Y: "+solidArea.y);
        // troubleshoot collision Rectangles
    }
   
    
}
