package hello.core.web;

import hello.core.common.JunyHarangLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller @RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoSevice logDemoSevice;
    private final ObjectProvider<JunyHarangLogger> jhLoggerProvider;

    // @ResponseBody는 문자 그대로 응답으로 보낼 수 있다.
    @RequestMapping("log-demo") @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();

        JunyHarangLogger jhLogger = jhLoggerProvider.getObject();
        jhLogger.setRequestURL(requestURL);

        jhLogger.log("이것은 컨트롤러 입네다!");

        Thread.sleep(1000);

        logDemoSevice.logic("testId");

        return "작업이 혁명적으로 완료되었습네다!";
    } // logDemo() 끝
}
