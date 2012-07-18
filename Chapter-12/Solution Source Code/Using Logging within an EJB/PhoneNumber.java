package packt;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class PhoneNumber {
    private Logger logger;

    public PhoneNumber() {
        logger = Logger.getLogger("phonenumber");
        logger.setLevel(Level.ALL);
        logger.log(Level.INFO, "Phone number logger created");
    }

    public String format(int areaCode, int prefix, int lineNumber) {
        try {
            validateAreaCode(areaCode);
            validatePrefix(prefix);
            validateLineNumber(lineNumber);
            logger.log(Level.FINEST, "Formatted phone number returned");
            return "(" + areaCode + ")" + prefix + "-" + lineNumber;
        }
        catch(InvalidAreaCodeException e) {
            logger.log(Level.FINE, "InvalidAreaCodeException");
        }
        catch(InvalidPrefixException e) {
            logger.log(Level.FINE, "InvalidPrefixException");
        }
        catch(InvalidLineNumberException e) {
            logger.log(Level.FINE, "InvalidLineNumberException");
        }
        return "";
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
