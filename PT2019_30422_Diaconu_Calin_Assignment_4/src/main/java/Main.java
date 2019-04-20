public class Main {
    public static ChefGraphicalUserInterface chefGraphicalUserInterface;

    public static void main(String[] args) {
        FileWriter.resetStreams();
        new AdministratorGraphicalUserInterface();
        chefGraphicalUserInterface =  new ChefGraphicalUserInterface();
        new WaiterGraphicalUserInterface();
    }

    public static ChefGraphicalUserInterface getChefGraphicalUserInterface() {
        return chefGraphicalUserInterface;
    }
}
