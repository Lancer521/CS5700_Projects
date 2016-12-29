package GUI;

import Commands.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import Utils.ActionConstants;

/**
 * Created by Ty on 11/1/2016 at 11:59 PM.
 */
public class MainForm implements ActionListener, MouseListener {
    private JFrame frame;
    private JButton byuLogoButton;
    private JButton usuLogoButton;
    private JButton suuLogoButton;
    private JButton weberLogoButton;
    private JButton uvuLogoButton;
    private JButton utahLogoButton;
    private JPanel contentPane;
    private JPanel drawingPanel;
    private JPanel buttonPanel;
    private JButton saveButton;
    private JButton loadButton;
    private JButton undoButton;
    private JButton selectButton;
    private JButton deleteButton;

    private DrawingPad drawingPad;
    private List<Command> commandList;

    private int whichButton = -1;

    public MainForm() {
        frame = new JFrame();
        frame.setContentPane(contentPane);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Stick Man Sketch Book");

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newMenuItem = new JMenu("New");

        //newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_CONTROL, InputEvent.CTRL_MASK));
        menu.add(newMenuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        frame.addMouseListener(this);

        drawingPad = new DrawingPad(drawingPanel);
        commandList = new ArrayList<>();

        setActionListeners();


        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MainForm form = new MainForm();
    }

    private void setActionListeners() {
        byuLogoButton.addActionListener(this);
        usuLogoButton.addActionListener(this);
        suuLogoButton.addActionListener(this);
        weberLogoButton.addActionListener(this);
        uvuLogoButton.addActionListener(this);
        utahLogoButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        undoButton.addActionListener(this);
        deleteButton.addActionListener(this);
        selectButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object eventObject = e.getSource();

        if (eventObject == saveButton) {
            whichButton = ActionConstants.SAVE;
        } else if (eventObject == loadButton) {
            whichButton = ActionConstants.LOAD;
        } else if (eventObject == deleteButton) {
            Command command = new RemoveSelectedCommand(drawingPanel);
            Invoker invoker = new Invoker(command, 0, 0);
            invoker.invoke();
            commandList.add(command);
        } else if (eventObject == undoButton) {
            whichButton = ActionConstants.UNDO;
        } else if (eventObject == selectButton) {
            whichButton = ActionConstants.SELECT;
            SelectionManager.getInstance().setSelectionMode(true);
        } else if (eventObject == usuLogoButton) {
            whichButton = ActionConstants.USU;
        } else if (eventObject == utahLogoButton) {
            whichButton = ActionConstants.UTAH;
        } else if (eventObject == weberLogoButton) {
            whichButton = ActionConstants.WEBER;
        } else if (eventObject == uvuLogoButton) {
            whichButton = ActionConstants.UVU;
        } else if (eventObject == suuLogoButton) {
            whichButton = ActionConstants.SUU;
        } else if (eventObject == byuLogoButton) {
            whichButton = ActionConstants.BYU;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        //Don't place labels if click occurs on buttonpanel
        if (e.getX() < buttonPanel.getWidth() + 20) return;

        Command command = null;
        switch (whichButton) {
            case ActionConstants.SAVE:
                break;
            case ActionConstants.LOAD:
                break;
            case ActionConstants.UNDO:
//                command = new UndoCommand(commandList);
                break;
            case ActionConstants.SELECT:
                command = new SelectImageCommand(SelectionManager.getInstance().getSelectedLogo());
                break;
            case ActionConstants.USU:
            case ActionConstants.UTAH:
            case ActionConstants.WEBER:
            case ActionConstants.UVU:
            case ActionConstants.SUU:
            case ActionConstants.BYU:
                command = new AddToCanvasCommand(drawingPad, whichButton);
                break;
        }
        if(command == null) return;
        Invoker invoker = new Invoker(command, e.getX() - buttonPanel.getWidth() - 20, e.getY() - 50);
        invoker.invoke();
//        commandList.add(command);
        drawingPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //NO-OP
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //NO-OP
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //NO-OP
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //NO-OP
    }
}
