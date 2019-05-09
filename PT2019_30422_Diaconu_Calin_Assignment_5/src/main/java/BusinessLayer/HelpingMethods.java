package BusinessLayer;

import Structure.MonitoredData;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class HelpingMethods {
    public static LocalDateTime absolute = LocalDateTime.parse("0000-01-01T00:00:00");

    public static SplitText splitLine = (initial) -> {
        String delimiter = "\t\t";
        Pattern pattern = Pattern.compile(delimiter, Pattern.CASE_INSENSITIVE);
        return pattern.split(initial);
    };

    public static SplitText splitTime = (initial) -> {
        String delimiter = " ";
        Pattern pattern = Pattern.compile(delimiter, Pattern.CASE_INSENSITIVE);
        return pattern.split(initial);
    };

    private static SplitText splitDate = (initial) -> {
        String delimiter = "-";
        Pattern pattern = Pattern.compile(delimiter, Pattern.CASE_INSENSITIVE);
        return pattern.split(initial);
    };

    public static boolean notSameDay(String date1, String date2) {
        String[] day1 = splitDate.result(date1);
        String[] day2 = splitDate.result(date2);

        for (int i = 0; i < 3; i++) {
            if (day1[i].compareTo(day2[i]) != 0)
                return true;
        }

        return false;
    }

    public static Map<String, Integer> generateOccurrenceMap(List<MonitoredData> monitoredDataList) {
        Map<String, Integer> occurrenceMap = new HashMap<>();

        for (MonitoredData monitoredData : monitoredDataList) {
            String activity = monitoredData.getActivity();

            if (occurrenceMap.containsKey(activity)) {
                int old = occurrenceMap.get(activity);
                occurrenceMap.replace(activity, old, old + 1);
            } else
                occurrenceMap.put(activity, 1);
        }

        return occurrenceMap;
    }

    public static boolean check4(LocalDateTime value) {
        if (value.getYear() > 0)
            return true;
        if (value.getMonth().getValue() > 1)
            return true;
        if (value.getDayOfMonth() > 1)
            return true;
        return value.getHour() >= 10;
    }
}
