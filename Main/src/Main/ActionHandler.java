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
            case "searchKeys":
                gm.event01.searchKeys();
                break;
        }
    }
}
