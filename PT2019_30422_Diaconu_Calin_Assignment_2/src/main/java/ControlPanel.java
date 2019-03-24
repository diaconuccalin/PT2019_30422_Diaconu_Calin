import javax.swing.*;

public class ControlPanel extends JPanel {
    public ControlPanel() {
        this.setBorder(UIElements.etchedTitleBorder("Control Panel"));
        this.setLayout(null);

        //interval time
        TimeSlidersPanel intervalTime = new TimeSlidersPanel("Interval Time", "Min:", "Max:", 0, 50, 20, 100);

        int intervalTimePositionX = 10;
        int intervalTimePositionY = 20;
        int intervalTimeWidth = 280;
        int intervalTimeHeight = 120;

        intervalTime.setBounds(intervalTimePositionX, intervalTimePositionY, intervalTimeWidth, intervalTimeHeight);

        this.add(intervalTime);


        //service time
        TimeSlidersPanel serviceTime = new TimeSlidersPanel("Service Time", "Min:", "Max:", 0, 50, 20, 100);

        int serviceTimePositionX = 10;
        int serviceTimePositionY = 140;
        int serviceTimeWidth = 280;
        int serviceTimeHeight = 120;

        serviceTime.setBounds(serviceTimePositionX, serviceTimePositionY, serviceTimeWidth, serviceTimeHeight);

        this.add(serviceTime);


        //info time
        TimeSlidersPanel infoTime = new TimeSlidersPanel("Information Time", "Min:", "Max:", 0, 50, 20, 100);

        int infoTimePositionX = 10;
        int infoTimePositionY = 260;
        int infoTimeWidth = 280;
        int infoTimeHeight = 120;

        infoTime.setBounds(infoTimePositionX, infoTimePositionY, infoTimeWidth, infoTimeHeight);

        this.add(infoTime);

        //other controls
        TimeSlidersPanel otherControls = new TimeSlidersPanel("Other Controls", "Sim time:", "Queue no:", 20, 100, 0, 30);

        int otherControlsPositionX = 10;
        int otherControlsPositionY = 380;
        int otherControlsWidth = 280;
        int otherControlsHeight = 120;

        otherControls.setBounds(otherControlsPositionX, otherControlsPositionY, otherControlsWidth, otherControlsHeight);

        this.add(otherControls);

        this.setVisible(true);
    }
}
