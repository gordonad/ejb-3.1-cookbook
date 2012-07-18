package packt;

import java.security.Principal;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import javax.ejb.SessionContext;

@Stateless
@DeclareRoles("manager")
@RunAs("manager")
public class VoucherVerification {
    @Resource
    private SessionContext sessionContext;

    public void submit() {
        Principal principal = sessionContext.getCallerPrincipal();
        System.out.println("Principal: " + principal.getName());
        // Perform verification checks

    }
}

