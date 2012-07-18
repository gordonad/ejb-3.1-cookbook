package packt;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;

@Stateless
public class SystemReportManager {

    @Resource
    TimerService timerService;
    long duration = 1000 ;

    @Schedule(second="0", minute="*", hour = "*")
    public void displayMemoryReport(Timer timer) {
        System.out.println("SystemReportManager: displayMemoryReport occurred");
        System.out.println(getMemoryReport());
        System.out.println(getTimerData(timer));
    }

    @Schedule(second = "0", minute="*", hour = "*")
    public void clearStatistics() {
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

    @AroundTimeout
    public Object interceptorTimeout(InvocationContext invocationContext) throws Exception {
        System.out.println("interceptTimeout executing");
        Timer timer = (Timer)invocationContext.getTimer();
        System.out.println("Timer: " + timer.getSchedule());
        Object object = invocationContext.proceed();
        System.out.println("interceptTimeout returning");
        return object;
    }

    public String getTimerData(Timer timer) {
        StringBuilder timerData = new StringBuilder();
        timerData.append("\nInfo: ").append(timer.getInfo());
        timerData.append("\nNext timeout: ").append(timer.getNextTimeout());
        timerData.append("\nSchedule: ").append(timer.getSchedule());
        timerData.append("\nTime remaining: ").append(timer.getTimeRemaining());
        timerData.append("\nCalendar timer: ").append(timer.isCalendarTimer());
        timerData.append("\nPersistent: ").append(timer.isPersistent());
        return timerData.toString();
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
