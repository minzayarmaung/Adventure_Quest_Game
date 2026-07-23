package Main;

import javax.swing.SwingConstants;
import java.awt.*;

public class SceneChanger {

    GameManager gm;

    public SceneChanger(GameManager gm) {
        this.gm = gm;
    }

    public void showScene1(){
        gm.ui.bgPanel[1].setVisible(true);
        gm.ui.bgPanel[2].setVisible(false);
        gm.ui.bgPanel[3].setVisible(false);
        gm.ui.messageText.setText("You are in a small village surrounded by a dense forest. There is a hut nearby and a guard standing at the entrance of the village.");

        gm.stopMusic(gm.currentMusic);
        gm.currentMusic = gm.fieldMusic;
        gm.playMusic(gm.currentMusic);
    }

    public void showScene2(){
        gm.ui.bgPanel[1].setVisible(false);
        gm.ui.bgPanel[2].setVisible(true);
        gm.ui.bgPanel[3].setVisible(false);
        gm.ui.messageText.setText("You are in a dark cave. It's very quiet here.");

        gm.stopMusic(gm.currentMusic);
        gm.currentMusic = gm.caveSound;
        gm.playMusic(gm.currentMusic);
    }

    public void showScene3(){
        gm.ui.bgPanel[1].setVisible(false);
        gm.ui.bgPanel[2].setVisible(false);
        gm.ui.bgPanel[3].setVisible(true);
        gm.ui.messageText.setText("You are in a dark forest. The trees are tall and the path is barely visible.");

        gm.stopMusic(gm.currentMusic);
        gm.currentMusic = gm.forestSound;
        gm.playMusic(gm.currentMusic);

        // Trigger forest encounter when entering the scene
        if (gm.event01 != null) {
            gm.event01.enterForest();
        }
    }

    public void showGameOverScreen(int currentBgNum){
        // Mark the game as finished so other actions are ignored
        gm.setGameOver(true);

        // DO NOT set bgPanel to false here, or the monster/scene disappears!
        // Configure title label (YOU DIED)
        gm.ui.titleLabel.setText("YOU DIED");
        gm.ui.titleLabel.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 72));
        gm.ui.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gm.ui.titleLabel.setBounds(150, 120, 500, 100);
        gm.ui.titleLabel.setForeground(Color.RED);
        gm.ui.titleLabel.setVisible(true);

        // Show restart button
        gm.ui.restartButton.setText("Click here to restart");
        gm.ui.restartButton.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 24));
        gm.ui.restartButton.setBounds(200, 300, 400, 50);
        gm.ui.restartButton.setBorderPainted(false);
        gm.ui.restartButton.setVisible(true);

        gm.playSE(gm.deathSound);
        gm.stopMusic(gm.currentMusic);
        gm.currentMusic = gm.fieldMusic2;
        gm.playMusic(gm.currentMusic);
    }

    public void existsGameOverScreen(){
        // Clear game finished flag so player can interact again
        gm.setGameOver(false);

        gm.ui.titleLabel.setVisible(false);
        gm.ui.restartButton.setVisible(false);
        // Remove any visible monsters/chests and reset event state before resetting player
        if (gm.ui != null) {
            gm.ui.removeMonster();
            gm.ui.removeGoldChest();
        }
        if (gm.event01 != null) {
            gm.event01.resetEvent();
        }
        gm.player.setPlayerDefaultStatus();
    }

    public void showVictoryScreen(){
        // Mark game finished so interactions are disabled
        gm.setGameOver(true);

        // Configure title label (CONGRATULATIONS)
        gm.ui.titleLabel.setText("CONGRATULATIONS");
        gm.ui.titleLabel.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 56));
        gm.ui.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gm.ui.titleLabel.setBounds(100, 120, 600, 100);
        gm.ui.titleLabel.setForeground(new Color(0, 128, 0));
        gm.ui.titleLabel.setVisible(true);

        // Show play again button
        gm.ui.restartButton.setText("Play Again");
        gm.ui.restartButton.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 24));
        gm.ui.restartButton.setBounds(250, 300, 300, 50);
        gm.ui.restartButton.setBorderPainted(false);
        gm.ui.restartButton.setVisible(true);
    }
}