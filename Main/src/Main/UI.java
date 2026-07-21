package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;

public class UI {

    GameManager gm;
    JFrame window;
    public JTextArea messageText;
    public JPanel bgPanel[] = new JPanel[10];
    public JLabel bgLabel[] = new JLabel[10];

    public UI(GameManager gm){
        this.gm = gm;
        createMainField();
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
        bgPanel[bgNum].add(bgLabel[bgNum]);
    }

    public void generateScreen(){

        //Screen 1
        createBackground(1, "bg.jpeg");
        createObject(1,450,130, 200, 200, "hut.png", "Look", "Talk", "Rest" , "lookHut" , "talkHut" , "restHut");
        createObject(1,70,170, 150, 150, "guard.png", "Look at the Guard", "Talk to Guard", "Attack the Guard" , "Look Guard" , "TalkToGuard" , "AttackGuard");
        createObject(1,250,220, 150, 130, "chest.png", "Open the chest", "Leave it alone", "Search for keys" , "openChest" , "leaveAlone" , "searchKeys");

    }
}
