package FrontEnd;

import javax.swing.*;

class TimeControlPanel extends JPanel {
    private JButton startButton;
    private JButton stopButton;
    private JLabel timer;

    TimeControlPanel() {
        this.setBorder(UIElements.etchedTitleBorder("Time Control"));
        this.setLayout(null);

        startButton = new JButton("START");
        startButton.setBounds(10, 15, 100, 25);
        add(startButton);

        stopButton = new JButton("STOP");
        stopButton.setBounds(120, 15, 100, 25);
//        add(stopButton);

        timer = new JLabel("BackEnd.runnable.Timer: 0 s");
        timer.setBounds(1100, 15, 100, 25);
        add(timer);

        this.setVisible(true);
    }

    JButton getStartButton() {
        return startButton;
    }

    JButton getStopButton() {
        return stopButton;
    }

    JLabel getTimer() {
        return timer;
    }
}
