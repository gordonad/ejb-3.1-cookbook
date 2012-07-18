package packt;

import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

@Stateful
@ DeclareRoles ({"employee", "manager"})
public class VoucherManager {
    @EJB
    VoucherFacade voucherFacade;

    Voucher voucher;

    @EJB
    VoucherVerification voucherVerification;

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

    @RolesAllowed("employee")
    public void submit() {
        System.out.println("Voucher submitted");
        voucherVerification.submit();
    }

    public boolean approve() {
        voucher.setApproved(true);
        return true;
    }

    public boolean reject() {
        voucher.setApproved(false);
        return false;
    }

}
