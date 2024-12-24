package main;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import title.TileManager;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    
    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; // 10 objects slots

    // Set player's default position delete because it redency
    // int playerX = 100;
    // int playerY = 100;
    // int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }
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

    public void setupGame() {
        aSetter.setObject();
        playMusic(0);
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
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // TILE
        tileM.draw(g2);

        // OBJECT
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // PLAYER
        player.draw(g2);

        // UI
        ui.draw(g2);
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