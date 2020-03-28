package model.dao;

import model.vo.comment;
import model.vo.like;
import model.vo.user;
import util.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by aboge on 2017/5/17.
 */
public class UserDao {

    /*验证用户名和密码是否正确*/
    public boolean checkLogin(String username,String password)
    {
        boolean ret=false;
        DBManager db=new DBManager();
        String sql="select * from inc_user where username='"+username;
        sql+="' and password='"+password+"'";
        ResultSet rs=null;
        try {
            rs = db.executeQuery(sql);
            if(rs.next())
                ret=true;
            rs.close();
            db.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    /*注册*/
    public boolean insertUser(user user){
        boolean ret=false;

        DBManager  db=new DBManager();
        String sql1="insert into inc_user(username,password,email) values('";
        sql1+=user.getUsername()+"','";
        sql1+=user.getPassword()+"','";
        sql1+=user.getEmail()+"')";

        System.out.println(sql1);

        try {
            int iCount=db.executeUpdate(sql1);
            if (iCount>0)
                ret=true;
            db.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    /*通过用户名查找信息*/
    public user findByUserName(String username)
    {
        user user=new user();

        DBManager db=new DBManager();
        String sql="select * from inc_user where username='"+username+"'";
        ResultSet rs=null;
        try {
            rs = db.executeQuery(sql);
            if(rs.next()){
                user.setIdinc_user(rs.getString("idinc_user"));
                user.setUsername(username);
                user.setBirthday(rs.getString("birthday"));
                user.setIntroduction(rs.getString("introduction"));
                user.setLocation(rs.getString("location"));
                user.setAvatar(rs.getString("avatar"));
                user.setSex(rs.getString("sex"));
                user.setEmail(rs.getString("email"));
            }
            rs.close();
            db.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return user;

    }

    /*获取所有对我的评论*/
    public ArrayList<comment> queryAllComment(user user)
    {
        ArrayList<comment> list=new ArrayList<>();

        DBManager db=new DBManager();
        /*在数据库中查询评论的相关信息*/
        String sql="select p.idinc_comment,p.reviewerid,p.worksid,p.message,p.publishTime,p.type,c.username,c.avatar from inc_comment as p \n" +
                "join inc_user c on c.idinc_user=p.reviewerid where authorid="+user.getIdinc_user()+" order by p.publishTime desc";

        System.out.println(sql);

        try {
            ResultSet rs=db.executeQuery(sql);
            while(rs.next())
            {
                comment s=new comment();
                s.setIdinc_comment(rs.getString("idinc_comment"));
                s.setReviewerid(rs.getString("reviewerid"));
                s.setWorksid(rs.getString("worksid"));
                s.setMessage(rs.getString("message"));
                s.setPublishTime(rs.getString("publishTime"));
                s.setType(rs.getString("type"));
                s.setUsername(rs.getString("username"));
                s.setAvatar(rs.getString("avatar"));

                /*通过type判断点赞的作品类型，分别在文字和图片表单内查询作品title*/
                String sql2="";
                if(rs.getString("type").equals("1")) {
                    sql2 = "select articletitle from inc_article_works where idinc_article_works=" + rs.getString("worksid");
                    try {
                        ResultSet rs2 = db.executeQuery(sql2);
                        while (rs2.next()) {
                            s.setTitle(rs2.getString("articletitle"));
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }else if(rs.getString("type").equals("2")) {
                    sql2 = "select photostitle from inc_photo_works where idinc_photo_works=" + rs.getString("worksid");
                    try {
                        ResultSet rs2 = db.executeQuery(sql2);
                        while (rs2.next()) {
                            s.setTitle(rs2.getString("photostitle"));
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                System.out.println(sql2);

                list.add(s);
            }
            rs.close();
            db.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }


    /*获取所有对我的喜欢*/
    public ArrayList<like> queryAllLike(user user)
    {
        ArrayList<like> list=new ArrayList<>();

        DBManager db=new DBManager();
        /*在数据库中查询点赞的相关信息*/
        String sql="select p.idinc_agree,p.approverid,p.worksid,p.agreeTime,p.type,c.username,c.avatar from inc_agree as p " +
                "join inc_user c on c.idinc_user=p.approverid where authorsid="+user.getIdinc_user()+" order by agreeTime desc";

        System.out.println(sql);

        try {
            ResultSet rs=db.executeQuery(sql);
            while(rs.next())
            {
                like s=new like();
                s.setIdinc_agree(rs.getString("idinc_agree"));
                s.setApproverid(rs.getString("approverid"));
                s.setWorksid(rs.getString("worksid"));
                s.setAgreeTime(rs.getString("agreeTime"));
                s.setType(rs.getString("type"));
                s.setUsername(rs.getString("username"));
                s.setAvatar(rs.getString("avatar"));

                /*通过type判断点赞的作品类型，分别在文字和图片表单内查询作品title*/
                String sql2="";
                if(rs.getString("type").equals("1")) {
                    sql2 = "select articletitle from inc_article_works where idinc_article_works=" + rs.getString("worksid");
                    try {
                        ResultSet rs2 = db.executeQuery(sql2);
                        while (rs2.next()) {
                            s.setTitle(rs2.getString("articletitle"));
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }else if(rs.getString("type").equals("2")) {
                    sql2 = "select photostitle from inc_photo_works where idinc_photo_works=" + rs.getString("worksid");
                    try {
                        ResultSet rs2 = db.executeQuery(sql2);
                        while (rs2.next()) {
                            s.setTitle(rs2.getString("photostitle"));
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                System.out.println(sql2);

                list.add(s);
            }
            rs.close();
            db.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /*获取我所有的评论*/
    public ArrayList<comment> queryMyComment(user user)
    {
        ArrayList<comment> list=new ArrayList<>();

        DBManager db=new DBManager();
        /*在数据库中查询评论的相关信息*/
        String sql="select idinc_comment,worksid,message,publishTime,type from inc_comment " +
                "where reviewerid="+user.getIdinc_user()+" order by publishTime desc";

        System.out.println(sql);

        try {
            ResultSet rs=db.executeQuery(sql);
            while(rs.next())
            {
                comment s=new comment();
                s.setIdinc_comment(rs.getString("idinc_comment"));
                s.setWorksid(rs.getString("worksid"));
                s.setMessage(rs.getString("message"));
                s.setPublishTime(rs.getString("publishTime"));
                s.setType(rs.getString("type"));

                /*通过type判断点赞的作品类型，分别在文字和图片表单内查询作品title*/
                String sql2="";
                if(rs.getString("type").equals("1")) {
                    sql2 = "select articletitle from inc_article_works where idinc_article_works=" + rs.getString("worksid");
                    try {
                        ResultSet rs2 = db.executeQuery(sql2);
                        while (rs2.next()) {
                            s.setTitle(rs2.getString("articletitle"));
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }else if(rs.getString("type").equals("2")) {
                    sql2 = "select photostitle from inc_photo_works where idinc_photo_works=" + rs.getString("worksid");
                    try {
                        ResultSet rs2 = db.executeQuery(sql2);
                        while (rs2.next()) {
                            s.setTitle(rs2.getString("photostitle"));
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                System.out.println(sql2);

                list.add(s);
            }
            rs.close();
            db.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /*删除一条我的评论*/
    public boolean deleteComment(String commentId,user user){
        boolean ret=false;
        DBManager db=new DBManager();
        String sql1="delete from inc_comment where idinc_comment="+Integer.parseInt(commentId)+" and reviewerid="+user.getIdinc_user();

        System.out.println(sql1);

        try {
            int iCount=db.executeUpdate(sql1);
            if (iCount>0)
                ret=true;
            db.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }


    /*获取我所有的喜欢*/
    public ArrayList<like> queryMyLike(user user)
    {
        ArrayList<like> list=new ArrayList<>();

        DBManager db=new DBManager();
        /*在数据库中查询点赞的相关信息*/
        String sql="select idinc_agree,worksid,agreeTime,type from inc_agree where approverid="+user.getIdinc_user()+" order by agreeTime desc";

        System.out.println(sql);

        try {
            ResultSet rs=db.executeQuery(sql);
            while(rs.next())
            {
                like s=new like();
                s.setIdinc_agree(rs.getString("idinc_agree"));
                s.setWorksid(rs.getString("worksid"));
                s.setAgreeTime(rs.getString("agreeTime"));
                s.setType(rs.getString("type"));

                /*通过type判断点赞的作品类型，分别在文字和图片表单内查询作品title*/
                String sql2="";
                if(rs.getString("type").equals("1")) {
                    sql2 = "select articletitle from inc_article_works where idinc_article_works=" + rs.getString("worksid");
                    try {
                        ResultSet rs2 = db.executeQuery(sql2);
                        while (rs2.next()) {
                            s.setTitle(rs2.getString("articletitle"));
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }else if(rs.getString("type").equals("2")) {
                    sql2 = "select photostitle from inc_photo_works where idinc_photo_works=" + rs.getString("worksid");
                    try {
                        ResultSet rs2 = db.executeQuery(sql2);
                        while (rs2.next()) {
                            s.setTitle(rs2.getString("photostitle"));
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                System.out.println(sql2);

                list.add(s);
            }
            rs.close();
            db.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /*删除一条我的喜欢*/
    public boolean deleteAgree(String agreeId,user user){
        boolean ret=false;
        DBManager db=new DBManager();
        String sql1="delete from inc_agree where idinc_agree="+Integer.parseInt(agreeId)+" and approverid="+user.getIdinc_user();

        System.out.println(sql1);

        try {
            int iCount=db.executeUpdate(sql1);
            if (iCount>0)
                ret=true;
            db.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    /*更新用户信息*/
    public boolean updatesUser(user user){
        boolean ret=false;
        DBManager  db=new DBManager();
        String sql="update inc_user set ";
        sql+="email='"+user.getEmail()+"',";
        sql+="sex='"+user.getSex()+"',";
        sql+="location='"+user.getLocation()+"',";
        sql+="birthday='"+user.getBirthday()+"',";
        sql+="introduction='"+user.getIntroduction()+"'";
        sql+=" where idinc_user='"+user.getIdinc_user()+"'";

        System.out.println(sql);

        try {
            int iCount=db.executeUpdate(sql);
            if (iCount>0)
                ret=true;
            db.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;

    }

}

