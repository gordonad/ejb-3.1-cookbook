package packt;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Schedules;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
public class SystemReportManager {

//    @Resource
//    private TimerService timerService;
//    @Resource
//    private SessionContext sessionContext;
//    long duration = 1000;

//    //@AroundTimeout
//    public Object interceptorTimeout(InvocationContext invocationContext) throws Exception {
//        System.out.println("interceptorTimeout executing");
//        Timer timer = (Timer) invocationContext.getTimer();
//        System.out.println("Timer: " + timer.getSchedule());
//        Object object = invocationContext.proceed();
//        System.out.println("interceptorTimeout returning");
//        return object;
//    }

//    @Schedules(
//        {@Schedule(minute="0", hour = "*"),
//        @Schedule(second = "20,30", minute="*", hour = "*")})
//    @Schedule(second="0", minute="*", hour = "*", info="", persistent=true)
    @Schedule(second = "0,10,20,30,40,50", minute="*", hour = "*")
//    @Schedule(second = "0", minute = "*", hour = "*")
    public void displayMemoryReport(Timer timer) {
        System.out.println("SystemReportManager: displayMemoryReport occurred");
        System.out.println(getMemoryReport());
//        if (!"NoTimerData".equals(timer.getInfo())) {
//            System.out.println(getTimerData(timer));
//        }
    }

//    public String getTimerData(Timer timer) {
//        StringBuilder timerData = new StringBuilder();
//        timerData.append("\nInfo: ").append(timer.getInfo());
//        timerData.append("\nNext timeout: ").append(timer.getNextTimeout());
//        timerData.append("\nSchedule: ").append(timer.getSchedule());
//        timerData.append("\nTime remaining: ").append(timer.getTimeRemaining());
//        timerData.append("\nCalendar timer: ").append(timer.isCalendarTimer());
//        timerData.append("\nPersistent: ").append(timer.isPersistent());
//        return timerData.toString();
//    }
//
//    //@Schedule(second = "0", minute="*", hour = "*")
//    public void clearStatistics(Timer timer) {
//        System.out.println("clearStatistics executed");
//    }
//
//    @Timeout
//    public void timeout(Timer timer) {
////        System.out.println("timeout: timeout occurred");
////        System.out.println("getMemoryReport: " + getMemoryReport());
////        if (!"NoTimerData".equals(timer.getInfo())) {
////            System.out.println(getTimerData(timer));
////        }
//        ArrayList<String> list = (ArrayList<String>) timer.getInfo();
//        System.out.println("List Elements"+timer);
//        for(String element : list) {
//            System.out.println(element);
//        }
//    }
//
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

//    public void createTimer() {
//////        GregorianCalendar reportCalendar = new GregorianCalendar(2014,
//////                Calendar.JANUARY, 5, 11, 12);
//////        Date reportDate = reportCalendar.getTime();
//////        timerService.createSingleActionTimer(reportDate, new TimerConfig());
//////        GregorianCalendar reportCalendar = new GregorianCalendar();
//////        Date reportDate = reportCalendar.getTime();
//////        timerService.createIntervalTimer(1000, 3000, new TimerConfig());
////        ScheduleExpression scheduleExpression = new ScheduleExpression();
////        scheduleExpression.year(2012);
////        scheduleExpression.month(1);
////        scheduleExpression.dayOfMonth(1);
////        timerService.createCalendarTimer(scheduleExpression, new TimerConfig());
////
////        scheduleExpression = new ScheduleExpression();
////        scheduleExpression.year(2013);
////        scheduleExpression.month(1);
////        scheduleExpression.dayOfMonth(1);
////        timerService.createCalendarTimer(scheduleExpression, new TimerConfig());
////
////
//////        GregorianCalendar reportCalendar = new GregorianCalendar(2011,
//////                Calendar.JANUARY, 5, 47, 0);
//////        Date startDate = reportCalendar.getTime();
////        scheduleExpression = new ScheduleExpression();
//////        scheduleExpression.start(startDate);
//////        scheduleExpression.year(2011);
//////        scheduleExpression.month(1);
//////        scheduleExpression.dayOfMonth(5);
////        scheduleExpression.second("0/10");
////        scheduleExpression.minute("*");
////        scheduleExpression.hour("*");
////        TimerConfig timerConfig = new TimerConfig();
////        timerConfig.setInfo("information");
////        timerConfig.setPersistent(true);
////        timerService.createCalendarTimer(scheduleExpression, timerConfig);
////
////        Collection<Timer> timers = timerService.getTimers();
////        for (Timer timer : timers) {
////            System.out.println(timer.getSchedule());
////        }
//
//        GregorianCalendar reportCalendar = new GregorianCalendar(2011,
//                Calendar.JANUARY, 6, 19, 56);
//        Date reportDate = reportCalendar.getTime();
//        ArrayList<String> list = new ArrayList<String>();
//        list.add("capitalize");
//        list.add("center");
//        list.add("arial");
//        timerService.createTimer(reportDate, list);
//
//    }
//
//    public String getSessionContext() {
//        timerService = sessionContext.getTimerService();
//        timerService.createSingleActionTimer(duration, new TimerConfig());
//        return sessionContext.toString();
//    }
//
//    public String getContextInformationJNDI() {
//        SessionContext sctxLookup;
//        try {
//            InitialContext ic = new InitialContext();
//            sctxLookup =
//                    (SessionContext) ic.lookup("java:comp/env/sessionContext");
//        } catch (NamingException ex) {
//            return "NamingException: " + ex.toString();
//            //throw new IllegalStateException(ex);
//        }
//        return sctxLookup.toString() + "<br/>"
//                + sctxLookup.getInvokedBusinessInterface().toString() + "<br/>";
//    }
}
