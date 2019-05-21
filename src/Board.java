
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.Timer;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu20924612v
 */
public class Board extends javax.swing.JPanel implements ActionListener {

    private int counterFood = 0;

    public void actionPerformed(ActionEvent ae) {
        snake.move();
        //snake2.move();
        makeCollision();

        if (snake.eatedFood(specialFood) == true && counterFood == 5) {
            snake.growSnake(20);
            snake.eatFood = false;
            scoreBoard1.increment(5);
        }
        if (snake.eatedFood(food) == true && counterFood != 5) {
            counterFood++;
            snake.growSnake(10);
            food.generatePosition(snake, snake2);
            snake.eatFood = false;
            scoreBoard1.increment(1);

        }
        if (snake2.eatedFood(specialFood) == true && counterFood == 5) {
            snake2.growSnake(20);
            snake2.eatFood = false;
            scoreBoard2.increment(5);
        }
        if (snake2.eatedFood(food) == true && counterFood != 5) {
            counterFood++;
            snake2.growSnake(10);
            food.generatePosition(snake, snake2);
            snake2.eatFood = false;
            scoreBoard2.increment(1);

        }
        repaint();

        Toolkit.getDefaultToolkit().sync();

    }

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent evt) {
            switch (evt.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.DOWN) {
                        snake.setDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.UP) {
                        snake.setDirection(Direction.DOWN);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.RIGHT) {
                        snake.setDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.LEFT) {
                        snake.setDirection(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_P:
                    pause();
            }
        }
    }

    class MyKeyAdapter2 extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent evt) {
            switch (evt.getKeyCode()) {
                case KeyEvent.VK_W:
                    if (snake2.getDirection() != Direction.DOWN) {
                        snake2.setDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_S:
                    if (snake2.getDirection() != Direction.UP) {
                        snake2.setDirection(Direction.DOWN);
                    }
                    break;
                case KeyEvent.VK_A:
                    if (snake2.getDirection() != Direction.RIGHT) {
                        snake2.setDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_D:
                    if (snake2.getDirection() != Direction.LEFT) {
                        snake2.setDirection(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    pause();
            }
        }
    }

    private Timer timer;
    private Timer specialFoodTimer;
    private Snake snake;
    private Snake snake2;
    private Food food;
    private int deltaTime;
    private ScoreBoard scoreBoard1;
    private ScoreBoard scoreBoard2;
    private Direction direction;
    private boolean isPaused;
    private SpecialFood specialFood;
    private Level level;

    public Board() {
        super();
        snake = new Snake(5);
        snake2 = new Snake(5);
        food = new Food(true, snake);
        specialFood = new SpecialFood(true, snake);
        scoreBoard1 = new ScoreBoard();
        scoreBoard2 = new ScoreBoard();
        level = Level.MEDIUM;
        deltaTime = 150;
        timer = new Timer(deltaTime, this);
        timer.start();
        snake.setDirection(direction.UP);
        snake2.setDirection(direction.DOWN);
        isPaused = false;
        MyKeyAdapter keyAdapter = new MyKeyAdapter();
        MyKeyAdapter2 keyAdapter2 = new MyKeyAdapter2();
        addKeyListener(keyAdapter);
        addKeyListener(keyAdapter2);
        setFocusable(true);
        food.generatePosition(snake, snake2);
        specialFood.generatePosition(snake, snake2);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawBoard(g2d);
        if (snake != null) {
            snake.paint(g2d, getSquareWidth(), getSquareHeight());
            //snake2.paint(g2d, getSquareWidth(), getSquareHeight());
        }

        /*if (counterFood == 5) {
            specialFood.generatePosition(snake, snake2);
            specialFood.paint(g2d, getSquareWidth(), getSquareHeight());
            if (snake.eatedFood(specialFood) == true) {
                counterFood = 0;
            }
            if (snake2.eatedFood(specialFood) == true) {
                counterFood = 0;
            }
        }*/
        food.paint(g2d, getSquareWidth(), getSquareHeight());

    }

    public void setScoreBoard(ScoreBoard scoreBoard1) {
        this.scoreBoard1 = scoreBoard1;
    }

    public void setScoreBoard2(ScoreBoard scoreBoard2) {
        this.scoreBoard2 = scoreBoard2;
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

    public void drawBoard(Graphics2D g) {
        for (int row = 0; row < Config.NUM_ROWS; row++) {
            for (int col = 0; col < Config.NUM_COLS; col++) {
                drawSquare(g, row, col, getSquareWidth(), getSquareHeight(), Color.DARK_GRAY);
            }
        }
    }

    public void pause() {
        if (isPaused == false) {
            timer.stop();
            isPaused = true;
            return;
        }

        if (isPaused == true) {
            timer.restart();
            isPaused = false;
            return;
        }

    }

    public void gameOver() {
        timer.stop();
        repaint();
        JOptionPane.showMessageDialog(this, "Score : " + scoreBoard1.getScore() + "\n" + "Score 2 : " + scoreBoard2.getScore());
    }

    public void makeCollision() {
        for (int i = 1; snake.getBody().size() > i; i++) {
            if (snake.getBody().get(0).getRow() == snake.getBody().get(i).getRow() && snake.getBody().get(0).getCol() == snake.getBody().get(i).getCol()
                    || snake.getBody().get(0).getRow() == snake2.getBody().get(0).getRow() && snake.getBody().get(0).getCol() == snake2.getBody().get(0).getCol()) {
                gameOver();
            }
        }

        if (snake.getBody().get(0).getRow() > 30 || snake.getBody().get(0).getRow() < 0 || snake.getBody().get(0).getCol() > 40 || snake.getBody().get(0).getCol() < 0) {
            gameOver();
        }

        for (int i = 1; snake2.getBody().size() > i; i++) {
            if (snake2.getBody().get(0).getRow() == snake2.getBody().get(i).getRow() && snake2.getBody().get(0).getCol() == snake2.getBody().get(i).getCol()
                    || snake2.getBody().get(0).getRow() == snake.getBody().get(0).getRow() && snake2.getBody().get(0).getCol() == snake.getBody().get(0).getCol()) {
                gameOver();
            }
        }

        if (snake2.getBody().get(0).getRow() > 30 || snake2.getBody().get(0).getRow() < 0 || snake2.getBody().get(0).getCol() > 40 || snake2.getBody().get(0).getCol() < 0) {
            gameOver();
        }
    }

    /*private void level(){
        switch(level){
            case 3 : level.HARD;
            break;
            
        }
    }*/
}
