package packt;

import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class PrintBean {
//    private int printID;
//    private String text;
//    private String printer;
//
//    public void setPrintID(int printID) {
//        this.printID = printID;
//    }
//
//    public void setPrinter(String printer) {
//        this.printer = printer;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }

    @Asynchronous
    public void printAndForget() {
        // Print
        System.out.println("*** printAndForget ***");
    }
 
    @Asynchronous
    public Future<String> printAndCheckLater() {
        // Print
        System.out.println("*** printAndCheckLater ***");
        return new AsyncResult<String>("OK");
    }

}
