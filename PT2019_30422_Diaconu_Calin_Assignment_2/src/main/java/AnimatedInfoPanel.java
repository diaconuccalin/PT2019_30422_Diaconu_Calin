import javax.swing.*;

public class AnimatedInfoPanel extends JPanel {
    private JTextArea informationText;

    public AnimatedInfoPanel() {
        this.setBorder(UIElements.etchedTitleBorder("Queue evolution"));
        this.setLayout(null);

        informationText = new JTextArea();
        informationText.setBounds(10, 20, 225, 450);
        informationText.setEditable(false);

        this.add(informationText);

        this.setVisible(true);

        this.setVisible(true);
    }

    public void setText(String text) {
        informationText.setText(text);
    }
}
