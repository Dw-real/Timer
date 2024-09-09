package function;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import javax.sound.sampled.*;
import javax.swing.*;

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
    private volatile boolean paused = false; // 중지 상태를 나타내는 플래그
    private long lastUpdateTime = 0;
    private int time;
    private int intervals;
    private ArrayList<Integer> times;
    private int nextSoundTimeIndex = 0;
    private JButton controlButton;
    private File soundFile = null;

    public OperateTimer(JLabel hour, JLabel minute, JLabel second,
            JSpinner hourSpinner, JSpinner minuteSpinner, JSpinner secondSpinner, JButton controlButton) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.hourSpinner = hourSpinner;
        this.minuteSpinner = minuteSpinner;
        this.secondSpinner = secondSpinner;
        this.controlButton = controlButton;
        times = new ArrayList<>();
    }

    public void setTimes(ArrayList<Integer> times) {
        this.times = times;
        this.nextSoundTimeIndex = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn.getText().equals("시작")) {
            startTimer();
            // 스피너 비활성화
            deactivateSpinner();
            btn.setText("중지");
        } else if (btn.getText().equals("중지")) {
            pauseTimer();
            btn.setText("계속");
        } else if (btn.getText().equals("계속")) {
            resumeTimer();
            btn.setText("중지");
        } else if (btn.getText().equals("초기화")) {
            resetTimer();
            // 스피너 활성화
            activateSpinner();
            controlButton.setText("시작");
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
        intervals = 0;
        running = true;
        paused = false;
        resetRequested = false;

        executorService.submit(() -> {
            while (running && time > 0) {
                for (int i = 0; i < 10; i++) { // 1초를 100ms 단위로 나눠서 처리
                    if (resetRequested || paused) {
                        running = false; // 이 부분을 추가하여 바로 중지
                        return;
                    }

                    try {
                        Thread.sleep(100); // 100ms 대기
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                time--;
                intervals++;
                SwingUtilities.invokeLater(() -> {
                    updateUI();
                    checkAndPlaySound();
                });
            }

            if (time <= 0) {
                running = false;
                SwingUtilities.invokeLater(() -> {
                    activateSpinner();
                    controlButton.setText("시작");
                });
            }
        });
    }

    private void pauseTimer() {
        if (running) {
            paused = true;
            running = false;
        }
    }

    private void resumeTimer() {
        if (paused) {
            running = true;
            paused = false;

            executorService.submit(() -> {
                while (running && time > 0) {
                    if (resetRequested || paused)
                        break;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }

                    time--;
                    intervals++;
                    SwingUtilities.invokeLater(() -> {
                        updateUI();
                        checkAndPlaySound(); // 설정한 시간 간격으로 사운드 재생
                    });
                }

                if (time <= 0) {
                    running = false;
                    SwingUtilities.invokeLater(() -> {
                        activateSpinner();
                        controlButton.setText("시작");
                    });
                }
            });
        }
    }

    private void resetTimer() {
        running = false;
        resetRequested = true;
        paused = false;
        executorService.shutdown(); // 타이머 스레드 종료 요청
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

    private void checkAndPlaySound() {
        if (nextSoundTimeIndex < times.size() && intervals % times.get(nextSoundTimeIndex) == 0) {
            playSound();
            nextSoundTimeIndex = (nextSoundTimeIndex + 1) % times.size(); // times size만큼 반복
        }
    }

    private void playSound() {
        try {
            if (soundFile == null) {
                soundFile = new File("timer\\src\\resources\\Pling-Sound.wav");
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public void setSoundFile(String filePath) {
        soundFile = new File(filePath);
    }
}
