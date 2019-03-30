import javax.swing.*;

public class Timer extends Thread {
    private long initialTime;
    private long previousTime;
    private JLabel jLabel;

    public Timer(JLabel jLabel) {
        initialTime = System.currentTimeMillis();
        previousTime = initialTime;
        this.jLabel = jLabel;

        this.start();
    }

    public void run() {
        int currentTime = 0;

        jLabel.setText(currentTime + "");

        while(true) {
            if(System.currentTimeMillis() - previousTime >= 1000) {
                currentTime++;
                previousTime = System.currentTimeMillis();
                jLabel.setText(currentTime + "");
            }
        }
    }
}
