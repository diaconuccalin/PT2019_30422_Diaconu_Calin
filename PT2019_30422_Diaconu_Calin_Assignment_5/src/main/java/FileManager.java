import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileManager {
    public static List<MonitoredData> getInfo() {
        List<MonitoredData> toReturn = new ArrayList<>();

        File file = new File("Activities.txt");
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            String line;

            String delimiter = "\t\t";
            Pattern pattern = Pattern.compile(delimiter, Pattern.CASE_INSENSITIVE);

            while((line = bufferedReader.readLine()) != null) {
                String[] result = pattern.split(line);
                toReturn.add(new MonitoredData(result[0], result[1], result[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return toReturn;
    }
}
