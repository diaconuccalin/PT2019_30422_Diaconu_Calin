import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

class UIElements {
    static TitledBorder etchedTitleBorder(String title) {
        Border loweredEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        return BorderFactory.createTitledBorder(loweredEtched, title);
    }
}
