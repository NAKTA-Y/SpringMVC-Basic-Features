package hello.springmvc.basic.request;

import hello.springmvc.basic.entity.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username= {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String name,
            @RequestParam("age") int old) {

        log.info("username= {}, age = {}", name, old);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username= {}, age = {}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username,
            int age) {

        log.info("username= {}, age = {}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            // int 기본형에는 null이 들어갈 수 없으므로 Integer로 변환
            @RequestParam(required = false) Integer age) {

        log.info("username= {}, age = {}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
              @RequestParam(defaultValue = "guest") String username,
            // int 기본형에는 null이 들어갈 수 없으므로 Integer로 변환
            @RequestParam(defaultValue = "1") Integer age) {

        log.info("username= {}, age = {}", username, age);
        log.info("username Type = {}, age Type = {}", username.getClass(), age.getClass());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username= {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        log.info("username Type= {}, age Type = {}", paramMap.get("username").getClass(), paramMap.get("age").getClass());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }
}
