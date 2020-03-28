package controller;

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

import static java.lang.System.out;

/**
 * Created by aboge on 2017/5/20.
 */
@WebServlet(name = "Mycomment")
public class Mycomment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*接收前台删除操作ajax*/
        boolean ret=false;
        String commentId=request.getParameter("commentId");
        out.println(commentId);
        String action=request.getParameter("action");
        out.println(action);

        HttpSession session = request.getSession();
        UserDao dao1 = new UserDao();
        String userName=(String)session.getAttribute("currentUser");
        user user=dao1.findByUserName(userName);

        UserDao dao=new UserDao();

        /*删除操作*/
        action.equals("delete");
        ret = dao.deleteComment(commentId, user);


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

        /*获取我的评论信息*/
        HttpSession session = request.getSession();
        UserDao dao = new UserDao();
        String userName=(String)session.getAttribute("currentUser");
        user user=dao.findByUserName(userName);

        ArrayList<comment> list=dao.queryMyComment(user);

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
