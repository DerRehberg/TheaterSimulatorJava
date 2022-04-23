import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

public class Mouse extends MouseAdapter {

    private int x;
    private int y;
    static byte selectedStick = 1;
    boolean newstick = true;

    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        for(Stick stick : Stick.Sticks)
        {
            if(stick.Stickrect.contains(x,y) && newstick)
            {
                selectedStick = (byte)(stick.id -1);
                newstick = false;
            }
        }
    }

    public void mouseDragged(MouseEvent e) {

        int dx = e.getX() - x;
        int dy = e.getY() - y;


        if(Main.Curtain1.contains(x,y) && Main.Curtain1.x + dx <= 0)
        {
                Main.Curtain1.x += dx;
        }else if(Main.Curtain2.contains(x,y) && Main.Curtain2.x + dx >= 682)
        {
            Main.Curtain2.x += dx;
        }

        if(!newstick)
        {
            Stick.Sticks.get(selectedStick).Stickrect.x += dx;
            Stick.Sticks.get(selectedStick).Stickrect.y += dy;
            if(dx > 0 && Puppet.Puppets.get(selectedStick).rotation > -0.15f)
            {
                Puppet.Puppets.get(selectedStick).rotation -= 0.03f;
            }else if(dx < 0 && Puppet.Puppets.get(selectedStick).rotation < 0.15f)
            {
                Puppet.Puppets.get(selectedStick).rotation += 0.03f;
            }
            Puppet.Puppets.get(selectedStick).math();
        }
        x += dx;
        y += dy;
    }

    public void mouseReleased(MouseEvent e)
    {
        newstick = true;
    }

}
