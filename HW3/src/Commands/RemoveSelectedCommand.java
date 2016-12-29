package Commands;

import GUI.SelectionManager;

import javax.swing.*;

/**
 * Created by Ty on 11/8/2016 at 8:32 PM.
 */
public class RemoveSelectedCommand extends Command {

    JPanel drawingPanel;

    public RemoveSelectedCommand(JPanel newPanel){
        drawingPanel = newPanel;
    }

    @Override
    public void execute(int xVal, int yVal) {
        JLabel selectedLogo = SelectionManager.getInstance().getSelectedLogo();
        if(selectedLogo == null) return;
        drawingPanel.remove(selectedLogo);
        drawingPanel.repaint();
    }

    @Override
    public void undo() {

    }
}
