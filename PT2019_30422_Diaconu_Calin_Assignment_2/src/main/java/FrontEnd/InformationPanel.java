package FrontEnd;

import BackEnd.runnable.Queue;

import javax.swing.*;
import java.util.List;

public class InformationPanel extends JPanel {
    private TextInfoPanel entireTimeInfo;
    private TextInfoPanel selectedTimeInfo;
    private AnimatedInfoPanel animatedInfoPanel;

    InformationPanel() {
        this.setBorder(UIElements.etchedTitleBorder("Information Panel"));
        this.setLayout(null);

        //entire
        entireTimeInfo = new TextInfoPanel("Entire Simulation Time");

        int entireX = 10;
        int entireY = 20;
        int entireWidth = 300;
        int entireHeight = 480;

        entireTimeInfo.setBounds(entireX, entireY, entireWidth, entireHeight);

        this.add(entireTimeInfo);


        //selected
        selectedTimeInfo = new TextInfoPanel("Selected Simulation Time");

        int selectedX = 320;
        int selectedY = 20;
        int selectedWidth = 300;
        int selectedHeight = 480;

        selectedTimeInfo.setBounds(selectedX, selectedY, selectedWidth, selectedHeight);

        this.add(selectedTimeInfo);

        //animated info
        animatedInfoPanel = new AnimatedInfoPanel();

        int animatedX = 630;
        int animatedY = 20;
        int animatedWidth = 245;
        int animatedHeight = 480;

        animatedInfoPanel.setBounds(animatedX, animatedY, animatedWidth, animatedHeight);

        this.add(animatedInfoPanel);

        this.setVisible(true);
    }

    public void setValues(int[] values) {
        int[] entireValues = new int[9];
        int[] partialValues = new int[9];

        int i1 = 0;
        int i2 = 0;

        for (int i = 0; i < 18; i++) {
            if (i % 6 < 3) {
                entireValues[i1] = values[i];
                i1++;
            } else {
                partialValues[i2] = values[i];
                i2++;
            }
        }

        entireTimeInfo.setValues(entireValues);
        selectedTimeInfo.setValues(partialValues);
    }

    public void updateAnimation(List<Queue> queueList) {
        String toDisplay = "";
        int queueNumber = 1;

        for (Queue queue : queueList) {
            toDisplay = toDisplay.concat("Q" + queueNumber + ":");
            queueNumber++;
            for (int i = 0; i < queue.getClientList().size(); i++) {
                toDisplay = toDisplay.concat(" *");
            }
            toDisplay = toDisplay.concat("\n");
        }

        animatedInfoPanel.setText(toDisplay);
    }
}
