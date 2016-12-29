package FlyWeight;

import java.awt.*;

/**
 * Created by Ty on 11/7/2016 at 4:07 PM.
 */
public class LogoExtrinsicState {

    public LogoExtrinsicState(int x, int y){
        location = new Point(x-50,y-50);
        dimension = new Dimension(100, 100);
    }

    public Point location;

    public Dimension dimension;
}
