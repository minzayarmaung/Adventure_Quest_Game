package Main;

public class Player {

    GameManager gm;

    public int playerMaxLife;
    public int playerLife;

    public int hasSword;
    public int hasShield;
    public int hasLantern;

    public Player(GameManager gm) {
        this.gm = gm;
    }

    public void setPlayerDefaultStatus(){
        playerMaxLife = 5;
        playerLife = 2;
        hasSword = 0;
        hasShield = 0;
        hasLantern = 0;

        updatePlayerStatus();
    }

    public void updatePlayerStatus(){

        //Remove All Life Icons
        int i = 1;
        while(i<6){
            gm.ui.lifeLabel[i].setVisible(false);
            i++;
        }

        int lifeCount = playerLife;
        while(lifeCount!=0){
            gm.ui.lifeLabel[lifeCount].setVisible(true);
            lifeCount--;
        }

        // Checker Player Items
        if(hasSword==0){
            gm.ui.swordLabel.setVisible(false);
        } else {
            gm.ui.swordLabel.setVisible(true);
        }
        if(hasShield==0){
            gm.ui.shieldLabel.setVisible(false);
        } else {
            gm.ui.shieldLabel.setVisible(true);
        }
        if(hasLantern==0){
            gm.ui.lanternLabel.setVisible(false);
        } else {
            gm.ui.lanternLabel.setVisible(true);
        }
    }

}
