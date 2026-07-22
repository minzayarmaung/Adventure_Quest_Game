package Main;

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
}
