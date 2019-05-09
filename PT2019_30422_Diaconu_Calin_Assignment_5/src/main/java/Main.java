import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.*;

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

        //Task4
        task4(monitoredDataList);
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

    private static Map<String, LocalDateTime> task4(List<MonitoredData> monitoredDataList) {
        Map<String, LocalDateTime> durationMap = new HashMap<>();

        for(MonitoredData monitoredData : monitoredDataList) {
            String activity = monitoredData.getActivity();

            if(durationMap.containsKey(activity)) {
                LocalDateTime old = durationMap.get(activity);
                durationMap.replace(activity, old, old.plusSeconds(monitoredData.getDurationSeconds()));
            } else
                durationMap.put(activity, HelpingMethods.absolute.plusSeconds(monitoredData.getDurationSeconds()));
        }

        FileManager.printMap4(durationMap);

        return durationMap;
    }

    public static void main(String[] args) {
        new Main();
    }
}
