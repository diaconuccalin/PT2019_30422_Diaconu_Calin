import javax.swing.*;
import java.util.List;

public class Timer extends Thread {
    private long previousTime;
    private JLabel jLabel;
    private boolean runnable;
    private List<Queue> queueList;
    private int runningTime;
    private InformationPanel informationPanel;

    public Timer(JLabel jLabel, List<Queue> queueList, int runningTime, InformationPanel informationPanel) {
        this.informationPanel = informationPanel;
        this.runningTime = runningTime;
        this.jLabel = jLabel;
        this.runnable = true;
        this.queueList = queueList;
        this.start();
    }

    public void run() {
        int currentTime = 0;
        long initialTime = System.currentTimeMillis();
        previousTime = initialTime;

        jLabel.setText("Timer: " + currentTime + " s");

        while(runnable) {
            if(Queue.allQueuesEmpty(queueList) && currentTime >= runningTime) {
                runnable = false;

                int[] values = new int[18];
                for(int i = 0; i < 3; i++) {
                    values[i * 3] = queueList.get(i).getAverageWaitingTime();
                    values[i * 3 + 1] = queueList.get(i).getAverageServiceTime();
                    values[i * 3 + 2] = queueList.get(i).getEmptyQueueTime();
                    values[i * 3 + 3] = queueList.get(i).getPartialAWT();
                    values[i * 3 + 4] = queueList.get(i).getPartialAST();
                    values[i * 3 + 5] = queueList.get(i).getPartialEQT();
                }

                informationPanel.setValues(values);

                break;
            }

            if(System.currentTimeMillis() - previousTime >= 1000) {
                currentTime++;
                previousTime = System.currentTimeMillis();
                jLabel.setText("Timer: " + currentTime + " s");

                informationPanel.updateAnimation(queueList);
            }
        }

        queueList.removeAll(queueList);
    }

    public void setRunnable(boolean runnable) {
        this.runnable = runnable;
    }
}
