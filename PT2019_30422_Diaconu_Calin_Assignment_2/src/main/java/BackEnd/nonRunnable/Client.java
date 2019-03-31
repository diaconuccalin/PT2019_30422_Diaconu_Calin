package BackEnd.nonRunnable;

public class Client {
    private int serviceTime;
    private long birthTime;

    public Client(int minServiceTime, int maxServiceTime) {
        serviceTime = (int) (Math.random() * (maxServiceTime - minServiceTime) + minServiceTime);
        birthTime = System.currentTimeMillis();
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public long getBirthTime() {
        return birthTime;
    }
}
