package UI;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class timerFrame extends JFrame {
    public JButton startBtn; // 시작 버튼
    public JButton resetBtn; // 초기화 버튼
    public JLabel hourLabel; // 시간 
    public JLabel minuteLabel; // 분 
    public JLabel secondLabel; // 초
    public Container c = getContentPane();

    public timerFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLayout(null);
        attachBtn();
        attachLabel();
        setSize(600, 300);
        setVisible(true);   
    }

    public void attachBtn() {
        startBtn = new JButton("시작");
        resetBtn = new JButton("초기화");
       
        // 버튼 크기 조정
        startBtn.setSize(100, 20);
        resetBtn.setSize(100, 20);
        // 버튼 위치 조정
        startBtn.setLocation(200, 200);
        resetBtn.setLocation(300, 200);
        // 버튼 부착
        c.add(startBtn);
        c.add(resetBtn);
    }

    public void attachLabel() {
        hourLabel = new JLabel("시간");
        minuteLabel = new JLabel("분");
        secondLabel = new JLabel("초");

        // label 크기 조정
        hourLabel.setSize(50, 20);
        minuteLabel.setSize(50, 20);
        secondLabel.setSize(50, 20);
        // label 위치 조정
        hourLabel.setLocation(100,20);
        minuteLabel.setLocation(300, 20);
        secondLabel.setLocation(500, 20);
        
        // Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // 검정색, 두께 2
        // hourLabel.setBorder(border);
        // hourLabel.setHorizontalAlignment(JLabel.CENTER);
        // hourLabel.setVerticalAlignment(JLabel.CENTER);
        // minuteLabel.setHorizontalAlignment(JLabel.CENTER);
        // minuteLabel.setVerticalAlignment(JLabel.CENTER);
        // secondLabel.setHorizontalAlignment(JLabel.CENTER);
        // secondLabel.setVerticalAlignment(JLabel.CENTER);
        // label 부착
        c.add(hourLabel);
        c.add(minuteLabel);
        c.add(secondLabel);
    }
}
