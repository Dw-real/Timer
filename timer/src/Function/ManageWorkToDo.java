package Function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;

public class ManageWorkToDo implements ActionListener {
    private JPanel panel;
    private ArrayList<JTextField> textFieldList;
    
    public ManageWorkToDo(JPanel panel) {
        this.panel = panel;
        this.textFieldList = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("추가")) {
            addWorkToDo();
        }
        else if (command.equals("삭제")) {
            removeWorkToDo();
        }
    }

    private void addWorkToDo() {
        JTextField textField = new JTextField();
        textFieldList.add(textField);
        // textField 수에 따라 위치 조정
        int yPosition = textFieldList.size() * 30;
        textField.setSize(200, 30);
        textField.setLocation(0, yPosition);

        panel.add(textField);
        panel.revalidate();
        panel.repaint();
    }

    private void removeWorkToDo() {
        if (textFieldList.size() > 0) {
            JTextField lastField = textFieldList.remove(textFieldList.size() - 1);
            panel.remove(lastField);
            panel.revalidate();
            panel.repaint();
        }
    }
}
