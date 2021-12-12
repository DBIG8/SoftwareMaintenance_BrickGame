package highscore;

import main.java.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


/**
 * HighScore class
 */
public class HighScore extends JComponent implements MouseListener, MouseMotionListener{
    //ScoreBoard Menu Button Text
    private static final String TITLE = "Highscore";
    private static final String BACK_TEXT = "Back";

    //ScoreBoard Menu Text
    private Integer[] SCORE;
    private Integer LASTSCORE;

    //ScoreBoard Menu Colors
    private static final Color BG_COLOR = new Color(255, 255, 255);
    private static final Color BORDER_COLOR = new Color(255, 255, 255);
    private static final Color DASH_BORDER_COLOR = new  Color(220,20,60);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    //ScoreBoard Menu Button Rectangles
    private final Rectangle menuFace;
    private final Rectangle backButton;

    //Border sizes
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    //Border Strokes
    private final BasicStroke borderStroke;
    private final BasicStroke borderStroke_noDashes;

    //Fonts
    private final Font TitleFont;
    private final Font buttonFont;
    private final Font highscoreFont;

    private final GameFrame owner;

    private int strLen=0;

    //Mouse hover indicator
    private boolean backHover;

    public HighScore (GameFrame owner, Dimension area)
    {
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 5, area.height / 12);
        backButton = new Rectangle(btnDim);

        borderStroke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStroke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        //Setting fonts
        TitleFont = new Font("Noto Mono",Font.BOLD,40);
        buttonFont = new Font("Monospaced",Font.PLAIN,backButton.height-2);
        highscoreFont = new Font("ZapfDingbats",Font.PLAIN,20);
    }

    public void setSCORE(Integer[] SCORE,Integer lastScore) {
        this.SCORE = SCORE;
        this.LASTSCORE = lastScore;
    }

    /**
     * draw high score screen and also display text
     * @param g graphics parameter
     */
    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }

    /**
     * draw high score screen
     * @param g2d graphics2d parameter
     */
    public void drawMenu(Graphics2D g2d)
    {
        drawContainer(g2d);
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();

        g2d.setFont(TitleFont);
        g2d.setColor(TEXT_COLOR);

        if (strLen == 0) {
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = TitleFont.getStringBounds(TITLE, frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10 +20;

        g2d.drawString(TITLE, x, y);
        drawButton(g2d);

        drawText(g2d);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    /**
     * draw high score screen's text
     * @param g2d graphics2d parameter
     */
    private void drawText(Graphics2D g2d){
        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        int sX, sY;
        sY = (int) (menuFace.getHeight() / 4);
        int totalScoreSize = SCORE.length;

        for (int x=0; x<totalScoreSize; x++) {
            sX = 0;
            sY += 15;

            g2d.setFont(highscoreFont);

            sX += 10;

            g2d.setFont(highscoreFont);

            sX += 200;

            g2d.setFont(highscoreFont);
            if(SCORE[x] == LASTSCORE){
                g2d.setColor(new Color(255,255,0));
            }else{
                g2d.setColor(TEXT_COLOR);
            }
            g2d.drawString(String.format("%d",SCORE[x]), sX, sY);
        }
    }

    /**
     * @param g2d graphics2d parameter
     */
    private void drawContainer(Graphics2D g2d){
        //adding background image
        g2d.fill(menuFace);
        Image picture = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lenovo\\IdeaProjects\\Brick Destroy (2)\\Brick Destroy\\src\\main\\resources\\highscore.jpg");
        g2d.drawImage(picture, 0, 0, getWidth(), getHeight(), this);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStroke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(borderStroke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);
    }

    /**
     * draw return button which is for user to go back home menu from high score
     * @param g2d graphics2d parameter
     */
    private void drawButton(Graphics2D g2d){
        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D bTxtRect = buttonFont.getStringBounds(BACK_TEXT,frc);

        g2d.setFont(buttonFont);
        int x = 10;
        int y = (menuFace.height - backButton.height) -10;

        backButton.setLocation(x, y);

        x = (int) (backButton.getWidth() - bTxtRect.getWidth()) / 2;
        y = (int) (backButton.getHeight() - bTxtRect.getHeight()) / 2;

        x += backButton.x + 5;
        y += backButton.y + (backButton.height * 0.9);

        if (backHover) {
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(backButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(BACK_TEXT, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.draw(backButton);
            g2d.drawString(BACK_TEXT, x, y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){owner.disableHighScore();}
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());
    }
}
