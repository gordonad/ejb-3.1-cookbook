package packt;

import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class PrintBean {

    @Asynchronous
    public void printAndForget() {
        System.out.println("*** printAndForget ***");
    }
 
    @Asynchronous
    public Future<String> printAndCheckLater() {
        System.out.println("*** printAndCheckLater ***");
        return new AsyncResult<String>("OK");
    }

}
