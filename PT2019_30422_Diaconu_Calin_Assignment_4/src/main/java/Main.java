import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static ChefGraphicalUserInterface chefGraphicalUserInterface;
    private static WaiterGraphicalUserInterface waiterGraphicalUserInterface;
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
        waiterGraphicalUserInterface = new WaiterGraphicalUserInterface();
    }

    public static ChefGraphicalUserInterface getChefGraphicalUserInterface() {
        return chefGraphicalUserInterface;
    }

    public static BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public static void setChefGraphicalUserInterface(ChefGraphicalUserInterface chefGraphicalUserInterface) {
        Main.chefGraphicalUserInterface = chefGraphicalUserInterface;
    }

    public static void setWaiterGraphicalUserInterface(WaiterGraphicalUserInterface waiterGraphicalUserInterface) {
        Main.waiterGraphicalUserInterface = waiterGraphicalUserInterface;
    }

    public static WaiterGraphicalUserInterface getWaiterGraphicalUserInterface() {
        return waiterGraphicalUserInterface;
    }
}
