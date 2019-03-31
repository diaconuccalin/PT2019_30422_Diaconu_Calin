package FrontEnd;

import javax.swing.*;

class TimeSlidersPanel extends JPanel {
    private JSlider minSlider;
    private JSlider maxSlider;

    TimeSlidersPanel(String title, String firstLabel, String secondLabel, int min1, int max1, int min2, int max2) {
        this.setBorder(UIElements.etchedTitleBorder(title));
        this.setLayout(null);

        //labels
        JLabel minLabel = new JLabel(firstLabel);
        JLabel maxLabel = new JLabel(secondLabel);

        minLabel.setBounds(5, 15, 80, 20);
        maxLabel.setBounds(5, 65, 80, 20);

        this.add(minLabel);
        this.add(maxLabel);

        //sliders
        minSlider = UIElements.selectionSlider(min1, max1);
        minSlider.setBounds(60, 15, 210, 50);
        this.add(minSlider);

        maxSlider = UIElements.selectionSlider(min2, max2);
        maxSlider.setBounds(60, 65, 210, 50);
        this.add(maxSlider);

        this.setVisible(true);
    }

    int[] getValues() {
        int[] result = new int[2];

        result[0] = minSlider.getValue();
        result[1] = maxSlider.getValue();

        return result;
    }
}
