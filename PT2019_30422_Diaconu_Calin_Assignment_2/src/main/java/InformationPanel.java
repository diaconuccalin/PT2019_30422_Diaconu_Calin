import javax.swing.*;

public class InformationPanel extends JPanel {
    public InformationPanel() {
        this.setBorder(UIElements.etchedTitleBorder("Information Panel"));
        this.setLayout(null);

        //entire
        TextInfoPanel entireTimeInfo = new TextInfoPanel("Entire Simulation Time");

        int entireX = 10;
        int entireY = 20;
        int entireWidth = 300;
        int entireHeight = 480;

        entireTimeInfo.setBounds(entireX, entireY, entireWidth, entireHeight);

        this.add(entireTimeInfo);


        //selected
        TextInfoPanel selectedTimeInfo = new TextInfoPanel("Selected Simulation Time");

        int selectedX = 320;
        int selectedY = 20;
        int selectedWidth = 300;
        int selectedHeight = 480;

        selectedTimeInfo.setBounds(selectedX, selectedY, selectedWidth, selectedHeight);

        this.add(selectedTimeInfo);

        //animated info
        AnimatedInfoPanel animatedInfoPanel = new AnimatedInfoPanel();

        int animatedX = 630;
        int animatedY = 20;
        int animatedWidth = 245;
        int animatedHeight = 480;

        animatedInfoPanel.setBounds(animatedX, animatedY, animatedWidth, animatedHeight);

        this.add(animatedInfoPanel);

        this.setVisible(true);
    }
}
