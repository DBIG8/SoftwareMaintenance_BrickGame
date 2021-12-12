package main.java;

import gameboard.GameBoard;
import highscore.HighScore;
import homemenu.HomeMenu;
import instruction.Instruction;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


/**
 * GameFrame Class
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    public GameBoard gameBoard;
    private HomeMenu homeMenu;
    private Instruction instruction;
    private HighScore highScore;

    private boolean gaming;

    private int score;
    private Integer lastPlayerScore;
    private Integer[] highscoreScore;
    private ArrayList<Integer> highScoreList;

    /**
     * generate game frame
     */
    public GameFrame(){
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        gameBoard = new GameBoard(this);

        homeMenu = new HomeMenu(this,new Dimension(450,300));

        instruction = new Instruction(this,new Dimension(450,300));

        highScore = new HighScore(this,new Dimension(450,300));

        this.add(homeMenu,BorderLayout.CENTER);

        this.setUndecorated(true);
    }

    /**
     * Initializes GameFrame
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * enable game board screen
     */
    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenu);
        this.add(gameBoard,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * disable game board screen
     */
    public void disableGameBoard(){
        this.dispose();
        this.remove(gameBoard);
        this.add(homeMenu,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * enable Instruction screen
     */
    public void enableInstruction(){
        this.dispose();
        this.remove(homeMenu);
        this.add(instruction,BorderLayout.CENTER);//here boom
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * disable Instruction screen
     */
    public void disableInstruction(){
        this.dispose();
        this.remove(instruction);
        this.add(homeMenu,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }



    /**
     * enableHighscore screen
     */
    public void enableHighScore(){
        ReadFile();
        highscoreScore = new Integer[highScoreList.size()];
        highScoreList.toArray (highscoreScore);
        highScore.setSCORE(highscoreScore,lastPlayerScore);
        this.dispose();
        this.remove(homeMenu);
        this.add(highScore,BorderLayout.CENTER);//here boom
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * read the high score name and high score text file
     */
    private void ReadFile() {
        try {

            highScoreList= new ArrayList<Integer>();
            File leaderboardScore = new File("C:\\Users\\Lenovo\\IdeaProjects\\Brick_Destroy-master\\resources\\scoreList.txt");
            Scanner scanner1 = new Scanner(leaderboardScore);
            int x =0;
            while (scanner1.hasNext()) {
                if(scanner1.hasNextInt()){
                    highScoreList.add(scanner1.nextInt());
                }

            }
            scanner1.close();

            lastPlayerScore = highScoreList.get(highScoreList.size()-1);
            Collections.sort(highScoreList, Collections.reverseOrder());
        }
        catch(FileNotFoundException e)
        {
            System.out.println("txt files failed to open.");
            e.printStackTrace();
        }
    }


    /**
     * update high score name and high score list txt file
     * @param score high score array
     */
    public static void WriteFile(int score){
        try {
            System.out.println("Save Succeeded");
            FileWriter highscoreScore = new FileWriter("C:\\Users\\Lenovo\\IdeaProjects\\Brick_Destroy-master\\resources\\scoreList.txt",true);
            highscoreScore.write(String.format("\r\n%d", score));
            highscoreScore.close();
        }
        catch (IOException e)
        {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    /**
     * disable high score screen
     */
    public void disableHighScore(){

        this.dispose();
        this.remove(highScore);
        this.add(homeMenu,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore(){
        return score;
    }

    /**
     * set the position of the screen
     */
    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    /**
     * allow user to play the game when the window screen is focused
     * @param windowEvent indicate user's window screen
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     * everything stops when window screen lost focused
     * @param windowEvent indicate user's window screen
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }
}
