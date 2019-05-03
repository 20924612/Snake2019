
import java.awt.Color;
import java.awt.Graphics2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu20924612v
 */
public class Food {

    private int row;
    private int col;

    public Food(boolean special, Snake snake) {
        row = 0;
        col = 0;
        special = false;
    }
    
    private void generatePosition(){
        
    }

    public void paint(Graphics2D g, int squareWidth, int squareHeight) {
        Board.drawSquare(g, getRow(), getCol(), squareWidth, squareHeight, Color.GREEN);
    }
}
