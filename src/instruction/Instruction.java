package instruction;


import main.java.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Instruction Class
 */
public class Instruction extends JComponent implements MouseListener, MouseMotionListener {

    //Instruction Menu Display
    private static final String TITLE = "Instruction";
    private static final String INSTRUCTIONS1 = "To move LEFT - Press Key A / Left Arrow";
    private static final String INSTRUCTIONS2 = "To move RIGHT - Press Key D / Right Arrow";
    private static final String INSTRUCTIONS3 = "To PAUSE the game - Press Key Esc";
    private static final String INSTRUCTIONS4 = "To SKIP levels / RESET ball count - Press ALT + SHIFT + F1 ";
    private static final String INSTRUCTIONS5 = "Player can view is player's score is  in the HighScore Board";
    private static final String INSTRUCTIONS6 = "by going back to main menu and view HighScore.";

    //Instruction Menu Back Button Text
    private static final String BACK_TEXT = "Back";

    private Font instructionFont;

    //Instruction Menu Colors
    private static final Color BG_COLOR = new Color(255, 255, 255);
    private static final Color BORDER_COLOR = new Color(255, 255, 255);
    private static final Color DASH_BORDER_COLOR = new Color(255, 255, 255);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    //Instruction Menu Button Rectangles
    private Rectangle menuFace;
    private Rectangle backButton;

    //Border sizes
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12, 6};

    //Border Strokes
    private BasicStroke borderStroke;
    private BasicStroke borderStroke_noDashes;

    //Fonts
    private Font TitleFont;
    private Font buttonFont;

    private GameFrame owner;

    private int strLen = 0;

    //Mouse hover indicator
    private boolean backHover;

    /** show instruction
     * @param owner GameFrame owner will enable instruction screen to show up
     * @param area the screen size of instruction screen
     */
    public Instruction(GameFrame owner, Dimension area) {
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        menuFace = new Rectangle(new Point(0, 0), area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 5, area.height / 12);
        backButton = new Rectangle(btnDim);

        borderStroke = new BasicStroke(BORDER_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, DASHES, 0);
        borderStroke_noDashes = new BasicStroke(BORDER_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        //Setting fonts
        TitleFont = new Font("Times New Roman", Font.BOLD, 40);
        buttonFont = new Font("Monospaced", Font.PLAIN, backButton.height - 2);
        instructionFont = new Font("Monospaced", Font.PLAIN, 12);
    }

    /**
     * draw instruction page
     * @param g graphics parameter
     */
    public void paint(Graphics g) {
        drawMenu((Graphics2D) g);
    }

    /**
     * draw instruction page including text
     * @param g2d  graphics2d parameter
     */
    public void drawMenu(Graphics2D g2d) {
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
        int y = this.getHeight() / 10 + 20;

        g2d.drawString(TITLE, x, y);
        int tY;

        tY = (y + 50);
        g2d.setFont(instructionFont);
        g2d.drawString(INSTRUCTIONS1, 5, tY);

        int uY;
        uY = tY + 25;
        g2d.drawString(INSTRUCTIONS2, 5, uY);

        int vY;
        vY = uY + 25;
        g2d.drawString(INSTRUCTIONS3, 5, vY);

        int wY;
        wY = vY + 25;
        g2d.drawString(INSTRUCTIONS4, 5, wY);

        int xY;
        xY = wY + 25;
        g2d.drawString(INSTRUCTIONS5, 5, xY);

        int zY;
        zY = xY + 25;
        g2d.drawString(INSTRUCTIONS6, 5, zY);
        drawButton(g2d);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    /**
     * draw the instruction page border
     * @param g2d graphics2d parameter
     */
    private void drawContainer(Graphics2D g2d) {
        //adding background image
        g2d.fill(menuFace);
        Image picture = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lenovo\\IdeaProjects\\Brick Destroy (2)\\Brick Destroy\\src\\main\\resources\\instruction.png");
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
     * draw the button
     * @param g2d graphics2d parameter
     */
    private void drawButton(Graphics2D g2d) {
        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D bTxtRect = buttonFont.getStringBounds(BACK_TEXT, frc);

        g2d.setFont(buttonFont);
        int x = 10;
        int y = (menuFace.height - backButton.height) - 10;

        backButton.setLocation(x, y);

        x = (int) (backButton.getWidth() - bTxtRect.getWidth()) / 2;
        y = (int) (backButton.getHeight() - bTxtRect.getHeight()) / 2;

        x += backButton.x + 3;
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

    /**
     *implements methods is MouseListener
     * back button enable home menu
     * @param mouseEvent indicate user's mouse is clicked
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (backButton.contains(p)) {
            owner.disableInstruction();
        }
    }

    /**
     * @param mouseEvent indicate user's mouse clicked is released
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (backButton.contains(p)) {
            backHover = true;
            repaint(backButton.x, backButton.y, backButton.width + 1, backButton.height + 1);
        }
    }

    /**
     * @param mouseEvent indicate user's mouse clicked is released
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (backHover) {
            backHover = false;
            repaint(backButton.x, backButton.y, backButton.width + 1, backButton.height + 1);
        }
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

    /**
     * cursor is changed to hand cursor when the user is moved to the buttons
     * @param mouseEvent indicate user's mouse is moved
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (backButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());
    }
}