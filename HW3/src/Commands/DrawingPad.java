package Commands;

import FlyWeight.LogoFactory;
import FlyWeight.LogoBothStates;
import FlyWeight.LogoExtrinsicState;

import javax.swing.*;

/**
 * Created by Ty on 11/3/2016 at 7:09 PM.
 */
public class DrawingPad extends DrawableSurface {

    private JPanel panel;

    public DrawingPad(JPanel panel) {
        this.panel = panel;
        panel.setLayout(null);
    }

    @Override
    public void add(int xVal, int yVal, int imageKey) {
        LogoExtrinsicState logoExtrinsicState = new LogoExtrinsicState(xVal, yVal);
        LogoBothStates logo = LogoFactory.getImage(imageKey, logoExtrinsicState);
        logo.draw(panel);
    }

    @Override
    public void select() {

    }

    @Override
    public void delete() {
    }
}
