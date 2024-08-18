package Function;

import javax.swing.*;
import java.awt.event.*;

public class OperateTimer implements ActionListener {
    private JLabel hour;
    private JLabel minute;
    private JLabel second;
    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;
    private JSpinner secondSpinner;
    private Thread timerThread;
    private volatile boolean running;
    private volatile boolean resetRequested;
    private int time;

    public OperateTimer(JLabel hour, JLabel minute, JLabel second,
    JSpinner hourSpinner, JSpinner minuteSpinner, JSpinner secondSpinner) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.hourSpinner = hourSpinner;
        this.minuteSpinner = minuteSpinner;
        this.secondSpinner = secondSpinner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        if (btn.getText().equals("시작")) {
            startTimer();
            // 스피너 비활성화
            deactivateSpinner();
        }
        else if (btn.getText().equals("초기화")) {
            resetTimer();
            // 스피너 활성화
            activateSpinner();
        }
    }

    private void startTimer() {
        // 설정한 시간에 맞춰 초 단위로 변환
        int hours = (int)hourSpinner.getValue();
        int minutes = (int)minuteSpinner.getValue();
        int seconds = (int)secondSpinner.getValue();
        
        time = hours * 3600 + minutes * 60 + seconds;
        running = true;
        resetRequested = false;

        // 기존의 타이머 스레드가 실행 중이면 종료
        if (timerThread != null && timerThread.isAlive()) {
            running = false;
            try {
                timerThread.join();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

         // 새로운 스레드 생성 및 시작
         timerThread = new Thread(() -> {
            while (running && time > 0) {
                if (resetRequested)
                    break; // 스레드 종료

                try {
                    Thread.sleep(1000); // 1초 대기
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                    
                time--;

                // UI 업데이트
                SwingUtilities.invokeLater(() -> {
                    int hoursRemaining = time / 3600;
                    int minutesRemaining = (time % 3600) / 60;
                    int secondsRemaining = time % 60;

                    hour.setText(String.format("%02d", hoursRemaining));
                    minute.setText(String.format("%02d", minutesRemaining));
                    second.setText(String.format("%02d", secondsRemaining));
                    
                    if (time <= 0) {
                        running = false;
                        activateSpinner();
                    }
                });
            }
        });

        timerThread.start();
    }

    private void deactivateSpinner() {
        hourSpinner.setEnabled(false);
        minuteSpinner.setEnabled(false);
        secondSpinner.setEnabled(false);
    }

    private void activateSpinner() {
        hourSpinner.setEnabled(true);
        minuteSpinner.setEnabled(true);
        secondSpinner.setEnabled(true);
    }

    private void resetTimer() {
        // 초기화 요청을 설정하고 스레드 종료 대기
        running = false;
        resetRequested = true;

        if (timerThread != null && timerThread.isAlive()) {
            try {
                timerThread.join(); // 스레드 종료까지 대기
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        SwingUtilities.invokeLater(() -> {
            hourSpinner.setValue(0);
            minuteSpinner.setValue(0);
            secondSpinner.setValue(0);
            hour.setText("00");
            minute.setText("00");
            second.setText("00");
        });
    }
}
