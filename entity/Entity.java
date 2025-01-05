package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public String direction = "down";
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public boolean collisionOn = false;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    public boolean collision = false;
    public int type; // 0 = player, 1 = npc, 2 = monster

    // COUNTER
    public int actionLockCounter = 0;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public static int Counter;
    public boolean invincible = false; 
    public int invincibleCounter;

    // CHARACTER ATTIBUTES
    public String name;
    public int maxLife;
    public int life;
    public int speed;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {}
    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void update() {

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);//check monster and npc interfact with each
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true){
            if(gp.player.invincible == false){
                // give damage
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

        // IF COLISION IS FALSE, PLAYER CAN MOVE
        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
        spriteCounter++;
        if (spriteCounter > 8) { // speed Animation
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
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
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {// only load position player screen
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;

            }
            g2.drawImage(image, screenX, screenY, null);
            //  g2.setColor((Color.red));
            //  g2.drawRect(screenX +solidArea.x,screenY + solidArea.y , solidArea.width,solidArea.height);
            // troubleshoot collision Rectangles
        } // NOT WHOLE MAP

        g2.drawImage(image, (int) screenX, (int) screenY, gp.tileSize, gp.tileSize, null);

    }

    public BufferedImage setup(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res" + imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res" + imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}