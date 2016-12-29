package Commands;

/**
 * Created by Ty on 11/3/2016 at 6:24 PM.
 */
public abstract class Command {

    public abstract void execute(int xVal, int yVal);

    public abstract void undo();

}
