package controller;

import model.dao.PhotoDao;
import model.dao.UserDao;
import model.vo.photo;
import model.vo.user;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.out;

/**
 * Created by aboge on 2017/5/25.
 */
@WebServlet(name = "Otherphoto")
public class Otherphoto extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDao dao1 = new UserDao();

        String userName=request.getParameter("username1");
        out.println(userName);

        user user=dao1.findByUserName(userName);

        PhotoDao dao = new PhotoDao();
        ArrayList<photo> list=dao.queryMyPhoto(user);

        /*以json格式返回数据*/
        response.setContentType("application/json; charset=utf-8");

        JSONObject Json=new JSONObject();
        Json.put("user",user);
        Json.put("list",list);

        if(true){
            response.getWriter().write(Json.toString());
        }
        else{
            response.getWriter().write(Json.toString());
        }
    }
}
