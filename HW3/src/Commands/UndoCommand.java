package Commands;

import java.util.List;

/**
 * Created by Ty on 11/8/2016 at 9:52 PM.
 */
public class UndoCommand extends Command {

    private List<Command> commandList;

    public UndoCommand(List<Command> list){
        commandList = list;
    }

    @Override
    public void execute(int xVal, int yVal) {
        commandList.remove(commandList.size()-1).undo();
    }

    @Override
    public void undo() {
        System.out.println("Cannot UNDO within UNDO COMMAND");
    }
}
