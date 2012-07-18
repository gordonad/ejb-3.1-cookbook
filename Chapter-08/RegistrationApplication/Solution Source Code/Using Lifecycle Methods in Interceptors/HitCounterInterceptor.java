package packt;

import java.util.Map;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class HitCounterInterceptor {

    ApplicationStatistics applicationStatistics;

    @AroundInvoke
    public Object incrementCounter(InvocationContext context) throws Exception {
        System.out.println("HitCounterInterceptor");

        applicationStatistics = ApplicationStatistics.getInstance();
        applicationStatistics.increment();

        Map<String,Object> data = context.getContextData();
        data.put("count", applicationStatistics.getCount());

        Object result = context.proceed();
        return result;
    }
}
