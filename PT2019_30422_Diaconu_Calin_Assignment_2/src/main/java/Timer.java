import javax.swing.*;
import java.util.List;

public class Timer extends Thread {
    private long previousTime;
    private JLabel jLabel;
    private boolean runnable;
    private List<Queue> queueList;

    public Timer(JLabel jLabel) {
        this.jLabel = jLabel;
        this.runnable = true;
        this.start();
    }

    public void run() {
        int currentTime = 0;
        long initialTime = System.currentTimeMillis();
        previousTime = initialTime;

        jLabel.setText("Timer: " + currentTime + " s");

        while(runnable || !(isQueueEmpty())) {
            if(System.currentTimeMillis() - previousTime >= 1000) {
                currentTime++;
                previousTime = System.currentTimeMillis();
                jLabel.setText("Timer: " + currentTime + " s");
            }
        }
    }

    public void setRunnable(boolean runnable) {
        this.runnable = runnable;
    }

    private boolean isQueueEmpty() {
        for (Queue queue : queueList) {
            if (!queue.isEmpty())
                return false;
        }

        return true;
    }
}
