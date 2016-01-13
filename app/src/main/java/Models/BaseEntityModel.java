package Models;

import java.util.Date;

/**
 * Created by Hano on 20/12/2015.
 */
public class BaseEntityModel {
    private int Version;
    private Date InsDate;
    private String InsUser;
    private Date UpdDate;
    private String UpdUser;

    public int getVersion() {
        return Version;
    }

    public void setVersion(int version) {
        Version = version;
    }

    public Date getInsDate() {
        return InsDate;
    }

    public void setInsDate(Date insDate) {
        InsDate = insDate;
    }

    public String getInsUser() {
        return InsUser;
    }

    public void setInsUser(String insUser) {
        InsUser = insUser;
    }

    public Date getUpdDate() {
        return UpdDate;
    }

    public void setUpdDate(Date updDate) {
        UpdDate = updDate;
    }

    public String getUpdUser() {
        return UpdUser;
    }

    public void setUpdUser(String updUser) {
        UpdUser = updUser;
    }
}
