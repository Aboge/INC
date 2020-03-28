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
import java.util.ArrayList;

/**
 * Created by aboge on 2017/5/19.
 */
@WebServlet(name = "Myinformation")
public class Myinformation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*修改用户资料信息传递*/
        /*在session里面获取当前用户*/
        HttpSession session = request.getSession();
        String userName=(String)session.getAttribute("currentUser");
        UserDao dao = new UserDao();
        user user1=dao.findByUserName(userName);

        UserDao userDao = new UserDao();
        user user = new user();
        user.setEmail(request.getParameter("email"));
        user.setSex(request.getParameter("sex"));
        user.setBirthday(request.getParameter("birthday"));
        user.setIntroduction(request.getParameter("introduction"));
        user.setIdinc_user(user1.getIdinc_user());
        user.setLocation(request.getParameter("location"));

        boolean ret = userDao.updatesUser(user);

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
        UserDao dao = new UserDao();
        String userName=(String)session.getAttribute("currentUser");
        user user=dao.findByUserName(userName);

        /*以json格式返回数据*/
        response.setContentType("application/json; charset=utf-8");

        JSONObject Json=new JSONObject();

        Json.put("list",user);

        if(true){
            response.getWriter().write(Json.toString());
        }
        else{
            response.getWriter().write(Json.toString());
        }

    }
}
