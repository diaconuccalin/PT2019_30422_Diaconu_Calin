import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    long startingTime;

    public MainFrame() {
        //MainFrame initial conditions
        int w = 1210;
        int h = 600;

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

        //time control panel
        TimeControlPanel timeControlPanel = new TimeControlPanel();

        int timeControlPanelPositionX = 5;
        int timeControlPanelPositionY = 510;
        int timeControlPanelWidth = 1190;
        int timeControlPanelHeight = 50;

        timeControlPanel.setBounds(timeControlPanelPositionX, timeControlPanelPositionY, timeControlPanelWidth, timeControlPanelHeight);

        this.add(timeControlPanel);

        //action listeners
        timeControlPanel.getStartButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startingTime = System.nanoTime();
                System.out.println(startingTime);
            }
        });

        setVisible(true);
    }
}