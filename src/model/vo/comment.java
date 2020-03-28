package model.vo;

/**
 * Created by aboge on 2017/5/19.
 */
public class comment {
    private String idinc_comment;
    private String reviewerid;
    private String authorid;
    private String worksid;
    private String title;
    private String message;
    private String publishTime;
    private String type;
    private String username;
    private String avatar;


    public comment(){
        super();
    }

    public comment(String idinc_comment,String reviewerid,String authorid,String worksid,
                   String message,String publishTime,String type,String username,String avatar,
                   String title){
        this.idinc_comment = idinc_comment;
        this.reviewerid = reviewerid;
        this.authorid = authorid;
        this.worksid = worksid;
        this.message = message;
        this.publishTime = publishTime;
        this.type = type;
        this.username = username;
        this.avatar = avatar;
        this.title = title;
    }


    public String getIdinc_comment() {
        return idinc_comment;
    }

    public void setIdinc_comment(String idinc_comment) {
        this.idinc_comment = idinc_comment;
    }

    public String getReviewerid() {
        return reviewerid;
    }

    public void setReviewerid(String reviewerid) {
        this.reviewerid = reviewerid;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getWorksid() {
        return worksid;
    }

    public void setWorksid(String worksid) {
        this.worksid = worksid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
