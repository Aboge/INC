package model.dao;

import model.vo.comment;
import model.vo.photo;
import model.vo.user;
import util.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aboge on 2017/5/18.
 */
public class PhotoDao {

    /*向数据库请求photo信息*/
    public ArrayList<photo> queryHotPhoto(String type,user user){
        String where="";
        if(type==null){
            where="GROUP BY p.idinc_photo_works ";
        }else if(type.equals("hottest")){
            where="GROUP BY p.idinc_photo_works order by agreenumber desc";
        }else if(type.equals("latest")){
            where="GROUP BY p.idinc_photo_works ORDER BY p.publishTime desc";
        }else{
            where="where phototags="+type+" GROUP BY p.idinc_photo_works";
        }

        ArrayList<photo> list=new ArrayList<>();

        DBManager db=new DBManager();
        String sql = "select p.idinc_photo_works,p.authorid,p.photostitle,p.publishTime,p.phototags,p.photointroduction,p.photoname,\n" +
                "c.username,c.avatar,count(d.worksid) as agreenumber from inc_photo_works as p join inc_user c on c.idinc_user=p.authorid left join inc_agree d \n" +
                "on d.worksid=p.idinc_photo_works and d.type=2 "+where;

        System.out.println(sql);

        try {
            ResultSet rs=db.executeQuery(sql);
            while(rs.next())
            {
                photo s=new photo();
                s.setIdinc_photo_works(rs.getString("idinc_photo_works"));
                s.setAuthorid(rs.getString("authorid"));
                s.setPhotostitle(rs.getString("photostitle"));
                s.setPhotointroduction(rs.getString("photointroduction"));
                s.setPhototags(rs.getString("phototags"));
                s.setPublishTime(rs.getString("publishTime"));
                s.setUsername(rs.getString("username"));
                s.setAvatar(rs.getString("avatar"));
                s.setPhotoname(rs.getString("photoname"));
                s.setAgreenumber(rs.getString("agreeNumber"));
                String sql2="select count(worksid) as commentNumber from inc_comment where type=2 and worksid="+rs.getString("idinc_photo_works");

                try {
                    ResultSet rs2 = db.executeQuery(sql2);
                    while (rs2.next()) {
                        s.setCommentnumber(rs2.getString("commentNumber"));
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }

                String sql3="select * from inc_agree where type=2 and worksid="+rs.getString("idinc_photo_works")+" and approverid="+user.getIdinc_user();

                try {
                    ResultSet rs3 = db.executeQuery(sql3);
                    System.out.println(sql3);
                    rs3.last();
                    int rowCount = rs3.getRow(); //获得ResultSet的总行数
                    s.setTag(Integer.toString(rowCount));
                    System.out.println(rowCount);
                }catch(SQLException e){
                    e.printStackTrace();
                }
                list.add(s);
            }
            rs.close();
            db.close();
        }catch (SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /*通过photoId获取inc_photo信息*/
    public photo queryPhotoById(String photoId){
        photo photo=new photo();

        DBManager db=new DBManager();
        String sql="select * from inc_photo_works where idinc_photo_works="+Integer.parseInt(photoId);
        System.out.println(sql);
        ResultSet rs=null;
        try {
            rs = db.executeQuery(sql);
            if(rs.next()){
                photo.setIdinc_photo_works(rs.getString("idinc_photo_works"));
                photo.setAuthorid(rs.getString("authorid"));
            }
            rs.close();
            db.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return photo;

    }

    /*增加一条点赞*/
    public boolean insertAgree(String photoId,user user){
        boolean ret=false;
        Date now = new Date();

        photo photo=queryPhotoById(photoId);

        DBManager db=new DBManager();
        String sql1="insert into inc_agree(worksid,authorsid,approverid,agreeTime,type) values(";
        sql1+= photo.getIdinc_photo_works()+",";
        sql1+=photo.getAuthorid()+",";
        sql1+=user.getIdinc_user()+",'";
        sql1+=now.getTime()+"',";
        sql1+="2)";

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

    /*删除一条点赞*/
    public boolean deleteAgree(String photoId,user user){
        boolean ret=false;
        DBManager db=new DBManager();
        String sql1="delete from inc_agree where type=2 and worksid="+Integer.parseInt(photoId)+" and approverid="+user.getIdinc_user();

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

    /*获取我的图片信息*/

    /*向数据库请求photo信息*/
    public ArrayList<photo> queryMyPhoto(user user){

        ArrayList<photo> list=new ArrayList<>();

        DBManager db=new DBManager();
        String sql = "select idinc_photo_works,photostitle,photointroduction,photoname " +
                "from inc_photo_works where authorid="+user.getIdinc_user();

        System.out.println(sql);

        try {
            ResultSet rs=db.executeQuery(sql);
            while(rs.next())
            {
                photo s=new photo();
                s.setIdinc_photo_works(rs.getString("idinc_photo_works"));
                s.setPhotostitle(rs.getString("photostitle"));
                s.setPhotointroduction(rs.getString("photointroduction"));
                s.setPhotoname(rs.getString("photoname"));

                list.add(s);
            }
            rs.close();
            db.close();
        }catch (SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /*图片详细展示页面通过图博查找信息*/
    public photo findByPhotoId(String photoId)
    {
        System.out.println(photoId);

        photo photo=new photo();

        DBManager db=new DBManager();
        String sql="select p.idinc_photo_works,p.authorid,p.photostitle,p.publishTime,p.phototags,p.photointroduction,p.photoname," +
                "c.username,c.avatar,count(d.worksid) as agreenumber from inc_photo_works as p join inc_user c on c.idinc_user=p.authorid left join inc_agree d " +
                "on d.worksid=p.idinc_photo_works and type=2 where p.idinc_photo_works="+photoId+" GROUP BY p.idinc_photo_works";
        ResultSet rs=null;

        System.out.println(sql);

        try {
            rs = db.executeQuery(sql);
            if(rs.next()){
                photo.setIdinc_photo_works(rs.getString("idinc_photo_works"));
                photo.setAuthorid(rs.getString("authorid"));
                photo.setPhotostitle(rs.getString("photostitle"));
                photo.setPhotointroduction(rs.getString("photointroduction"));
                photo.setPublishTime(rs.getString("publishTime"));
                photo.setPhototags(rs.getString("phototags"));
                photo.setPhotoname(rs.getString("photoname"));
                photo.setUsername(rs.getString("username"));
                photo.setAvatar(rs.getString("avatar"));
                photo.setAgreenumber(rs.getString("agreenumber"));
            }
            rs.close();
            db.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return photo;

    }

    /*图博详细展示页面的评论显示信息*/
    public List<comment> getAllPhotoCommentsById(String photoId){
        ArrayList<comment> list=new ArrayList<>();

        DBManager db=new DBManager();
        /*在数据库中查询评论的相关信息*/
        String sql="select p.idinc_comment,p.reviewerid,p.worksid,p.message,p.publishTime,p.type,c.username,c.avatar from inc_comment as p \n" +
                "join inc_user c on c.idinc_user=p.reviewerid where type = 2 and worksid="+photoId+" order by p.publishTime desc";

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

    /*文章详细展示页面的添加评论*/
    public boolean addCommentByPhoto(comment comment){
        DBManager db = new DBManager();
        String sql = "insert into inc_comment(reviewerid,authorid,worksid,message,publishTime,type) values(";
        sql+= comment.getReviewerid()+",";
        sql+= comment.getAuthorid()+",";
        sql+= comment.getWorksid()+",'";
        sql+= comment.getMessage()+"',";
        sql+= comment.getPublishTime()+",";
        sql+= comment.getType()+")";

        System.out.println(sql);

        boolean ret = false;
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

    /*发布新的图博*/
    public boolean photo_publich(photo photo){
        boolean ret=false;

        DBManager  db=new DBManager();
        String sql1="insert into inc_photo_works(authorId,photosTitle,photoIntroduction,publishTime,photoTags,photoname) values('";
        sql1+=photo.getAuthorid()+"','";
        sql1+=photo.getPhotostitle()+"','";
        sql1+=photo.getPhotointroduction()+"','";
        sql1+=photo.getPublishTime()+"','";
        sql1+=photo.getPhototags()+"','";
        sql1+=photo.getPhotoname()+"')";

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
}
