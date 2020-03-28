package controller;

import model.dao.ArticleDao;
import model.dao.UserDao;
import model.vo.article;
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

/**
 * Created by aboge on 2017/5/19.
 */
@WebServlet(name = "NewArticle")
public class NewArticle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = request.getParameter("content");
        String articleTitle = request.getParameter("articleTitle");
        HttpSession session = request.getSession();
        UserDao dao1 = new UserDao();
        String userName=(String)session.getAttribute("currentUser");
        user user=dao1.findByUserName(userName);
        String authorId = user.getIdinc_user();
        String articleTags = request.getParameter("articleTags");
        Long time = System.currentTimeMillis();

        article article=new article();
        article.setContent(content);
        article.setAuthorid(authorId);
        article.setArtitcletitle(articleTitle);
        article.setArticletags(articleTags);
        article.setPublishTime(Long.toString(time));
        ArticleDao dao=new ArticleDao();
        boolean ret=dao.article_publich(article);

        /*后台返回值*/
        response.setContentType("application/json; charset=utf-8");

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

    }
}
