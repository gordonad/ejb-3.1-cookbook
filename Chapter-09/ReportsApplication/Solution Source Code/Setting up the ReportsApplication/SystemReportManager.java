package packt;

import java.util.Date;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import javax.ejb.Stateless;

@Stateless
public class SystemReportManager {

    long duration = 1000;    public String getMemoryReport() {
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
