package packt;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Stateless
public class SystemReportManager {

    @Resource
    TimerService timerService;
    long duration = 1000 ;

//    @Schedule(second = "0,10,20,30,40,50", minute="*", hour = "*")
    @Schedules(
    {@Schedule(second = "0", minute="*", hour = "*"),
    @Schedule(second = "20,30", minute="*", hour = "*")})
    public void displayMemoryReport(Timer timer) {
        System.out.println("SystemReportManager: displayMemoryReport occurred");
        System.out.println(getMemoryReport());
    }

    @Schedule(second = "0", minute="*", hour = "*")
    public void clearStatistics(Timer timer) {
        System.out.println("clearStatistics executed");
    }

    public void createTimer() {
        timerService.createSingleActionTimer(duration, new TimerConfig());
    }

    @Timeout
    public void timeout(Timer timer) {
        System.out.println("timeout: timeout occurred");
        System.out.println("getMemoryReport: " + getMemoryReport());
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
