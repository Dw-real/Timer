package Function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ManageWorkToDo implements ActionListener {
    private JPanel panel;
    private ArrayList<JPanel> groupList;
    
    public ManageWorkToDo(JPanel panel) {
        this.panel = panel;
        this.groupList = new ArrayList<>();
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
        // 10개 까지만 추가 가능
        if (groupList.size() < 10) {
            JPanel groupPanel = new JPanel();
            groupPanel.setLayout(null);
            groupPanel.setPreferredSize(new Dimension(400, 30));
    
            // textField 추가
            JTextField textField = new JTextField();
            textField.setSize(100, 30);
            textField.setLocation(0, 0);
            groupPanel.add(textField);
    
            // 3개의 spinner 추가
            JSpinner hour = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
            JSpinner minute = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
            JSpinner second = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
    
            hour.setBounds(200, 0, 35, 30);
            minute.setBounds(275, 0, 35, 30);
            second.setBounds(350, 0, 35, 30);
            groupPanel.add(hour);
            groupPanel.add(minute);
            groupPanel.add(second);
    
            // 그룹 패널을 메인 패널에 추가
            groupList.add(groupPanel);
            panel.add(groupPanel);
            
            panel.revalidate();
            panel.repaint();
        } 
    }

    private void removeWorkToDo() {
        if (groupList.size() > 0) {
            JPanel lastGroup = groupList.remove(groupList.size() - 1);
            panel.remove(lastGroup);
            panel.revalidate();
            panel.repaint();
        }
    }

    public ArrayList<Integer> getTime() {
        ArrayList<Integer> timeList = new ArrayList<>();

        for (JPanel panel : groupList) {
            int hours = -1; // 기본값 설정
            int minutes = -1; // 기본값 설정
            int seconds = -1; // 기본값 설정
            
            for (Component c : panel.getComponents()) {
                if (c instanceof JSpinner) {
                    JSpinner spinner = (JSpinner) c;
                    int value = (int) spinner.getValue();

                    if (hours == -1) {
                        hours = value;
                    }
                    else if (minutes == -1) {
                        minutes = value;
                    }
                    else if (seconds == -1) {
                        seconds = value;
                    }
                }
            }
            int time = hours * 3600 + minutes * 60 + seconds;
            timeList.add(time);
        }
        return timeList;
    }
    
}
