package BackEnd.nonRunnable;

import java.util.Random;

public class Client {
    private int serviceTime;
    private long birthTime;
    private String name;

    public Client(int minServiceTime, int maxServiceTime) {
        serviceTime = (int) (Math.random() * (maxServiceTime - minServiceTime) + minServiceTime);
        birthTime = System.currentTimeMillis();
        name = randomString();
    }

    private static String randomString() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public long getBirthTime() {
        return birthTime;
    }

    public String getName() {
        return name;
    }
}
