import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Puppet {

    static ArrayList<Rectangle2D.Float> Puppetrects = new ArrayList<>();
    static ArrayList<BufferedImage> Puppettexts = new ArrayList<>();
    static ArrayList<Puppet> Puppets = new ArrayList<>();
    Rectangle2D rect;
    BufferedImage img;
    AffineTransform at;AffineTransformOp op;
    byte id;
    float size = 1.0f;
    float rotation = 0;

    public Puppet(){
        id = (byte)(Puppets.size());
        img = Puppettexts.get(id);
        rect = new Rectangle2D.Float(0,0,img.getWidth(),img.getHeight());
        img = Puppettexts.get(id);
        Stick.Sticks.add(new Stick());
        math();
    }

    public void resize(float modifier)
    {
        size *= modifier;
        rect = new Rectangle2D.Float((float)rect.getX(),(float)rect.getY(),img.getWidth()*size,img.getHeight()*size);
    }

    public void math()
    {
        Rectangle2D Stickrect = Stick.Sticks.get((id)).Stickrect;
        rect.setRect((int)(Stickrect.getCenterX()-(rect.getWidth()/2)),(int)(Stickrect.getY()-(rect.getHeight()/2)),(int)rect.getWidth(),(int)rect.getHeight());
    }

    public static void draw(Graphics2D g)
    {
        for(Stick stick : Stick.Sticks)
        {
            stick.draw(g);
        }
        for(Puppet puppet : Puppets)
        {
            Rectangle2D Stickrect = Stick.Sticks.get((puppet.id)).Stickrect;
            //g.drawImage(puppet.img,at,null);
            //g.drawImage(puppet.op.filter(puppet.img,null),(int)(Stickrect.getCenterX()-(puppet.rect.getWidth()/2)),(int)(Stickrect.getY()-(puppet.rect.getHeight()/2)),(int)puppet.rect.getWidth(),(int)puppet.rect.getHeight(),null);
            Graphics2D gg = (Graphics2D) g.create();
            //Fehler Rectangle wird nicht geupdatet - Notfalls Stick mit Puppe verbinden und wenn nicht verbunden dann rectangle von Puppe nicht updaten
            //Hier nur Rect von Puppet nehmen und dieses unabhängig von diesem Befehl updaten #Mathe und Art trennen
            gg.rotate(puppet.rotation,(int)(puppet.rect.getX()+(puppet.rect.getWidth()/2)),(int)(puppet.rect.getY()+(puppet.rect.getHeight()/2)));
            //Rectangle abfragen
            gg.drawImage(puppet.img,(int)puppet.rect.getX(),(int)puppet.rect.getY(),(int)puppet.rect.getWidth(),(int)puppet.rect.getHeight(),null);
            gg.dispose();
        }
    }

}
