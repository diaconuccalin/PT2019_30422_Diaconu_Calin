package business;

import java.io.BufferedWriter;
import java.io.IOException;

public class BeforeShutdown extends Thread {
    BufferedWriter bufferedWriter;

    public BeforeShutdown(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public void run() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
