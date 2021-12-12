package gameboard;

import main.java.GameFrame;
import wall.Wall;
import wall.WallController;
import wall.WallModel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameBoardController {

    /**
     * implements actions of the specific keys are pressed like A, D, ESC, SPACE and ALT+SHIFT+F!
     * @param keyEvent indicate user's keyboard is pressed
     * @param gameBoard game board display
     */
    public void controllerKeyPressed(KeyEvent keyEvent, GameBoard gameBoard) {
        int a = keyEvent.getKeyCode();
        switch (a) {
            //Move player left
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                Wall.playerController.moveLeft();
                break;

            //Move player right
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                Wall.playerController.moveRight();
                break;

            //pause & display menu
            case KeyEvent.VK_ESCAPE:
                GameBoardModel.showPauseMenu = !GameBoardModel.showPauseMenu;
                gameBoard.repaint();
                gameBoard.gameTimer.stop();
                break;

            //Pause game
            case KeyEvent.VK_SPACE:
                if (gameBoard.gameTimer.isRunning())
                    gameBoard.gameTimer.stop();
                else
                    gameBoard.gameTimer.start();
                break;

            //Show Debug Panel
            case KeyEvent.VK_F1:
                if (keyEvent.isAltDown() && keyEvent.isShiftDown())
                    gameBoard.debugConsole.setVisible(true);
            default:
                gameBoard.wall.player.stop();
        }
    }


    /**
     * implements actions of the specific buttons are clicked on
     * @param mouseEvent indicate user's mouse is clicked
     * @param gameBoard game board display
     * @param owner game frame
     */
    public void controllerMouseClicked (MouseEvent mouseEvent,GameBoard gameBoard,GameFrame owner) {
        Point p = mouseEvent.getPoint();
        if(!GameBoardModel.showPauseMenu)
            return;

        if(gameBoard.continueButtonRect.contains(p)){
            GameBoardModel.showPauseMenu = false;
            gameBoard.repaint();
        }

        else if(gameBoard.restartButtonRect.contains(p)){
            GameBoardModel.message1 = "Please Wait, ";
            GameBoardModel.message2 = "Restarting Game...";
            gameBoard.wallController.ballReset();
            gameBoard.wallController.wallReset();
            GameBoardModel.showPauseMenu = false;
            gameBoard.repaint();
        }
        else if (gameBoard.homeButtonRect.contains(p)){
            GameBoardModel.showPauseMenu = false;
            gameBoard.wallController.ballReset();
            //gameBoard.wall.setPlayerScore(0);
            gameBoard.wallController.wallReset();
            gameBoard.wallController.resetLevels();
            owner.disableGameBoard();
        }
        else if(gameBoard.exitButtonRect.contains(p)){
            System.exit(0);
        }
    }

    /**
     * cursor is changed to hand cursor when the user clicks exit, continue or restart button
     * @param mouseEvent indicate user's mouse movement
     * @param gameboard game board display
     */
    public void controllerMouseMoved(MouseEvent mouseEvent, GameBoard gameboard) {
        Point p = mouseEvent.getPoint();
        if(gameboard.exitButtonRect != null && GameBoardModel.showPauseMenu) {
            if (gameboard.exitButtonRect.contains(p) || gameboard.continueButtonRect.contains(p) || gameboard.restartButtonRect.contains(p) || gameboard.homeButtonRect.contains(p))
                gameboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                gameboard.setCursor(Cursor.getDefaultCursor());
        }
        else{
            gameboard.setCursor(Cursor.getDefaultCursor());
        }
    }
}
