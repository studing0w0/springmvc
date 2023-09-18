package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {
    //핸들러 지원여부 파악 메소드
    boolean supports(Object handler);

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object hanlder) throws ServletException, IOException;
}
