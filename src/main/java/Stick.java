import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Stick {

    static BufferedImage Sticktext;
    Rectangle Stickrect;
    static ArrayList<Stick> Sticks = new ArrayList<>();
    byte id;

    public static void init()
    {
        try {Sticktext = ImageIO.read(new File("TheaterTextures/Stick.png"));} catch (IOException e) {e.printStackTrace();}
    }

    public Stick()
    {
        id = (byte)(Sticks.size()+1);
        Stickrect = new Rectangle(Sticktext.getWidth()*id,0,Sticktext.getWidth(),Sticktext.getHeight());
    }

    public void draw(Graphics2D g)
    {
        g.drawImage(Sticktext,(int)Stickrect.getX(),(int)Stickrect.getY(),(int)Stickrect.getWidth(),(int)Stickrect.getHeight(),null);
    }

}
