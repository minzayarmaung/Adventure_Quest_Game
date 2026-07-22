package Event;

import Main.GameManager;

public class Event01 {

    private int caveVisitCount = 0;
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
        gm.ui.typeText("HELLO !!\n(You shouted but nothing responds except your echo.)");
    }

    private int monsterLife = 0;
    private boolean monsterDefeated = false;

    public void enterCave(){
        // If player has sword, start the monster encounter in the cave
        if (gm.player.hasSword == 1) {
            if (monsterDefeated) {
                gm.ui.typeText("The cave is quiet now. The Monster has already been defeated.");
                return;
            }
            startMonsterEncounter();
            return;
        }

        if (gm.player.hasLantern == 1) {
            gm.ui.typeText("You enter the cave with your lantern and found the Key.");
        } else {
            if (caveVisitCount == 1) {
                gm.ui.typeText("Monster : GRRRRRRRR!\nThe Monster attacked you.\n(Hint: You need a lantern to enter the cave.)");
                gm.player.playerLife = 0;
                gm.player.updatePlayerStatus();

                gm.ui.showMonster(2);
                gm.sceneChanger.showGameOverScreen(2);

                caveVisitCount = 0;
            } else {
                gm.ui.typeText("It's too dark! There might be a Monster in there.");
            }
            caveVisitCount++;
        }
    }

    // Start the monster fight. Only allowed when player has a sword.
    public void startMonsterEncounter() {
        // Initialize monster lives and show it in the cave
        monsterLife = 3;
        gm.ui.showMonster(2);
        gm.ui.typeText("A Monster appears! It has 3 hearts. Right-click it and choose 'Attack'.\nWhen you attack, it will hit you back.");
    }

    // Called when player chooses to attack the monster
    public void attackMonster() {
        if (gm.player.hasSword == 0) {
            gm.ui.typeText("You don't have a sword to attack the Monster.");
            return;
        }

        if (monsterLife <= 0) {
            gm.ui.typeText("The Monster is already defeated.");
            return;
        }

        if (gm.player.playerLife <= 0) {
            gm.ui.typeText("You are already defeated. Restart to try again.");
            gm.sceneChanger.showGameOverScreen(2);
            return;
        }

        // Mutual damage: player attacks monster and monster hits back
        monsterLife--;
        gm.player.playerLife--;
        gm.player.updatePlayerStatus();

        if (gm.player.playerLife <= 0) {
            gm.ui.typeText("The Monster struck back and you died.");
            gm.ui.showMonster(2);
            gm.sceneChanger.showGameOverScreen(2);
            return;
        }

        if (monsterLife > 0) {
            gm.ui.typeText("You hit the Monster! Monster hearts left: " + monsterLife + ". The Monster hit you back!");
        } else {
            // Monster defeated
            monsterDefeated = true;
            gm.ui.typeText("You struck the final blow and defeated the Monster!\nYou can continue your journey.");
            gm.player.updatePlayerStatus();
            gm.ui.removeMonster();
        }
    }

    // Reset event state (used when restarting the game)
    public void resetEvent() {
        caveVisitCount = 0;
        monsterLife = 0;
        monsterDefeated = false;
    }
}

