package model.vo;

/**
 * Created by aboge on 2017/5/18.
 */
public class photo {
    private String idinc_photo_works;
    private String authorid;
    private String photostitle;
    private String photointroduction;
    private String phototags;
    private String photoname;
    private String publishTime;
    private String username;
    private String avatar;
    private String agreenumber;
    private String commentnumber;
    private String tag;


    public photo(){
        super();
    }

    public photo(String idinc_photo_works,String authorid,String photostitle,String photointroduction,
                 String phototags,String publishTime,String username,String photoname,String agreenumber,
                 String commentnumber,String avatar,String tag){

        this.idinc_photo_works = idinc_photo_works;
        this.authorid = authorid;
        this.photostitle = photostitle;
        this.photointroduction = photointroduction;
        this.phototags = phototags;
        this.publishTime = publishTime;
        this.username = username;
        this.photoname = photoname;
        this.agreenumber = agreenumber;
        this.commentnumber = commentnumber;
        this.avatar = avatar;
        this.tag = tag;
    }

    public String getIdinc_photo_works() {
        return idinc_photo_works;
    }

    public void setIdinc_photo_works(String idinc_photo_works) {
        this.idinc_photo_works = idinc_photo_works;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getPhotostitle() {
        return photostitle;
    }

    public void setPhotostitle(String photostitle) {
        this.photostitle = photostitle;
    }

    public String getPhotointroduction() {
        return photointroduction;
    }

    public void setPhotointroduction(String photointroduction) {
        this.photointroduction = photointroduction;
    }

    public String getPhototags() {
        return phototags;
    }

    public void setPhototags(String phototags) {
        this.phototags = phototags;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoname() {
        return photoname;
    }

    public void setPhotoname(String photoname) {
        this.photoname = photoname;
    }

    public String getAgreenumber() {
        return agreenumber;
    }

    public void setAgreenumber(String agreenumber) {
        this.agreenumber = agreenumber;
    }

    public String getCommentnumber() {
        return commentnumber;
    }

    public void setCommentnumber(String commentnumber) {
        this.commentnumber = commentnumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
