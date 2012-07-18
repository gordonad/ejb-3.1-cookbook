package packt;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;

@Stateful
@LocalBean
public class NamesBean {
    
    private List<String> names;

    @PostConstruct
    private void initialize() {
        names = new ArrayList<String>();
    }

    public void addName(String name) {
        names.add(name);
    }

    public List<String> getNames() {
        return names;
    }

    @PrePassivate
    private void prepareForPassivation() {
        // Perform prepassivation tasks
    }

    @PostActivate
    private void restoreFromPassivation() {
        // Restore stateful EJB
    }
}
