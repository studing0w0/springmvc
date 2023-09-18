package hello.servlet.web.servlet.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.apache.coyote.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MvcMemberSaveServlet.service");
        // 폼에서 보낸 것을 받음
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        // 비지니스 로직 호출
        Member member = new Member(username, age);
        System.out.println("member = " + member);
        memberRepository.save(member);
        // model에 데이터를 보관
        request.setAttribute("member", member);
        String path = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
