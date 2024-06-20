package graphics;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Runner extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //instance variable
    private int WIDTH;
    private int HEIGHT;
    private Player player;
    private Background mainBackground;
    private ArrayList<String> keys = new ArrayList<String>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<CollidableObject> hitBoxes = new ArrayList<CollidableObject>();
    private int tick;

    //Default Constructor
    public Runner()
    {
        //initializing instance variables
        WIDTH = 1000;
        HEIGHT = 500;
        tick = 0;

        mainBackground = new Background(0, 0, WIDTH*4, HEIGHT);
        player = new Player(1, 1, 100, 100, 10);

        addEnemy(3);

        //Setting up the GUI
        JFrame gui = new JFrame(); //This makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
        gui.setTitle("Learning Graphics"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30)); //Setting the size for gui
        gui.setResizable(false); //Makes it so the gui cant be resized
        gui.getContentPane().add(this); //Adding this class to the gui

        /*If after you finish everything, you can declare your buttons or other things
         *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
         */

        gui.pack(); //Packs everything together
        gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
        gui.setVisible(true); //Makes the gui visible
        gui.addKeyListener(this);//stating that this object will listen to the keyboard
        gui.addMouseListener(this); //stating that this object will listen to the Mouse
        gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse moves
    }
    //This method will acknowledge user input
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_D){
            if(!keys.contains("d"))
                keys.add("d");
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            if(!keys.contains("a"))
                keys.add("a");
        }
        else if(e.getKeyCode() == KeyEvent.VK_W){
            if(!keys.contains("w"))
                keys.add("w");
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            if(!keys.contains("s"))
                keys.add("s");
        }
        else if(e.getKeyCode() == KeyEvent.VK_J){
            if(!keys.contains("j"))
                keys.add("j");
        }
    }
    //All your UI drawing goes in here
    public void paintComponent(Graphics g)
    {
        mainBackground.drawSelf(g);
        player.drawSelf(g);
        drawHitboxes(g);
        drawEnemies(g);
    }
    public void loop()
    {
        moveBackground();
        processKeys();
        player.applyVelo();
        moveEnemy();

        tick++;

        repaint();
    }
    //These methods are required by the compiler.
    //You might write code in these methods depending on your goal.
    public void keyTyped(KeyEvent e)
    {
    }
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_A)
            keys.remove("a");
        else if(e.getKeyCode() == KeyEvent.VK_D)
            keys.remove("d");
        else if(e.getKeyCode() == KeyEvent.VK_S)
            keys.remove("s");
        else if(e.getKeyCode() == KeyEvent.VK_W)
            keys.remove("w");
        else if(e.getKeyCode() == KeyEvent.VK_J)
            keys.remove("j");
    }
    public void mousePressed(MouseEvent e)
    {
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void mouseMoved(MouseEvent e)
    {
    }
    public void mouseDragged(MouseEvent e)
    {
    }
    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    loop();
                    try{
                        Thread.sleep(1000 / ticks);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        gameThread.start();
    }

    public void processKeys(){
        if(keys.contains("a") && player.getNextX() > 0)
            player.moveLeft();
        else if(keys.contains("d") && player.getNextX() + player.getWidth() < WIDTH)
            player.moveRight();
        else
            player.resetXVelo();

        if(keys.contains("w") && player.getNextY() > 0)
            player.moveUp();
        else if(keys.contains("s") && player.getNextY() + player.getHeight() < HEIGHT)
            player.moveDown();
        else
            player.resetYVelo();

        if(keys.contains("j")){
            CollidableObject temp  = player.createHit(tick);
            if(temp != null){
                hitBoxes.add(temp);
            }
        }
    }

    public static void main(String[] args)
    {
        Runner g = new Runner();
        g.start(60);
    }

    public void moveBackground(){
        int offset = 300;
        double maxVelo = player.getMaxVelo();
        if(player.getX() + player.getWidth() >= WIDTH - offset && keys.contains("d")) {
            mainBackground.move(-maxVelo);
            for(int i = 0; i < enemies.size(); i++){
                enemies.get(i).move(-maxVelo);
            }
        }
        else if(player.getX() <= offset && keys.contains("a")){
            mainBackground.move(maxVelo);
            for(int i = 0; i < enemies.size(); i++){
                enemies.get(i).move(maxVelo);
            }
        }
    }

    public void moveEnemy(){
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).chaseTarget(player.getCenterX(), player.getCenterY());
            for(int b = i + 1; b < enemies.size(); b++) {
                //enemies.get(i).CollideWithOthers(enemies.get(b));
                //enemies.get(i).applyVelo();
                //enemies.get(b).applyVelo();
            }
            enemies.get(i).applyVelo();
        }

    }

    public void drawEnemies(Graphics g){
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).drawSelf(g);
        }
    }
    public void drawHitboxes(Graphics g){
        for(int i = 0; i < hitBoxes.size(); i++){
            hitBoxes.get(i).drawSelf(g);
        }
    }
    public void addEnemy(int a){
        for(int i = 0; i < a; i++){
            enemies.add(new Enemy(10, 10, 100, 100, 2));
        }
    }
}
