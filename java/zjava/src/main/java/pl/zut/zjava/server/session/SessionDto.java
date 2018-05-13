package pl.zut.zjava.server.session;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class SessionDto implements Serializable {

    private final String sid;

    private final Date validDate;

    private final Date createDate;

    public SessionDto() {
        sid = UUID.randomUUID().toString().replace("-","");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 2);

        validDate = cal.getTime();
        createDate = new Date();
    }

    public Boolean isValid() {
        return validDate.after(new Date());
    }

    @Override
    public String toString() {
        return "SessionDto{" +
                "sid='" + sid + '\'' +
                ", validDate=" + validDate +
                ", createDate=" + createDate +
                '}';
    }

    public String getSid() {
        return sid;
    }

    public Date getValidDate() {
        return validDate;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
