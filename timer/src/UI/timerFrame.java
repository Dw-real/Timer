package UI;

import java.awt.*;
import javax.swing.*;
import Function.SettingTime;
import Function.OperateTimer;

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
        for (int i=1; i<=2; i++) {
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
    public JButton startBtn; // 시작 버튼
    public JButton resetBtn; // 초기화 버튼
    private TimePanel timePanel;

    public ButtonPanel(TimePanel timePanel) {
        this.timePanel = timePanel;
        attachBtn();
        addBtnListener();
    }

    private void attachBtn() {
        startBtn = new JButton("시작");
        resetBtn = new JButton("초기화");

        // 버튼 크기 조정
        startBtn.setSize(100, 20);
        resetBtn.setSize(100, 20);

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

        startBtn.addActionListener(new OperateTimer(hour, minute, second, hourSpinner, minuteSpinner, secondSpinner));
        resetBtn.addActionListener(new OperateTimer(hour, minute, second, hourSpinner, minuteSpinner, secondSpinner));
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
        setSize(400, 300);
        setVisible(true);
    }
}
