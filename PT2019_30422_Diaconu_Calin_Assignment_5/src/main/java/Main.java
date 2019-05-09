import java.util.List;

public class Main {
    private List<MonitoredData> monitoredDataList;

    public Main() {
        monitoredDataList = FileManager.getInfo();

        for(MonitoredData monitoredData : monitoredDataList) {
            System.out.println(monitoredData.toString());
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
