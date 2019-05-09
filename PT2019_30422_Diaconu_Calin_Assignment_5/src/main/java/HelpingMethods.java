import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class HelpingMethods {
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

    public static SplitText splitDate = (initial) -> {
        String delimiter = "-";
        Pattern pattern = Pattern.compile(delimiter, Pattern.CASE_INSENSITIVE);
        return pattern.split(initial);
    };

    public static boolean sameDay(String date1, String date2) {
        String[] day1 = splitDate.result(date1);
        String[] day2 = splitDate.result(date2);

        for(int i = 0; i < 3; i++) {
            if(day1[i].compareTo(day2[i]) != 0)
                return false;
        }

        return true;
    }

    public static Map<String, Integer> generateOccurrenceMap(List<MonitoredData> monitoredDataList) {
        Map<String, Integer> occurrenceMap = new HashMap<>();

        for(MonitoredData monitoredData : monitoredDataList) {
            String activity = monitoredData.getActivity();

            if(occurrenceMap.containsKey(activity)) {
                int old = occurrenceMap.get(activity);
                occurrenceMap.replace(activity, old, old + 1);
            } else
                occurrenceMap.put(activity, 1);
        }

        return occurrenceMap;
    }
}
