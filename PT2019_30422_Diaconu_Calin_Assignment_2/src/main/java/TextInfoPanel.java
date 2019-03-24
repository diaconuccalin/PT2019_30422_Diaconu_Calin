import javax.swing.*;

public class TextInfoPanel extends JPanel {
    public TextInfoPanel(String title) {
        this.setBorder(UIElements.etchedTitleBorder(title));
        this.setLayout(null);

        int q1AWT = 0;
        int q1ST = 0;
        int q1EQT = 0;

        int q2AWT = 0;
        int q2ST = 0;
        int q2EQT = 0;

        int q3AWT = 0;
        int q3ST = 0;
        int q3EQT = 0;

        JTextArea informationText = new JTextArea();

        informationText.setText(
                " Queue 1:" +
                "\n    Average waiting time: " + q1AWT +
                "\n    Service time: " + q1ST +
                "\n    Empty queue time: " + q1EQT +

                "\n Queue2:" +
                "\n    Average waiting time: " + q2AWT +
                "\n    Service time: " + q2ST +
                "\n    Empty queue time: " + q2EQT +

                "\n Queue3:" +
                "\n    Average waiting time: " + q3AWT +
                "\n    Service time: " + q3ST +
                "\n    Empty queue time: " + q3EQT
        );

        informationText.setBounds(10, 20, 280, 450);
        informationText.setEditable(false);

        this.add(informationText);

        this.setVisible(true);
    }
}
