package Main;

import DataLayer.FileWriters;
import PresentationLayer.AdministratorGraphicalUserInterface;
import PresentationLayer.ChefGraphicalUserInterface;
import PresentationLayer.WaiterGraphicalUserInterface;

public class Main {
    private static ChefGraphicalUserInterface chefGraphicalUserInterface;
    private static WaiterGraphicalUserInterface waiterGraphicalUserInterface;

    public static void main(String[] args) {
        FileWriters.initializeBufferedWriter();
        FileWriters.resetStreams();
        new AdministratorGraphicalUserInterface();
        chefGraphicalUserInterface = new ChefGraphicalUserInterface();
        waiterGraphicalUserInterface = new WaiterGraphicalUserInterface();

        waiterGraphicalUserInterface.addObserver(chefGraphicalUserInterface);
    }

    public static ChefGraphicalUserInterface getChefGraphicalUserInterface() {
        return chefGraphicalUserInterface;
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
