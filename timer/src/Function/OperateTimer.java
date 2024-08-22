package Function;

import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.*;

public class OperateTimer implements ActionListener {
    private JLabel hour;
    private JLabel minute;
    private JLabel second;
    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;
    private JSpinner secondSpinner;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private volatile boolean running;
    private volatile boolean resetRequested;
    private long lastUpdateTime = 0;
    public int time;

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
        JButton btn = (JButton) e.getSource();
        if (btn.getText().equals("시작")) {
            startTimer();
            // 스피너 비활성화
            deactivateSpinner();
        } else if (btn.getText().equals("초기화")) {
            resetTimer();
            // 스피너 활성화
            activateSpinner();
        }
    }

    private void startTimer() {
        if (running) {
            resetTimer();
        }

        int hours = (int) hourSpinner.getValue();
        int minutes = (int) minuteSpinner.getValue();
        int seconds = (int) secondSpinner.getValue();
        time = hours * 3600 + minutes * 60 + seconds;
        running = true;
        resetRequested = false;

        executorService.submit(() -> {
            while (running && time > 0) {
                if (resetRequested)
                    break;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                time--;
                SwingUtilities.invokeLater(() -> updateUI());
            }

            if (time <= 0) {
                running = false;
                SwingUtilities.invokeLater(this::activateSpinner);
            }
        });
    }

    private void resetTimer() {
        running = false;
        resetRequested = true;
        executorService.shutdownNow(); // 타이머 스레드 종료 요청
        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            executorService = Executors.newSingleThreadExecutor(); // 새 스레드 풀 생성
        }

        SwingUtilities.invokeLater(() -> {
            hour.setText("00");
            minute.setText("00");
            second.setText("00");
        });
    }

    private void updateUI() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdateTime >= 500) { // 500ms마다 업데이트
            lastUpdateTime = currentTime;
            int hoursLeft = time / 3600;
            int minutesLeft = (time % 3600) / 60;
            int secondsLeft = time % 60;

            hour.setText(String.format("%02d", hoursLeft));
            minute.setText(String.format("%02d", minutesLeft));
            second.setText(String.format("%02d", secondsLeft));
        }
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
        hourSpinner.setValue(0);
        minuteSpinner.setValue(0);
        secondSpinner.setValue(0);
    }
}
