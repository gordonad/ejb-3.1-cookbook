package packt;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class PhoneNumberFormatter extends Formatter {

    public PhoneNumberFormatter() {
        super();
    }

    @Override
    public String format(LogRecord record) {
        String message = record.getMessage();
        Calendar calendar = new GregorianCalendar();
                DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);

        message = dateFormat.format(new Date().getTime()) + " - [" + message + "]";
        return message;
    }

}
