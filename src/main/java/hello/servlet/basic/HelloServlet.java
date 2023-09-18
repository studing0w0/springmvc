package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        
        String username =  request.getParameter("username"); //HttpServletRequest에서 쿼리파라미터를 가져옴
        System.out.println("username = " + username);
        
        //헤더에 요청 정보 담기
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8"); // 왠만하면 UTF-8로 써라
        response.getWriter().write("hello "+username);
        
    }
}
