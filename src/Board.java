
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu20924612v
 */
public class Board extends javax.swing.JPanel {

    private Timer timer;
    private Snake snake;
    private Food food;
    private int deltaTime;

    public Board(int numRows, int numCols) {
        super();
        deltaTime = 500;
        /*timer = new Timer(deltaTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                move();
            }
        });*/
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        snake.paint(g2d, getSquareWidth(), getSquareHeight());
        food.paint(g2d, getSquareWidth(), getSquareHeight());
    }

    public int getSquareWidth() {
        return getWidth() / Config.NUM_COLS;
    }

    public int getSquareHeight() {
        return getHeight() / Config.NUM_ROWS;
    }

    public static void drawSquare(Graphics2D g, int row, int col, int squareWidth, int squareHeight, Color color) {
        int x = col * squareWidth;
        int y = row * squareHeight;
        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth - 2, squareHeight - 2);
        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight - 1, x, y);
        g.drawLine(x, y, x + squareWidth - 1, y);
        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight - 1, x + squareWidth - 1, y + squareHeight - 1);
        g.drawLine(x + squareWidth - 1, y + squareHeight - 1, x + squareWidth - 1, y + 1);

    }
}
