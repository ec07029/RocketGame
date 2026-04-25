import java.awt.*;
import java.awt.event.*;
import javax.swing.*; //JFrame
import java.util.Random; //import random
import java.util.ArrayList; //to spawn more asteroids

public class gameDemo extends JComponent implements ActionListener{

    //width and height of game window
    private static int w = 500, h = 500;

    //x and y coordinates of rocket
    protected static int x = 250, y = x + 200;

    //Number of asteroids
    private static int asteroidNum = 3;

    //Random variable
    private static Random rand = new Random();

    //Asteroid array
    //private static asteroid[] asteroidArray = new asteroid[asteroidNum];
    private static ArrayList<asteroid> asteroidArray = new ArrayList<>();

    //Spawn timer
    private static int spawn = 0;

    //Must call asteroidArray in constructor to create once
    public gameDemo(){
        JOptionPane.showMessageDialog(this, "Goal: Move rocket to top of the window.");
        for (int i = 0; i < 3; i++){
            //asteroidArray[i] = new asteroid(rand.nextInt(w), 10);
            asteroidArray.add(new asteroid(rand.nextInt(w), 10));
        }
    }

    public static int getWindowHeight(){
        return h;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 10, 10);

        //Draw asteroids based on asteroid coordinates in asteroidArray in gameDemo constructor
        g.setColor(Color.RED);
        for (int i = 0; i < asteroidArray.size(); i++){
            g.fillRect(asteroidArray.get(i).asteroidX, asteroidArray.get(i).asteroidY, 15, 15);
        }
              
    }

    //Completes actionPerformed() from interface ActionListener
    public void actionPerformed(ActionEvent e){

        int r = rand.nextInt(100);
        if (r < 10) {
            asteroidArray.add(new asteroid(rand.nextInt(w), 10));
        }

        //Asteroid moves on its own by updating y-coordinate along with timer
        for (int i = 0; i < asteroidArray.size(); i++){
            asteroidArray.get(i).update();

            //If asteroid reaches bottom window
            if (asteroidArray.get(i).asteroidY == h){
                //Remove asteroid
                asteroidArray.remove(i);
                i--; //Removed ith asteroid so next asteroid in loop will be i + 1 which is now i after removal
                continue; //go to next iteration after removal

                //Bring asteroid back to the top
                //asteroidArray.get(i).asteroidY = 0;
                //asteroidArray.get(i).asteroidX = rand.nextInt(w);
            }

            //If rocket hits asteroid, game over
            //Take into account width and height of the asteroid overlap
            if (x > asteroidArray.get(i).asteroidX-15 && x < asteroidArray.get(i).asteroidX + 15 && 
                y > asteroidArray.get(i).asteroidY-15 && y < asteroidArray.get(i).asteroidY + 15){
                JOptionPane.showMessageDialog(this, "Rocket hit asteroid. Game over.");
                Object source = e.getSource();
                Timer gameTimer = (Timer) source;
                gameTimer.stop();
            }
        }

        //If rocket reaches top, display message and end game
        if (y <= 0){
            //JOptionPane requires constructor parameter and String parameter
            JOptionPane.showMessageDialog(this, "Success: Rocket reached the top!");
            Object source = e.getSource(); //get referece type of ActionEvent, which was designed to be Object
            Timer gameTimer = (Timer) source; //cast Object as Timer object
            gameTimer.stop(); //stop message from repeating display after pressing OK
        }
        repaint();
    }

    public static void main(String[] args){

        //Declare gameDemo object
        gameDemo g = new gameDemo();

        //Declare timer, which receives ActionListener/ActiveEvents
        Timer timer = new Timer(16, g); //Every 16ms, send an ActionEvent to g
        timer.start();

        //Use JFrame(String title) constructor
        //JFrame is GUI component
        JFrame frame = new JFrame("Rocket game - by Eren Chu"); //constructor: JFrame(String title)

        /*
        //Add image
        ImageIcon icon = new ImageIcon("icon.png");
        JLabel iconLabel = new JLabel(icon);
        frame.add(iconLabel);
        */

        //adds white shape from paintComponent in sketch() to Jframe
        frame.add(g);

        //Set frame size using setSize() from Component superclass
        frame.setSize(w, h);

        //Shows or hides frame from setVisible() from Component superclass
        frame.setVisible(true); 

        //Sets the background color of Content Pane
        Color c = new Color(0,0,0);
        frame.getContentPane().setBackground(c); // Color class is parameter

        //Default operation when the user closes frame
        //setDefaultCloseOperation(int operation) requires an int parameter
        //public static final int EXIT_ON_CLOSE where EXIT_ON_CLOSE stores an integer
        //Java compiler swaps EXIT_ON_CLOSE with the defined integer before code runs
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add key listener to receive keyboard presses
        gameKey k = new gameKey(g);
        frame.addKeyListener(k);

    }

}