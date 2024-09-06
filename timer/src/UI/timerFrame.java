package UI;

import java.awt.*;
import javax.swing.*;
import Function.ManageWorkToDo;

public class timerFrame extends JFrame {
    private ManageWorkToDo wtd;
    private ButtonPanel buttonPanel;
    private Color color = new Color(103, 153, 255);

    public timerFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        // panel 생성
        TimePanel timePanel = new TimePanel();
        PlanPanel planPanel = new PlanPanel();
        buttonPanel = new ButtonPanel(timePanel, planPanel);

        timePanel.setPreferredSize(new Dimension(400, 200));
        // timePanel과 planPanel을 담을 containerPanel
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(timePanel, BorderLayout.NORTH);
        containerPanel.add(planPanel, BorderLayout.CENTER);
        // panel 추가
        c.add(new LabelPanel(), BorderLayout.NORTH);
        c.add(containerPanel, BorderLayout.CENTER);
        c.add(buttonPanel, BorderLayout.SOUTH);

        addMenu(planPanel);
        setSize(400, 800);
        setResizable(false);
        setVisible(true);
    }

    private void addMenu(JPanel panel) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("메뉴");
        JMenuItem addPlan = new JMenuItem("추가");
        JMenuItem removePlan = new JMenuItem("삭제");

        // 색상 설정
        menuBar.setBackground(color);
        menu.setBackground(color);
        menu.setForeground(Color.WHITE);

        addPlan.setBackground(color);
        addPlan.setForeground(Color.WHITE);

        removePlan.setBackground(color);
        removePlan.setForeground(Color.WHITE);

        menu.add(addPlan);
        menu.add(removePlan);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        // 메뉴 아이템에 액션리스너 추가
        wtd = buttonPanel.gManageWorkToDo();
        addPlan.addActionListener(wtd);
        removePlan.addActionListener(wtd);

        setSize(400, 400);
        setVisible(true);
    }
}
