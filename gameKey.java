import java.awt.*;
import javax.swing.*; //JFrame
import java.awt.event.*; //KeyListener

//KeyAdapter is a dummy class that has dummy methods
//Making a subclass to KeyAdapter allows to pick/choose which methods to complete
public class gameKey extends KeyAdapter{

    protected gameDemo gd;

    public gameKey(){

    }

    public gameKey(gameDemo gd){
        this.gd = gd;
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_UP){
            gameDemo.y -= 5;
            //System.out.println("Moved up: " + gameDemo.y);
            gd.repaint(); //gd refers to the gameDemo object in gameDemo.java in gameKey k = new gameKey(g);
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            //System.out.println("Moved down");
            gameDemo.y += 5;
            gd.repaint(); //gd refers to the gameDemo object in gameDemo.java in gameKey k = new gameKey(g);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            //System.out.println("Moved left");
            gameDemo.x -= 5;
            gd.repaint(); //gd refers to the gameDemo object in gameDemo.java in gameKey k = new gameKey(g);
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            //System.out.println("Moved right");
            gameDemo.x += 5;
            gd.repaint(); //gd refers to the gameDemo object in gameDemo.java in gameKey k = new gameKey(g);
        }
        
    }

}