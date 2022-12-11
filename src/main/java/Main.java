import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JFrame{

     ArrayList<BufferedImage> Backgrounds = new ArrayList<>();
     Graphics2D g;
     BufferStrategy bs;
     Mouse mouse = new Mouse();
     int Curtain = 0;
     static Rectangle Curtain1,Curtain2;
     //Wall.png Duplizieren je nach größe und wie man zieht
     boolean Curtainbool = true;

    File dir = new File("TheaterTextures");

    public static void main(String[] args){
        //Vielleicht JX OS unfreundlich
        //System.setProperty("sun.java2d.opengl","True");
        //try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());} catch (Exception e) {e.printStackTrace();}
        new Main();
    }

    public Main()
    {
        super("Hentai Theater");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1366, 768);
        setLocationRelativeTo(null);
        addKeyListener(new Keyboard());
        addMouseMotionListener(mouse);
        addMouseListener(mouse);
        setVisible(true);
        ReadBackgrounds();
        ReadPuppets();
        Stick.init();
        for(int i = 0; i<Puppet.Puppettexts.size(); i++)
        {
            Puppet.Puppets.add(new Puppet());
        }
        while(true) {
            try {Thread.sleep(25);} catch (InterruptedException e) {}
            paint();
        }
    }


    public void paint()
    {
        if(bs == null) {
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        g = (Graphics2D)bs.getDrawGraphics();
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHints(hints);
        g.drawImage(Backgrounds.get(0),0,0,this.getWidth(),this.getHeight(),null);
        Puppet.draw(g);
        Curtain(g);
        g.dispose();
        bs.show();
    }

    public void Curtain(Graphics2D g)
    {
        if(!Curtainbool)
        {
            g.drawImage(Backgrounds.get(2),Curtain1.x,Curtain1.y,this.getWidth()/2,this.getHeight(),null);
            g.drawImage(Backgrounds.get(1),Curtain2.x,Curtain2.y,this.getWidth()/2,this.getHeight(),null);
        }else
        {
            g.drawImage(Backgrounds.get(3),Curtain1.x,Curtain1.y,this.getWidth()/2,this.getHeight(),null);
            g.drawImage(Backgrounds.get(3),Curtain2.x,Curtain2.y,this.getWidth()/2,this.getHeight(),null);
        }
    }

    public void math()
    {

    }

    public void ReadPuppets()
    {
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("Puppet");

            }
        });
        for(File file : files)
        {
            try {
                Puppet.Puppettexts.add(ImageIO.read(file));
            } catch (IOException e) {
                System.out.print(e);
            }
        }
        for(BufferedImage img : Puppet.Puppettexts)
        {
            if(img.getHeight() > getHeight() || img.getWidth() > getWidth())
            {
                float blub = img.getHeight()-getHeight();
                Puppet.Puppetrects.add(new Rectangle2D.Float(0,0,img.getWidth()-blub,img.getHeight()-blub));
            }
            else
            {
                Puppet.Puppetrects.add(new Rectangle2D.Float(0,0,img.getWidth(),img.getHeight()));
            }
        }
    }

    public void ReadBackgrounds() {
        //Curtain auf 0 setzten und als letzes am Anfang zeichnen
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("Background");

            }
        });
        for(File file : files)
        {
            try {
                Backgrounds.add(ImageIO.read(file));
            } catch (IOException e) {
                System.out.print(e);
            }
        }
        Curtain1 = new Rectangle(0-Curtain,0,this.getWidth()/2,this.getHeight());
        Curtain2 = new Rectangle(this.getWidth()/2+Curtain,0,this.getWidth()/2,this.getHeight());
    }

}
