package title;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/worldV2.txt");
    }

    public void getTileImage() {
        // PLACEHOLDER
        setup(0, "001", false);// grass
        setup(1, "001", false);// wall
        setup(2, "001", false);// water
        setup(3, "001", false);// dirt
        setup(4, "001", false);// tree
        setup(5, "001", false);// sand
        setup(6, "001", false);// sand
        setup(7, "001", false);// sand
        setup(8, "001", false);// sand
        setup(9, "001", false);// sand
        // PLACEHOLDER avoid nullPointer

        setup(10, "001", false);// grass
        setup(11, "002", false);// grass v2
        setup(12, "018", true);// water
        setup(13, "019", true);// water
        setup(14, "020", true);// water
        setup(15, "021", true);// water
        setup(16, "022", true);// water
        setup(17, "023", true);// water
        setup(18, "024", true);// water
        setup(19, "025", true);// water
        setup(20, "026", true);// water
        setup(21, "027", true);// water
        setup(22, "028", true);// water
        setup(23, "029", true);// water
        setup(24, "030", true);// water
        setup(25, "031", true);// water
        setup(26, "003", false);// road
        setup(27, "004", false);// road
        setup(28, "005", false);// road
        setup(29, "006", false);// road
        setup(30, "007", false);// road
        setup(31, "008", false);// road
        setup(32, "009", false);// road
        setup(33, "010", false);// road
        setup(34, "011", false);// road
        setup(35, "012", false);// road
        setup(36, "013", false);// road
        setup(37, "014", false);// road
        setup(38, "015", false);// road
        setup(39, "017", false);// earth
        setup(40, "032", true);// wall
        setup(41, "016", true);// tree

        setup(42, "016", false);// tree
        setup(43, "016", false);// tree
        setup(44, "016", false);// tree
        setup(45, "016", false);// tree
        setup(46, "016", false);// tree
        setup(47, "016", false);// tree

    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
            for (int i = 0; i < mapTileNum.length; i++) {
                System.out.println(i);
                for (int j = 0; j < mapTileNum.length; j++) {
                    System.out.print(mapTileNum[i][j] + " ");
                }

            }

        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {

        int WorldCol = 0;
        int WorldRow = 0;
        // a bit confuse
        while (WorldCol < gp.maxWorldCol && WorldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[WorldCol][WorldRow];

            int worldX = WorldCol * gp.tileSize;
            int worldY = WorldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenX &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {// only load position player screen
                                                                                  // NOT WHOLE MAP

                g2.drawImage(tile[tileNum].image, (int) screenX, (int) screenY, null);
            }
            WorldCol++;

            if (WorldCol == gp.maxWorldCol) {
                WorldCol = 0;
                WorldRow++;

            }

        }
    }
}

/*
 * old method make bad performace because create Object in Loop
 * 
 * tile[0] = new Tile();
 * tile[0].image =
 * ImageIO.read(getClass().getResourceAsStream("/res/title/002.png"));// grass
 * 
 * tile[1] = new Tile();
 * tile[1].image =
 * ImageIO.read(getClass().getResourceAsStream("/res/title/032.png"));// wall
 * tile[1].collision = true;
 * 
 * tile[2] = new Tile();
 * tile[2].image =
 * ImageIO.read(getClass().getResourceAsStream("/res/title/018.png"));// water
 * tile[2].collision = true;
 * 
 * tile[3] = new Tile();
 * tile[3].image =
 * ImageIO.read(getClass().getResourceAsStream("/res/title/017.png")); // dirt
 * 
 * tile[4] = new Tile();
 * tile[4].image =
 * ImageIO.read(getClass().getResourceAsStream("/res/title/016.png")); // tree
 * tile[4].collision = true;
 * 
 * tile[5] = new Tile();
 * tile[5].image =
 * ImageIO.read(getClass().getResourceAsStream("/res/title/003.png")); // sand
 * 
 * 
 * then g2.drawImage(tile[tileNum].image, (int) screenX, (int) screenY,
 * gp.tileSize, gp.tileSize, null);
 */