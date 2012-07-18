package packt;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class PhoneNumber {
    Logger logger;

    public PhoneNumber() {
        logger = Logger.getLogger("phonenumber");
        logger.setLevel(Level.ALL);
        logger.log(Level.INFO, "Phone number logger created");
    }

    @Interceptors(PhoneNumberInterceptor.class)
    public String format(int areaCode, int prefix, int lineNumber) {
        return "(" + areaCode + ")" + prefix + "-" + lineNumber;
    }

}
