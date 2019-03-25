import javax.swing.*;

public class TimeControlPanel extends JPanel {
    private JButton startButton;
    private JButton stopButton;

    public TimeControlPanel() {
        this.setBorder(UIElements.etchedTitleBorder("Time Control"));
        this.setLayout(null);

        startButton = new JButton("START");
        startButton.setBounds(10, 15, 100, 25);
        add(startButton);

        stopButton = new JButton("STOP");
        stopButton.setBounds(120, 15, 100, 25);
        add(stopButton);

        this.setVisible(true);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }
}
