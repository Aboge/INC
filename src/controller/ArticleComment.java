package controller;

import model.dao.ArticleDao;
import model.dao.UserDao;
import model.vo.comment;
import model.vo.user;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aboge on 2017/5/21.
 */
@WebServlet(name = "ArticleComment")
public class ArticleComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*发表评论*/
        /*在session里面获取当前用户*/
        HttpSession session = request.getSession();
        String userName=(String)session.getAttribute("currentUser");
        UserDao dao = new UserDao();
        user user=dao.findByUserName(userName);

        ArticleDao articleDao = new ArticleDao();
        comment comment = new comment();
        comment.setMessage(request.getParameter("message"));
        comment.setType("1");
        comment.setPublishTime(System.currentTimeMillis()+"");
        comment.setReviewerid(user.getIdinc_user());
        comment.setWorksid(request.getParameter("worksId"));
        comment.setAuthorid(request.getParameter("authorId"));

        boolean ret = articleDao.addCommentByArticle(comment);
        JSONObject Json=new JSONObject();
        JSONArray JsonArray=new JSONArray();

        Json.put("result",ret);
        JsonArray.add(Json);

        if(ret){
            response.getWriter().write(JsonArray.toString());
        }
        else{
            response.getWriter().write(JsonArray.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*获取当前文章作品的所有评论*/
        String articleid = request.getParameter("articleid");
        ArticleDao dao = new ArticleDao();
        List list = new ArrayList();
        list = dao.getAllCommentsById(articleid);
        response.setContentType("application/json; charset=utf-8");

        JSONObject Json=new JSONObject();

        Json.put("list",list);
        response.getWriter().write(Json.toString());
    }
}
