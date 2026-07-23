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
        gm.playSE(gm.enterSound);
    }

    public void talkHut() {
        gm.ui.typeText("You talk to the old man in the hut. He tells you about the dangers of the forest and warns you to be careful.");
    }

    public void restHut(){
        if(gm.player.playerLife != gm.player.playerMaxLife){
            gm.ui.messageText.setText("You rest at the house.\n(Your Life has been Recovered)");
            gm.player.playerLife++;
            gm.player.updatePlayerStatus();
            gm.playSE(gm.healSound);
        } else {
            gm.ui.messageText.setText("Your Life is Full.");
        }
    }

    public void lookGuard() {
        gm.ui.typeText("The guard is tall and muscular, wearing a suit of armor. He looks at you with suspicion.");
    }

    public void talkToGuard() {
        gm.ui.typeText("Guard : Stay Away, Fool!");
        gm.playSE(gm.guardSound4);
    }

    public void attackGuard() {
        // 1. Check if the player is already defeated
        if (gm.player.playerLife <= 0) {
            gm.ui.messageText.setText("You are defeated. Restart to try again.");
            gm.sceneChanger.showGameOverScreen(1);
            gm.playSE(gm.guardSound3);
            return;
        }

        // 2. Check if the guard was already defeated (player already has the shield)
        if (gm.player.hasShield != 0) {
            gm.ui.typeText("Guard : Just leave me alone.");
            gm.playSE(gm.guardSound6);
            return;
        }

        // 3. Combat logic without a sword
        if (gm.player.hasSword == 0) {
            // Dialogue depending on current health (before taking damage)
            if (gm.player.playerLife == 3) {
                gm.ui.typeText("Guard : Hey, Don't be Stupid!\n(The guard hits you back and your life decreases by 1)");
                gm.playSE(gm.guardSound1);
            } else if (gm.player.playerLife == 2) {
                gm.ui.typeText("Guard : Hey, Stop it!\n(The guard hits you back and your life decreases by 1)");
                gm.playSE(gm.guardSound2);
            } else if (gm.player.playerLife == 1) {
                gm.ui.typeText("Guard : Hey, I'm Warning You!\n(The guard hits you back and your life decreases by 1)");
            } else {
                gm.ui.typeText("Guard : Don't touch me!\n(The guard hits you back and your life decreases by 1)");
            }

            gm.playSE(gm.hitSound);
            gm.player.playerLife--;
            gm.player.updatePlayerStatus();

            // If the player just dropped to 0 or below, show game over
            if (gm.player.playerLife <= 0) {
                gm.ui.typeText("Guard : What a Fool!");
                gm.currentMusic = gm.guardSound3;
                gm.playSE(gm.currentMusic);
                gm.sceneChanger.showGameOverScreen(1);
                gm.stopMusic(gm.currentMusic);
                gm.playMusic(gm.fieldMusic2);
            }
        }
        // 4. Combat logic with a sword
        else {
            gm.ui.typeText("Guard : Oh, Shit!\n(You have defeated the guard and gotten his shield!)");
            gm.player.hasShield = 1;
            gm.player.updatePlayerStatus();
            gm.playSE(gm.guardSound5);
            gm.playSE(gm.itemSound);
        }
    }

    public void openChest() {
        if(gm.player.hasSword == 0){
            gm.player.hasSword = 1;
            gm.player.updatePlayerStatus();
            gm.ui.typeText("You open the chest and found the Sword.");
            gm.playSE(gm.itemSound);
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
    public boolean monsterDefeated = false;
    private boolean goldChestTaken = false;

    // Returns true when a live monster is present (player is engaged in the encounter)
    public boolean isMonsterActive() {
        return monsterLife > 0;
    }

    public void enterCave(){
        // If player has sword, handle monster encounter first
        if (gm.player.hasSword == 1) {
            if (!monsterDefeated) {
                startMonsterEncounter();
                return;
            } else {
                // Monster already defeated
                if (gm.player.hasLantern == 1) {
                    if (!goldChestTaken) {
                        gm.ui.typeText("The Monster is dead. The cave is lit by your lantern. You see a golden chest.");
                        gm.ui.showGoldChest(2);
                    } else {
                        gm.ui.typeText("The cave is quiet now. You already took the golden chest.");
                    }
                } else {
                    gm.ui.typeText("The Monster is dead, but the cave is still dark. Maybe a lantern would help you find something.");
                }
                return;
            }
        }


        // If player doesn't have a sword, original lantern/dark logic
        if (gm.player.hasLantern == 1) {
            gm.ui.typeText("You enter the cave with your lantern and found the Key.");
        } else {
            if (caveVisitCount == 1) {
                gm.playSE(gm.monsterSound2);
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
        gm.setMonsterAppear(true);
        monsterLife = 3;
        gm.ui.showMonster(2);
        gm.ui.typeText("A Monster appears! It has 3 hearts. Right-click it and choose 'Attack'.\nWhen you attack, it will hit you back.");
        gm.playMusic(gm.battleMusic);
        gm.playSE(gm.monsterSound3);
    }

    // Called when player chooses to attack the monster
    public void attackMonster() {
        if (gm.player.hasSword == 0) {
            gm.ui.typeText("You don't have a sword to attack the Monster.");
            return;
        }

        if (monsterLife == 3){
            gm.playSE(gm.monsterSound3);
        }

        if (monsterLife == 2){
            gm.playSE(gm.monsterSound2);
        }

        if (monsterLife == 1){
            gm.playSE(gm.monsterSound1);
        }

        if (monsterLife <= 0) {
            gm.ui.typeText("The Monster is already defeated.");
            return;
        }

        if (gm.player.playerLife <= 0) {
            gm.ui.typeText("You are already defeated. Restart to try again.");
            gm.sceneChanger.showGameOverScreen(2);
            gm.playSE(gm.deathSound);
            return;
        }

        // Mutual damage: player attacks monster and monster hits back
        gm.playSE(gm.hitSound);
        gm.setMonsterAppear(true);
        monsterLife--;
        gm.player.playerLife--;
        gm.player.updatePlayerStatus();

        if (gm.player.playerLife <= 0) {
            gm.ui.typeText("The Monster struck back and you died.");
            gm.ui.showMonster(2);
            gm.sceneChanger.showGameOverScreen(2);
            gm.stopMusic(gm.currentMusic);
            gm.playMusic(gm.fieldMusic2);
            gm.playSE(gm.deathSound);
            return;
        }

        if (monsterLife > 0) {
            gm.ui.typeText("You hit the Monster! Monster hearts left: " + monsterLife + ". The Monster hit you back!");
        } else {
            // Monster defeated
            monsterDefeated = true;
            gm.setMonsterDefeated(true);
            gm.ui.typeText("You struck the final blow and defeated the Monster!\nYou can continue your journey.");
            gm.player.updatePlayerStatus();
            gm.ui.removeMonster();
        }
    }

    // Open the golden chest in the cave (finish the game)
    public void openGoldChest() {
        if (goldChestTaken) {
            gm.ui.typeText("You already took the golden chest.");
            return;
        }
        goldChestTaken = true;
        gm.playSE(gm.itemSound);
        gm.ui.typeText("You opened the golden chest and found untold riches!\nCongratulations.You've finished the game.");
        gm.ui.removeGoldChest();
        gm.sceneChanger.showVictoryScreen();
    }

    public void leaveGoldChest() {
        gm.ui.typeText("You decide to leave the golden chest for now.");
    }

    public void lookIntoForest(){
        gm.ui.typeText("The forest is dark and dense. You can barely see the path ahead.");
    }

    public void talkInForest(){
        gm.ui.typeText("You shout into the forest, but there is no response. The forest seems to be alive with the sounds of wildlife.");
    }

    private boolean archerPresent = false;

    public void enterForest(){
        // If lantern already obtained, nothing to do
        if (gm.player.hasLantern == 1) {
            gm.ui.typeText("The forest is peaceful now. You've already searched it.");
            return;
        }

        // Show archer and prompt player to defend (right-click)
        gm.ui.showArcher(3);
        gm.ui.typeText("An archer appears in the trees! Right-click the archer and choose 'Defend'.");
        gm.playSE(gm.arrowSound);
        archerPresent = true;
    }

    // Called when player chooses to defend against the archer
    public void defendArcher() {
        // If archer not present, ignore
        if (!archerPresent) {
            gm.ui.typeText("There is no archer to defend against.");
            return;
        }

        if (gm.player.hasShield == 0) {
            gm.ui.typeText("You try to defend but you have no shield. The arrow strikes you down.");
            gm.player.playerLife = 0;
            gm.player.updatePlayerStatus();
            gm.sceneChanger.showGameOverScreen(3);
            gm.stopMusic(gm.currentMusic);
            gm.playMusic(gm.fieldMusic2);
        } else {
            gm.ui.typeText("You raise your shield and block the arrows!\nAmong the bushes you find a lantern.");
            gm.player.hasLantern = 1;
            gm.player.updatePlayerStatus();
            gm.ui.removeArcher();
            gm.playSE(gm.itemSound);
            archerPresent = false;
        }
    }

    // Reset event state (used when restarting the game)
    public void resetEvent() {
        caveVisitCount = 0;
        monsterLife = 0;
        monsterDefeated = false;
        goldChestTaken = false;
        archerPresent = false;
        // Ensure UI chests/monsters/archer are removed
        if (gm.ui != null) {
            gm.ui.removeGoldChest();
            gm.ui.removeMonster();
            gm.ui.removeArcher();
        }
    }
}

