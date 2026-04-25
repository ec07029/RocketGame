import java.awt.*;
import java.awt.event.*;
import javax.swing.*; //JFrame
import java.util.Random; //import random

public class asteroid extends JComponent implements ActionListener{

    //x and y coordinates of the asteroid
    protected int asteroidX = 10, asteroidY = 10;

    public asteroid(int asteroidX, int asteroidY){
        this.asteroidX = asteroidX;
        this.asteroidY = asteroidY;
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(asteroidX, asteroidY, 15, 15);
    }

    public void update(){
        asteroidY += 5;
    }

    /*
    public asteroid(){
        Random rand = new Random();
        asteroidX = rand.nextInt(gameDemo.x);
        System.out.println(asteroidX);
        //asteroidY = rand.nextInt(gameDemo.y);
        //System.out.println(asteroidY);
        asteroidSpeed = 5;
    }
    */

    public void actionPerformed(ActionEvent e){
        if(asteroidY == gameDemo.getWindowHeight()){
            asteroidY = 0;
        }
    }

}