package packt;

import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

@Stateful
public class VoucherManager {
    @EJB
    VoucherFacade voucherFacade;

    Voucher voucher;

    @Resource
    private SessionContext sessionContext;

    public void createVoucher(String name, String destination, BigDecimal amount) {
        voucher = new Voucher(name, destination, amount);
        voucherFacade.create(voucher);
    }

    public String getName() {
        return voucher.getName();
    }

    public String getDestination() {
        return voucher.getDestination();
    }

    public BigDecimal getAmount() {
        return voucher.getAmount();
    }

    public void submit() {
        System.out.println("Voucher submitted");
    }

    public boolean approve() {
        Principal principal = sessionContext.getCallerPrincipal();
        System.out.println("Principal: " + principal.getName());
        if("mary".equals(principal.getName())) {
            voucher.setApproved(true);
            System.out.println("approve method returned true");
            return true;
        } else {
            System.out.println("approve method returned false");
            return false;
        }
    }

//    Use for demonstrating isCallerInRole
//    public boolean approve() {
//        if(sessionContext.isCallerInRole("manager")) {
//            voucher.setApproved(true);
//            System.out.println("approve method returned true");
//           return true;
//        } else {
//            System.out.println("approve method returned false");
//            return false;
//        }
//    }


    public boolean reject() {
        voucher.setApproved(false);
        return false;
    }

}
