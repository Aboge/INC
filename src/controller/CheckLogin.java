package controller;

import model.dao.UserDao;
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

import static java.lang.System.out;

/**
 * Created by aboge on 2017/5/17.
 */
@WebServlet(name = "CheckLogin")
public class CheckLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName=request.getParameter("username");
        out.println(userName);
        String password=request.getParameter("password");
        out.println(password);

        UserDao dao = new UserDao();
        HttpSession session = request.getSession();
        boolean ret = dao.checkLogin(userName, password);


        /*后台返回值*/
        response.setContentType("application/json; charset=utf-8");

        JSONObject Json=new JSONObject();
        JSONArray JsonArray=new JSONArray();

        Json.put("result",ret);
        JsonArray.add(Json);

        if(ret){
            session.setAttribute("currentUser", userName);
            response.getWriter().write(JsonArray.toString());
        }
        else{
            response.getWriter().write(JsonArray.toString());
        }


    }

    /*将数据返回给前台*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String currentUser= (String) session.getAttribute("currentUser");
        /*以json格式返回数据*/
        response.setContentType("application/json; charset=utf-8");

        JSONObject Json=new JSONObject();

        Json.put("username",currentUser);

        if(currentUser!=null){
            UserDao dao=new UserDao();
            user userinfo=dao.findByUserName(currentUser);
            Json.put("avatar",userinfo.getAvatar());
            response.getWriter().write(Json.toString());
        }
        else{
            response.getWriter().write(Json.toString());
        }

    }
}
