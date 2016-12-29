package Commands;

import GUI.SelectionManager;

import javax.swing.*;

/**
 * Created by Ty on 11/3/2016 at 7:35 PM.
 */
public class SelectImageCommand extends Command {

    private JLabel selectedLogo;

    public SelectImageCommand(final JLabel logo){
        selectedLogo = logo;
    }

    @Override
    public void execute(int xVal, int yVal) {
        SelectionManager.getInstance().addSelectedLogo(selectedLogo);
    }

    @Override
    public void undo() {

    }
}
