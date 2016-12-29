package GUI;

import javax.swing.*;

/**
 * Created by Ty on 11/8/2016 at 6:59 PM.
 */
public class SelectionManager {

    private static SelectionManager uniqueInstance;
    private JLabel selectedLogo;
    private boolean selectionMode;

    private SelectionManager(){}

    public static SelectionManager getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new SelectionManager();
        }
        return uniqueInstance;
    }

    public void addSelectedLogo(JLabel logo){
        selectedLogo = logo;
    }

    public boolean isSelectionMode(){
        return selectionMode;
    }

    public void setSelectionMode(boolean mode){
        selectionMode = mode;
        if(!selectionMode){
            deselect();
        }
    }

    public void deselect(){
        selectedLogo = new JLabel();
    }

    public JLabel getSelectedLogo(){
        return selectedLogo;
    }
}
