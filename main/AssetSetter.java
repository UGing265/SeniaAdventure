package main;

import entity.NPC_OldMan;
import entity.redGuys;
import monster.MON_Merchant;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter{
    
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        //delete 
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX =23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;
    
        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;
    
        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 11 * gp.tileSize;

        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;

        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 22 * gp.tileSize;

        gp.obj[6] = new OBJ_Chest(gp);
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 7 * gp.tileSize;
    
        gp.obj[7] = new OBJ_Boots(gp);
        gp.obj[7].worldX = 37 * gp.tileSize;
        gp.obj[7].worldY = 42 * gp.tileSize;
    
    }
    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
        
        gp.npc[1] = new NPC_OldMan(gp);
        gp.npc[1].worldX = gp.tileSize*22;
        gp.npc[1].worldY = gp.tileSize*21;

        gp.npc[2] = new NPC_OldMan(gp);
        gp.npc[2].worldX = gp.tileSize*19;
        gp.npc[2].worldY = gp.tileSize*20;

        gp.npc[3] = new redGuys(gp);
        gp.npc[3].worldX = gp.tileSize*27;
        gp.npc[3].worldY = gp.tileSize*21;

        gp.npc[4] = new redGuys(gp);
        gp.npc[4].worldX = gp.tileSize*28;
        gp.npc[4].worldY = gp.tileSize*21;

        gp.npc[5] = new redGuys(gp);
        gp.npc[5].worldX = gp.tileSize*29;
        gp.npc[5].worldY = gp.tileSize*21;

        gp.npc[6] = new redGuys(gp);
        gp.npc[6].worldX = gp.tileSize*30;
        gp.npc[6].worldY = gp.tileSize*21;
        
        gp.npc[7] = new redGuys(gp);
        gp.npc[7].worldX = gp.tileSize*32;
        gp.npc[7].worldY = gp.tileSize*21;

        gp.npc[8] = new redGuys(gp);
        gp.npc[8].worldX = gp.tileSize*33;
        gp.npc[8].worldY = gp.tileSize*21;

        gp.npc[9] = new redGuys(gp);
        gp.npc[9].worldX = gp.tileSize*33;
        gp.npc[9].worldY = gp.tileSize*20;

        gp.npc[10] = new redGuys(gp);
        gp.npc[10].worldX = gp.tileSize*32;
        gp.npc[10].worldY = gp.tileSize*20;
    }

    public void setMonster(){

        gp.monster[0] = new MON_Merchant(gp);
        gp.monster[0].worldX = gp.tileSize*15;
        gp.monster[0].worldY = gp.tileSize*36;

        gp.monster[1] = new MON_Merchant(gp);
        gp.monster[1].worldX = gp.tileSize*23;
        gp.monster[1].worldY = gp.tileSize*36;
        
        gp.monster[2] = new MON_Merchant(gp);
        gp.monster[2].worldX = gp.tileSize*23;
        gp.monster[2].worldY = gp.tileSize*37;

        gp.monster[3] = new MON_Merchant(gp);
        gp.monster[3].worldX = gp.tileSize*23;
        gp.monster[3].worldY = gp.tileSize*38;

        gp.monster[4] = new MON_Merchant(gp);
        gp.monster[4].worldX = gp.tileSize*23;
        gp.monster[4].worldY = gp.tileSize*25;

        gp.monster[5] = new MON_Merchant(gp);
        gp.monster[5].worldX = gp.tileSize*24;
        gp.monster[5].worldY = gp.tileSize*24;

        gp.monster[6] = new MON_Merchant(gp);
        gp.monster[6].worldX = gp.tileSize*23;
        gp.monster[6].worldY = gp.tileSize*20;

        gp.monster[7] = new MON_Merchant(gp);
        gp.monster[7].worldX = gp.tileSize*22;
        gp.monster[7].worldY = gp.tileSize*20;

        gp.monster[8] = new MON_Merchant(gp);
        gp.monster[8].worldX = gp.tileSize*24;
        gp.monster[8].worldY = gp.tileSize*20;
        
        gp.monster[9] = new MON_Merchant(gp);
        gp.monster[9].worldX = gp.tileSize*25;
        gp.monster[9].worldY = gp.tileSize*20;

        gp.monster[10] = new MON_Merchant(gp);
        gp.monster[10].worldX = gp.tileSize*26;
        gp.monster[10].worldY = gp.tileSize*20;
    }
    // public void setRedGuys(){
    //     gp.npc[1] = new redGuys(gp);
    //     gp.npc[1].worldX = gp.tileSize*27;
    //     gp.npc[1].worldY = gp.tileSize*21;
    // }
}