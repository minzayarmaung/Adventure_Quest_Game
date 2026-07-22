package Event;

import Main.GameManager;

public class Event01 {

    GameManager gm;

    public Event01(GameManager gm) {
        this.gm = gm;
    }

    public void lookHut() {
        gm.ui.typeText("Inside the hut, you see a small bed, a table with some food, and a chest in the corner.");
    }

    public void talkHut() {
        gm.ui.typeText("You talk to the old man in the hut. He tells you about the dangers of the forest and warns you to be careful.");
    }

    public void restHut(){
        if(gm.player.playerLife != gm.player.playerMaxLife){
            gm.ui.messageText.setText("You rest at the house.\n(Your Life has been Recovered)");
            gm.player.playerLife++;
            gm.player.updatePlayerStatus();
        } else {
            gm.ui.messageText.setText("Your Life is Full.");
        }
    }

    public void lookGuard() {
        gm.ui.typeText("The guard is tall and muscular, wearing a suit of armor. He looks at you with suspicion.");
    }

    public void talkToGuard() {
        gm.ui.typeText("Guard : Stay Away, Fool!");
    }

    public void attackGuard() {
        // 1. Check if the player is already defeated
        if (gm.player.playerLife <= 0) {
            gm.ui.messageText.setText("You are defeated. Restart to try again.");
            gm.sceneChanger.showGameOverScreen(1);
            return;
        }

        // 2. Check if the guard was already defeated (player already has the shield)
        if (gm.player.hasShield != 0) {
            gm.ui.typeText("Guard : Just leave me alone.");
            return;
        }

        // 3. Combat logic without a sword
        if (gm.player.hasSword == 0) {
            // Dialogue depending on current health (before taking damage)
            if (gm.player.playerLife == 3) {
                gm.ui.typeText("Guard : Hey, Don't be Stupid!\n(The guard hits you back and your life decreases by 1)");
            } else if (gm.player.playerLife == 2) {
                gm.ui.typeText("Guard : Hey, Stop it!\n(The guard hits you back and your life decreases by 1)");
            } else if (gm.player.playerLife == 1) {
                gm.ui.typeText("Guard : Hey, I'm Warning You!\n(The guard hits you back and your life decreases by 1)");
            } else {
                gm.ui.typeText("Guard : Don't touch me!\n(The guard hits you back and your life decreases by 1)");
            }

            gm.player.playerLife--;
            gm.player.updatePlayerStatus();

            // If the player just dropped to 0 or below, show game over
            if (gm.player.playerLife <= 0) {
                gm.ui.typeText("Guard : What a Fool!");
                gm.sceneChanger.showGameOverScreen(1);
            }
        }
        // 4. Combat logic with a sword
        else {
            gm.ui.typeText("Guard : Oh, Shit!\n(You have defeated the guard and gotten his shield!)");
            gm.player.hasShield = 1;
            gm.player.updatePlayerStatus();
        }
    }

    public void openChest() {
        if(gm.player.hasSword == 0){
            gm.player.hasSword = 1;
            gm.player.updatePlayerStatus();
            gm.ui.typeText("You open the chest and found the Sword.");
        } else {
            gm.ui.typeText("The Chest is Empty.");
        }
    }

    public void leaveAlone() {
        gm.ui.typeText("You decide to leave the chest alone and continue on your journey.");
    }

    public void search() {
        gm.ui.typeText("You search the area around the chest found nothing.");
    }

    public void lookIntoCave(){
        gm.ui.typeText("The cave is dark and damp. You can hear the sound of water dripping from the ceiling.");
    }

    public void talkInCave(){
        gm.ui.typeText("You talk to the mysterious figure in the cave. He tells you that he has been waiting for you and that he has a task for you.");
    }

    public void enterCave(){
        gm.ui.typeText("You enter the cave and find yourself in a large chamber filled with treasure. The mysterious figure tells you that you have proven yourself worthy and that you may take whatever you wish.");
    }
}

