package Function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AddWorkToDo implements ActionListener {
    private JPanel panel;
    private int count;

    public AddWorkToDo(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField textField = new JTextField();
        // 현재 추가된 컴포넌트의 수를 이용해 새로운 텍스트 필드의 위치 설정
        int yPosition = 150 + count * 30;
        count++;
        textField.setSize(200, 30);
        textField.setLocation(50, yPosition);
        panel.add(textField);
        panel.revalidate();
        panel.repaint();
    }
}
