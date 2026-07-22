package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;

public class UI {

    private Timer typeTimer;
    private int charIndex = 0;

    GameManager gm;
    JFrame window;
    public JTextArea messageText;
    public JPanel bgPanel[] = new JPanel[10];
    public JLabel bgLabel[] = new JLabel[10];

    //Player UI
    JPanel lifePanel;
    JLabel lifeLabel[] = new JLabel[6];
    JPanel inventoryPanel;
    public JLabel swordLabel, shieldLabel , lanternLabel;

    public UI(GameManager gm){
        this.gm = gm;
        createMainField();
        createPlayerField();
        generateScreen();
        window.setVisible(true);
    }

    public void createMainField(){
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);

        messageText = new JTextArea("Welcome Adventurer!");
        messageText.setBounds(50,410,700,150);
        messageText.setBackground(Color.BLACK);
        messageText.setForeground(Color.WHITE);
        messageText.setEditable(false);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);

        try {
            InputStream is = getClass().getResourceAsStream("/resources/font/pixel_font.ttf");
            Font customPixelFont = Font.createFont(Font.TRUETYPE_FONT, is);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customPixelFont);

            messageText.setFont(customPixelFont.deriveFont(Font.PLAIN, 24f));
        } catch (Exception e) {
            e.printStackTrace();
            messageText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
        }
        window.add(messageText);
    }

    public void createBackground(int bgNum , String bgFileName){
        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(50,50,700,350);
        bgPanel[bgNum].setBackground(Color.GREEN);
        bgPanel[bgNum].setLayout(null);
        window.add(bgPanel[bgNum]);

        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0,0,700,350);

        ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/images/" + bgFileName));

        Image image = bgIcon.getImage();
        Image scaledImage = image.getScaledInstance(700, 350, Image.SCALE_FAST);
        bgIcon = new ImageIcon(scaledImage);

        bgLabel[bgNum].setIcon(bgIcon);
    }

    public void createObject(int bgNum ,int objx , int objy , int objWidth , int objHeight , String objFileName ,
                             String choice1Name , String choice2Name , String choice3Name,
                             String choice1Command , String choice2Command , String choice3Command){

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem[] = new JMenuItem[4];

        menuItem[1] = new JMenuItem(choice1Name);
        menuItem[1].addActionListener(gm.actionHandler);
        menuItem[1].setActionCommand(choice1Command);
        popupMenu.add(menuItem[1]);

        menuItem[2] = new JMenuItem(choice2Name);
        menuItem[2].addActionListener(gm.actionHandler);
        menuItem[2].setActionCommand(choice2Command);
        popupMenu.add(menuItem[2]);

        menuItem[3] = new JMenuItem(choice3Name);
        menuItem[3].addActionListener(gm.actionHandler);
        menuItem[3].setActionCommand(choice3Command);
        popupMenu.add(menuItem[3]);

        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(objx, objy, objWidth, objHeight);
//        objectLabel.setOpaque(true);
//        objectLabel.setBackground(Color.blue);

        ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/images/" + objFileName));

        Image image = objectIcon.getImage();
        Image scaledImage = image.getScaledInstance(objWidth, objHeight, Image.SCALE_FAST);
        objectIcon = new ImageIcon(scaledImage);

        objectLabel.setIcon(objectIcon);

        objectLabel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(objectLabel , e.getX() , e.getY());
                }
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });

        bgPanel[bgNum].add(objectLabel);
    }

    public void createArrowButton(int bgNum , int x, int y , int width , int height ,
                                  String arrowFileName, String command){

        ImageIcon arrowIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/images/"+ arrowFileName));

        Image image = arrowIcon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_FAST);
        arrowIcon = new ImageIcon(scaledImage);

        JButton arrowButton = new JButton();
        arrowButton.setBounds(x,y,width,height);
        arrowButton.setBackground(null);
        arrowButton.setContentAreaFilled(false);
        arrowButton.setFocusPainted(false);
        arrowButton.setBorder(null);
        arrowButton.setIcon(arrowIcon);
        arrowButton.addActionListener(gm.actionHandler);
        arrowButton.setActionCommand(command);
        arrowButton.setBorderPainted(false);

        bgPanel[bgNum].add(arrowButton);
        bgPanel[bgNum].setComponentZOrder(arrowButton, 0);
    }

    public void generateScreen(){

        //Scene 1
        createBackground(1, "bg.jpeg");
        createObject(1,450,130, 200, 200, "hut.png",
                "Look", "Talk", "Rest" , "lookHut" , "talkHut" , "restHut");
        createObject(1,70,170, 150, 150, "guard.png",
                "Look at the Guard", "Talk to Guard", "Attack the Guard" , "Look Guard" , "TalkToGuard" , "AttackGuard");
        createObject(1,250,220, 150, 130, "chest.png",
                "Open the chest", "Leave it alone", "Search for keys" , "openChest" , "leaveAlone" , "searchKeys");

        createArrowButton(1, 0, 150, 50, 50, "left.png", "goScene2");
        createArrowButton(1, 650, 150, 50, 50, "right.png", "goScene3");
        bgPanel[1].add(bgLabel[1]);

        //Scene 2
        createBackground(2, "cavee.png");
        createObject(2,0,0,700,350, "cavee.png",
                "Look","Talk","Enter","lookIntoCave","talkInCave","enterCave");
        createArrowButton(2, 650, 150, 50, 50, "right.png", "goScene1");
        bgPanel[2].add(bgLabel[2]);

        //Scene 3
        createBackground(3,"darkforest.png");
        createArrowButton(3, 0, 150, 50, 50, "left.png", "goScene1");
        bgPanel[3].add(bgLabel[3]);
    }

    public void createPlayerField(){

        lifePanel = new JPanel();
        lifePanel.setBounds(50, 10, 200, 30);
        lifePanel.setBackground(Color.BLACK);
        lifePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        window.add(lifePanel);

        for (int i = 0; i < 6; i++) {
            lifeLabel[i] = new JLabel();
            ImageIcon heartIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/images/heart.png"));
            Image image = heartIcon.getImage();
            Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
            heartIcon = new ImageIcon(scaledImage);
            lifeLabel[i].setIcon(heartIcon);
            lifePanel.add(lifeLabel[i]);
        }

        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(600, 10, 150, 30);
        inventoryPanel.setBackground(Color.BLACK);
        inventoryPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        window.add(inventoryPanel);

        //Create Items
        swordLabel = new JLabel();
        ImageIcon swordIcon =  new ImageIcon(getClass().getClassLoader().getResource("resources/images/sword.png"));
        Image image = swordIcon.getImage();
        Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
        swordIcon = new ImageIcon(scaledImage);
        swordLabel.setIcon(swordIcon);

        shieldLabel = new JLabel();
        ImageIcon shieldIcon =  new ImageIcon(getClass().getClassLoader().getResource("resources/images/shield.png"));
        image = shieldIcon.getImage();
        scaledImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
        shieldIcon = new ImageIcon(scaledImage);
        shieldLabel.setIcon(shieldIcon);

        lanternLabel = new JLabel();
        ImageIcon lanternIcon =  new ImageIcon(getClass().getClassLoader().getResource("resources/images/lantern.png"));
        image = lanternIcon.getImage();
        scaledImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
        lanternIcon = new ImageIcon(scaledImage);
        lanternLabel.setIcon(lanternIcon);

        inventoryPanel.add(swordLabel);
        inventoryPanel.add(shieldLabel);
        inventoryPanel.add(lanternLabel);
    }

    public void typeText(String text) {
        if (typeTimer != null && typeTimer.isRunning()) {
            typeTimer.stop();
        }

        messageText.setText("");
        charIndex = 0;

        typeTimer = new Timer(20, e -> {
            if (charIndex < text.length()) {
                messageText.append(String.valueOf(text.charAt(charIndex)));
                charIndex++;
            } else {
                ((Timer) e.getSource()).stop();
            }
        });

        typeTimer.start();
    }
}
