package model.dao;

import model.vo.article;
import model.vo.comment;
import model.vo.user;
import util.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by aboge on 2017/5/17.
 */
public class ArticleDao {

    /*向数据库请求article信息*/
    public ArrayList<article> queryArticle(String w)
    {
        ArrayList<article> list=new ArrayList<>();

        String where=w==null?" limit 1":w;
        DBManager db=new DBManager();
        String sql="select p.idinc_article_works,p.authorid,p.articletitle,p.publishTime,p.articletags,p.content,c.username,c.avatar from inc_article_works as p join inc_user c on c.idinc_user=p.authorid"+where;

        System.out.println(sql);

        try {
            ResultSet rs=db.executeQuery(sql);
            while(rs.next())
            {
                article s=new article();
                s.setIdinc_article_works(rs.getString("idinc_article_works"));
                s.setAuthorid(rs.getString("authorid"));
                s.setArtitcletitle(rs.getString("articletitle"));
                s.setContent(rs.getString("content"));
                s.setArticletags(rs.getString("articletags"));
                s.setPublishTime(rs.getString("publishTime"));
                s.setUsername(rs.getString("username"));
                s.setAvatar(rs.getString("avatar"));

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

    /*获取最热文章信息*/
    public ArrayList<article> queryHotArticle(String type,user user)
    {
        String where="";
        if(type==null){
            where="GROUP BY p.idinc_article_works order by agreeNumber desc limit 5";
        }else{
            where="where articletags="+type+" GROUP BY p.idinc_article_works order by publishTime desc";
        }

        ArrayList<article> list=new ArrayList<>();

        DBManager db=new DBManager();
        String sql="select p.idinc_article_works,p.authorid,p.articletitle,p.publishTime," +
                "p.articletags,p.content,c.username,c.avatar,count(d.worksid) as agreeNumber " +
                "from inc_article_works as p join inc_user c on c.idinc_user=p.authorid left join " +
                "inc_agree d on d.worksid=p.idinc_article_works and d.type=1 "+where;

        System.out.println(sql);

        try {
            ResultSet rs=db.executeQuery(sql);
            while(rs.next())
            {
                article s=new article();
                s.setIdinc_article_works(rs.getString("idinc_article_works"));
                s.setAuthorid(rs.getString("authorid"));
                s.setArtitcletitle(rs.getString("articletitle"));
                s.setContent(rs.getString("content"));
                s.setArticletags(rs.getString("articletags"));
                s.setPublishTime(rs.getString("publishTime"));
                s.setUsername(rs.getString("username"));
                s.setAvatar(rs.getString("avatar"));
                s.setAgreenumber(rs.getString("agreeNumber"));

                String sql2="select count(worksid) as commentNumber from inc_comment where worksid="+rs.getString("idinc_article_works");

                try {
                    ResultSet rs2 = db.executeQuery(sql2);
                    while (rs2.next()) {
                        s.setCommentnumber(rs2.getString("commentNumber"));
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }

                String sql3="select * from inc_agree where type=1 and worksid="+rs.getString("idinc_article_works")+" and approverid="+user.getIdinc_user();

                try {
                    ResultSet rs3 = db.executeQuery(sql3);
                    rs3.last();
                    int rowCount = rs3.getRow(); //获得ResultSet的总行数
                    s.setTag(Integer.toString(rowCount));
                }catch(SQLException e){
                    e.printStackTrace();
                }
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

    public article queryArticelById(String articleId){
        article article=new article();

        DBManager db=new DBManager();
        String sql="select * from inc_article_works where idinc_article_works="+Integer.parseInt(articleId);
        System.out.println(sql);
        ResultSet rs=null;
        try {
            rs = db.executeQuery(sql);
            if(rs.next()){
                article.setIdinc_article_works(rs.getString("idinc_article_works"));
                article.setAuthorid(rs.getString("authorid"));
            }
            rs.close();
            db.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return article;

    }

    /*增加一条点赞*/
    public boolean insertAgree(String articleId,user user){
        boolean ret=false;
        Date now = new Date();

        article article=queryArticelById(articleId);

        DBManager db=new DBManager();
        String sql1="insert into inc_agree(worksid,authorsid,approverid,agreeTime,type) values(";
        sql1+= article.getIdinc_article_works()+",";
        sql1+=article.getAuthorid()+",";
        sql1+=user.getIdinc_user()+",'";
        sql1+=now.getTime()+"',";
        sql1+="1)";

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
    public boolean deleteAgree(String articleId,user user){
        boolean ret=false;
        DBManager db=new DBManager();
        String sql1="delete from inc_agree where type=1 and worksid="+Integer.parseInt(articleId)+" and approverid="+user.getIdinc_user();

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

    /*查询我的文博*/
    /*获取最热文章信息*/
    public ArrayList<article> queryMyArticle(user user)
    {
        ArrayList<article> list=new ArrayList<>();

        DBManager db=new DBManager();
        String sql="select idinc_article_works,articletitle,publishTime,content " +
                "from inc_article_works where authorid="+user.getIdinc_user();

        System.out.println(sql);

        try {
            ResultSet rs=db.executeQuery(sql);
            while(rs.next())
            {
                article s=new article();
                s.setIdinc_article_works(rs.getString("idinc_article_works"));
                s.setArtitcletitle(rs.getString("articletitle"));
                s.setContent(rs.getString("content"));
                s.setPublishTime(rs.getString("publishTime"));
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

    /*文章详细展示页面通过文章Id查找信息*/
    public article findByArticleId(String articleId)
    {
        article article=new article();

        DBManager db=new DBManager();
        String sql="select p.idinc_article_works,p.authorid,p.articletitle,p.publishTime,p.articletags,p.content," +
                "c.username,c.avatar,count(d.worksid) as agreenumber from inc_article_works as p join inc_user c on c.idinc_user=p.authorid left join inc_agree d " +
                "on d.worksid=p.idinc_article_works and type=1 where p.idinc_article_works="+articleId+" GROUP BY p.idinc_article_works";
        ResultSet rs=null;

        System.out.println(sql);

        try {
            rs = db.executeQuery(sql);
            if(rs.next()){
                article.setIdinc_article_works(rs.getString("idinc_article_works"));
                article.setAuthorid(rs.getString("authorid"));
                article.setArtitcletitle(rs.getString("articletitle"));
                article.setContent(rs.getString("content"));
                article.setPublishTime(rs.getString("publishTime"));
                article.setArticletags(rs.getString("articletags"));
                article.setUsername(rs.getString("username"));
                article.setAvatar(rs.getString("avatar"));
                article.setAgreenumber(rs.getString("agreenumber"));
            }
            rs.close();
            db.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return article;

    }

    /*文章详细展示页面的评论显示信息*/
    public List<comment> getAllCommentsById(String articleId){
        ArrayList<comment> list=new ArrayList<>();

        DBManager db=new DBManager();
        /*在数据库中查询评论的相关信息*/
        String sql="select p.idinc_comment,p.reviewerid,p.worksid,p.message,p.publishTime,p.type,c.username,c.avatar from inc_comment as p \n" +
                "join inc_user c on c.idinc_user=p.reviewerid where type = 1 and worksid="+articleId+" order by p.publishTime desc";

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
    public boolean addCommentByArticle(comment comment){
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

    /*发布新的文章*/
    public boolean article_publich(article article){
        boolean ret=false;

        DBManager  db=new DBManager();
        String sql1="insert into inc_article_works(authorId,articleTitle,publishTime,articleTags,content) values('";
        sql1+=article.getAuthorid()+"','";
        sql1+=article.getArtitcletitle()+"','";
        sql1+=article.getPublishTime()+"','";
        sql1+=article.getArticletags()+"','";
        sql1+=article.getContent()+"')";

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
