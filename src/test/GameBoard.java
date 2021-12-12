package gameboard;

import brick.Brick;
import main.java.DebugConsole;
import main.java.GameFrame;
import player.Player;
import player.PlayerController;
import wall.Wall;
import wall.WallController;
import wall.WallModel;
import ball.BallModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;

/**
 GameBoard class
 */
public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    public Wall wall;
    public WallModel wallModel;
    public WallController wallController;
    public PlayerController playerController;
    private GameBoardModel gameBoardModel;
    private BallModel ballModel;
    private final GameBoardController gameBoardController;
    public Rectangle continueButtonRect;
    public Rectangle exitButtonRect;
    public Rectangle restartButtonRect;
    public Rectangle homeButtonRect;
    public Rectangle backButtonRect;
    public GameFrame owner;

    private String message1;
    private String message2;
    private String message3;
    private int strLen;

    public DebugConsole debugConsole;

    public Timer gameTimer;

    /**
     * shows the game screen which includes ball, brick and player
     * @param owner JFram owner will enable game board screen show up
     */
    public GameBoard(GameFrame owner){
        super();
        this.owner = owner;
        wallController = new WallController();
        gameBoardController = new GameBoardController();
        gameBoardModel = new GameBoardModel();
        wallModel = new WallModel();
        ballModel = new BallModel();
        playerController = new PlayerController();

        gameBoardModel.setMenuFont ( new Font("Monospaced",Font.PLAIN,gameBoardModel.getTextSize()));

        this.initialize();
        message1 = "";
        message2 = "";
        message3 = "";
        wall = new Wall(new Rectangle(0,0, gameBoardModel.getDefWidth(), gameBoardModel.getDefHeight()),30,3,6/2,new Point(300,430));

        debugConsole = new DebugConsole(owner,wallController,this);
        //initialize the first level
        wallController.nextLevel();
        gameTimer = new Timer (10,e -> {
            playerController.move();
            wallController.move();
            wallController.findImpacts();
            message1 = String.format("Bricks: %d Balls: %d",wallModel.getBrickCount(),wallModel.getBallCount());
            message2 = String.format("Player Score: %d",wallModel.getPlayerScore());
            message3 = String.format("");

            if(wallModel.isBallLost()){
                if(wallModel.ballEnd()){
                    wallController.wallReset();
                    message1 = "Game over";
                    message2 = "Press ALT+SHIFT+F1";
                    message3 = "to Restart or Go Home Menu";
                    int totalScore = wallModel.getPlayerScore();
                    wallModel.setPlayerScore(totalScore);
                    owner.WriteFile(totalScore);
                }

                wallController.ballReset();
                gameTimer.stop();
            }
            else if(wallModel.isDone()){
                if(wallModel.hasLevel()){
                    message1 = "Go to Next Level!";
                    message2 = "";
                    message3 = "";
                    gameTimer.stop();
                    wallController.ballReset();
                    wallController.wallReset();
                    wallController.nextLevel();
                }
                else{
                    wallController.ballReset();
                    wallController.wallReset();
                    wallController.resetLevels();
                    message1 = "CONGRATULATIONS,";
                    message2 = "ALL WALLS DESTROYED!";
                    message3 = "";
                    int totalScore = wallModel.getPlayerScore();
                    owner.WriteFile(totalScore);
                    gameTimer.stop();
                }
            }
            repaint();
        });
    }

    /**
     * initialize the game board
     * notified when key and mouse motion occured
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(gameBoardModel.getDefWidth(), gameBoardModel.getDefHeight()));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * paint the game board screen
     * @param g graphics parameter
     */
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(new Color(255,140,0));
        g2d.drawString(message1,255,220);
        g2d.drawString(message2,255,235);

        drawBall(g2d);

        for(Brick b : wall.brick)
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.player,g2d);

        if(GameBoardModel.showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * clear the screen
     * @param g2d graphics2d parameter
     */
    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(gameBoardModel.getBackgroundColour());
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * draw the brick including shape, colour and type
     * @param brick pass in the brick of the Brick class
     * @param g2d graphics2d parameter
     */
    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrickFace());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrickFace());

        g2d.setColor(tmp);
    }

    /**
     * draw the brick including shape and colour
     * @param g2d graphics2d parameter
     */
    private void drawBall(Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ballModel.getBallFace();

        g2d.setColor(ballModel.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ballModel.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * draw the player shape and colour
     * @param p passing in player of the Player class
     * @param g2d graphics2d parameter
     */
    private void drawPlayer(Player p,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * draw pause menu screen
     * @param g2d graphics2d parameter
     */
    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * draw game screen
     * @param g2d graphics2d parameter
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,gameBoardModel.getDefWidth(), gameBoardModel.getDefHeight());

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * draw the buttons like continue, reset and etc in the pause menu screen
     * @param g2d graphics2d parameter
     */
    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();

        g2d.setFont(gameBoardModel.getMenuFont());
        g2d.setColor(gameBoardModel.getMenuColor());

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = gameBoardModel.getMenuFont().getStringBounds(GameBoardModel.PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(GameBoardModel.PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;

        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = gameBoardModel.getMenuFont().getStringBounds(GameBoardModel.CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(GameBoardModel.CONTINUE,x,y);

        y += 60;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(GameBoardModel.RESTART,x,y);

        y += 60;

        if(homeButtonRect == null){
            homeButtonRect = (Rectangle) continueButtonRect.clone();
            homeButtonRect.setLocation(x,y-homeButtonRect.height);
        }

        g2d.drawString(GameBoardModel.HOME,x,y);

        y += 60;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(GameBoardModel.EXIT,x,y);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        gameBoardController.controllerKeyPressed(keyEvent,this);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.player.stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        gameBoardController.controllerMouseClicked(mouseEvent,this,owner);
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
        gameBoardController.controllerMouseMoved(mouseEvent,this);
    }

    /**
     * focus lost msg is showed when focus is lost
     * redraw the screen
     */
    public void onLostFocus(){
        gameTimer.stop();
        message1 = "Focus Lost";
        message2 = "";
        repaint();
    }
}


