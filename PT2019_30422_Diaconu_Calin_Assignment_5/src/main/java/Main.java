import java.util.List;

public class Main {
    public Main() {
        //Task 0
        List<MonitoredData> monitoredDataList = task0();

        //Task 1
        System.out.println(task1(monitoredDataList));
    }

    private static List<MonitoredData> task0() {
        return FileManager.getInfo();
    }

    private static int task1(List<MonitoredData> monitoredDataList) {
        int days = 1;

        String date1;
        String date2;

        for(MonitoredData monitoredData : monitoredDataList) {
            date1 = monitoredData.getStartDate();
            date2 = monitoredData.getEndDate();

            if(!HelpingMethods.sameDay(date1, date2))
                days++;

            if(monitoredDataList.indexOf(monitoredData) != monitoredDataList.size() - 1) {
                date1 = date2;
                date2 = monitoredDataList.get(monitoredDataList.indexOf(monitoredData) + 1).getStartDate();

                if(!HelpingMethods.sameDay(date1, date2))
                    days++;
            }
        }

        return days;
    }

    public static void main(String[] args) {
        new Main();
    }
}
