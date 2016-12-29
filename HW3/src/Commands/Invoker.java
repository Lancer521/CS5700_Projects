package Commands;

/**
 * Created by Ty on 11/4/2016 at 5:30 PM.
 */
public class Invoker {

    Command command;
    int xVal, yVal;

    public Invoker(Command newCommand, int x, int y){
        command = newCommand;
        xVal = x;
        yVal = y;
    }

    public void invoke(){
        command.execute(xVal, yVal);
    }
}
