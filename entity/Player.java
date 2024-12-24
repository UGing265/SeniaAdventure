package entity;

import java.awt.Color;
//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.nio.Buffer;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;// imagine it's camera view
    public final int screenY;// final because DONT CHANGE
    public int hasKey = 0;
    int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); // make sure in center screen
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(25, 38, 23, 20);// max: 48 + 10
        // solidArea.x = 0; x and y mean distance
        // solidArea.y = 0;
        // solidArea.width = 48; if give empty object like Rectangle();
        // solidArea.height = 48; with and height mean hitbox
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/Seina/Layer 1_SEINA_37 up.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/Seina/Layer 1_SEINA_38 up.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/Seina/Layer 1_SEINA_39 up.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/res/Seina/Layer 1_SEINA_01 down.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/Seina/Layer 1_SEINA_02 down.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/Seina/Layer 1_SEINA_03 down.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/res/Seina/Layer 1_SEINA_13 left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/Seina/Layer 1_SEINA_14 left.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/Seina/Layer 1_SEINA_15 left.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/res/Seina/Layer 1_SEINA_25 right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/Seina/Layer 1_SEINA_26 right.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/Seina/Layer 1_SEINA_27 right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";

            }
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISON
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

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
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }else{
            standCounter++;
            if(standCounter == 20){
                spriteNum = 2; //stop walk :)
                standCounter = 0;
            }       
        }
    }

    public void pickUpObject(int i) {
        // i != 999 mean not touch any object Key
        if (i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null; // remove object
                    gp.ui.showMessage("You got a key!");
                    // System.out.println("Key:" + hasKey);
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door!");
                    } else {
                        gp.ui.showMessage("You need a key!");
                    }
                    // System.out.println("Key:" + hasKey);
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed += 10;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed up!");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }

        }
    }

    public void draw(Graphics2D g2) {
        System.out.format("\nX: %d -  Y: %d", worldX, worldY);
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

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
        g2.drawImage(image, screenX, screenY, gp.tileSize + 10, gp.tileSize + 10, null);
        //g2.setColor((Color.red));
        //g2.drawRect(screenX +solidArea.x,screenY + solidArea.y , solidArea.width, solidArea.height);
        //troubleshoot collision Rectangles
    }
}
