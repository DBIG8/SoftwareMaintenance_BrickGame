package gameboard;

import wall.Wall;

import java.awt.*;


public class GameBoardModel {
    //Text in Pause Menu
    public static final String CONTINUE = "Continue";      //Continue Button Text
    public static final String RESTART = "Restart";        //Restart Button Text
    public static final String HOME = "Home";              //Home Button Text
    public static final String EXIT = "Exit";              //Exit Button Text
    public static final String PAUSE = "Pause Menu";       //Pause Menu Text

    //Format for Pause Menu
    private static final int TEXT_SIZE = 40;
    private static final Color MENU_COLOR = new Color(255,255,255);
    private static final Color BG_COLOR = new Color(238,232,170);

    //Game frame size
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    public Wall wall;

    public static String message1;
    public static String message2;
    public static String message3;

    public static boolean showPauseMenu;

    private Font menuFont;

    /**
     * get the colour of the background
     * @return return the colour of the background
     */
    public Color getBackgroundColour()
    {
        return BG_COLOR;
    }

    /**
     * get the size of the text
     * @return return the text size
     */
    public int getTextSize()
    {
        return TEXT_SIZE;
    }

    /**
     * get the height of the game board
     * @return return the integer for the gameboard height
     */
    public int getDefHeight()
    {
        return DEF_HEIGHT;
    }

    /**
     *
     * @return integer for the gameboard width
     */
    public int getDefWidth()
    {
        return DEF_WIDTH;
    }

    /**
     *
     * @return Colour for the pause menu
     */
    public Color getMenuColor()
    {
        return MENU_COLOR;
    }

    /**
     * set font for the pause menu
     * @param font Font
     */

    public void setMenuFont(Font font)
    {
        menuFont=font;

    }

    /**
     * get font for the pause menu
     * @return return Font for the pause menu
     */
    public Font getMenuFont()
    {
        return menuFont;
    }

}
