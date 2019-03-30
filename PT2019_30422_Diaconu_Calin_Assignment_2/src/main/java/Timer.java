import javax.swing.*;
import java.util.List;

public class Timer extends Thread {
    private long previousTime;
    private JLabel jLabel;
    private boolean runnable;
    private List<Queue> queueList;
    private int runningTime;

    public Timer(JLabel jLabel, List<Queue> queueList, int runningTime) {
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
                break;
            }

            if(System.currentTimeMillis() - previousTime >= 1000) {
                currentTime++;
                previousTime = System.currentTimeMillis();
                jLabel.setText("Timer: " + currentTime + " s");
            }
        }

        queueList.removeAll(queueList);
    }

    public void setRunnable(boolean runnable) {
        this.runnable = runnable;
    }
}
