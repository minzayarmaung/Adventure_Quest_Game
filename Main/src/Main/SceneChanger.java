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
    }

    public void showScene2(){
        gm.ui.bgPanel[1].setVisible(false);
        gm.ui.bgPanel[2].setVisible(true);
        gm.ui.bgPanel[3].setVisible(false);
        gm.ui.messageText.setText("You are in a dark cave. It's very quiet here.");
    }

    public void showScene3(){
        gm.ui.bgPanel[1].setVisible(false);
        gm.ui.bgPanel[2].setVisible(false);
        gm.ui.bgPanel[3].setVisible(true);
        gm.ui.messageText.setText("You are in a dark forest. The trees are tall and the path is barely visible.");
    }

    public void showGameOverScreen(int currentBgNum){
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
    }

    public void existsGameOverScreen(){
        gm.ui.titleLabel.setVisible(false);
        gm.ui.restartButton.setVisible(false);
        // Remove any visible monsters and reset event state before resetting player
        if (gm.ui != null) {
            gm.ui.removeMonster();
        }
        if (gm.event01 != null) {
            gm.event01.resetEvent();
        }
        gm.player.setPlayerDefaultStatus();
    }
}