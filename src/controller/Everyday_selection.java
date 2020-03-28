package controller;

import model.dao.ArticleDao;
import model.vo.article;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by aboge on 2017/5/17.
 */
@WebServlet(name = "Everyday_selection")
public class Everyday_selection extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /*将数据返还给前台*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArticleDao dao = new ArticleDao();
        ArrayList<article> list=dao.queryArticle(null);

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
