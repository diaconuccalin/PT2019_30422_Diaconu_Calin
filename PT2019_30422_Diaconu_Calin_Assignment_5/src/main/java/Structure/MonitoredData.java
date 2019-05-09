package Structure;

import BusinessLayer.HelpingMethods;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MonitoredData {
    private String startTime;
    private String endTime;
    private String activity;

    public MonitoredData(String startTime, String endTime, String activity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return startTime + "\t\t" + endTime + "\t\t" + activity;
    }

    public String getStartDate() {
        return HelpingMethods.splitTime.result(startTime)[0];
    }

    public String getEndDate() {
        return HelpingMethods.splitTime.result(startTime)[0];
    }

    public String getActivity() {
        return activity;
    }

    public long getDurationSeconds() {
        String s = startTime.replace(' ', 'T');
        String e = endTime.replace(' ', 'T');

        LocalDateTime startLDT = LocalDateTime.parse(s);
        LocalDateTime endLDT = LocalDateTime.parse(e);

        return ChronoUnit.SECONDS.between(startLDT, endLDT);
    }
}
