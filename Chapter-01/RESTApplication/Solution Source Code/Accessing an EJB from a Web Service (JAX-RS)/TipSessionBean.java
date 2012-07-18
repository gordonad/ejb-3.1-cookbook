package tip;

import javax.ejb.Stateless;

@Stateless
public class TipSessionBean {
    private final static String tips[] = {
        "I hear and I forget. I see and I remember. I do and I understand",
        "Study the past if you would define the future",
        "Attack life, it’s going to kill you anyway"};

    public String getTip() {
        return tips[(int)(Math.random()*tips.length)];
    }
 
}
