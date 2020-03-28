package controller;


import model.dao.PhotoDao;
import model.dao.UserDao;
import model.vo.photo;
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
 * Created by aboge on 2017/5/18.
 */
@WebServlet(name = "Hottest_photo")
public class Hottest_photo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean ret=false;
        String photoId=request.getParameter("photoId");
        out.println(photoId);
        String action=request.getParameter("action");
        out.println(action);

        HttpSession session = request.getSession();
        UserDao dao1 = new UserDao();
        String userName=(String)session.getAttribute("currentUser");
        user user=dao1.findByUserName(userName);

        /*喜欢与去除喜欢*/
        PhotoDao dao=new PhotoDao();

        if(action.equals("add")) {
            ret = dao.insertAgree(photoId, user);
        }else if(action.equals("delete")){
            ret = dao.deleteAgree(photoId, user);
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
        PhotoDao dao = new PhotoDao();
        ArrayList<photo> list=dao.queryHotPhoto(type,user);

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
