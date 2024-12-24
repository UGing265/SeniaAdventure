package main;

import javax.swing.JFrame;

import entity.Entity;



public class Main{
    public static void main(String[] args){
    
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D SEINA");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

        gamePanel.setupGame();
        System.out.println();  
    }
}