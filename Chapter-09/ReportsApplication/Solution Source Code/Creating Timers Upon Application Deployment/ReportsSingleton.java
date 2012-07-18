package packt;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Singleton
@Startup
public class ReportsSingleton {

    @Resource
    private TimerService timerService;
    long duration = 1000;
    @EJB
    SystemReportManager systemReportManager;

    @PostConstruct
    public void initialization() {
        System.out.println("ReportsSingleton initialization");
        timerService.createSingleActionTimer(duration, new TimerConfig());
    }

    @Timeout
    public void timeout(Timer timer) {
        System.out.println("timeout occurred");
        System.out.println("\n" + systemReportManager.getMemoryReport());
    }

}
