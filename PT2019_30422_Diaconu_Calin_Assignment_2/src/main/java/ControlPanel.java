import javax.swing.*;

public class ControlPanel extends JPanel {
    private TimeSlidersPanel intervalTime;
    private TimeSlidersPanel serviceTime;
    private TimeSlidersPanel infoTime;
    private TimeSlidersPanel otherControls;

    public ControlPanel() {
        this.setBorder(UIElements.etchedTitleBorder("Control Panel"));
        this.setLayout(null);

        //interval time
        intervalTime = new TimeSlidersPanel("Interval Time", "Min (s):", "Max (s):", 0, 20, 10, 30);

        int intervalTimePositionX = 10;
        int intervalTimePositionY = 20;
        int intervalTimeWidth = 280;
        int intervalTimeHeight = 120;

        intervalTime.setBounds(intervalTimePositionX, intervalTimePositionY, intervalTimeWidth, intervalTimeHeight);

        this.add(intervalTime);


        //service time
        serviceTime = new TimeSlidersPanel("Service Time", "Min (s):", "Max (s):", 0, 20, 10, 30);

        int serviceTimePositionX = 10;
        int serviceTimePositionY = 140;
        int serviceTimeWidth = 280;
        int serviceTimeHeight = 120;

        serviceTime.setBounds(serviceTimePositionX, serviceTimePositionY, serviceTimeWidth, serviceTimeHeight);

        this.add(serviceTime);


        //info time
        infoTime = new TimeSlidersPanel("Information Time", "Min (s):", "Max (s):", 0, 120, 0, 120);

        int infoTimePositionX = 10;
        int infoTimePositionY = 260;
        int infoTimeWidth = 280;
        int infoTimeHeight = 120;

        infoTime.setBounds(infoTimePositionX, infoTimePositionY, infoTimeWidth, infoTimeHeight);

        this.add(infoTime);

        //other controls
        otherControls = new TimeSlidersPanel("Other Controls", "Sim time:", "Queue no:", 0, 120, 3, 10);

        int otherControlsPositionX = 10;
        int otherControlsPositionY = 380;
        int otherControlsWidth = 280;
        int otherControlsHeight = 120;

        otherControls.setBounds(otherControlsPositionX, otherControlsPositionY, otherControlsWidth, otherControlsHeight);

        this.add(otherControls);

        this.setVisible(true);
    }

    public int[] getValues() {
        int[] result = new int[8];

        int[] result1;
        int[] result2;
        int[] result3;
        int[] result4;

        result1 = intervalTime.getValues();
        result2 = serviceTime.getValues();
        result3 = infoTime.getValues();
        result4 = otherControls.getValues();

        System.arraycopy(result1, 0, result, 0, 2);
        System.arraycopy(result2, 0, result, 2, 2);
        System.arraycopy(result3, 0, result, 4, 2);
        System.arraycopy(result4, 0, result, 6, 2);

        return result;
    }
}
