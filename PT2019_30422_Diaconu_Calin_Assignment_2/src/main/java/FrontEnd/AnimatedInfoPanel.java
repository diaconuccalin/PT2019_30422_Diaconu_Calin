package FrontEnd;

import javax.swing.*;

class AnimatedInfoPanel extends JPanel {
    private JTextArea informationText;

    AnimatedInfoPanel() {
        this.setBorder(UIElements.etchedTitleBorder("Queue evolution"));
        this.setLayout(null);

        informationText = new JTextArea();
        informationText.setBounds(10, 20, 225, 450);
        informationText.setEditable(false);

        this.add(informationText);

        this.setVisible(true);
    }

    void setText(String text) {
        informationText.setText(text);
    }
}
