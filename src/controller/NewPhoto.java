package controller;

import model.dao.PhotoDao;
import model.dao.UserDao;
import model.vo.article;
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

/**
 * Created by aboge on 2017/5/23.
 */
@WebServlet(name = "NewPhoto")
public class NewPhoto extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String photointroduction = request.getParameter("photointroduction");
        String photoname = request.getParameter("photoname");
        String photoTitle = request.getParameter("photoTitle");
        String photoTag = request.getParameter("photoTag");

        System.out.println(photoTitle);
        System.out.println(photointroduction);

        HttpSession session = request.getSession();
        UserDao dao = new UserDao();
        String userName=(String)session.getAttribute("currentUser");
        user user=dao.findByUserName(userName);
        String authorId = user.getIdinc_user();

        Long time = System.currentTimeMillis();

        photo photo=new photo();
        photo.setPhotointroduction(photointroduction);
        photo.setPhotoname(photoname);
        photo.setAuthorid(authorId);
        photo.setPhotostitle(photoTitle);
        photo.setPhototags(photoTag);
        photo.setPublishTime(Long.toString(time));
        PhotoDao _dao=new PhotoDao();
        boolean ret=_dao.photo_publich(photo);

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
