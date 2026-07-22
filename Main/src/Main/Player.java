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
        playerMaxLife =5;
        playerLife = 3;
        hasSword = 0;
        hasShield = 0;
        hasLantern = 0;

        updatePlayerStatus();
    }

    public void updatePlayerStatus(){

        // Safely hide all life icons (guard against nulls and index issues)
        for (int i = 0; i < gm.ui.lifeLabel.length; i++) {
            if (gm.ui.lifeLabel[i] != null) {
                gm.ui.lifeLabel[i].setVisible(false);
            }
        }

        // Show icons for current life count (0..playerLife-1)
        int livesToShow = Math.max(0, Math.min(playerLife, gm.ui.lifeLabel.length));
        for (int i = 0; i < livesToShow; i++) {
            if (gm.ui.lifeLabel[i] != null) {
                gm.ui.lifeLabel[i].setVisible(true);
            }
        }

        // Checker Player Items
        gm.ui.swordLabel.setVisible(hasSword != 0);
        gm.ui.shieldLabel.setVisible(hasShield != 0);
        gm.ui.lanternLabel.setVisible(hasLantern != 0);
    }

}
