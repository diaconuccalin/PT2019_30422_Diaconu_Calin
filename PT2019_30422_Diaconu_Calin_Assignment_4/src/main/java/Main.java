import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static ChefGraphicalUserInterface chefGraphicalUserInterface;
    private static BufferedWriter bufferedWriter;

    public static void main(String[] args) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("orders.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new BeforeShutdown(bufferedWriter));

        FileWriters.resetStreams();
        new AdministratorGraphicalUserInterface();
        chefGraphicalUserInterface =  new ChefGraphicalUserInterface();
        new WaiterGraphicalUserInterface();
    }

    public static ChefGraphicalUserInterface getChefGraphicalUserInterface() {
        return chefGraphicalUserInterface;
    }

    public static BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }
}
