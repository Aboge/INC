package controller;

import model.dao.PhotoDao;
import model.vo.photo;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.System.out;

/**
 * Created by aboge on 2017/5/20.
 */
@WebServlet(name = "Photo_show")
public class Photo_show extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idinc_photo_works=request.getParameter("photoid");

        out.println(idinc_photo_works);

        PhotoDao dao=new PhotoDao();
        photo photo=dao.findByPhotoId(idinc_photo_works);

        response.setContentType("application/json; charset=utf-8");

        JSONObject Json=new JSONObject();

        Json.put("list",photo);

        if(true){
            response.getWriter().write(Json.toString());
        }
        else{
            response.getWriter().write(Json.toString());
        }

    }
}
