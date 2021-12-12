package main.java;

import java.awt.*;

/**
 * Entry point of the game.
 */
public class GraphicsMain {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> new GameFrame().initialize());
    }
}
