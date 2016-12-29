package FlyWeight;

import GUI.SelectionManager;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Ty on 11/8/2016 at 1:52 AM.
 */
public class LogoBothStates extends Logo implements MouseListener {

    private JLabel label;

    private LogoIntrinsicState intrinsicState;
    public LogoExtrinsicState extrinsicState;

    public LogoBothStates(LogoIntrinsicState logoIntrinsicState, LogoExtrinsicState logoExtrinsicState) {
        intrinsicState = logoIntrinsicState;
        extrinsicState = logoExtrinsicState;

        label = new JLabel(intrinsicState.getImage());
        label.setLocation(extrinsicState.location);
        label.setSize(extrinsicState.dimension);
    }

    @Override
    public void draw(JPanel panel) {
        if (label == null) return;
        panel.add(label, 0); // Index zero will place the label on top of anything already on the panel.
        label.addMouseListener(this);
    }

    @Override
    public void erase(JPanel panel) {
        panel.remove(label);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!SelectionManager.getInstance().isSelectionMode()) return;
        if (this.label.equals(e.getSource())) {
            SelectionManager.getInstance().addSelectedLogo(label);
            System.out.println("TESTING MOUSE CLICK LISTENER at " + label.getLocation());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
