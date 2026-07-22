package Main;

import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {
    GameManager gm;

    public ActionHandler(GameManager gm) {
        this.gm = gm;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String command = e.getActionCommand();

        switch(command){
            case "lookHut":
                gm.event01.lookHut();
                break;
            case "talkHut":
                gm.event01.talkHut();
                break;
            case "restHut":
                gm.event01.restHut();
                break;
            case "Look Guard":
                gm.event01.lookGuard();
                break;
            case "TalkToGuard":
                gm.event01.talkToGuard();
                break;
            case "AttackGuard":
                gm.event01.attackGuard();
                break;
            case "openChest":
                gm.event01.openChest();
                break;
            case "leaveAlone":
                gm.event01.leaveAlone();
                break;
            case "search":
                gm.event01.search();
                break;
            case "lookIntoCave":
                gm.event01.lookIntoCave();
                break;
            case "talkInCave":
                gm.event01.talkInCave();
                break;
            case "enterCave":
                gm.event01.enterCave();
                break;
            case "attackMonster":
                gm.event01.attackMonster();
                break;
            case "defendArcher":
                gm.event01.defendArcher();
                break;
            case "openGoldChest":
                gm.event01.openGoldChest();
                break;
            // Change Scenes
            case "goScene1":
                gm.sceneChanger.showScene1();
                break;
            case "goScene2":
                gm.sceneChanger.showScene2();
                break;
            case "goScene3":
                gm.sceneChanger.showScene3();
                break;
            // Restart Game
            case "restart":
                gm.sceneChanger.existsGameOverScreen();
                gm.sceneChanger.showScene1();
                break;
        }
    }
}
