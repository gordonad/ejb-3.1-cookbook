package packt;

import java.math.BigDecimal;
import java.security.Principal;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

@Stateful
@DeclareRoles ("manager") 
@RolesAllowed("manager")
public class VoucherManager {
    @EJB
    VoucherFacade voucherFacade;

    Voucher voucher;

    @EJB
    VoucherVerification voucherVerification;

    @Resource
    private SessionContext sessionContext;

    @PermitAll
    public void createVoucher(String name, String destination, BigDecimal amount) {
        voucher = new Voucher(name, destination, amount);
        voucherFacade.create(voucher);
    }

    @PermitAll
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

    //@RolesAllowed("manager")
    public boolean approve() {
        if(sessionContext.isCallerInRole("manager")) {
            voucher.setApproved(true);
            System.out.println("approve method returned true");
            return true;
        } else {
            System.out.println("approve method returned false");
            return false;
        }
    }

    public boolean validateAllowance(BigDecimal allowance) {
        if(sessionContext.isCallerInRole("manager")) {
            if(allowance.compareTo(BigDecimal.valueOf(2500)) <= 0) {
                return true;
            } else {
                return false;
            }
        } else  if(sessionContext.isCallerInRole("employee")) {
            if(allowance.compareTo(BigDecimal.valueOf(1500)) <= 0) {
                return true;
            } else {
                return false;
            }
        } else  if(sessionContext.isCallerInRole("auditor")) {
            if(allowance.compareTo(BigDecimal.valueOf(1000)) <= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

//    //@RolesAllowed("manager")
//    public boolean approve() {
////        System.out.println("Name: " + getName());
//        Principal principal = sessionContext.getCallerPrincipal();
//        System.out.println("Principal: " + principal.getName());
//        if("mary".equals(principal.getName())) {
//            voucher.setApproved(true);
//            System.out.println("approve method returned true");
//            return true;
//        } else {
//            System.out.println("approve method returned false");
//            return false;
//        }
//    }

    @RolesAllowed("manager")
    public boolean reject() {
        voucher.setApproved(false);
        return false;
    }
}
