package Commands;

/**
 * Created by Ty on 11/3/2016 at 6:33 PM.
 */
public class AddToCanvasCommand extends Command {

    private DrawableSurface surface;
    private int imageKey;

    public AddToCanvasCommand(DrawableSurface newSurface, int key) {
        surface = newSurface;
        imageKey = key;
    }

    @Override
    public void execute(int xVal, int yVal) {
        surface.add(xVal, yVal, imageKey);
    }

    @Override
    public void undo() {
        surface.delete();
    }
}
