package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* v1 하위의 어떤 경로가 들어와도 무조건 해당 서블릿이 호출됨 */
@WebServlet(name="frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        String requestURI = request.getRequestURI();
        // 다형성에 의해 인터페이스로 받을 수 있음 (부모가 ControllerV1이기 때문)
        // 부모는 자식을 받을 수 있다!!
        ControllerV3 controller = controllerMap.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // view 논리 이름 얻어오기
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();
        // viewResolver(view의 논리이름을 가지고 실제 물리 이름을 반환
        MyView myView = viewResolver(viewName);
        // view 객체를 통해 HTML 화면을 렌더링한다
        // 뷰 객체의 render()는 모델 정보도 함께 받음
        // jsp 는 request.getAttrbute()로 데이터를 조회하기 때문에 모델의 데이터를 꺼내 request.setAttribute()로 담아둠
        // jsp로 포워드 해서 jsp를 랜더링함
        myView.render(mv.getModel(), request, response);



    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    // HttpServletRequest에서 파라미터 정보를 꺼내 Map으로 반환 및 컨트롤러에 전달
    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        // 모든 parameter 꺼내서 map에 넣어주기
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
