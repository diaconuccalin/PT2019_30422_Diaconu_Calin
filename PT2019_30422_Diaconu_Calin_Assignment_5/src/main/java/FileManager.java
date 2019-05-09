import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static void printMap(Map<String, Integer> map) {
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
}
