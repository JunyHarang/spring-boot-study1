package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component @Scope(value = "request")
public class JunyHarangLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + requestURL + "\t" + message);
    } // log() 끝

    @PostConstruct public void init() {
        // 전세계의 거의 유일한 값을 만들어 uuid Field 에 넣는다.
        this.uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] 동무! request Scope Bean 이 만들어졌습네다!" + this);
    } // init() 끝

    @PreDestroy public void close() {
        System.out.println("[" + uuid + "] 동무! request Scope Bean 을 혁명적으로다가 없애 버렸습네다!" + this);
    } // close() 끝
} // class 끝
