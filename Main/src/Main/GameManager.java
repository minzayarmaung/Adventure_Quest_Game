package Main;

import Event.Event01;

import java.net.URL;

public class GameManager {

    ActionHandler actionHandler = new ActionHandler(this);
    public UI ui = new UI(this);
    public Player player = new Player(this);
    public SceneChanger sceneChanger = new SceneChanger(this);
    Music music = new Music();
    SE se = new SE();
    public Event01 event01 = new Event01(this);

    // Flag to indicate the game is finished (victory or defeat)
    private boolean gameOver = false;

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private boolean monsterAppear = false;
    public boolean isMonsterAppear(){
        return monsterAppear;
    }

    public void setMonsterAppear(boolean monsterAppear){
        this.monsterAppear = monsterAppear;
    }

    private boolean monsterDefeated = false;
    public boolean isMonsterDefeated(){
        return monsterDefeated;
    }

    public void setMonsterDefeated(boolean monsterDefeated){
        this.monsterDefeated = monsterDefeated;
    }

    // SOUND
    public URL fieldMusic    = getClass().getResource("/resources/music/bensound-acousticbreeze.wav");
    public URL fieldMusic2   = getClass().getResource("/resources/music/bensound-ofeliasdream.wav");
    public URL deathSound    = getClass().getResource("/resources/music/death.wav");
    public URL hitSound      = getClass().getResource("/resources/music/hit.wav");
    public URL healSound     = getClass().getResource("/resources/music/heal.wav");
    public URL itemSound     = getClass().getResource("/resources/music/itemSound.wav");
    public URL enterSound    = getClass().getResource("/resources/music/enter.wav");
    public URL guardSound1   = getClass().getResource("/resources/music/guard1.wav");
    public URL guardSound2   = getClass().getResource("/resources/music/guard_2.wav");
    public URL guardSound3   = getClass().getResource("/resources/music/guard_3.wav");
    public URL guardSound4   = getClass().getResource("/resources/music/guard_4.wav");
    public URL guardSound5   = getClass().getResource("/resources/music/guard_5.wav");
    public URL guardSound6   = getClass().getResource("/resources/music/guard_6.wav");
    public URL monsterSound1 = getClass().getResource("/resources/music/monster_1.wav");
    public URL monsterSound2 = getClass().getResource("/resources/music/monster_2.wav");
    public URL monsterSound3 = getClass().getResource("/resources/music/monster_3.wav");
    public URL arrowSound    = getClass().getResource("/resources/music/arrow_1.wav");
    public URL caveSound     = getClass().getResource("/resources/music/cave_1.wav");
    public URL forestSound   = getClass().getResource("/resources/music/forest_1.wav");
    public URL battleMusic   = getClass().getResource("/resources/music/battle.wav");
    public URL currentMusic;

    public static void main(String[] args) {
        new GameManager();
    }

    public GameManager(){
        currentMusic = fieldMusic;
        playMusic(currentMusic);
        player.setPlayerDefaultStatus();
        sceneChanger.showScene1();
    }

    public void playSE(URL url){
        se.setFile(url);
        se.play(url);
    }

    public void playMusic(URL url){
        music.setFile(url);
        music.play(url);
        music.loop(url);
    }

    public void stopMusic(URL url){
        music.stop(url);
    }




}