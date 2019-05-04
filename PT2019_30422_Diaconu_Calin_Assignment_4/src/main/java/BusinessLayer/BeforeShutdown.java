package BusinessLayer;

import DataLayer.FileWriters;

import java.io.BufferedWriter;
import java.io.IOException;

public class BeforeShutdown extends Thread {
    private BufferedWriter bufferedWriter;

    public BeforeShutdown(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public void run() {
        FileWriters.exportMenu();

        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
