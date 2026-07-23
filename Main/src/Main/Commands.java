package Main;

/**
 * Centralized action command constants to avoid magic strings spread across the codebase.
 * Adding new commands here keeps the code open for extension and closed for modification elsewhere.
 */
public final class Commands {
    private Commands() {}

    public static final String LOOK_HUT = "lookHut";
    public static final String TALK_HUT = "talkHut";
    public static final String REST_HUT = "restHut";

    public static final String LOOK_GUARD = "Look Guard";
    public static final String TALK_GUARD = "TalkToGuard";
    public static final String ATTACK_GUARD = "AttackGuard";

    public static final String OPEN_CHEST = "openChest";
    public static final String LEAVE_CHEST = "leaveAlone";
    public static final String SEARCH_CHEST = "search";

    public static final String LOOK_INTO_CAVE = "lookIntoCave";
    public static final String TALK_IN_CAVE = "talkInCave";
    public static final String ENTER_CAVE = "enterCave";

    public static final String ATTACK_MONSTER = "attackMonster";
    public static final String DEFEND_ARCHER = "defendArcher";
    public static final String OPEN_GOLD_CHEST = "openGoldChest";

    public static final String LOOK_INTO_FOREST = "lookIntoForest";
    public static final String TALK_IN_FOREST = "talkInForest";
    public static final String ENTER_FOREST = "enterForest";

    public static final String GO_SCENE_1 = "goScene1";
    public static final String GO_SCENE_2 = "goScene2";
    public static final String GO_SCENE_3 = "goScene3";

    public static final String RESTART = "restart";
}
