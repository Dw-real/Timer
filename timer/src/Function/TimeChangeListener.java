package function;

import javax.swing.*;
import javax.swing.event.*;

public class TimeChangeListener implements ChangeListener {
    private JSpinner spinner;

    public TimeChangeListener(JSpinner spinner) {
        this.spinner = spinner;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        SpinnerNumberModel model = (SpinnerNumberModel) spinner.getModel();
        int value = (Integer) model.getValue();
        int min = (Integer) model.getMinimum();
        int max = (Integer) model.getMaximum();

        // 0~23을 자유롭게 설정
        if (value == min) {
            model.setValue(max - 1);
        }
        if (value == max) {
            model.setValue(min + 1);
        }
    }
}
