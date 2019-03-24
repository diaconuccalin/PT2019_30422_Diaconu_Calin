import javax.swing.*;

public class TimeControlPanel extends JPanel {
    private JButton startButton;

    public TimeControlPanel() {
        this.setBorder(UIElements.etchedTitleBorder("Time Control"));
        this.setLayout(null);

        startButton = new JButton("START");

        startButton.setBounds(10, 15, 100, 25);

        add(startButton);

        this.setVisible(true);
    }

    public JButton getStartButton() {
        return startButton;
    }
}
