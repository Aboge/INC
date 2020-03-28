package controller;

import model.dao.UserDao;
import model.vo.comment;
import model.vo.user;
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
@WebServlet(name = "Allcomment")
public class Allcomment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDao dao = new UserDao();
        String userName=(String)session.getAttribute("currentUser");
        user user=dao.findByUserName(userName);

        ArrayList<comment> list=dao.queryAllComment(user);

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
