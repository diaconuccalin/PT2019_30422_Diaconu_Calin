package Main;

import FrontEnd.MainFrame;

import java.io.BufferedWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

    public static void writeToFile(String string, BufferedWriter writer) {
        try {
            writer.append(string);
        } catch (IOException ignored) {
        }
    }
}
