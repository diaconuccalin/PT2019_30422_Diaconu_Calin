import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        //MainFrame initial conditions
        int w = 1210;
        int h = 550;

        setLayout(null);
        setSize(w, h);
        setTitle("Queues");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //control panel
        ControlPanel controlPanel = new ControlPanel();

        int controlPanelPositionX = 5;
        int controlPanelPositionY = 0;
        int controlPanelWidth = 300;
        int controlPanelHeight = 510;

        controlPanel.setBounds(controlPanelPositionX, controlPanelPositionY, controlPanelWidth, controlPanelHeight);

        this.add(controlPanel);

        //information panel
        InformationPanel informationPanel = new InformationPanel();

        int informationPanelPositionX = 310;
        int informationPanelPositionY = 0;
        int informationPanelWidth = 885;
        int informationPanelHeight = 510;

        informationPanel.setBounds(informationPanelPositionX, informationPanelPositionY, informationPanelWidth, informationPanelHeight);

        this.add(informationPanel);

        setVisible(true);
    }
}
