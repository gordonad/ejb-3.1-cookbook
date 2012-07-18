package packt;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@CountInterceptorBinding
public class CountInterceptor {
    private static int counter;

    @AroundInvoke
    public Object increment(InvocationContext context) throws Exception {
        counter++;
        System.out.println("counter: " + counter);
        Object result = context.proceed();
        return result;
    }

}
