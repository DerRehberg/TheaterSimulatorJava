import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {

            if(keyEvent.getKeyChar() == '+')
            {
                Puppet.Puppets.get(Mouse.selectedStick).resize(1.1f);
                Puppet.Puppets.get(Mouse.selectedStick).math();
            }else if(keyEvent.getKeyChar() == '-')
            {
                Puppet.Puppets.get(Mouse.selectedStick).resize(0.9f);
                Puppet.Puppets.get(Mouse.selectedStick).math();
            }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
