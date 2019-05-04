package BusinessLayer;

import DataLayer.RestaurantSerializer;

import java.io.BufferedWriter;
import java.io.IOException;

public class BeforeShutdown extends Thread {
    private BufferedWriter bufferedWriter;

    public BeforeShutdown(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public void run() {
        RestaurantSerializer.exportMenu();

        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
