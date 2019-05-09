package Main;

import BusinessLayer.HelpingMethods;
import DataLayer.FileManager;
import Structure.MonitoredData;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    private Main() {
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

        //Task5
        task5(monitoredDataList);
    }

    private static List<MonitoredData> task0() {
        return FileManager.getInfo();
    }

    private static int task1(List<MonitoredData> monitoredDataList) {
        int days = 1;

        String date1;
        String date2;

        for (MonitoredData monitoredData : monitoredDataList) {
            date1 = monitoredData.getStartDate();
            date2 = monitoredData.getEndDate();

            if (HelpingMethods.notSameDay(date1, date2))
                days++;

            if (monitoredDataList.indexOf(monitoredData) != monitoredDataList.size() - 1) {
                date1 = date2;
                date2 = monitoredDataList.get(monitoredDataList.indexOf(monitoredData) + 1).getStartDate();

                if (HelpingMethods.notSameDay(date1, date2))
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

        for (MonitoredData monitoredData : monitoredDataList) {
            int currentIndex = monitoredDataList.indexOf(monitoredData);

            if (currentIndex > 0 && monitoredData.getStartDate().compareTo(monitoredDataList.get(currentIndex - 1).getStartDate()) != 0) {
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

        for (MonitoredData monitoredData : monitoredDataList) {
            String activity = monitoredData.getActivity();

            if (durationMap.containsKey(activity)) {
                LocalDateTime old = durationMap.get(activity);
                durationMap.replace(activity, old, old.plusSeconds(monitoredData.getDurationSeconds()));
            } else
                durationMap.put(activity, HelpingMethods.absolute.plusSeconds(monitoredData.getDurationSeconds()));
        }

        FileManager.printMap4(durationMap);

        return durationMap;
    }

    private static List<String> task5(List<MonitoredData> monitoredDataList) {
        List<String> toReturn = new ArrayList<>();

        Map<String, Integer> totalMap = new HashMap<>();
        Map<String, Integer> selectionMap = new HashMap<>();

        for (MonitoredData monitoredData : monitoredDataList) {
            String activity = monitoredData.getActivity();

            if (totalMap.containsKey(activity)) {
                int old = totalMap.get(activity);
                totalMap.replace(activity, old, old + 1);
            } else
                totalMap.put(activity, 1);

            if (monitoredData.getDurationSeconds() < 300) {
                if (selectionMap.containsKey(activity)) {
                    int old = selectionMap.get(activity);
                    selectionMap.replace(activity, old, old + 1);
                } else
                    selectionMap.put(activity, 1);
            }
        }

        Set<Map.Entry<String, Integer>> entrySet = selectionMap.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            int selection = entry.getValue();
            int total = totalMap.get(entry.getKey());

            if ((selection * 100) / total >= 90)
                toReturn.add(entry.getKey());
        }

        FileManager.printTask5(toReturn);

        return toReturn;
    }

    public static void main(String[] args) {
        new Main();
    }
}
