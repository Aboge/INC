package model.vo;

/**
 * Created by aboge on 2017/5/17.
 */
public class article {
    private String idinc_article_works;
    private String authorid;
    private String artitcletitle;
    private String content;
    private String articletags;
    private String publishTime;
    private String username;
    private String avatar;
    private String agreenumber;
    private String commentnumber;
    private String tag;


    public article(){
        super();
    }

    public article(String idinc_article_works, String authorid, String articletags, String content, String artitcletitle,
                   String publishTime,String username,String avatar,String agreenumber,String commentnumber,String tag){

        this.idinc_article_works = idinc_article_works;
        this.authorid = authorid;
        this.articletags = articletags;
        this.content = content;
        this.artitcletitle = artitcletitle;
        this.publishTime = publishTime;
        this.username = username;
        this.avatar = avatar;
        this.agreenumber = agreenumber;
        this.commentnumber = commentnumber;
        this.tag = tag;
    }

    public String getIdinc_article_works() {
        return idinc_article_works;
    }

    public void setIdinc_article_works(String idinc_article_works) {
        this.idinc_article_works = idinc_article_works;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getArtitcletitle() {
        return artitcletitle;
    }

    public void setArtitcletitle(String artitcletitle) {
        this.artitcletitle = artitcletitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticletags() {
        return articletags;
    }

    public void setArticletags(String articletags) {
        this.articletags = articletags;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
