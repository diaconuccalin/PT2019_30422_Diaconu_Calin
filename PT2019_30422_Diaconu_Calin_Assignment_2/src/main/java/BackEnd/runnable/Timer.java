package BackEnd.runnable;

import FrontEnd.InformationPanel;
import Main.Main;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class Timer extends Thread {
    private JLabel jLabel;
    private boolean runnable;
    private List<Queue> queueList;
    private int runningTime;
    private InformationPanel informationPanel;

    private BufferedWriter writer;

    private long previousTime;

    public Timer(JLabel jLabel, List<Queue> queueList, int runningTime, InformationPanel informationPanel, BufferedWriter writer) {
        this.jLabel = jLabel;
        this.runnable = true;
        this.queueList = queueList;
        this.runningTime = runningTime;
        this.informationPanel = informationPanel;
        this.writer = writer;
        this.start();
    }

    public void run() {
        int currentTime = 0;
        int peakHour = 0;
        int peakHourClients = 0;
        previousTime = System.currentTimeMillis();
        jLabel.setText("Timer: " + currentTime + " s");
        while (runnable) {
            if (BackEnd.runnable.Queue.allQueuesEmpty(queueList) && currentTime >= runningTime)
                break;
            if (System.currentTimeMillis() - previousTime >= 1000) {
                currentTime++;
                if(getTotalClients(queueList) > peakHourClients) {
                    peakHour = currentTime;
                    peakHourClients = getTotalClients(queueList);
                }
                previousTime = System.currentTimeMillis();
                System.out.println(currentTime);
                jLabel.setText("Timer: " + currentTime + " s");
                Main.writeToFile("\n " + currentTime + ": ", writer);
                informationPanel.updateAnimation(queueList);
            }
        }
        System.out.println("ALL DONE");
        try {
            writer.append("ALL DONE\nPEAK HOUR: ").append(String.valueOf(peakHour)).append(" - ").append(String.valueOf(peakHourClients)).append(" clients");
            writer.close();
        } catch (IOException ignored) {
        }
        int[] values = new int[18];
        for (int i = 0; i < 3; i++) {
            values[i * 6] = queueList.get(i).getAverageWaitingTime();
            values[i * 6 + 1] = queueList.get(i).getAverageServiceTime();
            values[i * 6 + 2] = queueList.get(i).getEmptyQueueTime();
            values[i * 6 + 3] = queueList.get(i).getPartialAWT();
            values[i * 6 + 4] = queueList.get(i).getPartialAST();
            values[i * 6 + 5] = queueList.get(i).getPartialEQT();
        }
        informationPanel.setValues(values);
        informationPanel.updateAnimation(queueList);
        //noinspection CollectionAddedToSelf
        queueList.removeAll(queueList);
    }


    static private int getTotalClients(List<Queue> queueList) {
        int result = 0;

        for (Queue queue : queueList) {
            result += queue.getClientList().size();
        }

        return result;
    }

}