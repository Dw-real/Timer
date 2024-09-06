package UI;

import java.awt.*;
import javax.swing.*;
import Function.SettingTime;
import Function.TimeChangeListener;
import Graphic.FontManager;

public class TimePanel extends JPanel {
    public JSpinner hourSpinner;
    public JSpinner minuteSpinner;
    public JSpinner secondSpinner;
    public JLabel hour;
    public JLabel minute;
    public JLabel second;
    private JLabel workToDoLabel;
    private JLabel hourLabel;
    private JLabel minuteLabel;
    private JLabel secondLabel;
    private Font customFont = FontManager.getCustomFont(15f);

    public TimePanel() {
        setLayout(null);
        attachSpinner();
        attachTimeLabel();
        attachColonLabel();
        attachLabel();
        alignText();
        addChangeListeners();
        setBackground(Color.WHITE);
    }

    private void attachSpinner() {
        hourSpinner = new JSpinner(new SpinnerNumberModel(0, -1, 24, 1));
        minuteSpinner = new JSpinner(new SpinnerNumberModel(0, -1, 60, 1));
        secondSpinner = new JSpinner(new SpinnerNumberModel(0, -1, 60, 1));
        // 스피너 크기 조정
        hourSpinner.setSize(35, 35);
        minuteSpinner.setSize(35, 35);
        secondSpinner.setSize(35, 35);
        // 스피너 위치 조정
        hourSpinner.setLocation(33, 20);
        minuteSpinner.setLocation(175, 20);
        secondSpinner.setLocation(320, 20);

        // 0~23 자유롭게 이동
        TimeChangeListener htc = new TimeChangeListener(hourSpinner);
        hourSpinner.addChangeListener(htc);
        TimeChangeListener mtc = new TimeChangeListener(minuteSpinner);
        minuteSpinner.addChangeListener(mtc);
        TimeChangeListener stc = new TimeChangeListener(secondSpinner);
        secondSpinner.addChangeListener(stc);

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

    private void attachLabel() {
        workToDoLabel = new JLabel("할 일");
        hourLabel = new JLabel("시간");
        minuteLabel = new JLabel("분");
        secondLabel = new JLabel("초");

        // label 크기 조정
        workToDoLabel.setSize(50, 20);
        hourLabel.setSize(50, 20);
        minuteLabel.setSize(50, 20);
        secondLabel.setSize(50, 20);

        // label 위치 조정
        workToDoLabel.setLocation(65, 170);
        hourLabel.setLocation(190, 170);
        minuteLabel.setLocation(265, 170);
        secondLabel.setLocation(340, 170);

        workToDoLabel.setFont(customFont);
        hourLabel.setFont(customFont);
        minuteLabel.setFont(customFont);
        secondLabel.setFont(customFont);

        this.add(workToDoLabel);
        this.add(hourLabel);
        this.add(minuteLabel);
        this.add(secondLabel);
    }

    private void alignText() {
        workToDoLabel.setHorizontalAlignment(JLabel.CENTER);
        workToDoLabel.setVerticalAlignment(JLabel.CENTER);
        hourLabel.setHorizontalAlignment(JLabel.CENTER);
        hourLabel.setVerticalAlignment(JLabel.CENTER);
        minuteLabel.setHorizontalAlignment(JLabel.CENTER);
        minuteLabel.setVerticalAlignment(JLabel.CENTER);
        secondLabel.setHorizontalAlignment(JLabel.CENTER);
        secondLabel.setVerticalAlignment(JLabel.CENTER);
    }

    private void addChangeListeners() {
        // 각 스피너에 이벤트 추가
        hourSpinner.addChangeListener(new SettingTime(hourSpinner, hour));
        minuteSpinner.addChangeListener(new SettingTime(minuteSpinner, minute));
        secondSpinner.addChangeListener(new SettingTime(secondSpinner, second));
    }
}
