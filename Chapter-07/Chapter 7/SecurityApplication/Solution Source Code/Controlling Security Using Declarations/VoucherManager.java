package packt;

import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

@Stateful
@ DeclareRoles ({"employee", "manager"})
// Add for @PermitAll demonstration
//@DeclareRoles("manager")
//@RolesAllowed("manager")

public class VoucherManager {
    @EJB
    VoucherFacade voucherFacade;

    Voucher voucher;

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
    }

    @RolesAllowed("manager")
    public boolean approve() {
        voucher.setApproved(true);
        return true;
    }

    @RolesAllowed("manager")
    public boolean reject() {
        voucher.setApproved(false);
        return false;
    }

}
