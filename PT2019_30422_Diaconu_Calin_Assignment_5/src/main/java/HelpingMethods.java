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
}
