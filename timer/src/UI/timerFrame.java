package UI;

import java.awt.*;
import javax.swing.*;
import Function.SettingTime;
import Function.AddWorkToDo;
import Function.OperateTimer;
import Graphic.RoundButton;

class LabelPanel extends JPanel {
    public JLabel hourLabel; // 시간
    public JLabel minuteLabel; // 분
    public JLabel secondLabel; // 초

    public LabelPanel() {
        setLayout(new GridLayout(1, 3, 50, 10));
        attachLabel();
        alignText();
    }

    private void attachLabel() {
        hourLabel = new JLabel("시간");
        minuteLabel = new JLabel("분");
        secondLabel = new JLabel("초");

        // label 크기 조정
        hourLabel.setSize(50, 20);
        minuteLabel.setSize(50, 20);
        secondLabel.setSize(50, 20);

        // label 부착
        this.add(hourLabel);
        this.add(minuteLabel);
        this.add(secondLabel);
    }

    private void alignText() {
        hourLabel.setHorizontalAlignment(JLabel.CENTER);
        hourLabel.setVerticalAlignment(JLabel.CENTER);
        minuteLabel.setHorizontalAlignment(JLabel.CENTER);
        minuteLabel.setVerticalAlignment(JLabel.CENTER);
        secondLabel.setHorizontalAlignment(JLabel.CENTER);
        secondLabel.setVerticalAlignment(JLabel.CENTER);
    }
}

class TimePanel extends JPanel {
    public JSpinner hourSpinner;
    public JSpinner minuteSpinner;
    public JSpinner secondSpinner;
    public JLabel hour;
    public JLabel minute;
    public JLabel second;

    public TimePanel() {
        setLayout(null);
        attachSpinner();
        attachTimeLabel();
        attachColonLabel();
        addChangeListeners();
    }

    private void attachSpinner() {
        hourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        secondSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        // 스피너 크기 조정
        hourSpinner.setSize(35, 35);
        minuteSpinner.setSize(35, 35);
        secondSpinner.setSize(35, 35);
        // 스피너 위치 조정
        hourSpinner.setLocation(33, 20);
        minuteSpinner.setLocation(175, 20);
        secondSpinner.setLocation(320, 20);
        // 스피너 부착
        this.add(hourSpinner);
        this.add(minuteSpinner);
        this.add(secondSpinner);
    }

    private void attachTimeLabel() {
        hour = new JLabel("00");
        minute = new JLabel("00");
        second = new JLabel("00");

        hour.setFont(new Font("Arial", Font.PLAIN, 30));
        minute.setFont(new Font("Arial", Font.PLAIN, 30));
        second.setFont(new Font("Arial", Font.PLAIN, 30));

        hour.setLocation(33, 100);
        minute.setLocation(175, 100);
        second.setLocation(320, 100);

        hour.setSize(50, 50);
        minute.setSize(50, 50);
        second.setSize(50, 50);

        this.add(hour);
        this.add(minute);
        this.add(second);
    }

    private void attachColonLabel() {
        for (int i = 1; i <= 2; i++) {
            JLabel colonLabel = new JLabel(":");
            colonLabel.setSize(50, 50);
            colonLabel.setFont(new Font("Arial", Font.PLAIN, 30));
            colonLabel.setLocation(i * 130, 100);
            this.add(colonLabel);
        }
    }

    private void addChangeListeners() {
        // 각 스피너에 이벤트 추가
        hourSpinner.addChangeListener(new SettingTime(hourSpinner, hour));
        minuteSpinner.addChangeListener(new SettingTime(minuteSpinner, minute));
        secondSpinner.addChangeListener(new SettingTime(secondSpinner, second));
    }
}

class ButtonPanel extends JPanel {
    public RoundButton startBtn; // 시작 버튼
    public RoundButton resetBtn; // 초기화 버튼
    private TimePanel timePanel;

    public ButtonPanel(TimePanel timePanel) {
        this.timePanel = timePanel;
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

        startBtn.addActionListener(timer);
        resetBtn.addActionListener(timer);
    }
}

public class timerFrame extends JFrame {
    public timerFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        TimePanel timePanel = new TimePanel();
        ButtonPanel buttonPanel = new ButtonPanel(timePanel);
        c.add(new LabelPanel(), BorderLayout.NORTH);
        c.add(timePanel, BorderLayout.CENTER);
        c.add(buttonPanel, BorderLayout.SOUTH);
        addMenu(timePanel);
        setSize(400, 400);
        setVisible(true);
    }

    private void addMenu(JPanel panel) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("메뉴");
        JMenuItem menuItem = new JMenuItem("추가");

        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        // 메뉴 아이템에 액션리스너 추가
        AddWorkToDo wtd = new AddWorkToDo(panel);
        menuItem.addActionListener(wtd);

        setSize(400, 400);
        setVisible(true);
    }
}
