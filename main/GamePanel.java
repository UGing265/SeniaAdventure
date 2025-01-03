package main;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import title.TileManager;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int orginalTileSize = 16;
    final int scale = 4;

    public int tileSize = orginalTileSize * scale;// 1 tile scale to big screen
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol; // u can use if u want or remove
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[50]; // 50 objects slots
    public Entity npc[] = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();//all entity include: player, npc,...
    //and we dont need call draw when use SUPEROBJECT
    //Arraylist always order player,npc,... draw follow 1, 2, 3,...

    // GAME STATE (add new story :> )
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        //aSetter.setRedGuys();
        //playMusic(0);
        // stopMusic();
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // game Loop
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {

            // 1 UDATE: update information as character
            update();
            // 2 DRAW: draw the screen with the update
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                // avoid minus number
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                /*
                 * mean pause not do anything
                 * 1.000.000 nano = 1 second
                 * 0.0166666s for 60FPS
                 */
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            // PLAYER
            player.update();
            // NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            // nothing (we dont update anything)
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            // TILE
            tileM.draw(g2);       

            // ADD ENTITIES TO THE LIST
            entityList.add(player);

            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    entityList.add(npc[i]);
                }
            }

            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }

            // DOTY
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {
                    
                    int result = Integer.compare(e1.worldX, e2.worldY);
                    return result;
                }
                
            });

            // DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }

            // EMPTY ENTITY LIST
            for(int i = 0; i < entityList.size(); i++){
                entityList.remove(i);
            }


            // UI
            ui.draw(g2);
        }

        // DEBUG
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }

    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {

        se.setFile(i);
        se.play();
    }
}

// Set player's default position delete because it redency
// int playerX = 100;
// int playerY = 100;
// int playerSpeed = 4;

// public void zoomInOut(int i){
// int oldWorldWidth = tileSize * maxWorldCol;
// tileSize += i;
// int newWorldWidth = tileSize * maxWorldCol;

// double multiplier = (double)newWorldWidth/oldWorldWidth;

// System.out.println("tileSize: "+ tileSize);
// System.out.println("WorldWidth: "+newWorldWidth);
// System.out.println("player worldX: "+player.worldX);

// double newPlayerWorldX = player.worldX * multiplier;
// double newPlayerWorldY = player.worldY * multiplier;

// player.worldX = newPlayerWorldX;
// player.worldY = newPlayerWorldY;
// }