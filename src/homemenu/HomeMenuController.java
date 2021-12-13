package homemenu;

import java.awt.*;
import java.awt.event.MouseEvent;

public class HomeMenuController {

    HomeMenuModel homeMenuModel = new HomeMenuModel();

    /*
     * implements methods is MouseListener
     * Start button enable GameBoard
     * Instruction button enable Instruction
     * Highscore button enable Highscore
     * Exit button enable user to exit the game
     * @param mouseEvent indicate user's mouse is clicked
     */
    public void homeMenuMouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (homeMenuModel.startButton.contains(p)) {
            homeMenuModel.startgameboard();
        } else if (homeMenuModel.instructionButton.contains(p)) {
            homeMenuModel.startinstruction();
        } else if (homeMenuModel.highscoreButton.contains(p)) {
            homeMenuModel.starthighscore();
        } else if (homeMenuModel.exitButton.contains(p)) {
            System.out.println("Goodbye ");
            System.exit(0);
        }
    }

    /**
     * cursor is changed to hand cursor when the user is moved to the buttons
     * @param mouseEvent indicate user's mouse is moved
     * @param homeMenuView home menu display
     */
    public void homeMenuMouseMoved(MouseEvent mouseEvent, HomeMenu homeMenuView) {
        Point p = mouseEvent.getPoint();
        if (homeMenuModel.startButton.contains(p) || homeMenuModel.exitButton.contains(p) || homeMenuModel.instructionButton.contains(p) || homeMenuModel.backButton.contains(p) || homeMenuModel.highscoreButton.contains(p)) {
            homeMenuView.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (homeMenuModel.startButton.contains(p)) {
                homeMenuModel.setStartHoverTrue();
                homeMenuView.repaint(homeMenuModel.getStartButtonX(), homeMenuModel.getStartButtonY(), homeMenuModel.startButton.width + 1, homeMenuModel.startButton.height + 1);
            } else if (homeMenuModel.exitButton.contains(p)) {
                homeMenuModel.setExitHoverTrue();
                homeMenuView.repaint(homeMenuModel.getExitButtonX(), homeMenuModel.getExitButtonY(), homeMenuModel.exitButton.width + 1, homeMenuModel.exitButton.height + 1);
            } else if (homeMenuModel.instructionButton.contains(p)) {
                homeMenuModel.setInstructionHoverTrue();
                homeMenuView.repaint(homeMenuModel.getInstructionButtonX(), homeMenuModel.getInstructionButtonY(), homeMenuModel.instructionButton.width + 1, homeMenuModel.instructionButton.height + 1);
            } else if (homeMenuModel.backButton.contains(p)) {
                homeMenuModel.setBackHoverTrue();
                homeMenuView.repaint(homeMenuModel.getBackButtonX(), homeMenuModel.getBackButtonY(), homeMenuModel.backButton.width + 1, homeMenuModel.backButton.height + 1);
            } else if (homeMenuModel.highscoreButton.contains(p)) {
                homeMenuModel.setHighScoreHoverTrue();
                homeMenuView.repaint(homeMenuModel.getHighscoreButtonX(), homeMenuModel.getHighscoreButtonY(), homeMenuModel.highscoreButton.width + 1, homeMenuModel.highscoreButton.height + 1);
            }
        } else {
            homeMenuView.setCursor(Cursor.getDefaultCursor());
        }
    }
}
