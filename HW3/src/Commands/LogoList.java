package Commands;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ty on 11/8/2016 at 10:50 PM.
 */
public class LogoList {

    private static LogoList uniqueInstance;
    private List<JLabel> logoList;

    private LogoList(){
        logoList = new ArrayList<>();
    }

    public static LogoList getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new LogoList();
        }
        return uniqueInstance;
    }

    public void add(JLabel logo){
        logoList.add(logo);
    }

    public JLabel removeLast(){
        return logoList.remove(logoList.size()-1);
    }
}
