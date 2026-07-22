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
        gm.ui.typeText("You rest in the hut and regain your strength.\n(Your Life has been Recovered)");
    }

    public void lookGuard() {
        gm.ui.typeText("The guard is tall and muscular, wearing a suit of armor. He looks at you with suspicion.");
    }

    public void talkToGuard() {
        gm.ui.typeText("You talk to the guard. He tells you that the forest is dangerous and that you should not go in alone.");
    }

    public void attackGuard() {
        gm.ui.typeText("You attack the guard, but he easily overpowers you. You are thrown out of the village and left to die in the forest.");
    }

    public void openChest() {
        gm.ui.typeText("You open the chest and find a small key inside.");
    }

    public void leaveAlone() {
        gm.ui.typeText("You decide to leave the chest alone and continue on your journey.");
    }

    public void searchKeys() {
        gm.ui.typeText("You search the area around the chest and find a small key hidden under a rock.");
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

