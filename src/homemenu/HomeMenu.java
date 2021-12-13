package homemenu;

import main.java.GameFrame;
import gamemusic.GameMusic;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


/*
 * HomeMenu class
 */
public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    HomeMenuController homeMenuController = new HomeMenuController();

    //Start Menu Text
    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 0.2";

    //Start Menu Button text
    private static final String START_TEXT = "Start";
    private static final String EXIT_TEXT = "Exit";
    private static final String INSTRUCTION_TEXT = "Instruction";
    private static final String HIGHSCORE_TEXT = "Highscore";
    //Instruction Menu Button Text
    private static final String BACK_TEXT = "Back";

    //Start Menu Colors
    private static final Color BG_COLOR = new Color(0, 0, 0);
    private static final Color BORDER_COLOR = new Color(255, 255, 255);
    private static final Color DASH_BORDER_COLOR = new Color(255, 255, 255);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color BUTTON_COLOR = new Color(23, 23, 35, 255);
    private static final Color BUTTON_TEXT_COLOR = new Color(255, 255, 255);
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    //Fonts
    private Font greetingsFont;                     //welcome to:
    private Font gameTitleFont;                     //Brick Breaker
    private Font creditsFont;                       //version 0.2
    private Font buttonFont;                        //Button Text Font

    private boolean showInstruction;
    private boolean showHighscore;

    //Border sizes
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12, 6};

    //Border Strokes
    private BasicStroke borderStroke;
    private BasicStroke borderStroke_noDashes;

    private int strLen = 0;

    private boolean audioPlaying;
    private GameMusic gamemUsic = new GameMusic("C:\\Users\\Lenovo\\IdeaProjects\\Brick Destroy (2)\\Brick Destroy\\src\\main\\resources\\gameMusic.wav");

    /**
     * show home menu
     * @param owner GameFrame owner will enable home screen show up
     * @param area the screen size of home menu
     */
    public HomeMenu(GameFrame owner, Dimension area) {

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        homeMenuController.homeMenuModel.owner = owner;

        homeMenuController.homeMenuModel.menuFace = new Rectangle(new Point(0, 0), area);
        this.setPreferredSize(area);

        //Setting Button Dimensions
        Dimension btnDim1 = new Dimension(area.width / 3, area.height / 12);
        Dimension btnDim2 = new Dimension(area.width / 5, area.height / 12);
        homeMenuController.homeMenuModel.startButton = new Rectangle(btnDim1);
        homeMenuController.homeMenuModel.exitButton = new Rectangle(btnDim1);
        homeMenuController.homeMenuModel.instructionButton = new Rectangle(btnDim1);
        homeMenuController.homeMenuModel.highscoreButton = new Rectangle(btnDim1);
        homeMenuController.homeMenuModel.backButton = new Rectangle(btnDim2);

        borderStroke = new BasicStroke(BORDER_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, DASHES, 0);
        borderStroke_noDashes = new BasicStroke(BORDER_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        //Setting Fonts
        greetingsFont = new Font("Times New Roman", Font.PLAIN, 25);
        gameTitleFont = new Font("Times New Roman", Font.BOLD, 40);
        creditsFont = new Font("Monospaced", Font.PLAIN, 10);
        buttonFont = new Font("Monospaced", Font.PLAIN, homeMenuController.homeMenuModel.startButton.height - 2);
    }

    /**
     * draw home menu including all the text, button and so on
     * @param g graphics parameter
     */
    public void paint(Graphics g) {
        gamemUsic.play();
        gamemUsic.loop();
        audioPlaying=true;

        drawMenu((Graphics2D) g);
    }


    /**
     * draw the home menu including text, buttons and so on
     * @param g2d graphics2d  parameter
     */
    public void drawMenu(Graphics2D g2d) {
        if (showInstruction) {
            drawContainer(g2d);
            Font tmpFont = g2d.getFont();
            Color tmpColor = g2d.getColor();

            g2d.setFont(gameTitleFont);
            g2d.setColor(TEXT_COLOR);

            if (strLen == 0) {
                FontRenderContext frc = g2d.getFontRenderContext();
                strLen = gameTitleFont.getStringBounds(INSTRUCTION_TEXT, frc).getBounds().width;
            }

            int x = (this.getWidth() - strLen) / 2;
            int y = this.getHeight() / 10 + 20;

            g2d.drawString(INSTRUCTION_TEXT, x, y);
            drawButton(g2d);

            g2d.setFont(tmpFont);
            g2d.setColor(tmpColor);
        } else {
            drawContainer(g2d);
            Color prevColor = g2d.getColor();
            Font prevFont = g2d.getFont();

            double x = homeMenuController.homeMenuModel.menuFace.getX();
            double y = homeMenuController.homeMenuModel.menuFace.getY();

            g2d.translate(x, y);
            /*
            all the following method calls need a relative
            painting directly into the HomeMenu rectangle,
            so the translation is made here so the other methods do not do that.
             */

            //methods calls
            drawText(g2d);
            drawButton(g2d);
            //end of methods calls

            g2d.translate(-x, -y);
            g2d.setFont(prevFont);
            g2d.setColor(prevColor);
        }
    }

    /**
     * draw the home menu border
     * @param g2d graphics2d  parameter
     */
    private void drawContainer(Graphics2D g2d) {

        //adding background image
        g2d.fill(homeMenuController.homeMenuModel.menuFace);
        Image picture = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lenovo\\IdeaProjects\\Brick Destroy (2)\\Brick Destroy\\src\\main\\resources\\brick_homemenu.png");
        g2d.drawImage(picture, 0, 0, getWidth(), getHeight(), this);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStroke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(homeMenuController.homeMenuModel.menuFace);

        g2d.setStroke(borderStroke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(homeMenuController.homeMenuModel.menuFace);

        g2d.setStroke(tmp);
    }

    /**
     * draw the text
     * @param g2d graphics2d  parameter
     */
    private void drawText(Graphics2D g2d) {

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS, frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE, frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS, frc);

        int sX, sY;

        sX = (int) (homeMenuController.homeMenuModel.menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int) (homeMenuController.homeMenuModel.menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS, sX, sY);

        sX = (int) (homeMenuController.homeMenuModel.menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE, sX, sY);

        sX = (int) (homeMenuController.homeMenuModel.menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS, sX, sY);
    }

    /**
     * draw the buttons
     * @param g2d graphics2d  parameter
     */
    private void drawButton(Graphics2D g2d) {

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT, frc);
        Rectangle2D eTxtRect = buttonFont.getStringBounds(EXIT_TEXT, frc);
        Rectangle2D muTxtRect = buttonFont.getStringBounds(HIGHSCORE_TEXT, frc);
        Rectangle2D tTxtRect = buttonFont.getStringBounds(INSTRUCTION_TEXT, frc);

        g2d.setFont(buttonFont);

        int x = (homeMenuController.homeMenuModel.menuFace.width - homeMenuController.homeMenuModel.startButton.width) / 5;
        int y = (int) ((homeMenuController.homeMenuModel.menuFace.height - homeMenuController.homeMenuModel.startButton.height) * 0.7);

        homeMenuController.homeMenuModel.startButton.setLocation(x, y);

        x = (int) (homeMenuController.homeMenuModel.startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int) (homeMenuController.homeMenuModel.startButton.getHeight() - txtRect.getHeight()) / 2;

        x += homeMenuController.homeMenuModel.startButton.x;
        y += homeMenuController.homeMenuModel.startButton.y + (homeMenuController.homeMenuModel.startButton.height * 0.9);

        if (homeMenuController.homeMenuModel.startHover) {
            Color tmp = g2d.getColor();
            g2d.setColor(BUTTON_COLOR);
            g2d.fill(homeMenuController.homeMenuModel.startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.draw(homeMenuController.homeMenuModel.startButton);
            g2d.drawString(START_TEXT, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.setColor(BUTTON_COLOR);
            g2d.fill(homeMenuController.homeMenuModel.startButton);
            g2d.setColor(TEXT_COLOR);
            g2d.draw(homeMenuController.homeMenuModel.startButton);
            g2d.drawString(START_TEXT, x, y);
        }

        x = homeMenuController.homeMenuModel.startButton.x;
        y = homeMenuController.homeMenuModel.startButton.y + 40;

        homeMenuController.homeMenuModel.instructionButton.setLocation(x, y);

        x = (int) (homeMenuController.homeMenuModel.instructionButton.getWidth() - tTxtRect.getWidth()) / 2;
        y = (int) (homeMenuController.homeMenuModel.instructionButton.getHeight() - tTxtRect.getHeight()) / 2;

        x += homeMenuController.homeMenuModel.instructionButton.x;
        y += homeMenuController.homeMenuModel.instructionButton.y + (homeMenuController.homeMenuModel.startButton.height * 0.9);

        if (homeMenuController.homeMenuModel.instructionHover) {
            Color tmp = g2d.getColor();

            g2d.setColor(BUTTON_COLOR);
            g2d.fill(homeMenuController.homeMenuModel.instructionButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.draw(homeMenuController.homeMenuModel.instructionButton);
            g2d.drawString(INSTRUCTION_TEXT, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.setColor(BUTTON_COLOR);
            g2d.fill(homeMenuController.homeMenuModel.instructionButton);
            g2d.setColor(TEXT_COLOR);
            g2d.draw(homeMenuController.homeMenuModel.instructionButton);
            g2d.drawString(INSTRUCTION_TEXT, x, y);
        }

        x = homeMenuController.homeMenuModel.startButton.width + 100;
        y = homeMenuController.homeMenuModel.startButton.y + 40;

        homeMenuController.homeMenuModel.exitButton.setLocation(x, y);

        x = (int) (homeMenuController.homeMenuModel.exitButton.getWidth() - eTxtRect.getWidth()) / 2;
        y = (int) (homeMenuController.homeMenuModel.exitButton.getHeight() - eTxtRect.getHeight()) / 2;

        x += homeMenuController.homeMenuModel.exitButton.x;
        y += homeMenuController.homeMenuModel.exitButton.y + (homeMenuController.homeMenuModel.startButton.height * 0.9);

        if (homeMenuController.homeMenuModel.exitHover) {
            Color tmp = g2d.getColor();

            g2d.setColor(BUTTON_COLOR);
            g2d.fill(homeMenuController.homeMenuModel.exitButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.draw(homeMenuController.homeMenuModel.exitButton);
            g2d.drawString(EXIT_TEXT, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.setColor(BUTTON_COLOR);
            g2d.fill(homeMenuController.homeMenuModel.exitButton);
            g2d.setColor(TEXT_COLOR);
            g2d.draw(homeMenuController.homeMenuModel.exitButton);
            g2d.drawString(EXIT_TEXT, x, y);
        }

        x = homeMenuController.homeMenuModel.startButton.width + 100;
        y = homeMenuController.homeMenuModel.startButton.y;

        //draw highscore button
        homeMenuController.homeMenuModel.highscoreButton.setLocation(x, y);

        x = (int) (homeMenuController.homeMenuModel.highscoreButton.getWidth() - muTxtRect.getWidth()) / 2;
        y = (int) (homeMenuController.homeMenuModel.highscoreButton.getHeight() - muTxtRect.getHeight()) / 2;

        x += homeMenuController.homeMenuModel.highscoreButton.x;
        y += homeMenuController.homeMenuModel.highscoreButton.y + (homeMenuController.homeMenuModel.startButton.height * 0.9);

        if (homeMenuController.homeMenuModel.highscoreHover) {
            Color tmp = g2d.getColor();
            g2d.setColor(BUTTON_COLOR);
            g2d.fill(homeMenuController.homeMenuModel.highscoreButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.draw(homeMenuController.homeMenuModel.highscoreButton);
            g2d.drawString(HIGHSCORE_TEXT, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.setColor(BUTTON_COLOR);
            g2d.fill(homeMenuController.homeMenuModel.highscoreButton);
            g2d.setColor(TEXT_COLOR);
            g2d.draw(homeMenuController.homeMenuModel.highscoreButton);
            g2d.drawString(HIGHSCORE_TEXT, x, y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        homeMenuController.homeMenuMouseClicked(mouseEvent);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        homeMenuController.homeMenuMouseMoved(mouseEvent, this);
    }


}
