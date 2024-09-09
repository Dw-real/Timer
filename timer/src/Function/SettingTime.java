package function;

import javax.swing.*;
import javax.swing.event.*;

public class SettingTime implements ChangeListener {
    private JSpinner timeSpinner;
    private JLabel timeLabel;

    public SettingTime(JSpinner timeSpinner, JLabel timeLabel) {
        this.timeSpinner = timeSpinner;
        this.timeLabel = timeLabel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int time = (int)timeSpinner.getValue();
        timeLabel.setText(String.format("%02d", time));
    }
    
}
