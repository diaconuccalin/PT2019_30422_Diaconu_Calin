package BackEnd.runnable;

import FrontEnd.InformationPanel;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Timer extends Thread {
    private long previousTime;
    private JLabel jLabel;
    private boolean runnable;
    private List<Queue> queueList;
    private int runningTime;
    private InformationPanel informationPanel;

    private BufferedWriter writer;

    public Timer(JLabel jLabel, List<Queue> queueList, int runningTime, InformationPanel informationPanel, BufferedWriter writer) {
        this.writer = writer;

        this.informationPanel = informationPanel;
        this.runningTime = runningTime;
        this.jLabel = jLabel;
        this.runnable = true;
        this.queueList = queueList;
        this.start();
    }

    public void run() {
        int currentTime = 0;
        previousTime = System.currentTimeMillis();

        jLabel.setText("Timer: " + currentTime + " s");

        while (runnable) {
//            if(BackEnd.runnable.Queue.allQueuesEmpty(queueList) && currentTime >= runningTime) {
//                runnable = false;
//
//                int[] values = new int[18];
//                for(int i = 0; i < 3; i++) {
//                    values[i * 3] = queueList.get(i).getAverageWaitingTime();
//                    values[i * 3 + 1] = queueList.get(i).getAverageServiceTime();
//                    values[i * 3 + 2] = queueList.get(i).getEmptyQueueTime();
//                    values[i * 3 + 3] = queueList.get(i).getPartialAWT();
//                    values[i * 3 + 4] = queueList.get(i).getPartialAST();
//                    values[i * 3 + 5] = queueList.get(i).getPartialEQT();
//                }
//
//                informationPanel.setValues(values);

//                break;
//            }

            if (System.currentTimeMillis() - previousTime >= 1000) {
                currentTime++;
                previousTime = System.currentTimeMillis();
                jLabel.setText("Timer: " + currentTime + " s");

                try {
                    writer.append("\n ").append(String.valueOf(currentTime)).append(": ");
                } catch (IOException ignored) {
                }

                informationPanel.updateAnimation(queueList);
            }
        }

        try {
            writer.close();
        } catch (IOException ignored) {
        }
        queueList.removeAll(queueList);
    }

    public void setRunnable(boolean runnable) {
        this.runnable = runnable;
    }
}
