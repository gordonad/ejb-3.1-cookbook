package packt;

import java.lang.annotation.Annotation;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ValidationInterceptor {
    @AroundInvoke
    public Object validateParameters(InvocationContext context) throws Exception{
        System.out.println("ValidationInterceptor");

        Annotation annotations[] = context.getMethod().getAnnotations();
        for(Annotation annotation: annotations) {
            System.out.println(annotation);
        }
        
        Object parameters[] = context.getParameters();
        for(int i=0; i<parameters.length; i++) {
            System.out.println("Before: ["+(String)parameters[i] + "]");
            parameters[i] = ((String)parameters[i]).trim();
            System.out.println("After: ["+(String)parameters[i]+"]");
        }
        context.setParameters(parameters);

        Object result =  context.proceed();
        return result;
    }
}

