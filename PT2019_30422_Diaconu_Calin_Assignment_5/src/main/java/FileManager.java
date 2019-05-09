import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
                toReturn.add(new MonitoredData(splitLine[0], splitLine[1], splitLine[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return toReturn;
    }
}
