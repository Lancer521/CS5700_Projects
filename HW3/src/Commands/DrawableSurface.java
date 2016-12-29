package Commands;

/**
 * Created by Ty on 11/3/2016 at 7:08 PM.
 */
public abstract class DrawableSurface {

    abstract void add(int xVal, int yVal, int imageKey);

    abstract void select();

    abstract void delete();
}
