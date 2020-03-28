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

import static java.lang.System.out;

/**
 * Created by aboge on 2017/5/21.
 */
@WebServlet(name = "Article_show")
public class Article_show extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idinc_article_works=request.getParameter("articleid");

        out.println(idinc_article_works);
        ArticleDao dao=new ArticleDao();
        article article=dao.findByArticleId(idinc_article_works);

        response.setContentType("application/json; charset=utf-8");

        JSONObject Json=new JSONObject();

        Json.put("list",article);

        if(true){
            response.getWriter().write(Json.toString());
        }
        else{
            response.getWriter().write(Json.toString());
        }
    }
}
