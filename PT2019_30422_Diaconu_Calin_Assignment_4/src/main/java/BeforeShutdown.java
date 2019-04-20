import java.io.BufferedWriter;
import java.io.IOException;

public class BeforeShutdown extends Thread {
    private BufferedWriter bufferedWriter;

    public BeforeShutdown(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public void run() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
