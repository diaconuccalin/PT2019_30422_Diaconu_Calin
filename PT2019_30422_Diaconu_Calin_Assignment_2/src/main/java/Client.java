public class Client {
    private int serviceTime;

    public Client(int minServiceTime, int maxServiceTime) {
        serviceTime = (int)(Math.random() * (maxServiceTime - minServiceTime) + minServiceTime);
    }

    public int getServiceTime() {
        return serviceTime;
    }
}
