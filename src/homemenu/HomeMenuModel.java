package homemenu;

import main.java.GameFrame;

import java.awt.*;

public class HomeMenuModel extends Component{

    //Start Menu Button Rectangles
    public Rectangle menuFace;
    public Rectangle startButton;
    public Rectangle exitButton;
    public Rectangle highscoreButton;
    public Rectangle instructionButton;
    //Instruction Menu Button Rectangles
    public Rectangle backButton;

    public GameFrame owner;

    //Hover confirmation
    public boolean startHover;
    public boolean highscoreHover;
    public boolean instructionHover;
    public boolean exitHover;
    public boolean backHover;

    public void setStartHoverTrue()
    {
        startHover = true;
    }

    public void setHighScoreHoverTrue()
    {
        highscoreHover = true;
    }


    public void setInstructionHoverTrue()
    {
        instructionHover = true;
    }


    public void setExitHoverTrue()
    {
        exitHover = true;
    }

    public void setBackHoverTrue()
    {
        backHover = true;
    }


    public int getStartButtonX()
    {
        return startButton.x;
    }


    public int getStartButtonY()
    {
        return startButton.y;
    }
    public int getInstructionButtonX()
    {
        return instructionButton.x;
    }
    public int getInstructionButtonY()
    {
        return instructionButton.y;
    }
    public int getHighscoreButtonX()
    {
        return highscoreButton.x;
    }
    public int getHighscoreButtonY()
    {
        return highscoreButton.y;
    }
    public int getExitButtonX()
    {
        return exitButton.x;
    }
    public int getExitButtonY()
    {
        return exitButton.y;
    }
    public int getBackButtonX()
    {
        return exitButton.x;
    }
    public int getBackButtonY()
    {
        return exitButton.y;
    }

    public void startgameboard()
    {
        owner.enableGameBoard();
    }
    public void starthighscore()
    {
        owner.enableHighScore();
    }
    public void  startinstruction()
    {
        owner.enableInstruction();
    }
}
