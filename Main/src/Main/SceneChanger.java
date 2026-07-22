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
        // Ensure the background for the current scene is visible
        gm.ui.bgPanel[currentBgNum].setVisible(false);

        // Configure and show the large title label (YOU DIED)
        gm.ui.titleLabel.setText("YOU DIED");
        gm.ui.titleLabel.setFont(new java.awt.Font("Time New Roman", java.awt.Font.BOLD, 72));
        gm.ui.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gm.ui.titleLabel.setBounds(150, 120, 500, 100);
        gm.ui.titleLabel.setForeground(Color.RED);
        gm.ui.titleLabel.setVisible(true);

        // Show restart button
        gm.ui.restartButton.setText("Click here to restart");
        gm.ui.titleLabel.setFont(new java.awt.Font("Time New Roman", java.awt.Font.BOLD, 40));
        gm.ui.restartButton.setBounds(200, 300, 400, 50);
        gm.ui.restartButton.setBorderPainted(false);
        gm.ui.restartButton.setVisible(true);

        // Preserve existing message text (e.g., last dialogue) — don't overwrite gm.ui.messageText
    }

    public void existsGameOverScreen(){
        gm.ui.titleLabel.setVisible(false);
        gm.ui.restartButton.setVisible(false);
        gm.player.setPlayerDefaultStatus();
    }
}
