import javax.swing.*;

public class TimeControlPanel extends JPanel {
    private JButton startButton;
    private JButton stopButton;
    private JLabel timer;

    public TimeControlPanel() {
        this.setBorder(UIElements.etchedTitleBorder("Time Control"));
        this.setLayout(null);

        startButton = new JButton("START");
        startButton.setBounds(10, 15, 100, 25);
        add(startButton);

        stopButton = new JButton("STOP");
        stopButton.setBounds(120, 15, 100, 25);
        add(stopButton);

        timer = new JLabel("Timer: 0 s");
        timer.setBounds(1100, 15, 100, 25);
        add(timer);

        this.setVisible(true);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JLabel getTimer() {
        return timer;
    }
}
