package Main;

import business.BeforeShutdown;
import presentation.MainFrame;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    static BufferedWriter bufferedWriter;

    public static void main(String[] args) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("orders.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new BeforeShutdown(bufferedWriter));

        MainFrame mainFrame = new MainFrame(bufferedWriter);
    }
}
