package controller;

import model.dao.ArticleDao;
import model.dao.UserDao;
import model.vo.article;
import model.vo.user;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.System.out;

/**
 * Created by aboge on 2017/5/17.
 */
@WebServlet(name = "Hottest_article")
public class Hottest_article extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean ret=false;
        String articleId=request.getParameter("articleId");
        out.println(articleId);

        String action=request.getParameter("action");
        out.println(action);

        HttpSession session = request.getSession();
        UserDao dao1 = new UserDao();
        String userName=(String)session.getAttribute("currentUser");
        user user=dao1.findByUserName(userName);

        /*喜欢与去除喜欢*/
        ArticleDao dao=new ArticleDao();

        if(action.equals("add")) {
            ret = dao.insertAgree(articleId, user);
        }else if(action.equals("delete")){
            ret = dao.deleteAgree(articleId, user);
        }

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
        HttpSession session = request.getSession();
        UserDao dao1 = new UserDao();
        String userName=(String)session.getAttribute("currentUser");
        user user=dao1.findByUserName(userName);
        String type=request.getParameter("type");
        out.println(type);

        ArticleDao dao = new ArticleDao();
        ArrayList<article> list=dao.queryHotArticle(type,user);

        /*以json格式返回数据*/
        response.setContentType("application/json; charset=utf-8");

        JSONObject Json=new JSONObject();

        Json.put("list",list);

        if(true){
            response.getWriter().write(Json.toString());
        }
        else{
            response.getWriter().write(Json.toString());
        }
    }
}
