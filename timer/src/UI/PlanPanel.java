package UI;

import java.awt.*;
import javax.swing.*;

public class PlanPanel extends JPanel {
    public PlanPanel() {
        setLayout(new GridLayout(10, 1));
        setBackground(Color.WHITE);
    }

    // 패널의 콘텐츠 크기를 강제로 조정
    public void setContentSize(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setSize(width, height);
        revalidate(); // 레이아웃을 다시 계산
    }
}
