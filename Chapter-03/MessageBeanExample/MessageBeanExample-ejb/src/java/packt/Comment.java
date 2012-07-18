package packt;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private boolean pro;
    private String comment;
    private Date date;

    public Comment(boolean pro, String comment, Date date) {
        this.pro = pro;
        this.comment = comment;
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPro() {
        return pro;
    }

    public void setPro(boolean pro) {
        this.pro = pro;
    }

    
}
