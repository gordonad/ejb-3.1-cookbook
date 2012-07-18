package packt;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.transaction.UserTransaction;

public class TransactionInterceptor {

    @Resource
    private UserTransaction userTransaction;

    @AroundInvoke
    public Object verifyAccess(InvocationContext context) throws Exception {
        userTransaction.begin();
        System.out.println("Beginning transaction");
        Object result = context.proceed();
        System.out.println("Committing the transaction");
        userTransaction.commit();
        return result;
    }
}
