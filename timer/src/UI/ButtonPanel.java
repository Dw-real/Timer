package UI;

import java.awt.*;
import javax.swing.*;
import Graphic.RoundButton;
import Function.ManageWorkToDo;
import Function.OperateTimer;
import java.util.*;

public class ButtonPanel extends JPanel {
    public RoundButton startBtn; // 시작 버튼
    public RoundButton resetBtn; // 초기화 버튼
    private TimePanel timePanel;
    private ManageWorkToDo manageWorkToDo;

    public ButtonPanel(TimePanel timePanel, PlanPanel planPanel) {
        this.timePanel = timePanel;
        this.manageWorkToDo = new ManageWorkToDo(planPanel);
        attachBtn();
        addBtnListener();
    }

    private void attachBtn() {
        startBtn = new RoundButton("시작");
        resetBtn = new RoundButton("초기화");
        // 버튼 색 지정
        startBtn.setBackground(Color.CYAN);
        startBtn.setForeground(Color.BLACK);
        resetBtn.setBackground(Color.CYAN);
        resetBtn.setForeground(Color.BLACK);
        // 버튼 부착
        this.add(startBtn);
        this.add(resetBtn);
    }

    private void addBtnListener() {
        JLabel hour = timePanel.hour;
        JLabel minute = timePanel.minute;
        JLabel second = timePanel.second;
        JSpinner hourSpinner = timePanel.hourSpinner;
        JSpinner minuteSpinner = timePanel.minuteSpinner;
        JSpinner secondSpinner = timePanel.secondSpinner;
        OperateTimer timer = new OperateTimer(hour, minute, second, hourSpinner, minuteSpinner, secondSpinner);

        startBtn.addActionListener(e -> {
            ArrayList<Integer> times = manageWorkToDo.getTime();
            timer.setTimes(times);
            timer.actionPerformed(e); // 타이머 시작
        });

        resetBtn.addActionListener(timer);
    }

    public ManageWorkToDo gManageWorkToDo() {
        return manageWorkToDo;
    }
}
