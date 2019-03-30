import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class UIElements {
    public static TitledBorder etchedTitleBorder(String title) {
        Border loweredEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder result = BorderFactory.createTitledBorder(loweredEtched, title);

        return result;
    }

    public static JSlider selectionSlider(int min, int max) {
        JSlider jSlider = new JSlider(min, max);

        jSlider.setSnapToTicks(true);
        jSlider.setMinorTickSpacing(1);
        jSlider.setMajorTickSpacing((max - min) / 5);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);

        return jSlider;
    }
}
