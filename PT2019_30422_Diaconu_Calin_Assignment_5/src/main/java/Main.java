import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public Main() {
        //Task 0
        List<MonitoredData> monitoredDataList = task0();

        //Task 1
        System.out.println(task1(monitoredDataList));

        //Task2
        task2(monitoredDataList);

        //Task3
        task3(monitoredDataList);
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

    private static Map<String, Integer> task2(List<MonitoredData> monitoredDataList) {
        Map<String, Integer> occurrenceMap = HelpingMethods.generateOccurrenceMap(monitoredDataList);
        FileManager.printMap2(occurrenceMap);
        return occurrenceMap;
    }

    private static Map<Integer, Map<String, Integer>> task3(List<MonitoredData> monitoredDataList) {
        Map<Integer, Map<String, Integer>> occurrenceMap = new HashMap<>();
        Map<String, Integer> dayMap;
        List<MonitoredData> dayList = new ArrayList<>();

        int day = 1;

        for(MonitoredData monitoredData : monitoredDataList) {
            int currentIndex = monitoredDataList.indexOf(monitoredData);

            if(currentIndex > 0 && monitoredData.getStartDate().compareTo(monitoredDataList.get(currentIndex - 1).getStartDate()) != 0) {
                dayMap = HelpingMethods.generateOccurrenceMap(dayList);
                dayList = new ArrayList<>();
                occurrenceMap.put(day, dayMap);
                day++;
            }

            dayList.add(monitoredData);
        }

        FileManager.printMap3(occurrenceMap);
        return occurrenceMap;
    }

    public static void main(String[] args) {
        new Main();
    }
}
