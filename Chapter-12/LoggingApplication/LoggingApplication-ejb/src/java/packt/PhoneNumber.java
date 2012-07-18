package packt;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.interceptor.Interceptors;

@Stateless
@LocalBean
public class PhoneNumber {

    public static final Logger logger = Logger.getLogger("phonenumber");

    public PhoneNumber() {
//        logger.setFilter(new PhoneNumberFilter());
        logger.setLevel(Level.ALL);
        logger.log(Level.INFO, "Phone number logger created");
//        logger.setUseParentHandlers(false);
    }

//    @Interceptors(PhoneNumberInterceptor.class)
    @Interceptors({PhoneNumberInterceptor.class, CountInterceptor.class})
    public String format(int areaCode, int prefix, int lineNumber) {
        return "(" + areaCode + ")" + prefix + "-" + lineNumber;
    }

    private boolean validateAreaCode(int areaCode) throws InvalidAreaCodeException {
        if (areaCode < 0 || areaCode > 999) {
            throw new InvalidAreaCodeException();
        }
        return true;
    }

    private boolean validatePrefix(int prefix) throws InvalidPrefixException {
        if (prefix < 0 || prefix > 999) {
            throw new InvalidPrefixException();
        }
        return true;
    }

    private boolean validateLineNumber(int lineNumber) throws InvalidLineNumberException {
        if (lineNumber < 0 || lineNumber > 9999) {
            throw new InvalidLineNumberException();
        }
        return true;
    }
}
