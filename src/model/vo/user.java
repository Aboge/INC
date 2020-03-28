package model.vo;

/**
 * Created by aboge on 2017/5/17.
 */
public class user {
    private String idinc_user;
    private String username;
    private String password;
    private String birthday;
    private String introduction;
    private String location;
    private String avatar;
    private String email;
    private String sex;

    public user(){
        super();
    }

    public user(String username, String password, String idinc_user, String birthday,
                String introduction, String location, String  avatar, String sex,String email){
        super();
        this.password = password;
        this.idinc_user = idinc_user;
        this.username = username;

        this.birthday = birthday;
        this.introduction = introduction;
        this.location = location;
        this.avatar = avatar;
        this.sex = sex;
        this.email = email;
    }

    public String getIdinc_user() {
        return idinc_user;
    }

    public void setIdinc_user(String idinc_user) {
        this.idinc_user = idinc_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


