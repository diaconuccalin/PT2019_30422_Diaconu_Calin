package Main;

import business.BeforeShutdown;
import presentation.MainFrame;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static BufferedWriter bufferedWriter;

    public static void main(String[] args) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("orders.txt"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new BeforeShutdown(bufferedWriter));

        new MainFrame(bufferedWriter);
    }
}
