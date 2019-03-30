import javax.swing.*;

public class TextInfoPanel extends JPanel {
    private JTextArea informationText;

    public TextInfoPanel(String title) {
        this.setBorder(UIElements.etchedTitleBorder(title));
        this.setLayout(null);

        informationText = new JTextArea();

        informationText.setText(
                " Queue 1:" +
                "\n    Average waiting time: " + 0 +
                "\n    Average service time: " + 0 +
                "\n    Empty queue time: " + 0 +

                "\n\n Queue2:" +
                "\n    Average waiting time: " + 0 +
                "\n    Average service time: " + 0 +
                "\n    Empty queue time: " + 0 +

                "\n\n Queue3:" +
                "\n    Average waiting time: " + 0 +
                "\n    Average service time: " + 0 +
                "\n    Empty queue time: " + 0
        );

        informationText.setBounds(10, 20, 280, 450);
        informationText.setEditable(false);

        this.add(informationText);

        this.setVisible(true);
    }

    public void setValues(int[] values) {
        informationText.setText(
                " Queue 1:" +
                        "\n    Average waiting time: " + values[0] +
                        "\n    Average service time: " + values[1] +
                        "\n    Empty queue time: " + values[2] +

                        "\n\n Queue2:" +
                        "\n    Average waiting time: " + values[3] +
                        "\n    Average service time: " + values[4] +
                        "\n    Empty queue time: " + values[5] +

                        "\n\n Queue3:" +
                        "\n    Average waiting time: " + values[6] +
                        "\n    Average service time: " + values[7] +
                        "\n    Empty queue time: " + values[8]
        );
    }
}
