package FrontEnd;

import BackEnd.runnable.ClientGenerator;
import BackEnd.runnable.Queue;
import BackEnd.runnable.Timer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private ClientGenerator clientGenerator;
    private List<Queue> queueList;
    private Timer timer;

    public MainFrame() {
        //FrontEnd.MainFrame initial conditions
        int w = 1210;
        int h = 600;

        setLayout(null);
        setSize(w, h);
        setTitle("Queues");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //control panel
        final ControlPanel controlPanel = new ControlPanel();

        int controlPanelPositionX = 5;
        int controlPanelPositionY = 0;
        int controlPanelWidth = 300;
        int controlPanelHeight = 510;

        controlPanel.setBounds(controlPanelPositionX, controlPanelPositionY, controlPanelWidth, controlPanelHeight);

        this.add(controlPanel);

        //information panel
        final InformationPanel informationPanel = new InformationPanel();

        int informationPanelPositionX = 310;
        int informationPanelPositionY = 0;
        int informationPanelWidth = 885;
        int informationPanelHeight = 510;

        informationPanel.setBounds(informationPanelPositionX, informationPanelPositionY, informationPanelWidth, informationPanelHeight);

        this.add(informationPanel);

        //time control panel
        final TimeControlPanel timeControlPanel = new TimeControlPanel();

        int timeControlPanelPositionX = 5;
        int timeControlPanelPositionY = 510;
        int timeControlPanelWidth = 1190;
        int timeControlPanelHeight = 50;

        timeControlPanel.setBounds(timeControlPanelPositionX, timeControlPanelPositionY, timeControlPanelWidth, timeControlPanelHeight);

        this.add(timeControlPanel);

        //action listeners

        timeControlPanel.getStartButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] values = controlPanel.getValues();
                queueList = new ArrayList<Queue>();
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new BufferedWriter(new FileWriter("log.txt")));
                } catch (IOException ignored) {
                }

                for (int i = 0; i < values[7]; i++) {
                    Queue queue = new Queue(values[6], values[4], values[5], writer);
                    queueList.add(queue);
                }

                timer = new Timer(timeControlPanel.getTimer(), queueList, values[6], informationPanel, writer);
                clientGenerator = new ClientGenerator(values, queueList, writer);
            }
        });

        timeControlPanel.getStopButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientGenerator.interrupt();

                for (Queue queue : queueList) {
                    queue.interrupt();
                }
                queueList.removeAll(queueList);

                timer.setRunnable(false);
            }
        });

        setVisible(true);
    }
}
