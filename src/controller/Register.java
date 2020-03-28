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
@WebServlet(name = "Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String userName=request.getParameter("username");
        out.println(userName);
        String password=request.getParameter("password");
        out.println(password);
        String email=request.getParameter("email");
        out.println(email);

        user user=new user();
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);
        UserDao dao=new UserDao();

        boolean ret=dao.insertUser(user);

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
