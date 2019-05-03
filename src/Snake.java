
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu20924612v
 */
public class Snake {

    private List<Node> body;
    private Direction direction;
    private int remainingRow;

    public Snake(int numNodes) {
        body = new ArrayList<Node>();
        body.add(new Node(Config.NUM_ROWS / 2, Config.NUM_COLS / 2));
        body.add(new Node(Config.NUM_ROWS / 2, Config.NUM_COLS / 2 - 1));
        body.add(new Node(Config.NUM_ROWS / 2, Config.NUM_COLS / 2 - 2));
        body.add(new Node(Config.NUM_ROWS / 2, Config.NUM_COLS / 2 - 3));
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void paint(Graphics2D g, int squareWidth, int squareHeight) {
        for (Node node : body) {
            Board.drawSquare(g, node.getRow(), node.getCol(), squareWidth, squareHeight, Color.BLUE);
        }
    }

}
