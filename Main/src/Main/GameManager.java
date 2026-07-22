package Main;

import Event.Event01;

public class GameManager {

    ActionHandler actionHandler = new ActionHandler(this);
    public UI ui = new UI(this);
    public SceneChanger sceneChanger = new SceneChanger(this);

    public Event01 event01 = new Event01(this);

    public static void main(String[] args) {
        new GameManager();
    }

    public GameManager(){
    }
}