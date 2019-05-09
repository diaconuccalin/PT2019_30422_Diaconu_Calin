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
}
