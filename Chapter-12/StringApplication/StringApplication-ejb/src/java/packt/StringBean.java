package packt;

import java.lang.StringBuilder;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class StringBean {

    public String getList() {
        String names[] = {"Bill", "Sue", "Mary", "Fred"};
//        String list = "";
//        for(int i=0; i<names.length; i++) {
//            list += names[i];
//            if(i < names.length-1) {
//                list += ", ";
//            }
//        }
        StringBuilder list = new StringBuilder(100);
        for (String name : names) {
            if (list.length() > 0) {
                list.append(", ");
            }
            list.append(name);
        }

        //doTime();
        doBigDecimal();

        return list.toString();
    }

    private void doBigDecimal() {
        BigDecimal amount = BigDecimal.ZERO;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
//        amount = new BigDecimal(45.35);
//        System.out.println(amount);
//        amount.setScale(0, RoundingMode.HALF_UP);
//        System.out.println(amount);

//        amount.setScale(2,BigDecimal.ROUND_HALF_EVEN);
//        System.out.println("amount Unformatted: " + amount.toString());
        amount = amount.add(new BigDecimal(1045.32));
        //        amount = new BigDecimal(1045.32);
        System.out.println("amount Unformatted: " + amount.toString());
        System.out.println("Scale of amount: " + amount.scale());
        System.out.println("amount Unformatted: " + amount.toString());
        System.out.println();
        BigDecimal amount2;
        amount2 = new BigDecimal("1045.321");
        System.out.println("amount2 Unformatted: " + amount2.toString());
        System.out.println("amount2 formatted: " + numberFormat.format(amount2));
        System.out.println("Scale of amount: " + amount2.scale());
        System.out.println();

        BigDecimal number1 = new BigDecimal("32.54");
        BigDecimal number2 = new BigDecimal("8.44");
        BigDecimal total = BigDecimal.ZERO;
        number1.add(number2);
//        number1 = number1.add(number2);
        System.out.println(numberFormat.format(number1));
        System.out.println();

        number1 = new BigDecimal("1.00");
        number2 = new BigDecimal("1.000");
        System.out.println(number1.equals(number2));
        System.out.println(number1.compareTo(number2)==0);
        System.out.println();

        number1 = new BigDecimal("0.134");
        number2 = new BigDecimal("0.133");
//        System.out.println(number1.add(number2).round(new MathContext(2,RoundingMode.HALF_UP)));
//        System.out.println();

        BigDecimal number3 = number1.round(new MathContext(2,RoundingMode.HALF_UP));
        BigDecimal number4 = number2.round(new MathContext(2,RoundingMode.HALF_UP));
//        System.out.println(number1);
//        System.out.println(number3);
//        number2.round(new MathContext(2,RoundingMode.HALF_UP));
        System.out.println(number1.add(number2).round(new MathContext(2,RoundingMode.HALF_UP)));
        System.out.println(number3.add(number4).round(new MathContext(2,RoundingMode.HALF_UP)));
        System.out.println();

        BigDecimal taxRate = new BigDecimal(0.0049);
        BigDecimal d2 = amount.multiply(taxRate);
        System.out.println("Unformatted: " + d2.toString());

        double money = d2.doubleValue();
        String s = numberFormat.format(money);
        System.out.println("Formatted:  " + s);
    }

    private void doTime() {
        Date today = new Date();
        System.out.println(today);
        Date tomorrow  = new Date(today.getTime() + 24L * 3600L * 1000L);
        System.out.println(tomorrow);

        Calendar day = Calendar.getInstance();
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Zurich");

        day.setTime(new Date());
        day.setTimeZone(timeZone);
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        System.out.println(dateFormat.format(day.getTime()));
        dateFormat.setTimeZone(timeZone);
        System.out.println(dateFormat.format(day.getTime()));
    }

}
