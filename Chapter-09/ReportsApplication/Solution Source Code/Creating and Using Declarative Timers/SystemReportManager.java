package packt;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Stateless;
import javax.ejb.Timer;

@Stateless
public class SystemReportManager {

    @Schedule(second = "0,10,20,30,40,50", minute="*", hour = "*")
    //@Schedules(
    //{@Schedule(second = "0", minute="*", hour = "*"),
    //@Schedule(second = "20,30", minute="*", hour = "*")})

    public void displayMemoryReport(Timer timer) {
        System.out.println("SystemReportManager: displayMemoryReport occurred");
        System.out.println(getMemoryReport());
    }

    @Schedule(second = "0", minute="*", hour = "*")
    public void clearStatistics(Timer timer) {
        System.out.println("clearStatistics executed");
    }

    public String getMemoryReport() {
        StringBuilder report = new StringBuilder();
        GregorianCalendar reportCalendar = new GregorianCalendar();
        Date reportDate = reportCalendar.getTime();
        Runtime runtime = Runtime.getRuntime();

        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        report.append("\n").append(dateFormat.format(reportDate));
        report.append("\nTotal Memory: ").append(runtime.totalMemory());
        report.append("\n");
        report.append("Maximum Memory: ").append(runtime.maxMemory());
        report.append("\n");
        report.append("Free Memory: ").append(runtime.freeMemory());
        report.append("\n");

        return report.toString();
    }

}
