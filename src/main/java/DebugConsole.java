package main.java;

import ball.BallModel;
import gameboard.GameBoard;
import wall.WallController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*
 * DebugConsole class
 */
public class DebugConsole extends JDialog implements WindowListener{

    private static final String TITLE = "Debug Console";

    private JFrame owner;
    private DebugPanel debugPanel;
    private GameBoard gameBoard;
    //private Wall wall;

    /** shows debug console
     * @param owner JFrame owner will enable the debug console screen
     * @param wall wall
     * @param gameBoard pass in game board of GameBoard class
     */
    public DebugConsole(JFrame owner,WallController wall,GameBoard gameBoard){

        //this.wall = wall;
        this.owner = owner;
        this.gameBoard = gameBoard;
        initialize();

        debugPanel = new DebugPanel(wall);
        this.add(debugPanel,BorderLayout.CENTER);

        this.pack();
    }

    /**
     * initialize the debug console screen
     */
    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }

    /**
     * set the location of debug console screen
     */
    private void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {
    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {
    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {
    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        debugPanel.setValues(BallModel.getSpeedX(),BallModel.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {
    }
}
