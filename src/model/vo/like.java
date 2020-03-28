package model.vo;

/**
 * Created by aboge on 2017/5/19.
 */
public class like {
    private String idinc_agree;
    private String worksid;
    private String title;
    private String authorsid;
    private String approverid;
    private String agreeTime;
    private String type;
    private String username;
    private String avatar;

    public like(){
        super();
    }

    public like(String idinc_agree,String worksid,String title,String authorsid,String approverid,
                String agreeTime,String type,String username,String avatar){
        this.idinc_agree = idinc_agree;
        this.worksid = worksid;
        this.title = title;
        this.authorsid = authorsid;
        this.approverid = approverid;
        this.agreeTime = agreeTime;
        this.type = type;
        this.username = username;
        this.avatar = avatar;
    }

    public String getIdinc_agree() {
        return idinc_agree;
    }

    public void setIdinc_agree(String idinc_agree) {
        this.idinc_agree = idinc_agree;
    }

    public String getWorksid() {
        return worksid;
    }

    public void setWorksid(String worksid) {
        this.worksid = worksid;
    }

    public String getAuthorsid() {
        return authorsid;
    }

    public void setAuthorsid(String authorsid) {
        this.authorsid = authorsid;
    }

    public String getApproverid() {
        return approverid;
    }

    public void setApproverid(String approverid) {
        this.approverid = approverid;
    }

    public String getAgreeTime() {
        return agreeTime;
    }

    public void setAgreeTime(String agreeTime) {
        this.agreeTime = agreeTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
