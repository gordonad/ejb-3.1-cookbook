package packt;

import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.interceptor.Interceptors;

@Singleton
@LocalBean
@Interceptors(SimpleInterceptor.class)
public class SingletonExample {
    
    public String getName() {
        System.out.println("Invoking getName");
        return "name";
    }
 
}
