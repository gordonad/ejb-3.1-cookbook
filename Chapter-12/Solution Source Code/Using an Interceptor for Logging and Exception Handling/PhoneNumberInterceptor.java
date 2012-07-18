package packt;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class PhoneNumberInterceptor {

    @AroundInvoke
    public Object validatePhoneNumer(InvocationContext context) throws Exception {
        Object result="";
        Object parameters[] = context.getParameters();
        int areaCode = new Integer(parameters[0].toString()).intValue();
        int prefix = new Integer(parameters[1].toString()).intValue();
        int lineNumber = new Integer(parameters[2].toString()).intValue();
        Logger logger = ((PhoneNumber) context.getTarget()).logger;
        try {
            validateAreaCode(areaCode);
            validatePrefix(prefix);
            validateLineNumber(lineNumber);
            logger.log(Level.FINEST, areaCode + " - Formatted phone number returned");
            result = context.proceed();
        } catch (InvalidAreaCodeException e) {
            logger.log(Level.FINE, "InvalidAreaCodeException");
        } catch (InvalidPrefixException e) {
            logger.log(Level.FINE, "InvalidPrefixException");
        } catch (InvalidLineNumberException e) {
            logger.log(Level.FINE, "InvalidLineNumberException");
        }
        return result; 
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
