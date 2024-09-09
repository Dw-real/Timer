package function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import ui.ButtonPanel;

public class SelectSound implements ActionListener {
    private JFileChooser chooser;
    private OperateTimer ot;

    public SelectSound(ButtonPanel buttonPanel) {
        chooser = new JFileChooser();
        ot = buttonPanel.gOperateTimer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV files", "wav");
        chooser.setFileFilter(filter);

        int ret = chooser.showOpenDialog(null);

        if (ret != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String filePath = chooser.getSelectedFile().getPath();
        
        if (filePath.toLowerCase().endsWith(".wav")) {
            ot.setSoundFile(filePath);
        }
    }
}
