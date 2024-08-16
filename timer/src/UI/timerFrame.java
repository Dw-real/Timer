package UI;

import java.awt.*;
import javax.swing.*;

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
    public JSpinner hourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
    public JSpinner minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
    public JSpinner secondSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));

    public TimePanel() {
        setLayout(null);
        attachSpinner();
    }

    private void attachSpinner() {
        hourSpinner.setLocation(33, 20);
        hourSpinner.setSize(35, 35);
        minuteSpinner.setLocation(175, 20);
        minuteSpinner.setSize(35, 35);
        secondSpinner.setLocation(320, 20);
        secondSpinner.setSize(35, 35);
        this.add(hourSpinner);
        this.add(minuteSpinner);
        this.add(secondSpinner);
    }
}

class ButtonPanel extends JPanel {
    public JButton startBtn; // 시작 버튼
    public JButton resetBtn; // 초기화 버튼

    public ButtonPanel() {
        attachBtn();
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
}

public class timerFrame extends JFrame {
    public timerFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.add(new LabelPanel(), BorderLayout.NORTH);
        c.add(new TimePanel(), BorderLayout.CENTER);
        c.add(new ButtonPanel(), BorderLayout.SOUTH);
        setSize(400, 300);
        setVisible(true);
    }
}
