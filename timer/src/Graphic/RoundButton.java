package Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class RoundButton extends JButton {

    public RoundButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    // 버튼의 모양을 동그랗게 그리도록 재정의
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 버튼 색상 설정
        g.setColor(getBackground());
        g2.fill(new Ellipse2D.Float(0, 0, getWidth() - 1, getHeight() - 1));
        super.paintComponent(g2);
    }

    // 버튼의 경계선을 동그랗게 그리도록 재정의
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.draw(new Ellipse2D.Float(0, 0, getWidth() - 1, getHeight() - 1));
    }

    // 버튼이 원형으로 보이도록 메서드 재정의
    @Override
    public Dimension getPreferredSize() {
        int diameter = 75;
        return new Dimension(diameter, diameter);
    }

    // 마우스 클릭 판정 설정
    @Override
    public boolean contains(int x, int y) {
        int radius = getWidth() / 2;
        return Math.pow(x - radius, 2) + Math.pow(y - radius, 2) <= Math.pow(radius, 2);
    }
}
