package packt;

import java.util.Map;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class TimeInMethodInterceptor {

    ApplicationStatistics applicationStatistics;

    @AroundInvoke
    public Object recordTime(InvocationContext context) throws Exception {
        System.out.println("TimeInMethodInterceptor");

        Map<String,Object> data = context.getContextData();
        System.out.println("ContextData count: " + data.get("count"));

        applicationStatistics = ApplicationStatistics.getInstance();
        long startTime = System.currentTimeMillis();
        Object result = context.proceed();
        long endTime = System.currentTimeMillis();
        applicationStatistics.increaseTotalTime(endTime-startTime);
        return result;
    }
}

