import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {
    public static List<MonitoredData> getInfo() {
        List<MonitoredData> toReturn = new ArrayList<>();

        try {
            Stream<String> stream = Files.lines(Paths.get("Activities.txt"));
            List<String> lines = stream.collect(Collectors.toList());

            for (String line : lines) {
                String[] splitLine = HelpingMethods.splitLine.result(line);

                if(splitLine[2].charAt(splitLine[2].length() - 1) == '\t')
                    splitLine[2] = splitLine[2].substring(0, splitLine[2].length() - 1);

                toReturn.add(new MonitoredData(splitLine[0], splitLine[1], splitLine[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    public static void printMap2(Map<String, Integer> map) {
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("task2.txt"));

            for(Map.Entry<String, Integer> entry : entrySet)
                bufferedWriter.append(entry.getKey()).append(" - ").append(String.valueOf(entry.getValue())).append("\n");

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printMap3(Map<Integer, Map<String, Integer>> map) {
        Set<Map.Entry<Integer, Map<String, Integer>>> entrySet = map.entrySet();

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("task3.txt"));

            for(Map.Entry<Integer, Map<String, Integer>> entry : entrySet) {
                bufferedWriter.append("Day ").append(String.valueOf(entry.getKey())).append(":\n");

                Set<Map.Entry<String, Integer>> entrySet1 = entry.getValue().entrySet();

                for(Map.Entry<String, Integer> entry1 : entrySet1)
                    bufferedWriter.append(entry1.getKey()).append(" - ").append(String.valueOf(entry1.getValue())).append("\n");

                bufferedWriter.append("\n");
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printMap4(Map<String, LocalDateTime> map) {
        Set<Map.Entry<String, LocalDateTime>> entrySet = map.entrySet();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("task4.txt"));

            for(Map.Entry<String, LocalDateTime> entry : entrySet) {
                if(HelpingMethods.check4(entry.getValue())) {
                    bufferedWriter.append(entry.getKey()).append(" - ");
                    LocalDateTime value = entry.getValue();
                    if(value.getYear() > 0)
                        bufferedWriter.append(String.valueOf(value.getYear())).append(" years ").append(String.valueOf(value.getMonth().getValue() - 1)).append(" months ").append(String.valueOf(value.getDayOfMonth() - 1)).append(" days ").append(String.valueOf(value.getHour())).append(" hours ").append(String.valueOf(value.getMinute())).append(" minutes ").append(String.valueOf(value.getSecond())).append(" seconds\n");
                    else if(value.getMonth().getValue() > 1)
                        bufferedWriter.append(String.valueOf(value.getMonth().getValue() - 1)).append(" months ").append(String.valueOf(value.getDayOfMonth() - 1)).append(" days ").append(String.valueOf(value.getHour())).append(" hours ").append(String.valueOf(value.getMinute())).append(" minutes ").append(String.valueOf(value.getSecond())).append(" seconds\n");
                    else if(value.getDayOfMonth() > 1)
                        bufferedWriter.append(String.valueOf(value.getDayOfMonth() - 1)).append(" days ").append(String.valueOf(value.getHour())).append(" hours ").append(String.valueOf(value.getMinute())).append(" minutes ").append(String.valueOf(value.getSecond())).append(" seconds\n");
                    else
                        bufferedWriter.append(String.valueOf(value.getHour())).append(" hours ").append(String.valueOf(value.getMinute())).append(" minutes ").append(String.valueOf(value.getSecond())).append(" seconds\n");
                }
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
