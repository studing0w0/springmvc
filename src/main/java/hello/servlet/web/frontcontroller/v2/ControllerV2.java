package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    // 기존은 void 였으나 리턴 타입을 MyView로 !!!
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
